import java.util.Collection;
import java.util.HashMap;

/**
 * Created by przemek on 17.12.16.
 */
public class Parliament {
    HashMap<String, PoliticalParty> parties=new HashMap<>();
    HashMap<String, Politican> politicans=new HashMap<>();
    HashMap<String, PoliticalParty> partiesByName=new HashMap<>();

    public Parliament(){}

    public void addParty(PoliticalParty party){
        if(!parties.containsValue(party)) {
            parties.put(party.getID(), party);
            partiesByName.put(party.getName(),party);
        }
    }

    public void addPolitican(Politican politican, String partyID){
        politicans.put(politican.getID(), politican);
        parties.get(partyID).addPolitical(politican);
    }
    public PoliticalParty getParty(String id){
        if(!parties.containsKey(id)) throw new IllegalArgumentException("brak takiej partii");
        return parties.get(id);
    }
    public Collection<PoliticalParty> showParties(){
        return parties.values();
    }


    public Collection<Politican> showPoliticans() {
        return politicans.values();
    }
    public PoliticalParty getPartybyName(String name){
        return partiesByName.get(name);

    }

}
