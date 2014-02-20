package demo.canvas;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import demo.entity.ImageDomain;
import demo.service.DrawImageService;
import demo.service.SAXImageService;
public class CanvasView extends ViewPart {
	public static final String ID = "demo.canvas.CanvasView"; //$NON-NLS-1$
	public Canvas canvas;
	private Composite composite;
	private SAXImageService saxService = new SAXImageService();
	private List<ImageDomain> imageDomains = new ArrayList<ImageDomain>();
	private DrawImageService service = new DrawImageService();
	public CanvasView() {
	}
	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(6, false));
		
		canvas = new Canvas(composite, SWT.NONE);
		GridData gd_canvas = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		canvas.setBounds(0, 0, 1000, 2000);
		gd_canvas.widthHint = 1000;
		gd_canvas.heightHint = 10000;
		canvas.setLayoutData(gd_canvas);
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
//		final Image image = new Image(canvas.getDisplay(), "E:\\images\\flowchart\\start.gif");
		canvas.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
//				e.gc.drawImage(image, 263, 10);
				InputStream in = getClass().getClassLoader().getResourceAsStream("image.xml");
				try {
					imageDomains = saxService.getImageDomains(in);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				service.drawImageLine(imageDomains, canvas);
			}
		});
		
		createActions();
		initializeToolBar();
		initializeMenu();
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
