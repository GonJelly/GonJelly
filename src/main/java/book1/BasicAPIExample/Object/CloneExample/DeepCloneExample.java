package basicAPIExample.Object.CloneExample;

public class DeepCloneExample {

    public static void main(String[] args) {

        DeepClone original = new DeepClone("admin","관리자",39,new int[] {90,60,80}, new Car("소나타"));

        DeepClone cloned = original.getDeepClone();
        cloned.score[0] = 100;
        cloned.car.model = "그랜져";

        System.out.println("복제 객체의 필드값");
        System.out.println("id : " + cloned.id);
        System.out.println("name : " + cloned.name);
        System.out.println("age : " + cloned.age);
        for(int score : cloned.score){
            System.out.println("score : " + score);
        }
        System.out.println("car : " + cloned.car.model);

        System.out.println();

        System.out.println("원본 객체의 필드값");
        System.out.println("id : " + original.id);
        System.out.println("name : " + original.name);
        System.out.println("age : " + original.age);
        for(int score : original.score){
            System.out.println("score : "+ score);
        }
        System.out.println("car : " + original.car.model);

    }

}
