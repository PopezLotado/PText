package org.pzone.text.util;

import static com.jinvoke.win32.WinConstants.*;

import javax.swing.JFrame;

import com.jinvoke.Callback;
import com.jinvoke.JInvoke;
import com.jinvoke.NativeImport;
import com.jinvoke.Util;
import com.jinvoke.win32.User32;
import com.jinvoke.win32.structs.Msg;

public class MouseHook {
	static {
		JInvoke.initialize();
	}

	@NativeImport(library = "user32")
	public native static int SetWindowsHookEx(int idHook, Callback hookProc, int hModule, int dwThreadId);

	@NativeImport(library = "user32")
	public native static int UnhookWindowsHookEx(int idHook);

	public static final int WH_MOUSE_LL = 14;
	static JFrame frame = UpHide.getJF();

	public void setMouseHook() {

		Thread hookThread = new Thread(new Runnable() {

			public void run() {
				if (MouseProc.hookHandle == 0) {
					int hInstance = User32.GetWindowLong(Util.getWindowHandle(frame), GWL_HINSTANCE);

					MouseProc.hookHandle = SetWindowsHookEx(WH_MOUSE_LL,
							new Callback(MouseProc.class, "lowLevelMouseProc"), hInstance, 0);

					// Standard message dispatch loop (message pump)
					Msg msg = new Msg();
					while (User32.GetMessage(msg, 0, 0, 0)) {
						User32.TranslateMessage(msg);
						User32.DispatchMessage(msg);
					}

				}
			}
		});
		hookThread.start();
	}

	public void unsetMouseHook() {
		UnhookWindowsHookEx(MouseProc.hookHandle);
		MouseProc.hookHandle = 0;
	}

}

class MouseProc {
	static int hookHandle;

	@NativeImport(library = "user32")
	public native static int CallNextHookEx(int idHook, int nCode, int wParam, int lParam);

	static {
		JInvoke.initialize();
	}

	public static int lowLevelMouseProc(int nCode, int wParam, int lParam) {
		if (nCode < 0)
			return CallNextHookEx(hookHandle, nCode, wParam, lParam);

		if (nCode == HC_ACTION) {
			MouseHookStruct mInfo = Util.ptrToStruct(lParam, MouseHookStruct.class);

			UpHide.setX(mInfo.pt.x);
			UpHide.setY(mInfo.pt.y);
			// String message = "Mouse pt: (" + mInfo.pt.x + ", " + mInfo.pt.y +
			// ") ";
			// switch (wParam) {
			//
			// case WM_LBUTTONDOWN:
			// message += "Left button down";
			// break;
			// case WM_LBUTTONUP:
			// message += "Left button up";
			// break;
			// case WM_MOUSEMOVE:
			// message += "Mouse moved";
			// break;
			// case WM_MOUSEWHEEL:
			// message += "Mouse wheel rotated";
			// break;
			// case WM_RBUTTONDOWN:
			// message += "Right button down";
			// break;
			// case WM_RBUTTONUP:
			// message += "Right button down";
			// break;
			// }
			// System.out.println(message);
			// MouseHook.mouseEventArea.append(message+"\n");
		}

		return CallNextHookEx(hookHandle, nCode, wParam, lParam);
	}
}

// =============================================
