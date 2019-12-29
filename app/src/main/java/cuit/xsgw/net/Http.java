package cuit.xsgw.net;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Http {
    public void Get(String url, @Nullable Map<String, String> params, @Nullable Map<String, String> headrs, Callback responseCallback) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder();

        if (params != null && !params.isEmpty()) {
            url += "?";
            for (Map.Entry<String, String> i : params.entrySet()) {
                url += (i.getKey() + "=" + i.getValue() + "&");
            }
            url = url.substring(0,url.length()-1);
        }
        if (headrs != null && !headrs.isEmpty())
            for (Map.Entry<String, String> i : headrs.entrySet()) {
                requestBuilder.header(i.getKey(), i.getValue());
            }

        Call call = client.newCall(requestBuilder.url(url).build());
        call.enqueue(responseCallback);
    }

    public void Post(String url, @Nullable Map<String, String> params, @Nullable Map<String, String> headrs, @Nullable Map<String, String> body, Callback responseCallback) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder();
        FormBody.Builder requestBody = new FormBody.Builder();

        if (params != null && !params.isEmpty())
            for (Map.Entry<String, String> i : params.entrySet()) {
                url += ("?" + i.getKey() + "=" + i.getValue());
            }
        if (headrs != null && !headrs.isEmpty())
            for (Map.Entry<String, String> i : headrs.entrySet()) {
                requestBuilder.header(i.getKey(), i.getValue());
            }
        if (body != null && !body.isEmpty())
            for (Map.Entry<String, String> i : body.entrySet()) {
                requestBody.add(i.getKey(), i.getValue());
            }
        Call call = client.newCall(requestBuilder.url(url).post(requestBody.build()).build());
        call.enqueue(responseCallback);
    }
}
