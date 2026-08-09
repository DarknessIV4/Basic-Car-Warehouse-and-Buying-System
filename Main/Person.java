import java.io.*;
import java.util.ArrayList;

public class Person {

    String name;
    byte age;
    String status;

    public Person() {

    }

    public Person(String name, byte age, String status) {
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public void CallPeople () {

        System.out.println(name + ", " + age + " | " + status);
    }

    public void savePeople (ArrayList<Person> People) {

        try {

        PrintWriter PeopleWriter = new PrintWriter(new FileWriter("People.txt"));
        for (Person P : People) {PeopleWriter.println(P.name + ", " + P.age + ", " + P.status);}

        PeopleWriter.close();

        } catch (IOException e) {System.out.println(e);} 
    } 
}

