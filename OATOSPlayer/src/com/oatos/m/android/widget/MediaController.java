/**
 * @(#) OATOSPlayerController.java Created on 2013-6-28
 *
 * Copyright © 2013 深圳企业云科技有限公司  版权所有
 */
package com.oatos.m.android.widget;

import java.util.Formatter;
import java.util.Locale;

import com.oatos.player.R;
import com.oatos.player.R.drawable;
import com.oatos.player.R.id;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * The class <code>OATOSPlayerController</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class MediaController implements OnClickListener, OnSeekBarChangeListener {

	private static final int FADE_OUT = 1;
	private static final int SHOW_PROGRESS = 2;
	private static final int sDefaultTimeout = 3000;

	private MediaPlayerControl mPlayer;
	private VisbleListener visbleListener;

	private View mRoot;
	private boolean mShowing;
	private boolean mDragging;

	private SeekBar mediacontroller_progress;
	private TextView time_current, endtime;
	private Button paly;
	private StringBuilder mStringBuilder;
	private Formatter mFormatter;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int pos;
			switch (msg.what) {
			case FADE_OUT:
				hide();
				break;
			case SHOW_PROGRESS:
				pos = setProgress();
				if (!mDragging && mShowing && mPlayer.isPlaying()) {
					msg = obtainMessage(SHOW_PROGRESS);
					sendMessageDelayed(msg, 1000 - (pos % 1000));
				}
				break;
			}
		}
	};

	public void setControlView(View view) {
		this.mRoot = view;
		initView();
	}

	/**
	 * 初始化所有组件
	 */
	private void initView() {
		if (null != mRoot) {
			paly = (Button) findViewById(R.id.play);

			mediacontroller_progress = (SeekBar) findViewById(R.id.mediacontroller_progress);
			time_current = (TextView) findViewById(R.id.time_current);
			endtime = (TextView) findViewById(R.id.endtime);

			paly.setOnClickListener(this);
			mediacontroller_progress.setMax(1000);
			mediacontroller_progress.setOnSeekBarChangeListener(this);
			mStringBuilder = new StringBuilder();
			mFormatter = new Formatter(mStringBuilder, Locale.getDefault());
		}
	}

	private View findViewById(int id) {
		return mRoot.findViewById(id);
	}

	private int setProgress() {
		if (mPlayer == null || mDragging) {
			return 0;
		}
		int position = mPlayer.getCurrentPosition();
		int duration = mPlayer.getDuration();
		if (mediacontroller_progress != null) {
			if (duration > 0) {
				// use long to avoid overflow
				long pos = 1000L * position / duration;
				mediacontroller_progress.setProgress((int) pos);
			}
			int percent = mPlayer.getBufferPercentage();
			mediacontroller_progress.setSecondaryProgress(percent * 10);
		}

		if (endtime != null)
			endtime.setText("-" + stringForTime(duration - position));
		if (time_current != null)
			time_current.setText(stringForTime(position));

		return position;
	}

	/**
	 * 
	 */
	public void hide() {

		if (mShowing) {
			mHandler.removeMessages(SHOW_PROGRESS);
			toggleUI(false);
			mShowing = false;
		}

	}

	private void toggleUI(boolean con) {
		if (con) {
			mRoot.setVisibility(View.VISIBLE);
		} else {
			mRoot.setVisibility(View.GONE);
		}
		if (null != visbleListener)
			visbleListener.onVisbleListener(con);
	}

	/**
	 * @param i
	 */
	public void show(int timeout) {

		if (!mShowing) {
			setProgress();
			toggleUI(true);
			mShowing = true;
		}

		updatePausePlay();

		mHandler.sendEmptyMessage(SHOW_PROGRESS);

		Message msg = mHandler.obtainMessage(FADE_OUT);
		if (timeout != 0) {
			mHandler.removeMessages(FADE_OUT);
			mHandler.sendMessageDelayed(msg, timeout);
		}
	}

	/**
	 * 
	 */
	public void show() {
		show(sDefaultTimeout);

	}

	/**
	 * @return
	 */
	public boolean isShowing() {
		return mShowing;
	}

	private void updatePausePlay() {
		if (mRoot == null)
			return;

		if (mPlayer.isPlaying()) {
			paly.setBackgroundResource(R.drawable.stop_icon);
		} else {
			paly.setBackgroundResource(R.drawable.play_icon);
		}
	}

	/**
	 * @param oatosPlayerView
	 */
	public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
		this.mPlayer = mediaPlayerControl;
	}

	/**
	 * @param inPlaybackState
	 */
	public void setEnabled(boolean inPlaybackState) {
		paly.setEnabled(inPlaybackState);
		mediacontroller_progress.setEnabled(inPlaybackState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.SeekBar.OnSeekBarChangeListener#onProgressChanged(android
	 * .widget.SeekBar, int, boolean)
	 */
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (!fromUser) {
			return;
		}
		long duration = mPlayer.getDuration();
		long newposition = (duration * progress) / 1000L;
		mPlayer.seekTo((int) newposition);
		if (endtime != null)
			endtime.setText("-" + stringForTime((int) (duration - newposition)));
		if (time_current != null)
			time_current.setText(stringForTime((int) newposition));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.SeekBar.OnSeekBarChangeListener#onStartTrackingTouch(android
	 * .widget.SeekBar)
	 */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		mDragging = true;
		mHandler.removeMessages(SHOW_PROGRESS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.SeekBar.OnSeekBarChangeListener#onStopTrackingTouch(android
	 * .widget.SeekBar)
	 */
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		mDragging = false;
		setProgress();
		show(sDefaultTimeout);
		mHandler.sendEmptyMessage(SHOW_PROGRESS);
	}

	private String stringForTime(int timeMs) {
		int totalSeconds = timeMs / 1000;

		int seconds = totalSeconds % 60;
		int minutes = (totalSeconds / 60) % 60;
		int hours = totalSeconds / 3600;

		mStringBuilder.setLength(0);
		if (hours > 0) {
			return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
		} else {
			return mFormatter.format("%02d:%02d", minutes, seconds).toString();
		}
	}

	/**
	 * @param visbleListener
	 *            the visbleListener to set
	 */
	public void setVisbleListener(VisbleListener visbleListener) {
		this.visbleListener = visbleListener;
	}

	/**
	 * 播放控制器,显示和隐藏 The class <code>VisbleListener</code>
	 * 
	 * @author Feng OuYang
	 * @version 1.0
	 */
	public interface VisbleListener {

		public void onVisbleListener(boolean isShow);

	}

	private void doPauseResume() {
		if (mPlayer.isPlaying()) {
			mPlayer.pause();
		} else {
			mPlayer.start();
		}
		updatePausePlay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		doPauseResume();
		show(sDefaultTimeout);

	}
}
