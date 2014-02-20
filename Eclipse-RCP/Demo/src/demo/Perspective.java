package demo;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView(HelloView.ID, true, IPageLayout.LEFT, .50f,
				layout.getEditorArea());
//		layout.addStandaloneView(HaHaView.ID, true, IPageLayout.LEFT, .50f,
//				layout.getEditorArea());
		layout.addView("demo.canvas.CanvasView", IPageLayout.RIGHT, 50f,
				layout.getEditorArea());
		// layout.getViewLayout("demo.canvas.CanvasView").setCloseable(false);
	}
}
