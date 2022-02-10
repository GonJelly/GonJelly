package basicAPIExample.CloneExample;

public class ThinCloneExample {

    public static void main(String[] args) {

        ThinClone original = new ThinClone("박준영", "PARK", "1234", 28, true);

        ThinClone clone = original.getThinClone();
        clone.password = "456789";


        System.out.println("[복제 객체의 필드값]");
        System.out.println("name :"    + clone.name);
        System.out.println("id :"      + clone.id);
        System.out.println("passWord :"+ clone.password);
        System.out.println("age :"     + clone.age);
        System.out.println("adult :"   + clone.adult);

        System.out.println();

        System.out.println("[원본 객체의 필드값]");
        System.out.println("name :"    + original.name);
        System.out.println("id :"      + original.id);
        System.out.println("password :"+ original.password);
        System.out.println("age :"     + original.age);
        System.out.println("adult :"   + original.adult);
    }
}
