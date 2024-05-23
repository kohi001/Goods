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

    void delete(Integer emplyee_id);

    List<EmployeeDepartmentDto> search(@Param("emplyee_id") Integer emplyee_id,
                          @Param("employee_name") String employee_name,
                          @Param("department") String department,
                          @Param("address") String address);


    List<EmployeeDepartmentDto> getEmployeeWithDepartments();

    List<Attendance> getAllAttendances(Integer emplyee_id);

    boolean hasAttendanceRecords(@Param("emplyee_id") Integer emplyee_id,
                                 @Param("year") int year,
                                 @Param("month") int month);


    void clock(Attendance attendance);

    List<Attendance> getAttendancesInRange(Integer emplyee_id, int startRow, int endRow);

    List<Attendance> searchDate(
            @Param("year") Integer year,
            @Param("month") Integer month,
            @Param("day") Integer day,
            @Param("emplyee_id")Integer emplyee_id);

    void updateAttendance(Attendance attendance);
}
