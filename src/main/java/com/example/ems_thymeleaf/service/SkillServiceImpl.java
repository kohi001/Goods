package com.example.ems_thymeleaf.service;

import com.example.ems_thymeleaf.dao.SkillDao;
import com.example.ems_thymeleaf.entity.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {
    private SkillDao skillDao;
    @Autowired
    public  SkillServiceImpl(SkillDao skillDao){
        this.skillDao = skillDao;
    }

    @Override
    public List<Skill> getSkills() {
        return skillDao.getSkills();
    }

    @Override
    public void update(Skill skill) {
        skillDao.update(skill);
    }

    @Override
    public boolean isEmployeeValid(Integer emplyee_id) {
        return skillDao.findById(emplyee_id)!=null;
    }

    @Override
    public void save(Skill skill) {
        skillDao.save(skill);
    }

    @Override
    public Skill findById(Integer emplyee_id) {
        return skillDao.findById(emplyee_id);
    }

    @Override
    public void delete(Integer emplyee_id) {
        skillDao.delete(emplyee_id);

    }
}
