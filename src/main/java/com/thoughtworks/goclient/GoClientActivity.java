package com.thoughtworks.goclient;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class GoClientActivity extends Activity {

    final CCTrayParser ccTrayParser = new CCTrayParser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireNetworkPermission();
        LinearLayout linearLayout = createVerticalLinearLayout();
        attchGopipelineStatusView(linearLayout);
        attchControllPanel(linearLayout);
    }

    private void attchControllPanel(LinearLayout linearLayout) {
        TextView debugInfo = new TextView(this);
        linearLayout.addView(debugInfo);
    }

    private void attchGopipelineStatusView(LinearLayout linearLayout) {
        List<PipelineData> pipelineStatuses = ccTrayParser.parseCCTray("http://10.18.7.153:8153");
        ListView listView = pipelinesView(pipelineStatuses);
        LinearLayout.LayoutParams param =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                                              LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(listView, param);
    }

    private LinearLayout createVerticalLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.WHITE);
        setContentView(linearLayout);
        return linearLayout;
    }

    private void requireNetworkPermission() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private ListView pipelinesView(List<PipelineData> pipelineStatuses) {
        ListView listView = new ListView(this);
        PipelineItemAdapter pipelineItemAdapter = new PipelineItemAdapter(this, R.layout.listitem, pipelineStatuses);
        listView.setAdapter(pipelineItemAdapter);
        return listView;
    }

}






































