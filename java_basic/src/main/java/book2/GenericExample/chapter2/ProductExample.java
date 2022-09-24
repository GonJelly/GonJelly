package book2.GenericExample.chapter2;

public class ProductExample {

    /** 제네릭은 멀테 파라미터를 가질 수 있다.
     * 파라미터 구분은 콤마(,)로 하면된다.
     * */
    public static void main(String[] args) {

        Product<Tv, String> product1 = new Product<Tv, String>();
        product1.setKind(new Tv());
        product1.setModel("스마트TV");
        Tv tv = product1.getKind();
        String tvModel = product1.getModel();

        Product<Cat, String> product2 = new Product<Cat, String>();
        product2.setKind(new Cat());
        product2.setModel("삼색고양이");
        Cat cat = product2.getKind();
        String catName = product2.getModel();
    }
}
