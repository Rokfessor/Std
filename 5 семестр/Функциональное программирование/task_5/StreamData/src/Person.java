import java.text.SimpleDateFormat;
import java.util.Date;


public class Person {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    public String name;
    public String secondname;
    public String surname;
    public String gender;
    public Date date;
    public int age;
    public String phoneNumber;
    public String login;
    public String password;
    public String email;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", secondname='" + secondname + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", date=" + formatter.format(date) +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
