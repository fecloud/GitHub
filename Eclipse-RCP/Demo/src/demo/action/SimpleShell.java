package demo.action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
public class SimpleShell {
	 public SimpleShell(){
	  Display d = new Display();
	  Shell s = new Shell(d, SWT.CLOSE|SWT.RESIZE);
	  s.setSize(500, 500);
	  s.open();
	  s.setMaximized(true);
	  DialogExample Dialog = new DialogExample(s);//创建对话框
//	  s.setText(Dialog.open());
	  s.setImage(new Image(d, "E:\\images\\PS\\354.jpg"));//设置窗口图标
	  while(!s.isDisposed()){
		 if(!d.readAndDispatch()){
			 d.sleep();
		 }
	  }
	  	d.dispose();
	 }
	 public static void main(String[] args) {
		SimpleShell simpleShell = new SimpleShell();
	}
}