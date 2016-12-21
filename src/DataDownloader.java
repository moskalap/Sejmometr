import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
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
            System.out.println((i*100)/15+"%");
           // progressBar1.setValue(i*100/15);
            JSONObject json = readJsonFromPage(new Integer(i).toString());
            JSONArray jsonArray = json.getJSONArray("Dataobject");
            for (int j = 0; j < jsonArray.length(); j++) {
                ih++;
                //System.out.println(j + " " + jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.nazwa"));
            if(!jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.nazwa").equals("")) {
                parliament.addParty(new PoliticalParty(jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.id"), jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.nazwa")));
                parliament.addPolitican(new Politican( Integer.parseInt(jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.id")), jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.imie_pierwsze"), jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.nazwisko")), jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.id") );
                //parliament.addPolitican(new Politican( Integer.parseInt(jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.id")), jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.imie_pierwsze"), jsonArray.getJSONObject(j).getJSONObject("data").getString("poslowie.nazwisko")), jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.id") );
            }
            }
        }
        System.out.println("\n Pobrano "+ih+" polityków");

    }

    public void downloadPoliticans(Parliament parliament) throws IOException, JSONException {
        System.out.println("Downloading Data");
        for(int i=1; i<=15; i++) {
            System.out.println((i*100)/15+"%");
            JSONObject json = readJsonFromPage(new Integer(i).toString());
            JSONArray jsonArray = json.getJSONArray("Dataobject");
            for (int j = 0; j < jsonArray.length(); j++) {
                System.out.println(j + " " + jsonArray.getJSONObject(j).getJSONObject("data").getString("sejm_kluby.nazwa"));
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
            String[] a=j.getJSONObject(i).getString("tytul").split("\\s+");
            System.out.println(j.getJSONObject(i).getString("tytul"));
         list.add(i+1+". "+a[0]+" "+" "+a[1]+" "+a[2]+"...");
        }
        return list;

    }

    public Double sumofExpenses(String id) throws IOException, JSONException {
        double sum=0.0;
        String u="https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=krs&layers[]=wydatki";
       JSONObject json =readJsonFrom(u);
        JSONArray j=json.getJSONObject("layers").getJSONObject("wydatki").getJSONArray("roczniki");
       for(int i=0; i<j.length(); i++){ //j-tablica z wydatkami w danym roku


            //String[] k= (String[]) j.getJSONObject(i).getJSONArray("pola").get(i); //tablica z wydatkami
            double a=Double.parseDouble((String) j.getJSONObject(i).getJSONArray("pola").get(i));
            System.out.println(j.getJSONObject(i).getJSONArray("pola"));
           JSONArray k=j.getJSONObject(i).getJSONArray("pola");

           for(int g=0; g<k.length(); g++){ //pod k.get(g) będzie teraz object ktorego castujemy do stringa, którego można zcastować do double // ja pierdole
            sum+=Double.parseDouble((String) k.get(g));
            }
















        }
        return sum;



    }

    public String downloadExpense(String id, String s) throws JSONException, IOException {
        System.out.println("pobiore dla "+id+" "+s);
        s=Integer.toString(Integer.parseInt(s)-1);
        double sum=0.0;
        String u="https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=krs&layers[]=wydatki";
        JSONObject json =readJsonFrom(u);
        JSONArray j=json.getJSONObject("layers").getJSONObject("wydatki").getJSONArray("roczniki");
         for(int i=0; i<j.length(); i++){ //j-tablica z wydatkami w danym roku
             JSONArray k=j.getJSONObject(i).getJSONArray("pola");
             System.out.println("adsa"+(String) k.get(Integer.parseInt(s)));
             sum+=Double.parseDouble((String) k.get(Integer.parseInt(s)));}

        //String[] k= (String[]) j.getJSONObject(i).getJSONArray("pola").get(i); //tablica z wydatkami
        //double a=Double.parseDouble((String) j.getJSONObject(i).getJSONArray("pola").get(i));


        System.out.println(sum);

         return String.valueOf(sum);



    }


    public String[] downloadArrayExp(int id) throws JSONException, IOException {

        String[] res=new String[20];

        String u="https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=krs&layers[]=wydatki";
        JSONObject json =readJsonFrom(u);
        LinkedList<String> list=new LinkedList<>();
        JSONArray j=json.getJSONObject("layers").getJSONObject("wydatki").getJSONArray("punkty");
        for(int i=0; i<j.length(); i++){
            String[] tmp=j.getJSONObject(i).getString("tytul").split("\\s+");
            String s="";
            for(int h=0; h<tmp.length; h++) {
                s += tmp[h] + " ";
                if (h % 5 == 4)
                    s += "\n";
            }
           res[i]=s;
        }
        return res;




    }
}





