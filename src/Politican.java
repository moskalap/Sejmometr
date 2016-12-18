/**
 * Created by przemek on 16.12.16.
 */
public class Politican {

    private int id;
    private String firstname;
    private String lastname;

    Politican (int id, String firstname, String lastname){
        this.id=id;
        this.firstname=firstname;
        this.lastname=lastname;

    }


    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Politican that = (Politican) other;
        return this.id==that.id;

    }


    public String getID() {
        return new Integer(id).toString();
    }

    public String getName() {
        return firstname+" "+lastname;
    }
}
