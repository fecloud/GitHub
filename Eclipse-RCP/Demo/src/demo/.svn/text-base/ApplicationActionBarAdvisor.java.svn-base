package demo;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import demo.action.ConditionManage;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private ConditionManage conditionManage;
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
		conditionManage = new ConditionManage(window);
	}
	//用来设置菜单栏
	protected void fillMenuBar(IMenuManager menuBar) {
		super.fillMenuBar(menuBar);
		MenuManager anliMenu = new MenuManager("&案例设计", "");
		MenuManager huanjingMenu = new MenuManager("&环境管理", "");
		MenuManager testScreeMenu = new MenuManager("&测试场景管理", "");
		MenuManager logMenu = new MenuManager("&操作日志查询", "");
		anliMenu.add(conditionManage);
		huanjingMenu.add(conditionManage);
		testScreeMenu.add(conditionManage);
		logMenu.add(conditionManage);
		menuBar.add(anliMenu);
		menuBar.add(huanjingMenu);
		menuBar.add(testScreeMenu);
		menuBar.add(logMenu);
		
	}
	//用来设置工具栏
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		coolBar.add(new ToolBarContributionItem(toolbar, "main"));
	}
}
