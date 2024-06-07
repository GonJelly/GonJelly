package book2.StreamExample.chapter5;

public class Student {

    private String name; // 이름
    private int score; // 점수

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
