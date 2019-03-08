package com.juchuang.controller;

import com.juchuang.bean.Department;
import com.juchuang.service.DepartmentService;
import com.juchuang.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * @Description :  [一句话描述该类的功能]
 * @Package Name :com.juchuang.controller
 * @Author : 1643091610@qq.com
 * @Date :2019 年 03月 02 日 17:14
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
@Controller
public class DepartmentController implements Serializable {

    private static final long serialVersionUID = -9003712236801666805L;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询所有部门信息
     * @return
     */
    @RequestMapping(value = "/getDepts", method = RequestMethod.GET)
    @ResponseBody
    public Message getDepts() {
        List<Department> depts = departmentService.getDepts();
        return Message.success().add("depts", depts);
    }


}
