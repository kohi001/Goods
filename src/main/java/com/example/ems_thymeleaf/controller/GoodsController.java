package com.example.ems_thymeleaf.controller;

import com.example.ems_thymeleaf.dto.GoodsClassificationDto;
import com.example.ems_thymeleaf.entity.Employee;
import com.example.ems_thymeleaf.entity.Goods;
import com.example.ems_thymeleaf.entity.GoodsOrder;
import com.example.ems_thymeleaf.service.EmployeeService;
import com.example.ems_thymeleaf.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("goods")
public class GoodsController {
    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);
    private GoodsService goodsService;
    private EmployeeService employeeService;

    @Value("${file.resume.dir}")
    private String realpath;
    @Autowired
    public GoodsController(GoodsService goodsService,EmployeeService employeeService){
        this.goodsService = goodsService;
        this.employeeService = employeeService;
    }

    @RequestMapping("loging")
    public String loginForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "goodslogin";
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
            return "goodslogin";
        } else if (StringUtil.isEmpty(employee.getEmployee_password())) {
            model.addAttribute("error2", "パスワードを入力してください");
            return "goodslogin";
        } else if (!employeeService.isPasswordValid(emplyee_id, employee_password)) {
            model.addAttribute("errorMsg", "ユーザまたはパスワードが違います");
            return "goodslogin";
        }
        return "redirect:/goods/lists";

    }

    @RequestMapping("search")
    public String search(@Param("goodsCode") Integer goodsCode,
                         @Param("quantity") String producer,
                         @Param("bigClassification") String bigClassification,
                         @Param("price") String price,
                         @RequestParam(required = false) List<Integer> color,
                         @RequestParam(required = false) List<Integer> size,Model model){
        PageHelper.clearPage();
        PageHelper.startPage(1,6);
        List<GoodsClassificationDto> goodsList = goodsService.search(goodsCode,producer,bigClassification,price,color,size);
        PageInfo<GoodsClassificationDto> pageInfo = new PageInfo<>(goodsList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("goodsCode",goodsCode);
        model.addAttribute("producer",producer);
        model.addAttribute("bigClassification",bigClassification);
        model.addAttribute("price",price);
        return "goodslist";
    }
    @RequestMapping("lists")
    public String lists( Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.clearPage();
        PageHelper.startPage(pageNum,6);
        List<GoodsClassificationDto> goodsList = goodsService.getGoodWithClassifications();
        PageInfo<GoodsClassificationDto> pageInfo = new PageInfo<>(goodsList);
        model.addAttribute("pageInfo",pageInfo);
        return "goodslist";
    }
    @RequestMapping("detail")
    public String detail(Integer goodsId,Model model){
        log.debug("当前查询商品id：{}",goodsId);
        Goods goods = goodsService.findById(goodsId);
        model.addAttribute("goods",goods);
        model.addAttribute("sizes",goodsService.findSize());
        model.addAttribute("bigclassifications",goodsService.findBigClassification());
        model.addAttribute("colors",goodsService.findColor());
        model.addAttribute("discounts",goodsService.findDiscount());
        return "goodsupdate";
    }

    @RequestMapping("update")
    public String update(@ModelAttribute("goods") @Valid Goods goods,BindingResult bindingResult,RedirectAttributes ra,Model model,MultipartFile fileFile) throws IOException {
        log.debug("商品信息：商品番号：{},尺寸：{},颜色：{}，大分类：{},中分类：{},价格：{},运费：{},描述:{},折扣：{}，产地：{}",
                goods.getGoodsId(),goods.getSize(),goods.getColor(),goods.getBigClassification(),goods.getMiddleClassification(),goods.getPrice(),goods.getExpenses(),goods.getDescription(),goods.getDiscount(),goods.getProducer());
        boolean notEmpty = !fileFile.isEmpty();
        log.debug("是否更新文件：{}",notEmpty);
        if (notEmpty) {
            String oldresume = goodsService.findById(goods.getGoodsId()).getFile();
            File file = new File(realpath, oldresume);
            if (file.exists()) file.delete();
            String originalFilename = fileFile.getOriginalFilename();
            String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = fileNamePrefix + fileNameSuffix;
            fileFile.transferTo(new File(realpath, newFileName));
            goods.setFile(newFileName);
        }
        if (bindingResult.hasErrors()){
            return "goodsupdate";
        }  else
            goodsService.update(goods);
        ra.addFlashAttribute("msg3","更新成功しました！");
        return "redirect:/goods/lists";

    }
    @RequestMapping("download")
    public void download(String file, HttpServletResponse response) throws IOException{
        log.debug("当前下载文件名为：{}",file);
        File file1 = new File(realpath,file);
        FileInputStream is = new FileInputStream(file1);
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(file,"UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        FileCopyUtils.copy(is,os);

    }


    @RequestMapping("add")
    public String addForm(Model model){
        model.addAttribute("goods",new Goods());
        model.addAttribute("sizes",goodsService.findSize());
        model.addAttribute("bigclassifications",goodsService.findBigClassification());
        model.addAttribute("colors",goodsService.findColor());
        model.addAttribute("discuonts",goodsService.findDiscount());
        return "goodsadd";
    }


    @RequestMapping("save")
    public String save(@ModelAttribute("goods") @Valid Goods goods, BindingResult bindingResult, Model model, RedirectAttributes ra, MultipartFile fileFile) throws IOException {
        log.debug("商品信息：商品番号：{},尺寸：{},颜色：{}，大分类：{},中分类：{},价格：{},运费：{},描述:{}",
                goods.getGoodsId(),goods.getSize(),goods.getColor(),goods.getBigClassification(),goods.getMiddleClassification(),goods.getPrice(),goods.getExpenses(),goods.getDescription());
        String originalFilename = fileFile.getOriginalFilename();
        log.debug("文件名称：{}",originalFilename);
        log.debug("文件大小：{}",fileFile.getSize());
        if (!StringUtil.isEmpty(originalFilename)){
            String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = fileNamePrefix+fileNameSuffix;
            fileFile.transferTo(new File(realpath,newFileName));
            goods.setFile(newFileName);
        }
        if (bindingResult.hasErrors()){
            return "goodsadd";
        } else if (goodsService.isGoodsValid(goods.getGoodsCode(),goods.getSize(),goods.getColor())) {
            model.addAttribute("errorMsg","この商品がすでに登録されています");
            return "goodsadd";
        }
        goodsService.save(goods);
        ra.addFlashAttribute("msg4","登録成功しました！");
        return "redirect:/goods/lists";


    }
    @RequestMapping("delete")
    public String delete(Integer goodsId, RedirectAttributes ra){
        log.debug("删除的商品id：{}",goodsId);
        goodsService.delete(goodsId);
        ra.addFlashAttribute("msg2","削除成功しました！");
        return "redirect:/goods/lists";

    }

    @RequestMapping("orderlist")
    public String orderlist( Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.clearPage();
        PageHelper.startPage(pageNum,6);
        List<GoodsClassificationDto> goodsList = goodsService.getGoodWithClassifications();
        PageInfo<GoodsClassificationDto> pageInfo = new PageInfo<>(goodsList);
        model.addAttribute("pageInfo",pageInfo);
        return "goodsorderlist";
    }

    @RequestMapping("ordering")
    public String ordering(Integer goodsId,Model model){
        log.debug("当前查询商品id：{}",goodsId);
        Goods goods = goodsService.findById(goodsId);
        model.addAttribute("goods",goods);
        model.addAttribute("sizes",goodsService.findSize());
        model.addAttribute("bigclassifications",goodsService.findBigClassification());
        model.addAttribute("colors",goodsService.findColor());
        model.addAttribute("discounts",goodsService.findDiscount());
        return "goodsorder";
    }

    @RequestMapping("order")
    public String order(Integer goodsId, GoodsOrder goodsOrder){

        return "redirect:/goods/orderlist";
    }

    @RequestMapping("ordered")
    public String ordered(){
        return "goodsordered";
    }

}
