package com.example.demo.repositories;

import com.example.demo.models.Employee;
import com.example.demo.utility.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IRepository<Employee> {

    @Override
    public List<Employee> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Employee> allEmployees = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee temp = newEmployeeFromResultSet(rs);
                allEmployees.add(temp);
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allEmployees;
    }

    @Override
    public Employee getSingleById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            rs.next();
          Employee employee = newEmployeeFromResultSet(rs);
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Employee entity) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO employees(`employee_name`, `job`, `manager`, `hiredate`, `salary`, `commission`, `department_number`) VALUES (?,?,?,?,?,?,?)");
            pstm.setString(1, entity.getEmpName());
            pstm.setString(2, entity.getJob());
            pstm.setInt(3, entity.getManager());
            pstm.setDate(4, Date.valueOf(entity.getHireDate()));
            pstm.setInt(5, entity.getSalary());
            pstm.setInt(6, entity.getCommission());
            pstm.setInt(7, entity.getDepID());

            pstm.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Employee> entitiesByName(String depName) {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Employee> empInDep = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT employees.* FROM employees, departments WHERE employees.department_number = departments.department_number AND departments.department_name =?");
            pstmt.setString(1, depName);
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Employee temp = newEmployeeFromResultSet(rs);
                empInDep.add(temp);
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return empInDep;
    }

    private Employee newEmployeeFromResultSet(ResultSet rs) {

        try {
            Employee temp = new Employee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8)
            );
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}