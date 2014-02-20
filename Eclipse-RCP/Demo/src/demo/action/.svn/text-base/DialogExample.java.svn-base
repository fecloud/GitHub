package demo.action;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;

import demo.entity.ImageDomain;
public class DialogExample extends Dialog {
	private Text text;
	public DialogExample(Shell parent){
		//调用基类的构造方法
		super(parent);
	}
	public ImageDomain transStart(){
		final ImageDomain imageDomain = new ImageDomain();
		
		final Shell parent = this.getParent();
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM|SWT.APPLICATION_MODAL);
		dialog.setSize(600,400);
		dialog.setText("新建步骤（事务开始）");
		
		Label nameLabel = new Label(dialog, SWT.NONE);
		nameLabel.setBounds(159, 69, 61, 17);
		nameLabel.setText("事务名称:");
		
		Label nextLabel = new Label(dialog, SWT.NONE);
		nextLabel.setBounds(159, 124, 61, 17);
		nextLabel.setText("下一步:");
		
		Label commentLabel = new Label(dialog, SWT.NONE);
		commentLabel.setBounds(159, 195, 61, 17);
		commentLabel.setText("步骤说明:");
		
		final Combo comboName = new Combo(dialog, SWT.NONE);
		comboName.setBounds(248, 66, 125, 25);
		comboName.add("普通值");
		comboName.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				imageDomain.setTransactionName(comboName.getText());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		final Combo comboNext = new Combo(dialog, SWT.NONE);
		comboNext.setBounds(248, 121, 125, 25);
		comboNext.setText("--------请选择-----");
		comboNext.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				imageDomain.setNext(comboNext.getText());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		text = new Text(dialog, SWT.BORDER);
		text.setBounds(248, 179, 125, 50);
		
		Button confirmButton = new Button(dialog, SWT.NONE);
		confirmButton.setBounds(131, 284, 61, 27);
		confirmButton.setText("确定");
		confirmButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				dialog.close();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		Button cancelButton = new Button(dialog, SWT.NONE);
		cancelButton.setText("取消");
		cancelButton.setBounds(255, 284, 61, 27);
		cancelButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				dialog.close();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		Button manageButton = new Button(dialog, SWT.NONE);
		manageButton.setText("案例变量管理");
		manageButton.setBounds(355, 284, 92, 27);
		manageButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				VarManageDialog dialog = new VarManageDialog(new Shell());
				dialog.open();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		dialog.open();
		Display display = parent.getDisplay();
		while(!dialog.isDisposed()){
			if(display.readAndDispatch()){
    			display.sleep();
			}
		}
		return imageDomain;
	}
	public static void main(String[] args) {
		DialogExample dialogExample = new DialogExample(new Shell());
		dialogExample.transStart();
	}
}