package basicAPIExample.Object.hashcodeExample;

public class Key {

    private int number;

    public Key(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Key){
            Key compareKey = (Key) obj;
            if(this.number == compareKey.number){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return number;
    }
}
