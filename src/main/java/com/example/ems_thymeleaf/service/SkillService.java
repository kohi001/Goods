package com.example.ems_thymeleaf.service;

import com.example.ems_thymeleaf.entity.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> getSkills();

    void update(Skill skill);

    boolean isEmployeeValid(Integer emplyee_id);

    void save(Skill skill);

    Skill findById(Integer emplyee_id);

    void delete(Integer emplyee_id);
}
