package com.feng.shoot.bridge;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.webkit.JavascriptInterface;

import com.feng.shoot.common.dao.album.Album;
import com.feng.shoot.common.dao.album.AlbumDao;
import com.feng.shoot.common.dao.order.Order;
import com.feng.shoot.common.dao.order.OrderDao;
import com.feng.shoot.common.dao.user.User;
import com.feng.shoot.common.dao.user.UserDao;
import com.feng.shoot.common.database.AppDatabase;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import wendu.dsbridge.CompletionHandler;

public class Controller {
    private AppDatabase db;

    public Controller(Activity activity) {
        db = Room.databaseBuilder(activity.getApplicationContext(),
                AppDatabase.class, "AppDatabase").build();
    }

    @JavascriptInterface
    public void request(Object msg, CompletionHandler<String> handler) {
        // handler.complete(msg+" [ asyn call]");
        UserDao userDao = db.getUserDao();
    }

    @JavascriptInterface
    public void login(Object stringObject, CompletionHandler<String> handler) {
        JSONObject object = null;
        try {
            object = new JSONObject(stringObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (object == null) {
            handler.complete("error");
            return;
        }

        long phone = object.optLong("phone", 0);
        String password = object.optString("password", "");

        if (phone == 0) {
            handler.complete("error");
            return;
        }

        if (password.isEmpty()) {
            handler.complete("error");
            return;
        }

        UserDao userDao = db.getUserDao();

        User user = userDao.getUserByPhone(phone);

        if (user == null) {
            handler.complete("error");
            return;
        }

        if (user != null && user.getPassword().equals(password)) {
            handler.complete("success");
            return;
        }
    }

    @JavascriptInterface
    public void register(Object stringObject, CompletionHandler<String> handler) {
        JSONObject object = null;
        try {
            object = new JSONObject(stringObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (object == null) {
            handler.complete("error");
            return;
        }
        UserDao userDao = db.getUserDao();

        long phone = object.optLong("phone", 0);
        String password = object.optString("password", "");
        String nickname = object.optString("nickname", "");
        String residence = object.optString("residence", "");
        boolean custom = object.optBoolean("custom", true);


        if (phone == 0) {
            handler.complete("error");
            return;
        }

        User user = userDao.getUserByPhone(phone);

        if (user != null) {
            handler.complete("error");
            return;
        }

        user = new User();

        user.setPhone(phone);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setResidence(residence);
        user.setCustom(custom);

        userDao.insertAll(user);

        handler.complete("success");
    }

    @JavascriptInterface
    public void getAlbumByPhone(Object phoneStr, CompletionHandler<String> handler) {
        if (phoneStr == null) {
            handler.complete("error");
            return;
        }

        long phone = Long.parseLong(phoneStr.toString());

        if (phone == 0) {
            handler.complete("error");
            return;
        }

        AlbumDao albumDao = db.getAlbumDao();

        Album album =  albumDao.getAlbumByPhone(phone);

        if (album == null) {
            handler.complete("error");
            return;
        }

        JSONObject albumJSONObject = new JSONObject();
        try {
            albumJSONObject.put("phone", album.getPhone());
            albumJSONObject.put("content", album.getContent());
            albumJSONObject.put("cover", album.getCover());
            albumJSONObject.put("price", album.getPrice());
            albumJSONObject.put("title", album.getTitle());
            albumJSONObject.put("type", album.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (album != null && albumJSONObject != null) {
            handler.complete(albumJSONObject.toString());
            return;
        }

    }

    @JavascriptInterface
    public void getAllAlbums(Object stringObject, CompletionHandler<String> handler) {

        AlbumDao albumDao = db.getAlbumDao();

        List<Album> albumList = albumDao.getAllAlbums();

        Gson gson = new Gson();

        if (albumList == null) {
            handler.complete("error");
            return;
        }

        handler.complete(gson.toJson(albumList));

    }

    @JavascriptInterface
    public void insertAlbum(Object stringObject, CompletionHandler<String> handler) {

        AlbumDao albumDao = db.getAlbumDao();

        Gson gson = new Gson();

        Album album = gson.fromJson(stringObject.toString(), Album.class);

        albumDao.insert(album);

        handler.complete("success");

    }

    @JavascriptInterface
    public void insertOrder(Object stringObject, CompletionHandler<String> handler) {
        /*JSONObject object = null;
        try {
            object = new JSONObject(stringObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (object == null) {
            handler.complete("error");
            return;
        }

        object.optLong("customerPhone", 0);
        object.optLong("artistPhone", 0);
        object.optInt("status", 0);*/

        OrderDao orderDao = db.getOrderDao();

        Gson gson = new Gson();

        Order order = gson.fromJson(stringObject.toString(), Order.class);

        orderDao.insert(order);

        handler.complete("success");


    }

    @JavascriptInterface
    public void getAllOrders(Object phone, CompletionHandler<String> handler) {

        OrderDao orderDao = db.getOrderDao();

        List<Order> orders = orderDao.getOrderByCustomerPhone(Long.parseLong(phone.toString()));

        Gson gson = new Gson();

        handler.complete(gson.toJson(orders));

    }
}
