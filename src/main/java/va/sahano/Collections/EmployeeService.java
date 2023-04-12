package va.sahano.Collections;


import org.springframework.stereotype.Service;
import va.sahano.Collections.exception.EmployeeAlreadyAddedException;
import va.sahano.Collections.exception.EmployeeNotFoundException;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    public Employee add(String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        if (employees.containsKey(person.getFullName())) {
            throw new EmployeeAlreadyAddedException("Добавляемый сотрудник уже есть в списке");
        }
        employees.put(person.getFullName(), person);
        return person;
    }

    public Employee remove (String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        if (employees.containsKey(person.getFullName())) {
            return employees.remove(person.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }


    public Employee find (String firstName, String lastName) {
        Employee person = new Employee(firstName, lastName);
        if (employees.containsKey(person.getFullName())) {
            return employees.get(person.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }


    public List<Employee> showAll() {
        return (List<Employee>) Collections.unmodifiableCollection(employees.values());
    }


}
