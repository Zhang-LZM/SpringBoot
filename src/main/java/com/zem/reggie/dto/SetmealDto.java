package com.zem.reggie.dto;

import com.zem.reggie.entity.Setmeal;
import com.zem.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;
@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;
    private String categoryName;
}
