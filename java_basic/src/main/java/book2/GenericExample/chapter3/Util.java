package book2.GenericExample.chapter3;

public class Util {

    public <T> Box<T> boxing(T t) {

        Box<T> box = new Box<T>();
        box.set(t);

        return box;
    }

}
