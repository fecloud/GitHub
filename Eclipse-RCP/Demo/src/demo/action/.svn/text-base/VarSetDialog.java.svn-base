package demo.action;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;

import demo.entity.ImageDomain;
public class VarSetDialog extends Dialog {
	private Text valueText;
	private Text commentText;
	private Text nameText;
	public VarSetDialog(Shell parent){
		//调用基类的构造方法
		super(parent);
	}
	public void open(){
		final ImageDomain imageDomain = new ImageDomain();
		
		final Shell parent = this.getParent();
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM|SWT.APPLICATION_MODAL);
		dialog.setSize(486,345);
		dialog.setText("新建变量");
		
		Label nameLabel = new Label(dialog, SWT.NONE);
		nameLabel.setBounds(111, 31, 39, 17);
		nameLabel.setText("变量名:");
		
		Label classLabel = new Label(dialog, SWT.NONE);
		classLabel.setBounds(111, 69, 51, 17);
		classLabel.setText("变量类型:");
		
		Label interfaceLabel = new Label(dialog, SWT.NONE);
		interfaceLabel.setBounds(111, 111, 51, 17);
		interfaceLabel.setText("接口属性:");
		
		final Combo classCombo = new Combo(dialog, SWT.NONE);
		classCombo.setBounds(204, 66, 125, 25);
		classCombo.setText("--------请选择-----");
		classCombo.add("字符串");
		classCombo.add("数字");
		classCombo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				imageDomain.setNext(classCombo.getText());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		valueText = new Text(dialog, SWT.BORDER);
		valueText.setBounds(204, 153, 125, 25);
		
		Button confirmButton = new Button(dialog, SWT.NONE);
		confirmButton.setBounds(123, 265, 61, 27);
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
		cancelButton.setBounds(296, 265, 61, 27);
		cancelButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				dialog.close();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		Label valueLabel = new Label(dialog, SWT.NONE);
		valueLabel.setText("变量值:");
		valueLabel.setBounds(123, 156, 39, 17);
		
		Label commentLabel = new Label(dialog, SWT.NONE);
		commentLabel.setText("备注:");
		commentLabel.setBounds(135, 201, 27, 17);
		
		commentText = new Text(dialog, SWT.BORDER);
		commentText.setBounds(204, 187, 125, 50);
		
		Combo interfaceCombo = new Combo(dialog, SWT.NONE);
		interfaceCombo.setBounds(204, 108, 125, 25);
		interfaceCombo.setText("--------请选择-----");
		interfaceCombo.add("内部");
		
		nameText = new Text(dialog, SWT.BORDER);
		nameText.setBounds(204, 23, 125, 25);
		
		dialog.open();
		Display display = parent.getDisplay();
		while(!dialog.isDisposed()){
			if(display.readAndDispatch()){
    			display.sleep();
			}
		}
	}
	public static void main(String[] args) {
		VarSetDialog dialogExample = new VarSetDialog(new Shell());
		dialogExample.open();
	}
}