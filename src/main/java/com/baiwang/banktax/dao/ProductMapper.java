package com.baiwang.banktax.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baiwang.banktax.beans.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

	/**
	  * @author gkm
	  * @Description: 查询产品列表
	  * @param @return  
	  * @return List<Product>  
	  * @throws
	  * @date 2015年11月25日 下午1:09:44
	  */
	List<Product> getproList(@Param("id")Integer id);

	
	
	
	
	int save(String ss);

	
}