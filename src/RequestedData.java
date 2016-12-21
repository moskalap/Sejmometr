/**
 * Created by przemek on 16.12.16.
 *
 *
 * 'paczka' która zawiera dane, które będziemy chcieli od interpretera
 */
public class RequestedData {
    public final String term;
    public final String politic;
    public final String party;
    public final int expense;


    public final boolean wantsPolitican;
    public final boolean wantsParty;
    public final boolean wantsAverage;
    public final boolean wantsTheLongestTravel;
    public final boolean wantsTheMostExpensiveTravel;
    public final boolean wantsListofTravellersTo_;

    public RequestedData(String term, String politic, String party, int expense, boolean wantsPolitican, boolean wantsParty, boolean wantsAverage, boolean wantsTheLongestTravel, boolean wantsTheMostExpensiveTravel, boolean wantsListofTravellersTo_) {
        this.term = term;
        this.politic = politic;
        this.party = party;
        this.expense = expense;
        this.wantsPolitican = wantsPolitican;
        this.wantsParty = wantsParty;
        this.wantsAverage = wantsAverage;
        this.wantsTheLongestTravel = wantsTheLongestTravel;
        this.wantsTheMostExpensiveTravel = wantsTheMostExpensiveTravel;
        this.wantsListofTravellersTo_ = wantsListofTravellersTo_;
    }
}
