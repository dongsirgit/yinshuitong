package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.ServiceOrg;

public interface ServiceOrgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceOrg record);

    int insertSelective(ServiceOrg record);

    ServiceOrg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceOrg record);

    int updateByPrimaryKey(ServiceOrg record);
}