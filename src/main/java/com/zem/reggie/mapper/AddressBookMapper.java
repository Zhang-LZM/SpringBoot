package com.zem.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zem.reggie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}
