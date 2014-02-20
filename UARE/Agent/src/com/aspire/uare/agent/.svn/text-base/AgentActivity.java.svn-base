package com.aspire.uare.agent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AgentActivity extends Activity implements OnClickListener {

    private int grabState;

    private static final String TAG = "AgentActivity";

    private AgentApplication agentApplication;

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.startbtn).setOnClickListener(this);
        // findViewById(R.id.uploadbtn).setOnClickListener(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, getString(R.string.settings));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            final Intent newIntent = new Intent(this, SettingActivity.class);
            startActivity(newIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.startbtn:
            startGrab(v);
            break;

        // case R.id.uploadbtn:
        // break;
        default:
            break;
        }

    }

    /**
     * @param v
     */
    private void startGrab(View v) {
        try {
            switch (grabState) {
            case 0:
                getAgentApplication();
                grabState = agentApplication.getAgentService().getRemoteAgentService().startGrabScreen();
                break;
            case 1:
                getAgentApplication();
                grabState = agentApplication.getAgentService().getRemoteAgentService().stopGrabScreen();
                break;
            default:
                break;
            }
            changeStartBtn(grabState, v);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
        }
    }

    public void changeStartBtn(int state, View view) {
        final Button button = (Button) view;
        switch (state) {
        case 0:
            button.setText(R.string.start);
            break;
        case 1:
            button.setText(R.string.stop);
            break;

        default:
            break;
        }
    }

    public void getAgentApplication() {
        if (null == agentApplication)
            agentApplication = (AgentApplication) getApplication();

    }

}
