package com.example.ems_thymeleaf.service;

import com.example.ems_thymeleaf.dao.DepartmentDao;
import com.example.ems_thymeleaf.dao.EmployeeDao;
import com.example.ems_thymeleaf.dao.PositionDao;
import com.example.ems_thymeleaf.dao.UserDao;
import com.example.ems_thymeleaf.dto.EmployeeDepartmentDto;
import com.example.ems_thymeleaf.entity.Attendance;
import com.example.ems_thymeleaf.entity.Department;
import com.example.ems_thymeleaf.entity.Employee;
import com.example.ems_thymeleaf.entity.Position;
import org.apache.catalina.util.ErrorPageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;
    private DepartmentDao departmentDao;
    private PositionDao positionDao;
    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao,DepartmentDao departmentDao,PositionDao positionDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
        this.positionDao = positionDao;
    }

    @Override
    public List<Employee> lists() {
        return employeeDao.lists();
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public Employee findById(Integer emplyee_id) {
      return employeeDao.findById(emplyee_id);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Integer emplyee_id) {
        employeeDao.delete(emplyee_id);

    }

    @Override
    public List<EmployeeDepartmentDto> search(Integer emplyee_id, String employee_name, String department, String address) {
        return employeeDao.search(emplyee_id,employee_name,department,address);
    }

    @Override
    public boolean isEmployeeValid(Integer emplyee_id) {

        return employeeDao.findById(emplyee_id)!=null;
    }

    @Override
    public boolean isBirthDateValid(LocalDate birth_date) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birth_date,currentDate);
        int age = period.getYears();
        return age >=18 && age <=60;
    }

    @Override
    public List<EmployeeDepartmentDto> getEmployeeWithDepartments() {
        return employeeDao.getEmployeeWithDepartments();
    }

    @Override
    public List<Department> findDepartments() { return departmentDao.findAll();}

    @Override
    public List<Position> findPosition() {
        return positionDao.findAll();
    }

    @Override
    public boolean isPasswordValid(Integer emplyee_id, String employee_password) {
        Employee employee=employeeDao.findById(emplyee_id);
        return employee!=null&&employee.getEmployee_password().equals(employee_password);

    }

    @Override
    public void generateDailyAttendance(Integer emplyee_id, int year, int month) {
        if (employeeDao.hasAttendanceRecords(emplyee_id, year, month)) {
            // 如果已存在记录，则不重新创建
            return;
        }
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();  // 获取当月天数
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate attendanceDate = LocalDate.of(year, month, day);
            // 为每一天创建一个新的 Attendance 对象
            Attendance attendance = new Attendance();
            attendance.setEmplyee_id(emplyee_id);
            attendance.setAttendance_date(Date.valueOf(attendanceDate));  // 将 LocalDate 转换为 Date
            attendance.setStatus("欠勤");
            // 将新创建的考勤记录保存到数据库
            employeeDao.clock(attendance);
        }
    }

    @Override
    public List<Attendance> getAllAttendances(Integer emplyee_id) {
        return employeeDao.getAllAttendances(emplyee_id);
    }

    @Override
    public List<Attendance> getMoreAttendances(Integer emplyee_id, Integer pageNum, Integer pageSize) {

        int startRow = (pageNum - 1) * pageSize + 1;
        int endRow = pageNum * pageSize;

        return employeeDao.getAttendancesInRange(emplyee_id, startRow, endRow);
    }

    @Override
    public List<Attendance> searchDate(Integer year, Integer month, Integer day, Integer emplyee_id) {
        return employeeDao.searchDate(year,month,day,emplyee_id);
    }

    @Override
    public void updateAttendance(Attendance attendance) {
        employeeDao.updateAttendance(attendance);
    }


}
