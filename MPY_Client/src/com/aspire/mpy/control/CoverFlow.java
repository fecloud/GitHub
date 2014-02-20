package com.aspire.mpy.control;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;


public class CoverFlow extends Gallery {
	

	public void addView(View child, LayoutParams params) {
		// TODO Auto-generated method stub
		super.addView(child, params);
	}

	private Camera mCamera = new Camera();
	private int mMaxRotationAngle = 50;
	private int mMaxZoom = -500;
	private int mCoveflowCenter;
	private boolean mAlphaMode = true;
	private boolean mCircleMode = false;

	public CoverFlow(Context context) {
		super(context);
		this.setStaticTransformationsEnabled(true);
	}
	
	public CoverFlow(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setStaticTransformationsEnabled(true);
	}

	private int getCenterOfCoverflow() {
		return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
				+ getPaddingLeft();
	}

	private static int getCenterOfView(View view) {
		return view.getLeft() + view.getWidth() / 2;
	}
	
	/** 重写Garray方法 ，产生层叠和放大效果*/
	@Override
	protected boolean getChildStaticTransformation(View child, Transformation t) {
		final int childCenter = getCenterOfView(child);
		final int childWidth = child.getWidth();
		int rotationAngle = 0;
		t.clear();
		t.setTransformationType(Transformation.TYPE_MATRIX);
		if (childCenter == mCoveflowCenter) {
			transformImageBitmap((ImageView) child, t, 0, 0);
		} else {
			rotationAngle = (int) (((float) (mCoveflowCenter - childCenter) / childWidth) * mMaxRotationAngle);
			if (Math.abs(rotationAngle) > mMaxRotationAngle) {
				rotationAngle = (rotationAngle < 0) ? -mMaxRotationAngle
						: mMaxRotationAngle;
			}
			transformImageBitmap((ImageView) child, t, rotationAngle,
					(int) Math.floor((mCoveflowCenter - childCenter)/ (childWidth==0?1:childWidth)));
		}
		return true;
	}

	/**
	 * 
	 * 当视图的大小改变时，调用此方法
	 * 
	 * @param w 当前view宽度
	 *           
	 * @param h 当前view高度
	 *             
	 * @param oldw 旧view宽度
	 *            
	 * @param oldh 旧view高度
	 *            
	 */
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mCoveflowCenter = getCenterOfCoverflow();
		super.onSizeChanged(w, h, oldw, oldh);
	}

	/**
	 * 当转动角度时，使图片变形
	 * 
	 * @param child 用于旋转的图片
	 *            
	 * @param t transformation对象
	 *            
	 * @param rotationAngle 旋转角度
	 *           
	 */
	private void transformImageBitmap(ImageView child, Transformation t,
			int rotationAngle, int d) {
		mCamera.save();
		final Matrix imageMatrix = t.getMatrix();
		final int imageHeight = child.getLayoutParams().height;
		final int imageWidth = child.getLayoutParams().width;
		final int rotation = Math.abs(rotationAngle);
		mCamera.translate(0.0f, 0.0f, 100.0f);
		if (rotation <= mMaxRotationAngle) {
			float zoomAmount = (float) (mMaxZoom + (rotation * 1.5));
			mCamera.translate(0.0f, 0.0f, zoomAmount);
			if (mCircleMode) {
				if (rotation < 40)
					mCamera.translate(0.0f, 155, 0.0f);
				else
					mCamera.translate(0.0f, (255 - rotation * 2.5f), 0.0f);
			}
			if (mAlphaMode) {
				((ImageView) (child)).setAlpha((int) (255 - rotation * 2.5));
			}
		}
		mCamera.rotateY(rotationAngle);
		mCamera.getMatrix(imageMatrix);

		imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
		imageMatrix.postTranslate((imageWidth / 2), (imageHeight / 2));
		mCamera.restore();
	}
}
