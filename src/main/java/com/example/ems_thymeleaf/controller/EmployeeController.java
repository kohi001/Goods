package com.example.ems_thymeleaf.controller;

import com.example.ems_thymeleaf.dto.EmployeeDepartmentDto;
import com.example.ems_thymeleaf.entity.Employee;
import com.example.ems_thymeleaf.service.EmployeeService;

import com.example.ems_thymeleaf.service.PdfService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.github.pagehelper.util.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Value("${file.resume.dir}")
    private String realpath;


    private EmployeeService employeeService;
    private PdfService pdfService;


    @Autowired
    public EmployeeController(EmployeeService employeeService,PdfService pdfService) {
        this.employeeService = employeeService;
        this.pdfService = pdfService;


    }
    @RequestMapping("search")
    public String search(@Param("emplyee_id") Integer emplyee_id,
                         @Param("employee_name") String employee_name,
                         @Param("department") String department,
                         @Param("address") String address,Model model){
        PageHelper.clearPage();
        PageHelper.startPage(1,6);
        List<EmployeeDepartmentDto> employeeList = employeeService.search(emplyee_id,employee_name,department,address);
        PageInfo<EmployeeDepartmentDto> pageInfo = new PageInfo<>(employeeList);
        model.addAttribute("pageInfo",pageInfo);
        return "emplist";
    }

    @RequestMapping("delete")
    public String delete(Integer emplyee_id, RedirectAttributes ra){
        log.debug("删除的员工id：{}",emplyee_id);
        employeeService.delete(emplyee_id);
        ra.addFlashAttribute("msg2","削除成功しました！");
        return "redirect:/employee/lists";

    }

    @RequestMapping("detail")
    public String detail(Integer emplyee_id,Model model){
        log.debug("当前查询员工id：{}",emplyee_id);
        Employee employee = employeeService.findById(emplyee_id);
        model.addAttribute("employee",employee);
        return "updateEmp";
    }

    @RequestMapping("update")
    public String update(@ModelAttribute("employee") @Valid Employee employee,BindingResult bindingResult,RedirectAttributes ra,Model model,MultipartFile resumeFile) throws IOException {
        log.debug("更新之后员工信息：社員番号：{},社員名：{},役職名：{},性別：{},部署名：{},住所：{},雇用形態：{},入社年月日：{},誕生日:{}",
                employee.getEmplyee_id(), employee.getEmployee_name(), employee.getJob_title(), employee.getSex(), employee.getDepartment(), employee.getAddress(), employee.getEmployment_status(), employee.getHire_date(),employee.getBirth_date());
        boolean notEmpty = !resumeFile.isEmpty();
        log.debug("是否更新履历：{}",notEmpty);
        if (notEmpty) {
            String oldresume = employeeService.findById(employee.getEmplyee_id()).getResume();
            File file = new File(realpath, oldresume);
            if (file.exists()) file.delete();
            String originalFilename = resumeFile.getOriginalFilename();
            String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = fileNamePrefix + fileNameSuffix;
            resumeFile.transferTo(new File(realpath, newFileName));
            employee.setResume(newFileName);
        }
        if (bindingResult.hasErrors()){
            return "updateEmp";
        } else if (employee.getBirth_date()!=null && !employeeService.isBirthDateValid(employee.getBirth_date())) {
            model.addAttribute("errorMsg1","社員年齢を18歳～60歳にしてください");
            return "updateEmp";
        } else
            employeeService.update(employee);
        ra.addFlashAttribute("msg3","更新成功しました！");
        return "redirect:/employee/lists";

    }

    @RequestMapping("download")
    public void download(String resume, HttpServletResponse response) throws IOException{
        log.debug("当前下载文件名为：{}",resume);
        File file = new File(realpath,resume);
        FileInputStream is = new FileInputStream(file);
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(resume,"UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        FileCopyUtils.copy(is,os);

    }




    @RequestMapping("add")
    public String addForm(Model model){
        model.addAttribute("employee",new Employee());
        model.addAttribute("departments",employeeService.findDepartments());
        model.addAttribute("position",employeeService.findPosition());
        return "addEmp";
    }


    @RequestMapping("save")
    public String save(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model, RedirectAttributes ra,MultipartFile resumeFile) throws IOException {
        log.debug("社員番号：{},社員名：{},役職名：{},性別：{},部署名：{},住所：{},雇用形態：{},入社年月日：{},誕生日:{}",
                employee.getEmplyee_id(), employee.getEmployee_name(), employee.getJob_title(), employee.getSex(), employee.getDepartment(), employee.getAddress(), employee.getEmployment_status(), employee.getHire_date(),employee.getBirth_date());
        String originalFilename = resumeFile.getOriginalFilename();
        log.debug("文件名称：{}",originalFilename);
        log.debug("文件大小：{}",resumeFile.getSize());
        if (!StringUtil.isEmpty(originalFilename)){
            String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = fileNamePrefix+fileNameSuffix;
            resumeFile.transferTo(new File(realpath,newFileName));
            employee.setResume(newFileName);
        }
        if (bindingResult.hasErrors()){
            return "addEmp";
        } else if (employeeService.isEmployeeValid(employee.getEmplyee_id())) {
            model.addAttribute("errorMsg","この社員番号がすでに登録されています");
            return "addEmp";
        }else if (employee.getBirth_date()!=null && !employeeService.isBirthDateValid(employee.getBirth_date())){
            model.addAttribute("errorMsg2","社員年齢を18歳～60歳にしてください");
            return "addEmp";
        }
            employeeService.save(employee);
            ra.addFlashAttribute("msg4","登録成功しました！");
            return "redirect:/employee/lists";


    }


    @RequestMapping("lists")
    public String lists(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.clearPage();
        PageHelper.startPage(pageNum,6);
        List<EmployeeDepartmentDto> employeeList = employeeService.getEmployeeWithDepartments();
        PageInfo<EmployeeDepartmentDto> pageInfo = new PageInfo<>(employeeList);
        model.addAttribute("pageInfo",pageInfo);
        return "emplist";
    }

    @PostMapping("/exportPdf")
    public void exportPdf(HttpServletResponse response,Employee employee) {
        pdfService.exportPdf(response,employee);
    }



}