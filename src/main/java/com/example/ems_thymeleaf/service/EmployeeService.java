package com.example.ems_thymeleaf.service;

import com.example.ems_thymeleaf.dto.EmployeeDepartmentDto;
import com.example.ems_thymeleaf.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<Employee> lists();

    void save(Employee employee);

    Employee findById(Integer emplyee_id);

    void update(Employee employee);

    void delete(Integer emplyee_id);

    List<EmployeeDepartmentDto> search(Integer emplyee_id, String employee_name, String department, String address);

    boolean isEmployeeValid(Integer emplyee_id);


    boolean isBirthDateValid(LocalDate birth_date);

    List<EmployeeDepartmentDto> getEmployeeWithDepartments();

    List<Department> findDepartments();

    List<Position> findPosition();

    boolean isPasswordValid(Integer emplyee_id, String employeePassword);

    void generateDailyAttendance(Integer emplyee_id, int year, int month);

    List<Attendance> getAllAttendances(Integer emplyee_id);

    List<Attendance> getMoreAttendances(Integer emplyee_id, Integer pageNum, Integer pageSize);

    List<Attendance> searchDate(Integer year, Integer month, Integer day, Integer emplyee_id);

    void updateAttendance(Attendance attendance);


}
