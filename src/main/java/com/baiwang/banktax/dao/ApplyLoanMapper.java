package com.baiwang.banktax.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.InviteeBean;
import com.baiwang.banktax.beans.InviterAwardBean;
import com.baiwang.banktax.beans.Puser;

/**
 * 
  * @ClassName: ApplyLoanMapper
  * @Description: 申请贷款 dao层
  * @author gkm
  * @date 2015年8月5日 下午2:37:24
 */
@SuppressWarnings("rawtypes")
public interface ApplyLoanMapper {

	/**
	  * @Description: 查询 申请贷款单信息
	  * @param @param id
	  * @param @param uid
	  * @param @return  
	  * @return ApplyLoan  
	  * @throws
	  * @version 2
	  */
	ApplyLoan queryApplyLoan(Map map);

	/**
	  * @Description: 提交
	  * @param @param id
	  * @param @param uid
	  * @param @return  
	  * @return int  
	  * @throws
	  * @version 2
	  */
	int submit(Map map);

	/**
	  * @Description: TODO
	  * @param @param uid
	  * @param @return  
	  * @return List<ApplyLoan>  
	  * @throws
	  * @version 2
	  */
	List<ApplyLoan> queryLoanList(Long uid);

	/**
	  * @Description: 贷款申请列表删除一个申请
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  * @version 2
	  */
	int delApplyLoan(Long id);

	/**
	  * @Description: 查询 申请贷款单信息浏览
	  * @param @param id
	  * @param @param uid
	  * @param @return  
	  * @return ApplyLoan  
	  * @throws
	  * @version 2
	  */
	ApplyLoan queryApplyLoan4show(Map map);

	/**
	  * @Description: 查询用户信息
	  * @param @param id
	  * @param @return  
	  * @return Cuser  
	  * @throws
	  * @version 2
	  */
	Cuser getCuser(Long id);

	/**
	  * @Description: 完善企业信息
	  * @param @param cuser
	  * @param @return  
	  * @return int  
	  * @throws
	  * @version 2
	  */
	int updateUserInfo(Cuser cuser);

	/**
	  * @Description: 根据纳税号 查询企业信息
	  * @param @param cuser
	  * @param @return  
	  * @return Cuser  
	  * @throws
	  * @version 2
	  */
	Cuser getCuserByTaxSn(Cuser cuser);

	/**
	  * @Description: 根据企业名称查询企业信息
	  * @param @param cuser
	  * @param @return  
	  * @return Cuser  
	  * @throws
	  * @version 2
	  */
	Cuser getCuserByCorpName(Cuser cuser);

	
	
	/**
	  * @author Administrator
	  * @Description: 保存企业申请贷款 2.0
	  * @param @param loan
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年9月6日 下午2:05:20
	  * @version 2.0
	  */
	int saveLoan(ApplyLoan loan);

	/**
	  * @author Administrator
	  * @Description: 保存企业申请贷款 2.0
	  * @param @param loan
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年9月6日 下午2:16:12
	  * @version 2.0
	  */
	int updateLoan(ApplyLoan loan);

	/**
	  * @author Administrator
	  * @Description: 根据身份证号查询 个人信息
	  * @param @param puser
	  * @param @return  
	  * @return Puser  
	  * @throws
	  * @date 2015年9月7日 上午10:57:11
	  * @version 2.0
	  */
	Puser getPuserByIdcard(Puser puser);

	/**
	  * @author Administrator
	  * @Description: 完善个人信息
	  * @param @param puser
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年9月7日 上午11:10:22
	  * @version 2.0
	  */
	int updateUserInfoPerson(Puser puser);

	/**
	  * @author Administrator
	  * @Description: 根据id查询 个人信息
	  * @param @param id  
	  * @return void  
	  * @throws
	  * @date 2015年9月7日 上午11:19:15
	  * @version 2.0
	  */
	Puser getPuser(Long id);

	/**
	  * @author Administrator
	  * @Description: 提交时插入邀请返利表
	  * @param @param id
	  * @param @param uid
	  * @param @param inviteUid
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年10月8日 下午5:55:01
	  */
	int insertRebate(@Param("id")Long id, @Param("uid")Long uid, @Param("inviteUid")Long inviteUid);

	/**
	  * @author Administrator
	  * @Description: 企业用户查询邀请的人
	  * @param @param id
	  * @param @param page
	  * @param @param pageSize
	  * @param @return  
	  * @return List<InviteeBean>  
	  * @throws
	  * @date 2015年10月12日 下午2:05:36
	  */
	List<InviteeBean> queryInviter(@Param("id")Long id, @Param("page")int page, @Param("pageSize")int pageSize);

	/**
	  * @author Administrator
	  * @Description: 查询邀请的人是否获得贷款
	  * @param @param id
	  * @param @return  
	  * @return boolean  
	  * @throws
	  * @date 2015年10月12日 下午2:37:19
	  */
	Integer querySucLoan(@Param("id")Long id);
	
	/**
	  * @author ldm
	  * @Description: 根据邀请人id 查询其邀请的好友申请成功的所有贷款
	  * @param @param id
	  * @param @param page
	  * @param @param pageSize
	  * @param @return  
	  * @return List<InviteeBean>  
	  * @throws
	  * @date 2015年10月12日 下午4:25:31
	  */
	List<InviteeBean> queryLoansByIvtId(@Param("id")Long id, @Param("page")int page, @Param("pageSize")int pageSize);
	

	/**
	  * @author ldm
	  * @Description: 根据邀请人id 查询其邀请的好友申请成功的所有贷款--总数（分页用）
	  * @param @param id
	  * @param @param page
	  * @param @param pageSize
	  * @param @return  
	  * @return List<InviteeBean>  
	  * @throws
	  * @date 2015年10月12日 下午4:25:31
	  */
	Integer queryLoansByIvtIdCount(@Param("id")Long id);

	/**
	  * @author Administrator
	  * @Description: 企业用户查询邀请贷款返利的我的奖励
	  * @param @param id
	  * @param @param page
	  * @param @param pageSize
	  * @param @return  
	  * @return List<InviterAwardBean>  
	  * @throws
	  * @date 2015年10月12日 下午6:09:13
	  */
	List<InviterAwardBean> queryInviterAward(@Param("id")Long id, @Param("page")int page, @Param("pageSize")int pageSize);

	/**
	  * @author gkm
	  * @Description: 查询被邀请人总数
	  * @param @param id
	  * @param @return  
	  * @return Object  
	  * @throws
	  * @date 2015年10月15日 下午3:57:11
	  */
	Integer queryInviterTotal(Long id);

	/**
	  * @author gkm
	  * @Description: 查询奖励总数
	  * @param @param id
	  * @param @return  
	  * @return Object  
	  * @throws
	  * @date 2015年10月15日 下午4:09:43
	  */
	Integer queryAwardTotal(Long id);
	
}