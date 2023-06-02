package book2.CollectionExample.chapter5;

public class Student {

    private int sno; // 학번
    private String name; // 이름
    private int  age; // 나이

    public Student(int sno, String name) {
        this.sno = sno;
        this.name = name;
    }

    // 학번과 이름이 같으면 같은 객체로 볼 수 있도록 hashCode() 와 equals() 메소드를 재정의
    @Override
    public int hashCode() {
        return name.hashCode() + sno;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Student) {
            Student student = (Student) obj;
            return student.sno == sno && student.name.equals(name);
        } else {
            return false;
        }
    }
}
