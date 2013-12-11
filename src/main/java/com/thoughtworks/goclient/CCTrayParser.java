package com.thoughtworks.goclient;

import android.util.Log;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class CCTrayParser {


    private String loadGoCCTray(String goServerUri) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(goServerUri + "/go/cctray.xml");
        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        return client.execute(request, responseHandler);
    }

    public List<PipelineData> parseCCTray(String uri) {
        try {
            return doParseCCTray(uri);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<PipelineData>() {{ add(PipelineData.FAIL_TO_CREATE); }};
        }
    }

    private List<PipelineData> doParseCCTray(String uri) throws IOException, XmlPullParserException {
        String cctray = loadGoCCTray(uri);
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(cctray));
        List<PipelineData> pipelineDatas = new ArrayList<PipelineData>();

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();
                    if (tagName.equals("Project")) {
                        Log.i("Pipeline", parser.getAttributeValue("", "name"));
                        Log.i("Pipeline", parser.getAttributeValue("", "lastBuildStatus"));
                        pipelineDatas.add(new PipelineData(parser.getAttributeValue("", "name"),
                                                           parser.getAttributeValue("", "lastBuildStatus"),
                                                           parser.getAttributeValue("", "activity")));
                    }
                    break;

                case XmlPullParser.END_TAG:
                    break;
                case XmlPullParser.END_DOCUMENT:
                    break;
            }
            eventType = parser.next();
        }

        return pipelineDatas;
    }
}
