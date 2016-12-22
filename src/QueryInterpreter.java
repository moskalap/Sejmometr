import org.json.JSONException;

import java.io.IOException;

/**
 * Created by przemek on 21.12.16.
 *
 * klasa, która zwraca stringa z danymi, których oczekuje requesteddData.
 */
public class QueryInterpreter implements IQueryInterpreter {

    private RequestedData requestedData;
    private DataDownloader dataDownloader;
    private Parliament parliament;


    public QueryInterpreter(RequestedData rd){
        this.requestedData=rd;

    }

    public String interpret(RequestedData requestedData) throws IOException, JSONException {
        this.requestedData=requestedData;



        dataDownloader=new DataDownloader("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]="+requestedData.term);
        this.parliament=new Parliament();
        dataDownloader.setParliament(this.parliament);
        dataDownloader.downloadParties(this.parliament);

        if(requestedData.wantsPolitican){
           return new StringBuilder().append("Poseł ").
                   append(requestedData.politic).
                   append(": \nSuma wydatków wynosi\n").
                   append(dataDownloader.sumofExpenses(requestedData.politicanid)).
                   append("zł\nW tym \n").
                   append(dataDownloader.downloadExpense(requestedData.politicanid, requestedData.expenseID)).
                   append("zł na\n").append(dataDownloader.getExpenseName(requestedData.expenseID)).toString();
        }
        if(requestedData.wantsParty){
            StringBuilder out= new StringBuilder();
            if(requestedData.wantsAverage)
                out.append("\nSuma wydatków partii ").
                        append(requestedData.party).
                        append(" wynosi:\n").
                        append(dataDownloader.downloadSumOfParty(requestedData.party, parliament)).
                        append("zł\n").
                        append("Średnia wartość wydatków posła partii ").
                        append(requestedData.party).
                        append(" wynosi:\n").
                        append(dataDownloader.downloadAvgOfParty(requestedData.party, parliament)).
                        append("zł\n");

            if(requestedData.wantsListofTravellersTo_) {
                out.append("\nPosłowie z partii ").
                        append(requestedData.party).
                        append("\nktórzy podrózowali do ").
                        append(requestedData.country+"\n");
                        for(Politican politican:dataDownloader.downloadListofTravellersto_(requestedData.country, requestedData.party)) {
                            out.append(politican.getName());
                            out.append("\n");
                        }


            }
            if(requestedData.wantsTheLongestTravel){

                out.append("\nCzłonek partii ").
                        append(requestedData.party).
                        append("\nktóry odbył najdłuższa podróż to\n").
                        append(dataDownloader.downloadTheLongTravel(requestedData.party).getName());



            }
            if(requestedData.wantsTheMostExpensiveTravel){

                out.append("\nCzłonek partii ").
                        append(requestedData.party).
                        append("\nktóry odbył najdroższą podróż to\n").
                        append(dataDownloader.downloadTheExpensiverTraveller(requestedData.party));

            }
            if(requestedData.wantsTheGreatestTraveller){
                out.append("\nCzłonek partii ").
                        append(requestedData.party).
                        append("\nktóry najwięcej podróżował to\n").
                        append(dataDownloader.downloadTheGreatestTrveller(requestedData.party));


            }


            return out.toString();


        }
        if(requestedData.wantsAll){

            StringBuilder out= new StringBuilder();
            if(requestedData.wantsAverage)
                out.append("\nŚrednia wydatków wszyskich posłów wynosi").
                        append(dataDownloader.downloadAverageOf(requestedData.party)).
                        append("\n\n\n\n");

            if(requestedData.wantsListofTravellersTo_) {
                out.append("\nPosłowie, ktorzy podróżowali do ").
                        append(requestedData.country);
                for(Politican politican:dataDownloader.downloadListofTravellersto_(requestedData.country, requestedData.party)) {
                    out.append(politican.getName());
                    out.append("\n");
                }


            }
            if(requestedData.wantsTheLongestTravel){

                out.append("\nCzłonek partii ").
                        append(requestedData.party).
                        append("\nktóry odbył najdłuższa podróż to\n").
                        append(dataDownloader.downloadTheLongTravel(requestedData.party));



            }
            if(requestedData.wantsTheMostExpensiveTravel){

                out.append("\nCzłonek partii ").
                        append(requestedData.party).
                        append("\nktóry odbył najdroższą podróż to\n").
                        append(dataDownloader.downloadTheExpensiverTraveller(requestedData.party));

            }
            if(requestedData.wantsTheGreatestTraveller){
                out.append("\nCzłonek partii ").
                        append(requestedData.party).
                        append("\nktóry najwięcej podróżował to\n").
                        append(dataDownloader.downloadTheGreatesTrveller(requestedData.party));


            }


            return out.toString();




        }
        return "error";
    }
}
