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

    public String interpret(RequestedData requestedData){
        this.requestedData=requestedData;



        dataDownloader=new DataDownloader(requestedData.term);
        if(requestedData.wantsPolitican){
           return new StringBuilder().append("Poseł ").
                   append(requestedData.politic).
                   append(": \nSuma wydatków wynosi\n").
                   append(dataDownloader.sumofExpenses(requestedData.politicanid)).
                   append(" W tym \n").
                   append(dataDownloader.downloadExpense(requestedData.politicanid, requestedData.expenseID)).toString();
        }
        if(requestedData.wantsParty){
            StringBuilder out= new StringBuilder();
            if(requestedData.wantsAverage)
                out.append("\nŚrednia wydatków partii ").
                        append(requestedData.party).
                        append(" wynosi:\n").
                        append(dataDownloader.downloadAverageOfParty(requestedData.party)).
                        append("\n\n\n\n");

            if(requestedData.wantsListofTravellersTo_) {
                out.append("\nPosłowie z partii ").
                        append(requestedData.party).
                        append("\nktórzy podrózowali do ").
                        append(requestedData.country);
                        for(Politican politican:dataDownloader.downloadListofTravellersto_(String country, String party)) {
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
                for(Politican politican:dataDownloader.downloadListofTravellersto_(String country, String party)) {
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
    }
}
