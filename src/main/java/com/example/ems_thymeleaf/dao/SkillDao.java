package com.example.ems_thymeleaf.dao;

import com.example.ems_thymeleaf.entity.Skill;

import java.util.List;

public interface SkillDao {
    List<Skill> getSkills();

    void update(Skill skill);

    Skill findById(Integer emplyee_id);

    void save(Skill skill);

    void delete(Integer emplyee_id);
}
