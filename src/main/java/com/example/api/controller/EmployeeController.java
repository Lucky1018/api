package com.example.api.controller;

import com.example.api.model.Employee;
import com.example.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") final Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        return null;

    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);

    }
    
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") final Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee) {
        Optional<Employee> e = employeeService.getEmployee(id);
        if (e.isPresent()) {
            Employee currentEmployee = e.get();

            String firstName = employee.getFirstName();
            if (firstName != null) {
                currentEmployee.setFirstName(firstName);
            }

            String lastName = employee.getLastName();
            if (lastName != null) {
                currentEmployee.setLastName(lastName);
            }

            String mail = employee.getMail();
            if (mail != null) {
                currentEmployee.setMail(mail);
            }

            String password = employee.getPassword();
            if (password != null) {
                currentEmployee.setPassword(password);
            }

            return employeeService.saveEmployee(currentEmployee);
        } else {
            return null;
        }
    }
}
