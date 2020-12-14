package com.zp.helloworld.dao;

import com.zp.helloworld.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by peng.zhang
 * Description:
 * Time: 2019/4/30-15:45.
 */
// 注解方式开启二级缓存
@CacheNamespace(blocking = true)
public interface EmployeeMapper {

    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("id")
    Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    //返回一条记录的map；key就是列名，值就是对应的值
    Map<String, Object> getEmpByIdReturnMap(Integer id);

    List<Employee> getEmpsByLastNameLike(String lastName);

    Employee getEmpByMap(Map<String, Object> map);

    Employee getEmpByIdAndLastName(@Param("id")Integer id, @Param("lastName")String lastName);

    // @Options(useCache = true)
    Employee getEmpById(Integer id);

    boolean updateEmpById(Employee employee);

    Long addEmp(Employee employee);

    boolean updateEmp(Employee employee);

    void deleteEmpById(Integer id);

    @Select("select * from employee where id = 1")
    Employee finaAll();

}
