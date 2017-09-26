package com.bignerdranch.photogallery;

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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hongy_000 on 2017/9/16.
 */

public class FlickrFetchrOkHttp {

    private static final String TAG = FlickrFetchrOkHttp.class.getSimpleName();

    private static final String API_KEY = "aeef2a25da952db7325b46add2fb1208";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();


    public byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream inputStream = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) > 0) {
               outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            return outputStream.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException{
        return new String(getUrlBytes(urlSpec));
    }

    public List<GalleryItem> fetchItems(){

        List<GalleryItem> items = new ArrayList<>();

        try {
            Request request = new Request.Builder()
                    .url("https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=aeef2a25da952db7325b46add2fb1208&formate=json&nojsoncallback=1")
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            parseItems(items, response);
        } catch (JSONException jsonException){
            Log.e(TAG, "Failed to parse json ", jsonException);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items ", ioe);
        }

        return items;
    }

    public void parseItems(List<GalleryItem> items, Response response) throws IOException, JSONException {

        RecentPhotoItems recentPhotoItems = gson.fromJson(String.valueOf(response.body().byteStream()), RecentPhotoItems.class);
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