package com.bignerdranch.photogallery;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by hongy_000 on 2017/9/16.
 */

public class FlickrFetchrOkHttp {

    private static final String TAG = FlickrFetchrOkHttp.class.getSimpleName();

    private static final String API_KEY = "aeef2a25da952db7325b46add2fb1208";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);
        byte[] bytes = new byte[1024];
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            try (Response response = call.execute()) {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response + ": with " + urlSpec);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                InputStream inputStream = response.body().byteStream();

                int bytesRead;
                byte[] buffer = new byte[1024];
                while ((bytesRead = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                bytes = outputStream.toByteArray();
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to get Bytes ", e);
        }
        return bytes;
    }

    public String getUrlString(String urlSpec) throws IOException{
        return new String(getUrlBytes(urlSpec));
    }

    public List<GalleryItem> fetchItems(){

        final List<GalleryItem> items = new ArrayList<>();

        try {
            final Request request = new Request.Builder()
                    .url("https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=aeef2a25da952db7325b46add2fb1208&format=json&nojsoncallback=1&extras=url_s")
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            parseItems(items, response);

            /*Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                   Log.d(TAG, "Unexpected code ", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try(ResponseBody responseBody = response.body()) {
                        Log.d(TAG, "Unexpected code " + response);
                        parseItems(items, response);
                    } catch (JSONException e) {
                        Log.e(TAG, "Failed to parse json ", e);
                    }
                }
            });*/
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse json ", e);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items ", ioe);
        }

        return items;
    }

    public void parseItems(List<GalleryItem> items, Response response) throws IOException, JSONException {

        RecentPhotoItems recentPhotoItems = gson.fromJson(response.body().string(), RecentPhotoItems.class);
        for (RecentPhotoItems.PhotosBean.PhotoBean photo : recentPhotoItems.getPhotos().getPhoto()) {
            GalleryItem item = new GalleryItem();
            item.setCaption(photo.getTitle());
            item.setId(photo.getId());

            if (photo.getUrl_s() == null) {
                continue;
            }
            item.setUrl(photo.getUrl_s());
            items.add(item);
        }
    }
}