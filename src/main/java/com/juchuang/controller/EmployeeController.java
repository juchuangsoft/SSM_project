package com.juchuang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juchuang.bean.Employee;
import com.juchuang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @Project : SSM_project
 * @Package Name :com.juchuang.controller
 * @Author : 1643091610@qq.com
 * @Date :2019 年 02月 16 日 23:41
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
@Controller
public class EmployeeController implements Serializable {

    @Autowired
    EmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value = "/empsAjax")
    public PageInfo getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        //1、查询之前调用，传入页码，以及每页大小
        PageHelper.startPage(pn,5);
        //2、startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps=employeeService.getAll();
        //3、使用PageInfo包装查询后的结果，只需要将pageInfo交给页面就可以了
        //封装详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
        PageInfo pageInfo=new PageInfo(emps,5);
        return pageInfo;
    }

    /**
     * 查询员工数据
     * @return
     *
     */
    @RequestMapping(value = "/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //1、查询之前调用，传入页码，以及每页大小
        PageHelper.startPage(pn,5);
        //2、startPage后面紧跟的这个查询就是一个分页查询
       List<Employee> emps=employeeService.getAll();
       //3、使用PageInfo包装查询后的结果，只需要将pageInfo交给页面就可以了
        //封装详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
        PageInfo pageInfo=new PageInfo(emps,5);
        model.addAttribute("pageInfo",pageInfo);
        return "list";
    }
}
