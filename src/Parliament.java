import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by przemek on 17.12.16.
 */
public class Parliament {
    HashMap<String, PoliticalParty> parties=new HashMap<>();
    HashMap<String, Politican> politicans=new HashMap<>();
    LinkedList<Politican> list=new LinkedList<>();
    HashMap<String, PoliticalParty> partiesByName=new HashMap<>();
    public HashMap<String, Politican> politicansbyName=new HashMap<>();

    public Parliament(){}

    public void addParty(PoliticalParty party){
        if(!parties.containsValue(party)) {
            parties.put(party.getID(), party);
            partiesByName.put(party.getName(),party);
        }

    }

    public void addPolitican(Politican politican, String partyID){
        politicans.put(politican.getID(), politican);
        politicansbyName.put(politican.getName(), politican);
        if(list.contains(politican)) System.out.println(politican.getName());
        list.add(politican);
        parties.get(partyID).addPolitical(politican);
    }
    public PoliticalParty getParty(String name){
        if(!partiesByName.containsKey(name)) throw new IllegalArgumentException("brak takiej partii");
      return this.partiesByName.get(name);
      // parties.get(name);
    }
    public Collection<PoliticalParty> showParties(){
        return parties.values();
    }


    public Collection<Politican> showPoliticans() {
        return politicans.values();
    }
    public LinkedList<Politican> getMembers(){
        return list;
    }
    public PoliticalParty getPartybyName(String name){
        return partiesByName.get(name);

    }

}
