package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.IRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    private final IRepository<Employee> employeeIRepository = new EmployeeRepository();

    @GetMapping("/employees")
    public String allEmployees(Model model) {
        List<Employee> employees = employeeIRepository.getAllEntities();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/singleEmployee")
    public String SingleEmployee() {
        return "singleEmployee";
    }

    @PostMapping("/singleEmployee")
    public String SingleDepartment(@RequestParam("id") int id, Model model) {
        Employee employee = employeeIRepository.getSingleById(id);
        model.addAttribute("employee", employee);
        if (employee == null) {
            model.addAttribute("search", true);
        }
        return "/singleEmployee";
    }

    @GetMapping("/createEmployee")
    public String CreateEmployee(Model model, @RequestParam("success") String success) {
        model.addAttribute("success", success);
        return "createEmployee";
    }

    @PostMapping("/createEmployee")
    public String CreateEmployee(Model model, @RequestParam("empName") String empName, @RequestParam("job") String job, @RequestParam("manager") int manager, @RequestParam("hireDate") String hireDate,
                                 @RequestParam("salary") int salary, @RequestParam("commission") Optional<Integer> commission, @RequestParam("depID") int depID) {
        String jobUp=job.toUpperCase();
        String empNameUp=empName.toUpperCase();
        Employee employee = new Employee(empNameUp, jobUp, manager, hireDate, salary, commission, depID);
        Boolean created = employeeIRepository.create(employee);
        if (created) {
            return new String("redirect:/createEmployee?success=true");
        } else {
            model.addAttribute("success", "nope");
            return "createEmployee";
        }
    }

    @GetMapping("/employeesByDepartment")
    public String employeesByDepartment(){
        return "employeesByDepartment";
    }

    @PostMapping("/employeesByDepartment")
    public String employeesByDepartment(@RequestParam("depName") String depName, Model model){
        List<Employee> empByDep= employeeIRepository.entitiesByName(depName);
        model.addAttribute("employees", empByDep);
        return "employeesByDepartment";
    }


}
