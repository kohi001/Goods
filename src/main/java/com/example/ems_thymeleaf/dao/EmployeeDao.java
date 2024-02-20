package com.example.ems_thymeleaf.dao;

import com.example.ems_thymeleaf.dto.EmployeeDepartmentDto;
import com.example.ems_thymeleaf.entity.Attendance;
import com.example.ems_thymeleaf.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {
    List<Employee> lists();


    void save(Employee employee);

    Employee findById(Integer emplyee_id);

    void update(Employee employee);

    void delete(Integer emplyee_Id);

    List<EmployeeDepartmentDto> search(@Param("emplyee_id") Integer emplyee_id,
                          @Param("employee_name") String employee_name,
                          @Param("department") String department,
                          @Param("address") String address);


    List<EmployeeDepartmentDto> getEmployeeWithDepartments();

    List<Attendance> getAllAttendances(Integer emplyeeId);

    boolean hasAttendanceRecords(Integer emplyeeId, int year, int month);

    void clock(Attendance attendance);

    List<Attendance> getAttendancesInRange(Integer emplyeeId, int startRow, int endRow);

    List<Attendance> searchDate(Integer year, Integer month, Integer day, Integer emplyeeId);

    void updateAttendance(Attendance attendance);
}
