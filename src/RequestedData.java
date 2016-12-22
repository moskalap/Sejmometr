/**
 * Created by przemek on 16.12.16.
 *
 *
 * 'paczka' która zawiera dane, które będziemy chcieli od interpretera
 */
public class RequestedData {
    public String term;
    public String politic;
    public String party;
    public int expense;


    public boolean wantsPolitican;
    public boolean wantsParty;
    public boolean wantsAverage;
    public boolean wantsTheLongestTravel;
    public boolean wantsTheMostExpensiveTravel;
    public boolean wantsListofTravellersTo_;
    public String politicanid;
    public String country;
    public boolean wantsTheGreatestTraveller;
    public boolean wantsAll;
    public String expenseID;
    public String partyID;

    public RequestedData(){}
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
