package book2.GenericExample.chapter1;

/**
 * 타입 파라미터는 변수명과 동일하게 이름을 지정할 수 있지만
 * 편의상 T, K, V 등으로 표기한다.
 * */
public class Box2<T> {

   private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
