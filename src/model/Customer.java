package model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String age;
    private String address;
    private String phonenumber;
    private String Email;

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Customer(String name, String age, String address, String phonenumber, String email) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phonenumber = phonenumber;
        Email = email;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
