package demo.action;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
public class VarManageDialog extends Dialog {
	private Table table;
	public VarManageDialog(Shell parent){
		//调用基类的构造方法
		super(parent);
	}
	public void open(){
		final Shell parent = this.getParent();
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM|SWT.APPLICATION_MODAL);
		dialog.setSize(600,400);
		dialog.setText("新建步骤（事务开始）");
		
		Button addButton = new Button(dialog, SWT.NONE);
		addButton.setBounds(485, 99, 61, 27);
		addButton.setText("新增");
		addButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				VarSetDialog dialog = new VarSetDialog(new Shell());
				dialog.open();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		Button modifyButton = new Button(dialog, SWT.NONE);
		modifyButton.setText("修改");
		modifyButton.setBounds(485, 174, 61, 27);
		modifyButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				dialog.close();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		Button delButton = new Button(dialog, SWT.NONE);
		delButton.setText("删除");
		delButton.setBounds(485, 247, 61, 27);
		delButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		table = new Table(dialog, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(41, 20, 413, 323);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setAlignment(SWT.CANCEL);
        tblclmnNewColumn.setText("变量名");
        tblclmnNewColumn.setWidth(50);

        TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_1.setWidth(50);
        tblclmnNewColumn_1.setText("变量值");

        TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_2.setWidth(80);
        tblclmnNewColumn_2.setText("数据类型");

        TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_3.setWidth(60);
        tblclmnNewColumn_3.setText("接口属性");

        TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_4.setWidth(60);
        tblclmnNewColumn_4.setText("备注");
		
		dialog.open();
		Display display = parent.getDisplay();
		while(!dialog.isDisposed()){
			if(display.readAndDispatch()){
    			display.sleep();
			}
		}
	}
	public static void main(String[] args) {
		VarManageDialog dialogExample = new VarManageDialog(new Shell());
		dialogExample.open();
	}
}