package example.models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Employee> employees = new ArrayList<>();
    public Team(Employee employee) {
        this.employees.add(employee);
    }
}
