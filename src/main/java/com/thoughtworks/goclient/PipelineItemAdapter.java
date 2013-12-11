package com.thoughtworks.goclient;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PipelineItemAdapter extends ArrayAdapter<PipelineData> {

    private Activity context;
    private int layoutResourceId;
    private List<PipelineData> data;

    private static final Map<String,  Integer> statusToColor = new HashMap<String, Integer>() {{
        put(PipelineData.SUCCESS,  Color.GREEN);
        put(PipelineData.FAIL,     Color.RED);
        put(PipelineData.BUILDING, Color.YELLOW);
    }};

    public PipelineItemAdapter(Activity context, int layoutResourceId, List<PipelineData> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        PipelineData pipelineData = data.get(position);
        TextView textViewItem = (TextView) convertView.findViewById(R.id.pipeline);
        textViewItem.setText(pipelineData.name);
        textViewItem.setBackgroundColor(statusToColor.get(pipelineData.status()));
        return convertView;
    }
}
