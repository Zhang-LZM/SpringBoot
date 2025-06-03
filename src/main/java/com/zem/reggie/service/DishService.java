package com.zem.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zem.reggie.dto.DishDto;
import com.zem.reggie.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    public DishDto getByIdWithFlavor(Long id);
    public void updateWithFlavor(DishDto dishDto);
    public void delByIdWithFlavor(List<Long> ids);
}