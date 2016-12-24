import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by przemek on 09.12.16.
 *
 * pobierane dane i zwraca je w postaci list lub pojedynczej wartości
 */
public class DataDownloader {
    String url;
    String mainUrl;
    String term;
    String[] expenses;
  private  Parliament parliament;
   // Parliament parliament=new Parliament();

    //"https://api-v3.mojepanstwo.pl/dane/poslowie.json?_type=objects&page=2";

    public DataDownloader(String url) throws IOException, JSONException {
        this.mainUrl=url;
        this.url=mainUrl+"&_type=objects&page=";
        expenses=this.downloadArrayExp(174);
        term="7";

    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
           sb.append((char) cp);
}

return sb.toString();

    }


    private void getConteext (URL page) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        page.openStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);

        in.close();
    }

    public void read(String id) throws IOException {
        String u="https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=wydatki&layers[]=wyjazdy";
        getConteext(new URL(u));

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

    public  void saveJsonFrom(String url, String id, String term) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            Writer writer = null;

            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("/home/przemek/Dokumenty/JavaWorkspace/oop/lab9/API/res/politican/"+term+"/"+id+".json"), "utf-8"));
                writer.write(jsonText);
            } catch (IOException ex) {
                // report
            } finally {
                try {writer.close();} catch (Exception ex) {/*ignore*/}
            }
           // PrintWriter out= new PrintWriter("/politican/"+id+".txt");
            //out.print(jsonText);
            //out.close();

        } finally {
            is.close();
        }
    }


    public String downloadimage(String name, int index) throws IOException, JSONException {

        name=name.replaceAll("\\s+", "%22");
        System.out.println(name);
      String u="https://www.googleapis.com/customsearch/v1?q="+name+"&cx=006436199788929835488%3Awfjcyviz_e0&imgSize=large&searchType=image&key=AIzaSyBxQyLyAKSC1chHULCTjwDHCZN_dD188vw";
      JSONObject j=readJsonFrom(u);
      System.out.println(j.getJSONArray("items").getJSONObject(index).getString("link"));


return //"https://pbs.twimg.com/profile_images/658536731315183616/6IX58ddO.jpg";
j.getJSONArray("items").getJSONObject(0).getString("link");




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
      //  this.parliament=parliament;
        System.out.println("\n Pobrano "+ih+" polityków");

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
           // String u="https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=wydatki";
           // JSONObject json =readJsonFrom(u);
        JSONObject json= new JSONObject(new Scanner(new File("/home/przemek/Dokumenty/JavaWorkspace/oop/lab9/API/res/politican/"+term+"/"+id+".json")).useDelimiter("\\Z").next());
        JSONArray j=json.getJSONObject("layers").getJSONObject("wydatki").getJSONArray("roczniki");
           for(int i=0; i<j.length(); i++){
             JSONArray expenses=j.getJSONObject(i).getJSONArray("pola");
           for(int g=0; g<expenses.length(); g++){
           sum+=Double.parseDouble((String) expenses.get(g));
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

    public String downloadSumOfParty(String party, Parliament parliament) throws IOException, JSONException {
        LinkedList<Politican> listOfPolitican;
        if(party.equals("-a")) {
            listOfPolitican = parliament.getMembers();
        }
        else {
            listOfPolitican = (LinkedList<Politican>) parliament.getPartybyName(party).getPoliticans();
        }
        double sum=0.0;
       // Politican politican;
        for(Politican politican:listOfPolitican)
            sum+=this.sumofExpenses(politican.getID());

        NumberFormat formatter = new DecimalFormat("#0.00");


        return formatter.format(sum);

    }

    public String downloadAvgOfParty(String party, Parliament parliament) throws IOException, JSONException {

        LinkedList<Politican> listOfPolitican;
                if(party.equals("-a")) {
                 listOfPolitican = parliament.getMembers();
                }
                else {
                    listOfPolitican = (LinkedList<Politican>) parliament.getPartybyName(party).getPoliticans();
                }
        double sum=0.0;

        for(Politican politican:listOfPolitican)
            sum+=this.sumofExpenses(politican.getID());

        sum=sum/listOfPolitican.size();

        NumberFormat formatter = new DecimalFormat("#0.00");


        return formatter.format(sum);

    }

    private boolean wasTravellerTo_(String country, String id) throws FileNotFoundException, JSONException {
        JSONObject json= new JSONObject(new Scanner(new File("/home/przemek/Dokumenty/JavaWorkspace/oop/lab9/API/res/politican/"+term+"/"+id+".json")).useDelimiter("\\Z").next());
        try{
            JSONArray j=json.getJSONObject("layers").getJSONArray("wyjazdy");
            for(int i=0; i<j.length(); i++){
                if(j.getJSONObject(i).getString("kraj").equals(country)) return true;
            }
        }
        catch(JSONException e){
            return false;
        }



        return false;
    }

    public ArrayList<Politican> downloadListofTravellersto_(String country, String party) throws FileNotFoundException, JSONException {
        ArrayList<Politican> res=new ArrayList<>();



        LinkedList<Politican> listOfPolitican;
        if(party.equals("-a")) {
            listOfPolitican = parliament.getMembers();
        }
        else {
            listOfPolitican = (LinkedList<Politican>) parliament.getPartybyName(party).getPoliticans();
        }







        for(Politican p:listOfPolitican){
            if (wasTravellerTo_(country,p.getID())) res.add(p);

        }

        return res;
    }

    public Politican  downloadTheLongTravel(String party) throws FileNotFoundException, JSONException {
    Politican res=new Politican(-23,"nikt","nikt");
        int days=0;


        LinkedList<Politican> listOfPolitican;
        if(party.equals("-a")) {
            listOfPolitican = parliament.getMembers();
        }
        else {
            listOfPolitican = (LinkedList<Politican>) parliament.getPartybyName(party).getPoliticans();
        }





        for(Politican p:listOfPolitican){


            System.out.println("CURMAX="+days+"\nSprawdzam"+p.getID()+" "+p.getName());


            JSONObject json= new JSONObject(new Scanner(new File("/home/przemek/Dokumenty/JavaWorkspace/oop/lab9/API/res/politican/"+term+"/"+p.getID()+".json")).useDelimiter("\\Z").next());
            try{

                JSONArray j=json.getJSONObject("layers").getJSONArray("wyjazdy");
                for(int i=0; i<j.length(); i++){
                    if(Integer.parseInt(j.getJSONObject(i).getString("liczba_dni"))>days){
                        days=Integer.parseInt(j.getJSONObject(i).getString("liczba_dni"));
                        res=p;
                        System.out.println(p.getID()+" "+p.getName()+ days);
                    }

                    System.out.print("|"+Integer.parseInt(j.getJSONObject(i).getString("liczba_dni")));
                }
            }
            catch(JSONException e){
                //poprostu nigdzie nie podrózówał
            }





        }


        return res;
    }

    public String downloadTheExpensiverTraveller(String party) throws FileNotFoundException, JSONException {








        Politican res=new Politican(-23,"nikt","nikt");



        LinkedList<Politican> listOfPolitican;
        if(party.equals("-a")) {
            listOfPolitican = parliament.getMembers();
        }
        else {
            listOfPolitican = (LinkedList<Politican>) parliament.getPartybyName(party).getPoliticans();
        }






        double price=0.0;
        for(Politican p:listOfPolitican){


            System.out.println("\nCURMAX="+price+"\nSprawdzam"+p.getID()+" "+p.getName());


            JSONObject json= new JSONObject(new Scanner(new File("/home/przemek/Dokumenty/JavaWorkspace/oop/lab9/API/res/politican/"+term+"/"+p.getID()+".json")).useDelimiter("\\Z").next());
            try{

                JSONArray j=json.getJSONObject("layers").getJSONArray("wyjazdy");
                for(int i=0; i<j.length(); i++){
                    if(Double.parseDouble(j.getJSONObject(i).getString("koszt_suma"))>price){
                        price=Double.parseDouble(j.getJSONObject(i).getString("koszt_suma"));
                        res=p;
                        System.out.println(p.getID()+" "+p.getName()+ price);
                    }

                    System.out.print("|"+Double.parseDouble(j.getJSONObject(i).getString("koszt_suma")));
                }
            }
            catch(JSONException e){
                //poprostu nigdzie nie podrózówał
            }





        }


        return res.getName()+" - kwota: "+price;











    }

    public String downloadTheGreatestTrveller(String party) throws FileNotFoundException, JSONException {





        Politican res=new Politican(-23,"nikt","nikt");


        LinkedList<Politican> listOfPolitican;
        if(party.equals("-a")) {
            listOfPolitican = parliament.getMembers();
        }
        else {
            listOfPolitican = (LinkedList<Politican>) parliament.getPartybyName(party).getPoliticans();
        }


        int travels=0;
        for(Politican p:listOfPolitican){


            System.out.println("\nCURMAX="+travels+"\nSprawdzam"+p.getID()+" "+p.getName());


            JSONObject json= new JSONObject(new Scanner(new File("/home/przemek/Dokumenty/JavaWorkspace/oop/lab9/API/res/politican/"+term+"/"+p.getID()+".json")).useDelimiter("\\Z").next());
            try{

                JSONArray j=json.getJSONObject("layers").getJSONArray("wyjazdy");
                    if(j.length()>travels){
                        travels=j.length();
                        res=p;
                    }

                    System.out.print(j.length());



            }
            catch(JSONException e){
                //poprostu nigdzie nie podrózówał
            }





        }


        return res.getName()+"- podróży: "+travels;






    }

    public void saveObj(String id, String term) throws IOException, JSONException {
        String u="https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=wydatki&layers[]=wyjazdy";

        saveJsonFrom(u, id, term);

    }

    public String getExpenseName(String expenseID) {
        return expenses[Integer.parseInt(expenseID)-1];

    }

    public void setParliament(Parliament parliament) {
        this.parliament = parliament;
    }

    public LinkedList<String> downloadCountries() throws FileNotFoundException, JSONException {
        LinkedList<String> country=new LinkedList<>();


        for (Politican p:parliament.showPoliticans()){

            JSONObject json= new JSONObject(new Scanner(new File("/home/przemek/Dokumenty/JavaWorkspace/oop/lab9/API/res/politican/"+term+"/"+p.getID()+".json")).useDelimiter("\\Z").next());
            try{
                JSONArray j=json.getJSONObject("layers").getJSONArray("wyjazdy");
                for(int i=0; i<j.length(); i++){

                    if(!country.contains(j.getJSONObject(i).getString("kraj")))
                        country.add(j.getJSONObject(i).getString("kraj"));
                }
            }
            catch(JSONException e){
               //
            }

        }

        return country;





    }
}





