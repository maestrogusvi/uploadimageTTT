package com.ut.myapi.service.imp;

import com.ut.myapi.service.OAuthService;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class OAuthServiceImp implements OAuthService {

    @Override
    public String getGoogleDriveToken() {
        /*
        HttpClient httpclient;
        HttpPost httppost = new HttpPost("http://127.0.0.1/oauth2/token");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("grant_type", "password"));
            nameValuePairs.add(new BasicNameValuePair("username", username));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            nameValuePairs.add(new BasicNameValuePair("client_id", clientID));
            nameValuePairs.add(new BasicNameValuePair("client_secret", clientSecret));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

            JSONObject json_auth = new JSONObject(EntityUtils.toString(response.getEntity()));
            String token = json_auth.getString("access_token");

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

         */
        return null;
    }
}
