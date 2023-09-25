package com.abc.demo.ads;

import org.json.JSONObject;

public interface getDataListner {

    void onSuccess();

    void onUpdate(String url);

    void onRedirect(String url);

    void onReload();

    void onGetExtradata(JSONObject extraData);
}
