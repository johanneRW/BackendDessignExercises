package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.IRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        return new String("redirect:/singleEmployee");
    }

    @GetMapping("/createEmployee")
    public String CreateEmployee() {
        return "createEmployee";
    }

    @PostMapping("/createEmployee")
    public String CreateEmployee(@RequestParam("empName") String empName, @RequestParam("job") String job, @RequestParam("manger") int manger, @RequestParam("hireDate") String hireDate,
                                 @RequestParam("salary") int salary, @RequestParam("commission") int commission, @RequestParam("depID") int depID) {
        String jobUp=job.toUpperCase();
        String empNameUp=empName.toUpperCase();
        Employee employee = new Employee(empNameUp, jobUp, manger, hireDate, salary, commission, depID);
        Boolean created = employeeIRepository.create(employee);

        return new String("redirect:/createEmployee");
    }

    @GetMapping("/employeesByDepartment")
    public String employeesByDepartment(){
        return "employeesByDepartment";
    }

    @PostMapping("/employeesByDepartment")
    public String employeesByDepartment(@RequestParam("depName") String depName, Model model){
        List<Employee> empByDep= employeeIRepository.employeesByDepartment(depName);
        model.addAttribute("employees", empByDep);
        return "employeesByDepartment";
    }


}
