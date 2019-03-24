package Model;

import java.util.ArrayList;

enum PersonType {
    ADMIN, TEACHER, STUDENT;

    public String toString() {
        if (this.equals(PersonType.ADMIN))
            return "Admin";
        else if (this.equals(PersonType.TEACHER))
            return "Teacher";
        else
            return "Student";
    }
}

public abstract class Person {
    private static ArrayList<Person> people;
    private String username, password;
    private PersonType personType;

    public Person() {
    }

    private static boolean validatePassword(String pass) {
        return pass.length() > 5;
    }

    private static boolean validateUsername(String name) {
        for (Person p : people)
            if (p.getUsername().equals(name))
                return false;
        return true;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public boolean setUsername(String name) {
        if (validateUsername(name)) {
            username = name;
            return true;
        }
        return false;
    }

    public boolean setPassword(String pass) {
        if (validatePassword(pass)) {
            password = pass;
            return true;
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}