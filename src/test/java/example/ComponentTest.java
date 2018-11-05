package example;

import example.models.Address;
import example.models.Employee;
import example.models.Team;
import org.junit.Test;
import utils.object.compare.ObjectDeepCompare;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class ComponentTest {


    @Test
    public void sameNameAndSalaryshouldEqual() {
        //given
        ObjectDeepCompare compare = new ObjectDeepCompare();
        Employee oldBoss = new Employee("Big Boss", 100);
        Employee newBoss = new Employee("Big Boss", 100);
        Employee largerBoss = new Employee("Large Boss", 100);

        assertThat(compare.compare(oldBoss, newBoss).isEmpty()).isEqualTo(true);
        assertThat(compare.compare(oldBoss, largerBoss).isEmpty()).isEqualTo(false);

        List<Employee> oldBossList = new ArrayList<>();
        oldBossList.add(newBoss);
        oldBossList.add(oldBoss);

        List<Employee> newBossList = new ArrayList<>();
        newBossList.add(newBoss);
        newBossList.add(oldBoss);

        List<Employee> largeBossList = new ArrayList<>();
        largeBossList.add(largerBoss);
        largeBossList.add(oldBoss);
        assertThat(compare.compare(oldBossList, newBossList).isEmpty()).isEqualTo(true);
        assertThat(compare.compare(oldBossList, largeBossList).isEmpty()).isEqualTo(false);
    }

    @Test
    public void complexObjectShowEqual() {
        ObjectDeepCompare compare = new ObjectDeepCompare();
        Address address1 = new Address("beijing");
        Address address2 = new Address("beijing");
        Address address3 = new Address("shanghai");
        Employee oldBoss = new Employee("Big Boss", 100, address1);
        Employee newBoss = new Employee("Big Boss", 100, address2);
        Employee newBoss2 = new Employee("Big Boss", 100, address3);
        Employee largerBoss = new Employee("Large Boss", 100, address1);

        assertThat(compare.compare(oldBoss, newBoss).isEmpty()).isEqualTo(true);
        assertThat(compare.compare(oldBoss, newBoss2).isEmpty()).isEqualTo(false);
        assertThat(compare.compare(oldBoss, largerBoss).isEmpty()).isEqualTo(false);

        Team team1 = new Team(oldBoss);
        Team team2 = new Team(newBoss);
        Team team3 = new Team(largerBoss);
        assertThat(compare.compare(team1, team2).isEmpty()).isEqualTo(true);
        assertThat(compare.compare(team1, team3).isEmpty()).isEqualTo(false);

        Employee[] employees1 = new Employee[]{oldBoss, newBoss};
        Employee[] employees2 = new Employee[]{oldBoss, newBoss};
        Employee[] employees3 = new Employee[]{oldBoss, largerBoss};
        assertThat(compare.compare(employees1, employees2).isEmpty()).isEqualTo(true);
        assertThat(compare.compare(employees1, employees3).isEmpty()).isEqualTo(false);

    }
}