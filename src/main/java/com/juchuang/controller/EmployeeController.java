package com.juchuang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juchuang.bean.Employee;
import com.juchuang.service.EmployeeService;
import com.juchuang.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final long serialVersionUID = -8490951366351314663L;

    @Autowired
    EmployeeService employeeService;

    /**
     * 校验用户名是否可用
     * @param empName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkUser",method = RequestMethod.GET)
    public Message checkUser(@RequestParam(value = "empName") String empName){
        String regx="(^[a-z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regx)){
            return Message.fail().add("va_msg","用户名可以是2-5位中文或者6-16位英文和数字组合");
        }
        boolean b=employeeService.checkUser(empName);
        if (b)
            return Message.success();
        else
            return Message.fail().add("va_msg","用户名不可用");
    }

    /**
     * 获取单个员工信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public Message getEmp(@PathVariable("id") Integer id){
       Employee employee= employeeService.getEmp(id);
        return Message.success().add("emp",employee);
    }

    /**
     * 修改员工
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    public Message saveEmp(Employee employee) {
    employeeService.updateEmp(employee);
    return Message.success();
    }


    /**
     * 删除员工（单个删除、批量删除都处理）
     */

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Message deleteEmpById(@PathVariable("id") String ids) {

        if (ids.contains("-")) {//批量删除
            List<Integer> integerList = new ArrayList<>();
            String[] split = ids.split("-");
            for (String string : split) {
                integerList.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(integerList);
        } else {//单个删除
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }

        return Message.success();
    }


    /**
     * 添加员工
     *
     * @param employee
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveEmp", method = RequestMethod.POST)
    public Message saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError f : fieldErrors) {
                System.out.println("错误字段："+f.getField());
                System.out.println("错误信息："+f.getDefaultMessage());
                map.put(f.getField(),f.getDefaultMessage());
            }
            return Message.fail().add("errorFields", map);
        } else {
            employeeService.saveEmp(employee);
            return Message.success();
        }
    }


    /**
     * ajax分页查询
     *
     * @param pn 页码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/empsAjax")
    public Message getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        //1、查询之前调用，传入页码，以及每页大小
        PageHelper.startPage(pn, 5);
        //2、startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        //3、使用PageInfo包装查询后的结果，只需要将pageInfo交给页面就可以了
        //封装详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
        PageInfo pageInfo = new PageInfo(emps, 5);
        return Message.success().add("pageInfo",pageInfo);
    }

    /**
     * 查询员工数据
     *
     * @return
     */
    @RequestMapping(value = "/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        //1、查询之前调用，传入页码，以及每页大小
        PageHelper.startPage(pn, 5);
        //2、startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        //3、使用PageInfo包装查询后的结果，只需要将pageInfo交给页面就可以了
        //封装详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
        PageInfo pageInfo = new PageInfo(emps, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }
}
