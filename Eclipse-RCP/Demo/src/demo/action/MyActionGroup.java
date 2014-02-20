package demo.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.actions.ActionGroup;

public class MyActionGroup extends ActionGroup {
	private Canvas canvas;
	public MyActionGroup(Canvas canvas,int step) {
		this.canvas = canvas;
	}

	// 生成菜单Menu，并将两个Action传入
	public void fillContextMenu(IMenuManager mgr) {
		// 加入两个Action对象到菜单管理器
		MenuManager menuManager = (MenuManager) mgr;
		menuManager.add(new OpenAction());
		Menu menu = menuManager.createContextMenu(canvas);
		canvas.setMenu(menu);
	}

	private class OpenAction extends Action {
		public OpenAction() {
			setText("删除");
		}
		// 继承自Action的方法，动作代码写在此方法中
		public void run() {
			MessageDialog.openInformation(null, null, "未完成");
		}
	}
}