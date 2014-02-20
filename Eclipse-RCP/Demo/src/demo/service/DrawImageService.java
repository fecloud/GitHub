package demo.service;

import java.util.List;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;

import demo.entity.ImageDomain;
import demo.entity.ImageEntity;

public class DrawImageService {
	private Image image;
	private String path = "E:\\images\\flowchart\\";
	private String[] imageArrays = new String[]{"","wait.gif","","verify.gif","key.gif",
			"","trans-start.gif","trans-success.gif","success.gif","start.gif","","",
			"timer.gif","","","function.gif","condition.gif","expression.gif",
			"trans-fail.gif","fail.gif"};
	
	public void drawImageLine(List<ImageDomain> imageDomains,Canvas canvas){
		GC gc = new GC(canvas);
		int step = 0;
		int z = 0;
		int y = 0;
		for (int i = 0; i < imageDomains.size(); i++) {
//			System.out.println("step:"+imageDomains.size()+";"+imageDomains.get(i));
			image = new Image(canvas.getDisplay(),
					path+imageArrays[imageDomains.get(i).getType()]);
			if(null!=imageDomains.get(i).getTransactionStatus()&&imageDomains.get(i).getTransactionStatus()==0){
				image = new Image(canvas.getDisplay(),path+imageArrays[18]);
			}else if(null!=imageDomains.get(i).getStatus()&&imageDomains.get(i).getStatus()==0){
				image = new Image(canvas.getDisplay(),path+imageArrays[19]);
			}
			step = imageDomains.get(i).getSerialNO();
			if (9==imageDomains.get(i).getType()||8==imageDomains.get(i).getType()) {
				gc.drawImage(image, 260, step*90+10);
//				gc.drawText(imageDomains.get(i).getSerialNO()+":"+imageDomains.get(i).getSummary(), 280, step*90+40);
			}else {
				gc.drawImage(image, 235, step*90+10);
				gc.drawText(imageDomains.get(i).getSerialNO()+":"+imageDomains.get(i).getSummary(), 280, step*90+60);
			}
			
			if(null!=imageDomains.get(i).getNext() || null!=imageDomains.get(i).getNext_true()){
				gc.drawLine(270, step*90+50, 270, step*90+100);
				gc.drawLine(265, step*90+95, 270, step*90+100);
				gc.drawLine(275, step*90+95, 270, step*90+100);
			}
			if(imageDomains.get(i).getNext_false()!=null &&
					!(imageDomains.get(i).getNext_false().equals(imageDomains.get(i).getNext_true()))){
				for(int j=0;j<imageDomains.size();j++){
					if((imageDomains.get(i).getNext_false()).equals(imageDomains.get(j).getKey())){
						int cha = imageDomains.get(i).getSerialNO()-imageDomains.get(j).getSerialNO();
						if(imageDomains.get(i).getSerialNO()>imageDomains.get(j).getSerialNO()){
							gc.drawLine(235, step*90+30, 205-z*20, step*90+30);
							gc.drawLine(205-z*25, step*90+30, 205-z*25, (step-cha)*90+30);
							gc.drawLine(205-z*25, (step-cha)*90+30, 235, (step-cha)*90+30);
							gc.drawLine(230, (step-cha)*90+25, 235, (step-cha)*90+30);
							gc.drawLine(230, (step-cha)*90+35, 235, (step-cha)*90+30);
							z++;
						}else{
							gc.drawLine(290, step*90+40, 340+y*40, step*90+40);
							gc.drawLine(340+y*40, step*90+40, 340+y*40, (step-cha)*90+20+y*5);
							gc.drawLine(340+y*40, (step-cha)*90+20+y*5, 290, (step-cha)*90+20+y*5);
							gc.drawLine(295, (step-cha)*90+15+y*5, 290, (step-cha)*90+20+y*5);
							gc.drawLine(295, (step-cha)*90+25+y*5, 290, (step-cha)*90+20+y*5);
							y++;
						}
					}
				}
			}
		}
		
	}
	public void drawImage(ImageEntity imageEntity){
		GC gc = new GC(imageEntity.getCanvas());
		imageEntity.setImg(getImage(imageEntity));
		gc.drawImage(imageEntity.getImg(), imageEntity.getPosition().x, 
				imageEntity.getPosition().y);
	}
	
	public Image getImage(ImageEntity imageEntity){
		image = new Image(imageEntity.getCanvas().getDisplay(),
				path+imageArrays[imageEntity.getImageKey()]);
		return image;
	}
}
