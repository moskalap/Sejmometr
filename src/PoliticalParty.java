import java.util.LinkedList;
import java.util.List;

/**
 * Created by przemek on 17.12.16.
 */
public class PoliticalParty{
    private final String id;
    private final String name;

    private List<Politican> members=new LinkedList<>();

    PoliticalParty(String id, String name){
        this.id=id;
        this.name=name;
    }

    public String getName() {
        return name;
    }


    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        PoliticalParty that = (PoliticalParty) other;
        return this.id.equals(that.id);
    }

    public void addPolitical(Politican politican){
        this.members.add(politican);
    }
    public List<Politican> getPoliticans(){
        return members;
    }


    public String getID() {
        return id;
    }
}
