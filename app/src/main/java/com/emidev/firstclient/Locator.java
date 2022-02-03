package com.emidev.firstclient;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;


public class Locator {

    private String prefix = "https://api.openweathermap.org/data/2.5/weather?q=";
    private String suffix = "&mode=xml&appid=2704bf5f288a0f7bad7f1713df752391";
    private StringBuilder url;

    public Locator(){}

    public boolean requestLocation(String place, Context context) {
        URL server;
        HttpsURLConnection service;
        BufferedReader input;
        BufferedWriter output;
        String line;
        int status;

        try {
            place.replace(" ", "+");
            url = new StringBuilder(prefix);
            url.append(URLEncoder.encode(place, "UTF-8"));
            url.append(suffix);
            System.out.println(url.toString());
            server = new URL(url.toString());
            service = (HttpsURLConnection) server.openConnection();
            service.setRequestProperty("Host", "api.openweathermap.org");
            service.setRequestProperty("Accept", "application/xml");
            service.setRequestProperty("Accept-Charset", "UTF-8");
            service.setRequestMethod("GET");
            service.setDoInput(true);
            service.connect();
            status = service.getResponseCode();
            Log.i("FirstFragment", "Status: " + status);
            if (status != 200) {
                return false;
            }
            String state = Environment.getExternalStorageState();
            if (!Environment.MEDIA_MOUNTED.equals(state)) {
                //If it isn't mounted - we can't write into it.
                return false;
            }

            input = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));

            String path = context.getExternalFilesDir(null) + "/seattle.xml";
            output = new BufferedWriter(new FileWriter(path));

            while ((line = input.readLine()) != null) {
                output.write(line);
                output.newLine();
            }
            input.close();
            output.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {

    }
}

