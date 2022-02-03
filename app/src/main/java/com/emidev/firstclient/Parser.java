package com.emidev.firstclient;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Parser {

    public Parser(){};

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Object parseDocument(Context context){
        try{
            DocumentBuilderFactory factory;
            DocumentBuilder builder;
            Document document;
            Element root;
            NodeList nodeList;
            Node nd;

            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            String path = context.getExternalFilesDir(null) + "/seattle.xml";
            File xml = new File(path);
            try {
                document = builder.parse(xml);
            }catch(Exception e){
                e.printStackTrace();
                Log.i("PST", e.getMessage());
                return null;
            }
            root = document.getDocumentElement();

            String name = "", country = "", sunrise = "", sunset = "";
            double lat, lng;

            //get coords
            nodeList = root.getElementsByTagName("coord");
            nd =  nodeList.item(0);
            lat = Double.parseDouble(nd.getAttributes().getNamedItem("lat").getTextContent());
            lng = Double.parseDouble(nd.getAttributes().getNamedItem("lon").getTextContent());

            //get city name
            nodeList = root.getElementsByTagName("city");
            nd =  nodeList.item(0);
            name = nd.getAttributes().getNamedItem("name").getTextContent();

            //get country name
            nodeList = root.getElementsByTagName("country");
            nd =  nodeList.item(0);
            country = nd.getFirstChild().getTextContent();

            //get sunset and sunrise times
            nodeList = root.getElementsByTagName("sun");
            nd =  nodeList.item(0);
            sunrise = nd.getAttributes().getNamedItem("rise").getTextContent();
            sunset = nd.getAttributes().getNamedItem("set").getTextContent();

            Place city = new Place(name, country, lat, lng, sunset, sunrise);

            return city;

        } catch (ParserConfigurationException /*| SAXException | IOException*/ e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]){

    }
}

