package pl.lodz.eksoc.userlistmanagement.domain;

import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private String email;

    public User() {};
    public User(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "imię: " + firstName +
                ", nazwisko: " + lastName +
                ", wiek: " + age +
                ", email: " + email;
    }
}
