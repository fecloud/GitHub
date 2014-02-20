/**
 * @(#) OATOSIndicatorStrategy.java Created on 2013-3-18
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.oatos.m.android.widget;

import com.oatos.player.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The class <code>IconIndicator</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class IconIndicator {

	private int icon;

	private int text;

	private int layout;

	private int id;

	private static LayoutInflater inflater;

	/**
	 * @param mContext
	 * @param icon
	 */
	public IconIndicator(Context mContext, int layout, int icon, int text) {
		super();
		if (null == inflater)
			inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.layout = layout;
		this.icon = icon;
		this.text = text;
	}

	/**
	 * @param mContext
	 * @param icon
	 */
	public IconIndicator(Context mContext, int id, int layout, int icon, int text) {
		super();
		if (null == inflater)
			inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.id = id;
		this.layout = layout;
		this.icon = icon;
		this.text = text;
	}

	public View getView() {
		final View view = inflater.inflate(layout, null);
		view.setId(id);
		final ImageView icon = (ImageView) view.findViewById(R.id.icon);
		icon.setImageResource(this.icon);
		final TextView text = (TextView) view.findViewById(R.id.text);
		text.setText(this.text);

		return view;
	}

}
