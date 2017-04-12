import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by przemek on 22.12.16.
 */
public class QueryInterpreterTest {

    @Test
    public void interpretTest() throws IOException, JSONException {
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

        assertEquals(q.interpret(rd),"Poseł Andrzej Adamczyk: \n" +
                "Suma wydatków wynosi\n" +
                new Double(50287.98 +90.00+19312.00+0.00+6958.64
                        +143.75
                        +714.19
                        +0.00
                        +35103.60
                        +228.95
                        +15952.94
                        +909.51
                        +0.00
                        +2763.86
                        +4059.22
                        +1825.17
                        +0.00
                        +896.93
                        +2257.75
                        +4667.92

                        +49665.90
                        +0.00
                        +17904.00
                        +0.00
                        +7551.21
                        +127.56
                        +1112.03
                        +0.00
                        +33432.00
                        +40.00
                        +13557.50
                        +810.58
                        +0.00
                        +4188.70
                        +371.64
                        +1260.13
                        +0.00
                        +896.93
                        +2487.75
                        +4090.99



                        ).toString()+" W tym \n" +
                new Double(19312.0+17904.0).toString() );










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
    }
}