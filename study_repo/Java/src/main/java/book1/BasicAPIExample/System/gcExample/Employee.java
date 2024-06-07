package book1.BasicAPIExample.System.gcExample;

public class Employee {

    public String name = "";
    public int age = 0;
    public String divition = "";
    public String gender = "";
    public int eno = 0;

    public Employee(int eno) {
        this.eno = eno;
    }

    public Employee(String name, int age, String divition, String gender) {
        this.name = name;
        this.age = age;
        this.divition = divition;
        this.gender = gender;
    }

}
