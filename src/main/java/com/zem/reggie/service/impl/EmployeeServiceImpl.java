package com.zem.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zem.reggie.entity.Employee;
import com.zem.reggie.mapper.EmployeeMapper;
import com.zem.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> 
        implements EmployeeService{ 
}