package book2.GenericExample.chapter2;

public class Product<K,V> {

    private K k;
    private V v;

    public K getKind() {
        return k;
    }

    public void setKind(K k) {
        this.k = k;
    }

    public V getModel() {
        return v;
    }

    public void setModel(V v) {
        this.v = v;
    }
}
