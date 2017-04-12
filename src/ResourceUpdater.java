import org.json.JSONException;

import java.io.IOException;

/**
 * Created by przemek on 11.01.17.
 */
public class ResourceUpdater {

    public static void buildResources() throws IOException, JSONException {

        DataDownloader dd = new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");
        Parliament parliament = new Parliament();
        dd.term = "8";
        dd.downloadParties(parliament);
        dd.savePoliticanlist(parliament);

        int perc = 1;
        int j = 1;
        for (Object obj : parliament.showParties()) {
            PoliticalParty o = (PoliticalParty) obj;

            int g = 1;
            for (Politican p : o.getPoliticans()) {

                dd.saveObj(p.getID(), "8");


                perc += 1.0;
                g++;
                j++;
            }

        }


        DataDownloader dd7 = new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=7");
        dd7.term = "7";
        Parliament parliament7 = new Parliament();
        dd7.downloadParties(parliament7);

        dd7.savePoliticanlist(parliament7);


        perc = 1;
        j = 1;
        for (Object obj : parliament7.showParties()) {
            PoliticalParty o = (PoliticalParty) obj;

            int g = 1;
            for (Politican p : o.getPoliticans()) {
                System.out.println(g + ". Polityk: " + p.getName() + "(" + p.getID() + ")");
                dd7.saveObj(p.getID(), "7");

                System.out.println(perc * 100 / (parliament7.showPoliticans().size()) + "%");
                perc += 1.0;
                g++;
                j++;
            }

        }


    }


}
