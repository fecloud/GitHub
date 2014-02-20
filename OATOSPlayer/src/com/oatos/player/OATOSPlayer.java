/**
 * @(#) OATOSPlayer.java Created on 2013-6-28
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.oatos.player;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.oatos.m.android.widget.IconIndicator;
import com.oatos.m.android.widget.MediaController;
import com.oatos.m.android.widget.MenuBar;
import com.oatos.m.android.widget.OATOSPlayerView;
import com.oatos.m.android.widget.MediaController.VisbleListener;
import com.oatos.m.android.widget.MenuBar.OnMenuClickListener;

/**
 * The class <code>OATOSPlayer</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class OATOSPlayer extends Activity implements OnPreparedListener, OnMenuClickListener,
		VisbleListener, OnErrorListener {

	static final String TAG = "OATOSPlayer";

	private OATOSPlayerView playerView;

	private MediaController mediaController;

	private View titleBar, loading, music_icon;

	private MenuBar rightTools;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.oatos_player);

		mediaController = new MediaController();
		mediaController.setControlView(findViewById(R.id.control));
		mediaController.setVisbleListener(this);

		playerView = (OATOSPlayerView) findViewById(R.id.playerView);
		rightTools = (MenuBar) findViewById(R.id.rightTools);
		titleBar = findViewById(R.id.title_bar);
		loading = findViewById(R.id.player_loading);
		music_icon = findViewById(R.id.music_icon);

		rightTools.setOnMenuClickListener(this);

		loadRightTools();
		super.onCreate(savedInstanceState);
	}

	// /**
	// * @return
	// */
	// private View getMediaController() {
	// LayoutInflater inflate = (LayoutInflater)
	// getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// return inflate.inflate(R.layout.oatos_player_controller, null);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onRestart();
		playerView.setVideoURI(getNetMp3File());
		playerView.setOnPreparedListener(this);
		playerView.setOnErrorListener(this);
		playerView.setMediaController(mediaController);
		playerView.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		playerView.release(true);
	}

	/**
	 * 取播放文件路径
	 * 
	 * @return
	 */
	protected Uri getVideoFile() {
		final String path = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator + "188193_7.mp4";
		return Uri.fromFile(new File(path));
	}

	/**
	 * 取播放文件路径
	 * 
	 * @return
	 */
	protected Uri getMp3File() {
		final String path = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator + "jjdd.mp3";
		return Uri.fromFile(new File(path));
	}

	/**
	 * 取播放文件路径
	 * 
	 * @return
	 */
	protected Uri getNetFile() {
		return Uri.parse("http://192.168.2.9:8080/android.png");
	}

	protected Uri getNetMp3File() {
		return Uri.parse("http://192.168.2.9:8080/jjdd.mp3");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.media.MediaPlayer.OnPreparedListener#onPrepared(android.media
	 * .MediaPlayer)
	 */
	@Override
	public void onPrepared(MediaPlayer mp) {
		final int duration = mp.getDuration();
		final int currentPosition = mp.getCurrentPosition();
		final int videoWidth = mp.getVideoWidth();
		final int videoHeight = mp.getVideoHeight();
		Log.d(TAG, "duration:" + duration + " currentPosition:" + currentPosition + " videoWidth:"
				+ videoWidth + " videoHeight:" + videoHeight);
		loading.setVisibility(View.GONE);
		if (videoHeight == 0 && videoWidth == 0) {
			music_icon.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 加载右边工具
	 */
	private void loadRightTools() {
		rightTools.addView(new IconIndicator(this, R.layout.player_indicator,
				R.drawable.favorites_icon48, R.string.favorites).getView());
		rightTools.addView(new IconIndicator(this, R.layout.player_indicator,
				R.drawable.share_icon48, R.string.share).getView());
		rightTools.addView(new IconIndicator(this, R.layout.player_indicator,
				R.drawable.delete_icon48, R.string.delete).getView());
		rightTools.addView(new IconIndicator(this, R.layout.player_indicator,
				R.drawable.rename_icon48, R.string.rename).getView());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oatos.m.android.widget.MenuBar.OnMenuClickListener#onMenuClick(int,
	 * android.view.View)
	 */
	@Override
	public void onMenuClick(int menuIndex, View view) {
		Log.d(TAG, "onMenuClick menuIndex:" + menuIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oatos.player.MediaController.VisbleListener#onVisbleListener(boolean)
	 */
	@Override
	public void onVisbleListener(boolean isShow) {
		if (isShow) {
			rightTools.setVisibility(View.VISIBLE);
			titleBar.setVisibility(View.VISIBLE);
		} else {
			rightTools.setVisibility(View.GONE);
			titleBar.setVisibility(View.GONE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.media.MediaPlayer.OnErrorListener#onError(android.media.MediaPlayer
	 * , int, int)
	 */
	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {

		return false;
	}
}
