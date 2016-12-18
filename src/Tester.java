import org.json.JSONException;

import java.io.IOException;

/**
 * Created by przemek on 17.12.16.
 */
public class Tester {

    private static void testujPobieraniePartii(DataDownloader dd, Parliament parliament) throws IOException, JSONException {


        dd.downloadParties(parliament);
        for(Object obj:parliament.showParties()){
            PoliticalParty o=(PoliticalParty) obj;
            System.out.println( o.getName());

        }
        System.out.println("end");
    }

    private static void testujPobieraniePolityków(DataDownloader dd, Parliament parliament) throws IOException, JSONException {


        dd.downloadPoliticans(parliament);
        for(Object obj:parliament.showPoliticans()){
            Politican o=(Politican) obj;
            System.out.println( o.getName());

        }
        System.out.println("end");
    }


    public static void main(String[] args) throws IOException, JSONException {
        DataDownloader dd=new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json");

        Parliament parliament = new Parliament();
        testujPobieraniePartii(dd, parliament);
        testujPobieraniePolityków(dd, parliament);
    }
}

