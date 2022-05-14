package book1.BasicAPIExample.System.getEnvExample;

public class SystemEnvExample {

    public static void main(String[] args) {

        String value = System.getenv("JAVA_HOME");
        System.out.println(value);

    }
}
