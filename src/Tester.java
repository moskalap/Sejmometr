import org.json.JSONException;

import java.io.IOException;

/**
 * Created by przemek on 17.12.16.
 */
public class Tester {







    public static void main(String[] args) throws IOException, JSONException {
        DataDownloader dd=new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=7");

        Parliament parliament = new Parliament();
        //testujBudowanieParlamentu(dd, parliament);
        //testujPobieranieSumyWydatków(dd, parliament);
        testujPobieranieWydatku(dd);



    }

    private static void testujPobieranieWydatku(DataDownloader dd) throws IOException, JSONException {


        System.out.println(dd.downloadExpense("70", "2"));
    }

    private static void testujBudowanieParlamentu(DataDownloader dd, Parliament parliament) throws IOException, JSONException {


        dd.downloadParties(parliament);
        System.out.println("pobrałem " + parliament.showParties().size() + "partii");
        int k=1;
        int j=1;
        for (Object obj : parliament.showParties()) {
            PoliticalParty o = (PoliticalParty) obj;
            System.out.println("Partia "+k+ o.getName());
            int g=1;
            for( Politican p:o.getPoliticans()){
                System.out.println( g+ ". Polityk: " + p.getName() +"("+j);
                g++;
                j++;
            }

        }


    }

    private static void testujPobieranieSumyWydatków(DataDownloader dd, Parliament parliament) throws IOException, JSONException {

        int perc=1;
        int j=1;
        for (Object obj : parliament.showParties()) {
            PoliticalParty o = (PoliticalParty) obj;
          //  System.out.println("Partia "+k+ o.getName());
            //int g=1;
            for( Politican p:o.getPoliticans()){
              //  System.out.println( g+ ". Polityk: " + p.getName() +"("+p.getID()+")");
                System.out.println( dd.sumofExpenses(p.getID()));

                System.out.println(perc*100/(parliament.showPoliticans().size())+"%");
                perc+=1.0;
                //g++;
               // j++;
            }

        }


    }


}

