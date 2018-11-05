package example.models;


public class Employee {

    private String name;
    private int salary;
    private Address address;

    public Employee(String name,int salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name,int salary,Address address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
    }
}