package book2.CollectionExample.chapter8;

public class Fruit implements Comparable<Fruit> {

    private int brix;
    private String name;
    private int size;

    public Fruit(int brix, String name) {
        this.brix = brix;
        this.name = name;
    }

    public int getBrix() {
        return brix;
    }

    public void setBrix(int brix) {
        this.brix = brix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Fruit o) {
        if(brix < o.brix) { return -1; }
        else if(brix == o.brix) { return 0; }
        else return 1;
    }
}
