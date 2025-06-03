package com.zem.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zem.reggie.entity.DishFlavor;
import com.zem.reggie.mapper.DishFlavorMapper;
import com.zem.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper,DishFlavor> implements DishFlavorService {
}
