package CollectionExample.chapter4;

public class Member {

    private String name; // 이름
    private int age; // 나이

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member) {
            Member member = (Member) obj;
            return member.name.equals(name) && member.age == age;
        } else {
            return false;
        }
    }
}
