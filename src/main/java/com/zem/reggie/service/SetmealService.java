package com.zem.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zem.reggie.dto.SetmealDto;
import com.zem.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
    public SetmealDto getByIdWithDish(Long id);
    public void updateWithDish(SetmealDto setmealDto);
    public void removeWithDish(List<Long> ids);
}