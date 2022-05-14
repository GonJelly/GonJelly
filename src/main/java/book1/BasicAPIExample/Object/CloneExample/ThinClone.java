package basicAPIExample.Object.CloneExample;

public class ThinClone implements Cloneable{

    public String id = "";
    public String name = "";
    public String password = "";
    public int age = 0;
    public boolean adult = false;

    public ThinClone(String name, String id, String passWord, int age, boolean adult) {
        this.name = name;
        this.id = id;
        this.password = passWord;
        this.age = age;
        this.adult = adult;
    }

    public ThinClone getThinClone(){
        ThinClone origin = null;

        try {
            origin = (ThinClone) clone();
        } catch (CloneNotSupportedException e){ }
        return origin;
    }
}
