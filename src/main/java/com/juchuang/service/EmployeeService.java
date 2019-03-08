package com.juchuang.service;

import com.juchuang.bean.Employee;
import com.juchuang.bean.EmployeeExample;
import com.juchuang.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Description :  [部门业务类]
 * @Package Name :com.juchuang.service
 * @Author : 1643091610@qq.com
 * @Date :2019 年 02月 16 日 23:55
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     * @return
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 保存员工
     * @param employee
     */
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 批量删除
     * @param id
     */
    public void deleteBatch(List<Integer> id) {
        EmployeeExample employeeExample=new EmployeeExample();
        employeeExample.createCriteria().andEmpIdIn(id);
        employeeMapper.deleteByExample(employeeExample);
    }

    /**
     * 删除单个员工
     * @param id
     */
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改员工
     * @param employee
     */
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 查询单个员工
     * @param id
     * @return
     */
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * 检验用户名是否可用
     * @param empName
     * @return  true： 可用   false 不可用
     */
    public boolean checkUser(String empName) {
        EmployeeExample employeeExample=new EmployeeExample();
        employeeExample.createCriteria().andEmpNameEqualTo(empName);
     return employeeMapper.countByExample(employeeExample)==0;
    }
}
