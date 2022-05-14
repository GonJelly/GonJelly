package basicAPIExample.Object.equalsExample;

public class MemberExample{

    public static void main(String[] args){

        Member obj1 = new Member("blue");
        Member obj2 = new Member("blue");
        Member obj3 = new Member("red");

        if(obj1.equals(obj2)){
            System.out.println("obj1 과 obj2가 동일합니다.");
        } else {
            System.out.println("obj1 과 obj2가 동일하지 않습니다.");
        }

        if(obj1.equals(obj3)){
            System.out.println("obj1 과 obj3가 동일합니다.");
        } else {
            System.out.println("obj1 과 obj3가 동일하지 않습니다.");
        }
    }
}