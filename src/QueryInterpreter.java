/**
 * Created by przemek on 21.12.16.
 */
public class QueryInterpreter implements IQueryInterpreter {

    private final RequestedData requestedData;
    private DataDownloader dataDownloader;

    public QueryInterpreter(RequestedData rd){
        this.requestedData=rd;
    }

    public String interpret(){

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
                out.append("Średnia wydatków partii ").
                        append(requestedData.party).
                        append(" wynosi:\n").
                        append(dataDownloader.downloadAverageOfParty(requestedData.party)).
                        append("\n");

            if(requestedData.wantsListofTravellersTo_) {
            }
            if(requestedData.wantsTheLongestTravel){

            }
            if(requestedData.wantsTheMostExpensiveTravel){

            }
            if(requestedData.wantsTheGreatestTraveller){
                
            }


        }
    }
}
