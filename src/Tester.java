import org.json.JSONException;

import javax.xml.crypto.Data;
import java.io.IOException;

/**
 * Created by przemek on 17.12.16.
 */
public class Tester {







    public static void main(String[] args) throws IOException, JSONException {
        //testDownloadingofPartyAvg();
testDownloadTMExpesTrav();
    }

    private static void testDownloadingTheMostTrav() throws IOException, JSONException {
        RequestedData rd=new RequestedData();
        rd.term="7";
        rd.politic="";
        rd.party="Prawo i Sprawiedliwość";
        //rd.expense;
        rd.wantsPolitican=false;
        rd.wantsParty=true;
        rd.wantsAverage=false;
        rd.wantsTheLongestTravel=true;
        rd.wantsTheMostExpensiveTravel=true;
        rd.wantsListofTravellersTo_=true;
        rd.politicanid="3";
        rd.country="Chiny";
        rd.wantsTheGreatestTraveller=false;
        rd.wantsAll=false;
        rd.expenseID="3";


        QueryInterpreter q= new QueryInterpreter(rd);
        System.out.println(q.interpret(rd));

    }


    private static void testDownloadTMExpesTrav() throws IOException, JSONException {
        RequestedData rd=new RequestedData();
        rd.term="7";
        rd.politic="";
        rd.party="Prawo i Sprawiedliwość";
        //rd.expense;
        rd.wantsPolitican=false;
        rd.wantsParty=true;
        rd.wantsAverage=true;
        rd.wantsTheLongestTravel=true;
        rd.wantsTheMostExpensiveTravel=true;
        rd.wantsListofTravellersTo_=true;
        rd.politicanid="3";
        rd.country="Chiny";
        rd.wantsTheGreatestTraveller=true;
        rd.wantsAll=false;
        rd.expenseID="3";


        QueryInterpreter q= new QueryInterpreter(rd);
        System.out.println(q.interpret(rd));
    }







    private static void testDownloadingListofTravellersTo() throws IOException, JSONException {
        RequestedData rd=new RequestedData();
        rd.term="7";
        rd.politic="";
        rd.party="Platforma Obywatelska";
        //rd.expense;
        rd.wantsPolitican=false;
        rd.wantsParty=true;
        rd.wantsAverage=false;
        rd.wantsTheLongestTravel=false;
        rd.wantsTheMostExpensiveTravel=false;
        rd.wantsListofTravellersTo_=true;
        rd.politicanid="3";
        rd.country="Chiny";
        rd.wantsTheGreatestTraveller=false;
        rd.wantsAll=false;
        rd.expenseID="3";


        QueryInterpreter q= new QueryInterpreter(rd);
        System.out.println(q.interpret(rd));

    }





    private static void testDownloadingofPartyAvg() throws IOException, JSONException {
        RequestedData rd=new RequestedData();
        rd.term="7";
        rd.politic="";
        rd.party="Prawo i Sprawiedliwość";
        //rd.expense;
        rd.wantsPolitican=false;
        rd.wantsParty=true;
        rd.wantsAverage=true;
        rd.wantsTheLongestTravel=false;
        rd.wantsTheMostExpensiveTravel=false;
        rd.wantsListofTravellersTo_=false;
        rd.politicanid="3";
        rd.wantsTheGreatestTraveller=false;
        rd.wantsAll=false;
        rd.expenseID="3";


        QueryInterpreter q= new QueryInterpreter(rd);
        System.out.println(q.interpret(rd));

    }

private static void single() throws IOException, JSONException {
    Parliament parliament;
    RequestedData rd=new RequestedData();
    rd.term="7";
    rd.politic="Andrzej Adamczyk";
    rd.party="PIS";
    rd.expense=2;
    rd.wantsPolitican=true;
    rd.wantsParty=false;
    rd.wantsAverage=false;
    rd.wantsTheLongestTravel=false;
    rd.wantsTheMostExpensiveTravel=false;
    rd.wantsListofTravellersTo_=false;
    rd.politicanid="3";
   // rd.country=true;
    rd.wantsTheGreatestTraveller=false;
    rd.wantsAll=false;
    rd.expenseID="3";


    QueryInterpreter q=new QueryInterpreter(rd);
    System.out.println(q.interpret(rd));
}

    private static void testDown() throws IOException, JSONException {
        Parliament parliament=new Parliament();
        DataDownloader dd=new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");
        dd.downloadParties(parliament);
        int perc=1;
        for (Object obj : parliament.showParties()) {
            PoliticalParty o = (PoliticalParty) obj;
            //  System.out.println("Partia "+k+ o.getName());
            int g = 1;
            for (Politican p : o.getPoliticans()) {
                System.out.println(g + ". Polityk: " + p.getName() + "(" + p.getID() + ")");
                dd.read(p.getID());
                //System.out.println( sum);

                System.out.println(perc * 100 / (parliament.showPoliticans().size()) + "%");
                perc += 1.0;
                g++;
                //j++;
            }

        }
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

    private static void buildResources() throws IOException, JSONException {

        DataDownloader dd=new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");
        Parliament parliament = new Parliament();
        dd.downloadParties(parliament);


        int perc=1;
        int j=1;
        for (Object obj : parliament.showParties()) {
            PoliticalParty o = (PoliticalParty) obj;
            //  System.out.println("Partia "+k+ o.getName());
            int g=1;
            for( Politican p:o.getPoliticans()){
                System.out.println( g+ ". Polityk: " + p.getName() +"("+p.getID()+")");
                dd.saveObj(p.getID(), "8");

                System.out.println(perc*100/(parliament.showPoliticans().size())+"%");
                perc+=1.0;
                g++;
                j++;
            }

        }





        DataDownloader dd7=new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=7");
        Parliament parliament7 = new Parliament();
        dd7.downloadParties(parliament7);


        perc=1;
        j=1;
        for (Object obj : parliament7.showParties()) {
            PoliticalParty o = (PoliticalParty) obj;
            //  System.out.println("Partia "+k+ o.getName());
            int g=1;
            for( Politican p:o.getPoliticans()){
                System.out.println( g+ ". Polityk: " + p.getName() +"("+p.getID()+")");
                dd7.saveObj(p.getID(), "7");

                System.out.println(perc*100/(parliament7.showPoliticans().size())+"%");
                perc+=1.0;
                g++;
                j++;
            }

        }










    }














    private static void testujPobieranieSumyWydatków(DataDownloader dd, Parliament parliament) throws IOException, JSONException {

        double sum=0.0;
        int perc=1;
        int j=1;
        for (Object obj : parliament.showParties()) {
            PoliticalParty o = (PoliticalParty) obj;
         //  System.out.println("Partia "+k+ o.getName());
              int g=1;
            for( Politican p:o.getPoliticans()){
                System.out.println( g+ ". Polityk: " + p.getName() +"("+p.getID()+")");
               dd.saveObj(p.getID(), "8");
                System.out.println( sum);

                System.out.println(perc*100/(parliament.showPoliticans().size())+"%");
                perc+=1.0;
              g++;
               j++;
            }

        }


    }


}

