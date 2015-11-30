package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.AreaProductBean;

public interface AreaProductBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AreaProductBean record);

    int insertSelective(AreaProductBean record);

    AreaProductBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaProductBean record);

    int updateByPrimaryKey(AreaProductBean record);
}