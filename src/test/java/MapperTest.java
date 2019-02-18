import com.juchuang.bean.Department;
import com.juchuang.bean.Employee;
import com.juchuang.dao.DepartmentMapper;
import com.juchuang.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.UUID;

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
 * @Package Name :PACKAGE_NAME
 * @Author : 1643091610@qq.com
 * @Date :2019 年 02月 16 日 1:58
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Resource
    private DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void test_CRUD(){
        //1、插入几个部门
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));
        //2、插入员工数据，测试员工插入
//        employeeMapper.insertSelective(new Employee(null,"Jerry","M","jerry@juchuang.com",7));
        //3、批量插入多员工，使用可执行的sqlsession
//        for (){
//            employeeMapper.insertSelective(new Employee(null,"Jerry","M","jerry@juchuang.com",7));
//        }
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i=0;i<1000;i++)
        {
            String substring = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(new Employee(null,substring,"M",substring+"@juchuang.com",7));
        }
        System.out.println("------>batch success");
    }
}
