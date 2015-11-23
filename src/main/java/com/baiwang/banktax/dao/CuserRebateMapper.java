package com.baiwang.banktax.dao;

import com.baiwang.banktax.beans.CuserRebate;
/**
 * 
  * @ClassName: CuserRebate
  * @Description: 邀请返利表
  * @author gkm
  * @date 2015年10月8日 下午5:42:50
 */
public interface CuserRebateMapper {
    int deleteByPrimaryKey(Long applyId);

    int insert(CuserRebate record);

    int insertSelective(CuserRebate record);

    CuserRebate selectByPrimaryKey(Long applyId);

    int updateByPrimaryKeySelective(CuserRebate record);

    int updateByPrimaryKey(CuserRebate record);
}