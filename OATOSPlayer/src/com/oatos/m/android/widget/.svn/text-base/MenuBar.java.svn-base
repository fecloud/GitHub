/**
 * @(#) MenuBar.java Created on 2013-4-19
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.oatos.m.android.widget;

import com.oatos.player.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * The class <code>MenuBar</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class MenuBar extends LinearLayout {

	protected OnMenuClickListener onMenuClickListener;

	private Drawable mDividerDrawable;

	private Context mContext;

	/**
	 * @param context
	 * @param attrs
	 */
	@SuppressLint("Recycle")
	public MenuBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MenuBar);
		this.mDividerDrawable = a.getDrawable(R.styleable.MenuBar_divider);
		setFocusable(true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.ViewGroup#addView(android.view.View)
	 */
	@Override
	public void addView(View child) {
		if (child.getLayoutParams() == null) {
			LinearLayout.LayoutParams lp = null;
			if (getOrientation() == HORIZONTAL) {
				lp = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
			} else {
				lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);
			}
			lp.setMargins(0, 0, 0, 0);
			child.setLayoutParams(lp);
		}

		// Ensure you can navigate to the tab with the keyboard, and you can
		// touch it
		child.setFocusable(true);
		child.setClickable(true);
		super.addView(child);

		addDivider();
		setChildClickListener(child);

	}

	/**
	 * 添加间隔线
	 */
	private void addDivider() {
		if (mDividerDrawable != null && getMenuCount() > 0) {
			ImageView divider = new ImageView(mContext);
			LinearLayout.LayoutParams lp = null;

			if (getOrientation() == HORIZONTAL) {
				lp = new LayoutParams(mDividerDrawable.getIntrinsicWidth(),
						LayoutParams.MATCH_PARENT);
			} else {
				lp = new LayoutParams(LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);
			}
			lp.setMargins(0, 0, 0, 0);
			divider.setLayoutParams(lp);
			divider.setBackgroundDrawable(mDividerDrawable);
			super.addView(divider);
		}
	}

	/**
	 * 设置每一项目的点击事件
	 * 
	 * @param child
	 */
	protected void setChildClickListener(View child) {
		child.setOnClickListener(new MenuClickListener(getMenuCount() - 1));
	}

	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	public int getMenuCount() {
		int children = getChildCount();

		// If we have dividers, then we will always have an odd number of
		// children: 1, 3, 5, ... and we want to convert that sequence to
		// this: 1, 2, 3, ...
		if (mDividerDrawable != null) {
			children = (children + 1) / 2;
		}
		return children;
	}

	/**
	 * Returns the tab indicator view at the given index.
	 * 
	 * @param index
	 *            the zero-based index of the tab indicator view to return
	 * @return the tab indicator view at the given index
	 */
	public View getChildMenuViewAt(int index) {
		// If we are using dividers, then instead of tab views at 0, 1, 2, ...
		// we have tab views at 0, 2, 4, ...
		if (mDividerDrawable != null) {
			index *= 2;
		}
		return getChildAt(index);
	}

	/**
	 * @param onMenuClickListener
	 *            the onMenuClickListener to set
	 */
	public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
		this.onMenuClickListener = onMenuClickListener;
	}

	/**
	 * 设置禁用
	 * 
	 * @param enable
	 * @param enables
	 */
	public void setNoEnabled(int... enables) {

		View view = null;

		for (int index : enables) {
			view = getChildMenuViewAt(index);
			if (null != view)
				view.setEnabled(false);
		}

	}

	/**
	 * 删除最后一根据间隔线
	 */
	public void removeLastDivider() {
		if (null != mDividerDrawable) {
			getChildAt(getChildCount() - 1).setVisibility(View.GONE);
		}
	}

	// registered with each tab indicator so we can notify tab host
	private class MenuClickListener implements OnClickListener {

		private final int menuIndex;

		private MenuClickListener(int menuIndex) {
			this.menuIndex = menuIndex;
		}

		public void onClick(View v) {
			if (null != onMenuClickListener)
				onMenuClickListener.onMenuClick(menuIndex, getChildMenuViewAt(menuIndex));
		}
	}

	/**
	 * 菜单项点击事件 The class <code>OnMenuClickListener</code>
	 * 
	 * @author Feng OuYang
	 * @version 1.0
	 */
	public interface OnMenuClickListener {

		void onMenuClick(int menuIndex, View view);

	}

	public static final class MenuItem {

		private int id;

		private int icon;

		private CharSequence text;

		private Object tag;

		/**
		 * @param icon
		 * @param text
		 */
		public MenuItem(int icon, CharSequence text) {
			super();
			this.icon = icon;
			this.text = text;
		}

		/**
		 * @param id
		 * @param icon
		 * @param text
		 */
		public MenuItem(int id, int icon, CharSequence text) {
			super();
			this.id = id;
			this.icon = icon;
			this.text = text;
		}

		/**
		 * @param id
		 * @param icon
		 * @param text
		 * @param tag
		 */
		public MenuItem(int id, int icon, CharSequence text, Object tag) {
			super();
			this.id = id;
			this.icon = icon;
			this.text = text;
			this.tag = tag;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the icon
		 */
		public int getIcon() {
			return icon;
		}

		/**
		 * @param icon
		 *            the icon to set
		 */
		public void setIcon(int icon) {
			this.icon = icon;
		}

		/**
		 * @return the text
		 */
		public CharSequence getText() {
			return text;
		}

		/**
		 * @param text
		 *            the text to set
		 */
		public void setText(CharSequence text) {
			this.text = text;
		}

		/**
		 * @return the tag
		 */
		public Object getTag() {
			return tag;
		}

		/**
		 * @param tag
		 *            the tag to set
		 */
		public void setTag(Object tag) {
			this.tag = tag;
		}

	}

}
