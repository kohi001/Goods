package com.example.ems_thymeleaf.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalTime;
import java.util.Date;

public class Attendance {
    private Integer record_id;
    private Integer emplyee_id;
    @NotNull(message = "日付を選択してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "未来の日付が入力できません")
    private Date attendance_date;
    private String status;
    private LocalTime start_date;
    private LocalTime end_date;

    public Attendance() {
    }

    public Attendance(Integer record_id, Integer emplyee_id, Date attendance_date, String status, LocalTime start_date, LocalTime end_date) {
        this.record_id = record_id;
        this.emplyee_id = emplyee_id;
        this.attendance_date = attendance_date;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }

    public Integer getEmplyee_id() {
        return emplyee_id;
    }

    public void setEmplyee_id(Integer emplyee_id) {
        this.emplyee_id = emplyee_id;
    }

    public Date getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(Date attendance_date) {
        this.attendance_date = attendance_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalTime start_date) {
        this.start_date = start_date;
    }

    public LocalTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalTime end_date) {
        this.end_date = end_date;
    }
}
