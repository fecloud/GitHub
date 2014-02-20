package demo;

import java.awt.Point;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;

import demo.action.DialogExample;
import demo.action.MyActionGroup;
import demo.canvas.CanvasView;
import demo.entity.ImageDomain;
import demo.entity.ImageEntity;
import demo.service.DrawImageService;
import demo.service.SAXImageService;

import org.eclipse.swt.widgets.Button;

public class HelloView extends ViewPart {
	
	public static final String ID = "demo.HelloView"; //$NON-NLS-1$
	private IWorkbenchWindow window;
	private Canvas canvas;
	private Integer imageX = 235;
	private Integer imageY = 90;
	private GC gc;
	int step = 0;
	private ImageEntity imageEntity = new ImageEntity();
	private DrawImageService service = new DrawImageService();
	private SAXImageService saxService = new SAXImageService();
	private Map<Integer, ImageEntity> imageMap = new HashMap<Integer, ImageEntity>();
	private List<ImageDomain> imageDomains = new ArrayList<ImageDomain>();
	public HelloView() {
		
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		String[] WEEK = { "案例开始", "注册信息", "登陆账号","发送信息","退出"};
		final Combo combo = new Combo(container, SWT.READ_ONLY);
		combo.setItems(WEEK);
		combo.addSelectionListener(new SelectionAdapter(){
            @Override
            public void widgetSelected(SelectionEvent e) {
//                step = step+1;
//               	imageEntity.setNext(1);
//                imageEntity.setCanvas(getCanvas());
//                imageEntity.setId(step);
//                imageEntity.setPosition(new Point(imageX, (step-1)*100+imageY));
//                imageEntity.setName(combo.getText());
//                imageEntity.setImageKey(combo.getSelectionIndex());
                
//                if(imageMap.size()==0||imageMap.get(step-1).getNext()==0){
//                	service.drawImage(imageEntity);
//                }else{
//                	drawJianTou(getCanvas(), step);
//                	service.drawImage(imageEntity);
//                }
//                imageMap.put(step, imageEntity);
//                
//              MyActionGroup actionGroup = new MyActionGroup(getCanvas(),step);
//	    	    actionGroup.fillContextMenu(new MenuManager());
            	System.out.println("**********");
//            	MessageBox messageBox = new MessageBox(getCanvas().getShell(), SWT.ICON_QUESTION |SWT.YES | SWT.NO);
//            	messageBox.setMessage("Is this question simple");
//            	messageBox.open();
            	
            	DialogExample dialog = new DialogExample(new Shell());//创建对话框
            	ImageDomain imageDomain = dialog.transStart();
            	System.out.println(imageDomain.getTransactionName()+";"+imageDomain.getNext());
            	
            	
            }
        });
		combo.setBounds(10, 87, 80, 25);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.setBounds(10, 41, 80, 27);
		btnNewButton.setText("New Button");
		btnNewButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
//					getViewSite().getWorkbenchWindow().getActivePage()
//					.openEditor(new HelloEditorInput(), HelloEditor.ID);
					InputStream in = getClass().getClassLoader().getResourceAsStream("image.xml");
					imageDomains = saxService.getImageDomains(in);
					service.drawImageLine(imageDomains, getCanvas());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		createActions();
		initializeToolBar();
		initializeMenu();
	}
	private Canvas getCanvas(){
		window = getViewSite().getWorkbenchWindow();
		IViewReference[] vfs = window.getActivePage().getViewReferences();
		IViewPart vw;
		vw = vfs[1].getView(false);
		canvas = ((CanvasView)vw).canvas;
		return canvas;
	}
	public void drawJianTou(Canvas canvas,int step){
		gc = new GC(canvas);
		gc.drawLine(imageX+40, (step-2)*100+130, imageX+40, (step-1)*100+imageY);
		gc.drawLine(imageX+35, (step-2)*100+180, imageX+40, (step-1)*100+imageY);
		gc.drawLine(imageX+45, (step-2)*100+180, imageX+40, (step-1)*100+imageY);
	}
	/**
	 * Create the actions.
	 */
	private void createActions() {
	}
	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		@SuppressWarnings("unused")
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}
	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		@SuppressWarnings("unused")
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}
	@Override
	public void setFocus() {
	}
}
