import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private double salary;
    private int age;
    private LocalDate joiningDate;
    private double rating;

    public Employee(int employeeId, String name, String department,
            double salary, int age, LocalDate joiningDate, double rating) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
        this.joiningDate = joiningDate;
        this.rating = rating;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return name + " | " + department + " | ₹" + salary + " | age " + age + " | joined " + joiningDate;
    }

    public static void main() {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(1, "Aarav", "Engineering", 120000, 29, LocalDate.of(2021, 3, 15), 4.5),
                new Employee(2, "Anika", "Sales", 95000, 34, LocalDate.of(2019, 7, 1), 3.8),
                new Employee(3, "Bhavya", "Engineering", 135000, 41, LocalDate.of(2015, 1, 10), 4.9),
                new Employee(4, "Chirag", "Marketing", 88000, 25, LocalDate.of(2022, 11, 5), 4.1),
                new Employee(5, "Aditi", "Sales", 110000, 30, LocalDate.of(2020, 6, 20), 4.6),
                new Employee(6, "Aman", "Engineering", 102000, 27, LocalDate.of(2021, 9, 12), 3.9),
                new Employee(7, "Divya", "Marketing", 75000, 23, LocalDate.of(2023, 2, 1), 4.0),
                new Employee(8, "Aryan", "Sales", 140000, 38, LocalDate.of(2016, 4, 18), 4.7)));

        // Exercise: write comparators for

        // 1. Sort by name (natural alphabetical order using a Comparator, not
        // Comparable)
        Collections.sort(employees, (e1, e2) -> {
            return e1.getName().compareTo(e2.getName());
        });
        System.out.println("by name: " + employees);

        // 2. Sort by salary, descending
        Collections.sort(employees, (e1, e2) -> {
            return Double.compare(e2.getSalary(), e1.getSalary());
        });
        System.out.println("salary desc: " + employees);

        // 3. Give preference: name starts with "A" on top AND salary > 100000, sorted
        // by salary descending
        Collections.sort(employees, (e1, e2) -> {
            boolean flag_e1 = (e1.getName().charAt(0) == 'A' && e1.getSalary() > 100000);
            boolean flag_e2 = (e2.getName().charAt(0) == 'A' && e2.getSalary() > 100000);

            if (flag_e1 && flag_e2) {

                return Double.compare(e2.getSalary(), e1.getSalary());
            }
            if (flag_e1 && !flag_e2) {
                return -1;
            }
            if (!flag_e1 && flag_e2) {
                return 1;
            }

            return Double.compare(e2.getSalary(), e1.getSalary());
        });
        System.out.println("3: " + employees);

        // 4. Sort by department, then by name within each department
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getDepartment().equals(e2.getDepartment())) {
                return e1.getName().compareTo(e2.getName());
            }
            return e1.getDepartment().compareTo(e2.getDepartment());
        });
        System.out.println("4: " + employees);

        // 5. Sort by age, ascending
        Collections.sort(employees, (e1, e2) -> {
            return Integer.compare(e1.getAge(), e2.getAge());
        });
        System.out.println("5: " + employees);

        // 6. Sort by joining date, oldest employee (earliest date) first
        Collections.sort(employees, (e1, e2) -> {
            return e1.getJoiningDate().compareTo(e2.getJoiningDate());
        });
        System.out.println("6: " + employees);

        // 7. Sort by rating descending, then by salary descending as a tiebreaker
        Collections.sort(employees, (e1, e2) -> {
            int res = Double.compare(e2.getRating(), e1.getRating());
            if (res != 0) {
                return res;
            }
            return Double.compare(e2.getSalary(), e1.getSalary());
        });
        System.out.println("7: " + employees);
    }
}
