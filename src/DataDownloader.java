import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;

/**
 * Created by przemek on 09.12.16.
 *
 * przechowuje pobrane dane z url, które będą wyświetlane
 */
public class DataDownloader {
    String url;
    String mainUrl;
    //"https://api-v3.mojepanstwo.pl/dane/poslowie.json?_type=objects&page=2";

    public DataDownloader(String url){
        this.mainUrl=url;
        this.url=mainUrl+"&_type=objects&page=";
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    public  JSONObject readJsonFromPage(String page) throws IOException, JSONException {
        InputStream is = new URL(this.url+page).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    public  JSONObject readJsonFrom(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }


    public void downloadParties(Parliament parliament) throws IOException, JSONException {
        int ih=0;
        System.out.println("Downloading Data");
        for(int i=1; i<=15; i++) {
            System.out.print(".");
            JSONObject json = readJsonFromPage(new Integer(i).toString());
            JSONArray jsonArray = json.getJSONArray("Dataobject");
            for (int j = 0; j < jsonArray.length(); j++) {
                ih++;
                //System.out.println(j + " " + jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.nazwa"));
            if(!jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.nazwa").equals("")) {
                parliament.addParty(new PoliticalParty(jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.id"), jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.nazwa")));
                parliament.addPolitican(new Politican( Integer.parseInt(jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.id")), jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.imie_pierwsze"), jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.nazwisko")), jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.id") ); parliament.addPolitican(new Politican( Integer.parseInt(jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.id")), jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.imie_pierwsze"), jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.nazwisko")), jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.id") );
            }
            }
        }
        System.out.println("\n Pobrano "+ih+" polityków");

    }

    public void downloadPoliticans(Parliament parliament) throws IOException, JSONException {
        System.out.println("Downloading Data");
        for(int i=1; i<=15; i++) {
            System.out.print(".");
            JSONObject json = readJsonFromPage(new Integer(i).toString());
            JSONArray jsonArray = json.getJSONArray("Dataobject");
            for (int j = 0; j < jsonArray.length(); j++) {
                //System.out.println(j + " " + jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.nazwa"));
                if(!jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.nazwa").equals(""))
                    parliament.addPolitican(new Politican( Integer.parseInt(jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.id")), jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.imie_pierwsze"), jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.nazwisko")), jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.id") );
            }
        }
      //  System.out.println("\n Pobrano "+ih+" polityków");

    }


    public LinkedList<String> downloadListofExpenses(int id) throws IOException, JSONException {
        String u="https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=krs&layers[]=wydatki";
        JSONObject json =readJsonFrom(u);
        LinkedList<String> list=new LinkedList<>();
      JSONArray j=json.getJSONObject("layers").getJSONObject("wydatki").getJSONArray("punkty");
        for(int i=0; i<j.length(); i++){
          list.add( j.getJSONObject(i).getString("tytul"));
        }
        return list;

    }
    }





