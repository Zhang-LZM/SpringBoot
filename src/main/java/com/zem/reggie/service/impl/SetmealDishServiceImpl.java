package com.zem.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zem.reggie.entity.SetmealDish;
import com.zem.reggie.mapper.SetmealDishMapper;
import com.zem.reggie.service.SetmealDishService;
import org.springframework.stereotype.Service;
@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper,SetmealDish>
       implements SetmealDishService {
}