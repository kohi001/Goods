package com.example.ems_thymeleaf.controller;

import com.example.ems_thymeleaf.dto.EmployeeDepartmentDto;
import com.example.ems_thymeleaf.entity.Employee;
import com.example.ems_thymeleaf.entity.Skill;
import com.example.ems_thymeleaf.service.EmployeeService;
import com.example.ems_thymeleaf.service.SkillService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("skill")
public class SkillController {
    private static final Logger log = LoggerFactory.getLogger(SkillController.class);
    private SkillService skillService;
    private EmployeeService employeeService;

    @Autowired
    public SkillController(SkillService skillService, EmployeeService employeeService) {
        this.skillService = skillService;
        this.employeeService = employeeService;
    }

    @RequestMapping("loging")
    public String loginForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "skilogin";
    }

    @RequestMapping("login")
    public String login(@ModelAttribute("employee") Employee employee, Model model) {
        log.debug("本地登录番号:{}", employee.getEmplyee_id());
        log.debug("本地登录密码:{}", employee.getEmployee_password());
        Integer emplyee_id = employee.getEmplyee_id();
        String employee_password = employee.getEmployee_password();
        //用户名与密码的校验。
        if (emplyee_id == null) {
            model.addAttribute("error1", "社員番号を入力してください");
            return "skilogin";
        } else if (StringUtil.isEmpty(employee.getEmployee_password())) {
            model.addAttribute("error2", "パスワードを入力してください");
            return "skilogin";
        } else if (!employeeService.isPasswordValid(emplyee_id, employee_password)) {
            model.addAttribute("errorMsg", "ユーザまたはパスワードが違います");
            return "skilogin";
        }
        return "redirect:/skill/lists";

    }

    @RequestMapping("lists")
    public String lists(@ModelAttribute("skill")Skill skill, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.clearPage();
        PageHelper.startPage(pageNum,6);
        List<Skill> skillList = skillService.getSkills();
        PageInfo<Skill> pageInfo = new PageInfo<>(skillList);
        model.addAttribute("pageInfo",pageInfo);
        return "skilist";
    }
    @RequestMapping("detail")
    public String detail(Integer emplyee_id,Model model){
        log.debug("当前查询员工id：{}",emplyee_id);
        Skill skill = skillService.findById(emplyee_id);
        model.addAttribute("skill",skill);
        return "skiupdate";
    }

    @RequestMapping("update")
    public String update(@ModelAttribute("skill") @Valid Skill skill, BindingResult bindingResult, RedirectAttributes ra, Model model) throws IOException {
        log.debug("更新之后员工信息：社員番号：{},データベース：{},開発経験：{},開発言語：{},OS：{},フレームワーク：{},資格：{}",
                skill.getEmplyee_id(),skill.getData_base(),skill.getExperience(),skill.getLanguage(),skill.getOS(),skill.getFramework(),skill.getQualification());
        if (bindingResult.hasErrors()){
            return "skiupdate";
        }  else
            skillService.update(skill);
            ra.addFlashAttribute("msg3","更新成功しました！");
        return "redirect:/skill/lists";

    }
    @RequestMapping("add")
    public String addForm(Model model){
        model.addAttribute("skill",new Skill());
        return "skiadd";
    }


    @RequestMapping("save")
    public String save(@ModelAttribute("skill") @Valid Skill skill, BindingResult bindingResult, Model model, RedirectAttributes ra) throws IOException {
        log.debug("社員番号：{},データベース：{},開発経験：{},開発言語：{},OS：{},フレームワーク：{},資格：{}",
                skill.getEmplyee_id(),skill.getData_base(),skill.getExperience(),skill.getLanguage(),skill.getOS(),skill.getFramework(),skill.getQualification());
        if (bindingResult.hasErrors()){
            return "skiadd";
        } else if (skillService.isEmployeeValid(skill.getEmplyee_id())) {
            model.addAttribute("errorMsg","この社員番号がすでに登録されています");
            return "addEmp";
        }
        skillService.save(skill);
        ra.addFlashAttribute("msg4","登録成功しました！");
        return "redirect:/skill/lists";


    }
    @RequestMapping("delete")
    public String delete(Integer emplyee_id, RedirectAttributes ra){
        log.debug("删除的员工id：{}",emplyee_id);
        skillService.delete(emplyee_id);
        ra.addFlashAttribute("msg2","削除成功しました！");
        return "redirect:/skill/lists";

    }


}