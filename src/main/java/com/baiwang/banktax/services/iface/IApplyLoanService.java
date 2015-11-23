/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.iface;

import java.util.List;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.InviteeBean;
import com.baiwang.banktax.beans.InviterAwardBean;
import com.baiwang.banktax.beans.Puser;

/**
  * @ClassName: IApplyLoanService
  * @Description: 申请 贷款 service层 接口
  * @author gkm
  * @date 2015年8月3日 上午10:15:24
  */
public interface IApplyLoanService {

	/*public int saveBasicInfo(ApplyLoan applyLoan,ServiceOrg serviceOrg, String companyName);*/

	/**
	  * @Description: TODO
	  * @param @param uid
	  * @param @return  
	  * @return String  
	  * @throws
	  */
	/*public String getCorpName(Long uid);*/

	/**
	  * @Description: 查询
	  * @param @param id
	  * @param @param uid
	  * @param @return  
	  * @return ApplyLoan  
	  * @throws
	  * @version 2
	  */
	public ApplyLoan queryApplyLoan(Long id, Long uid);

	/**
	  * @Description: 取纳税人纳税号
	  * @param @param uid
	  * @param @return  
	  * @return String  
	  * @throws
	  */
	/*public String getTaxNo(Long uid);*/

	/**
	  * @Description: 根据申请id查出抵押房地产
	  * @param @param id
	  * @param @return  
	  * @return ApplyLoanGuaranty  
	  * @throws
	  */
	/*public List<ApplyLoanGuaranty> queryGuaranty(Long id);*/

	/**
	  * @Description: 保存 贷款背景
	  * @param @param applyLoan
	  * @param @param guaranty1
	  * @param @param guaranty2
	  * @param @return  
	  * @return int  
	  * @throws
	  */
	/*public int saveBcgroud(ApplyLoan applyLoan, ApplyLoanGuaranty guaranty1, ApplyLoanGuaranty guaranty2);*/

	/**
	  * @Description: 保存贷款法人信息
	  * @param @param applyLoan
	  * @param @return  
	  * @return int  
	  * @throws
	  */
	/*public int saveOwner(ApplyLoan applyLoan);*/

	/**
	  * @Description: 提交
	  * @param @param id
	  * @param @param uid
	  * @param @return  
	  * @return int  
	  * @throws
	  * @version 2
	  */
	public int submit(Long id, Long uid);

	/**
	  * @Description: 获取机构 省份
	  * @param @return  
	  * @return List<String>  
	  * @throws
	  */
	/*public List<String> getProvince();*/

	/**
	 * @param province 
	  * @Description: 根据省份取城市List
	  * @param @return  
	  * @return List<String>  
	  * @throws
	  */
	/*public List<String> getCity(String province);*/
	
	/**
	 * 
	  * @Description: 据省份,城市 取机构List
	  * @param @param province 省份
	  * @param @param city 城市
	  * @param @return  
	  * @return List<ServiceOrg>  
	  * @throws
	 */
	/*public List<ServiceOrg> getServiceOrg(String province,String city );*/

	/**
	  * @Description: 查询选择的服务机构
	  * @param @param serviceOrgId
	  * @param @return  
	  * @return ServiceOrg  
	  * @throws
	  */
	/*public ServiceOrg getServiceOrg(Integer serviceOrgId);*/

	/**
	  * @Description: TODO
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  * @version 2
	  */
	public int deleteApplyLoan(Long id);
	/**
	  * @Description: 查询贷款申请单4浏览
	  * @param @param id
	  * @param @param uid
	  * @param @return  
	  * @return ApplyLoan  
	  * @throws
	  * @version 2
	  */
	public ApplyLoan queryApplyLoan4show(Long id, Long uid);

	/**
	  * @Description: 查询用户信息
	  * @param @param uid
	  * @param @return  
	  * @return Cuser  
	  * @throws
	  * @version 2
	  */
	public Cuser getCuser(Long id);

	/**
	  * @Description: 完善企业信息
	  * @param @param cuser
	  * @param @return  
	  * @return int  
	  * @throws
	  * @version 2
	  */
	public int saveUserInfo(Cuser cuser);
	/**
	 * @Description: 保存贷款信息（包含基本信息、贷款背景、法人信息）
	 * @param @param cuser
	 * @param @return  
	 * @return int  
	 * @throws
	 */
	/*public int saveApplyInfo(ApplyLoan applyLoan ,ApplyLoanGuaranty guaranty1, ApplyLoanGuaranty guaranty2);*/

	/**
	  * @Description: 根据纳税号 查询企业信息
	  * @param @param cuser
	  * @param @return  
	  * @return Cuser  
	  * @throws
	  * @version 2
	  */
	public Cuser getCuserByTaxSn(Cuser cuser);

	/**
	  * @Description:根据企业名称查询企业信息
	  * @param @param cuser
	  * @param @return  
	  * @return Cuser  
	  * @throws
	  * @version 2
	  */
	public Cuser getCuserByCorpName(Cuser cuser);

	/**
	  * @author gkm
	  * @Description: 保存企业申请贷款 2.0
	  * @param @param loan
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年9月6日 下午2:03:43
	  * version 2.0
	  */
	public int saveLoan(ApplyLoan loan);

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param puser
	  * @param @return  
	  * @return Puser  
	  * @throws
	  * @date 2015年9月7日 上午10:53:34
	  * version 2.0
	  */
	public Puser getPuserByIdcard(Puser puser);

	/**
	  * @author Administrator
	  * @Description: 完善个人信息
	  * @param @param puser
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年9月7日 上午11:07:29
	  * version 2.0
	  */
	public int saveUserInfo(Puser puser);

	/**
	  * @author Administrator
	  * @Description: 查询Puser放入session
	  * @param @param id
	  * @param @return  
	  * @return Puser  
	  * @throws
	  * @date 2015年9月7日 上午11:15:52
	  * version 2.0
	  */
	public Puser getPuser(Long id);

	/**
	  * @author gkm
	  * @Description: 企业用户查询邀请的人
	  * @param @param id
	  * @param @param page
	  * @param @param pageSize
	  * @param @return  
	  * @return List<InviteeBean>  
	  * @throws
	  * @date 2015年10月12日 下午2:03:57
	  */
	public List<InviteeBean> queryInviter(Long id, int page, int pageSize);

	/**
	  * @author Administrator
	  * @Description: 企业用户查询邀请贷款返利的我的奖励
	  * @param @param id
	  * @param @param i
	  * @param @param pageSize
	  * @param @return  
	  * @return List<InviterAwardBean>  
	  * @throws
	  * @date 2015年10月12日 下午5:38:28
	  */
	public List<InviterAwardBean> queryInviterAward(Long id, int page, int pageSize);
	
	/**
	  * @author ldm
	  * @Description: (金赋用户)根据邀请人id 查询其邀请的好友申请成功的所有贷款
	  * @param @param id
	  * @param @param page
	  * @param @param pageSize
	  * @param @return  
	  * @return List<InviteeBean>  
	  * @throws
	  * @date 2015年10月12日 下午3:36:14
	  */
	public List<InviteeBean> queryLoansByIvtId(Long id, int page, int pageSize);
	/**
	 * @author ldm
	 * @Description: (金赋用户)根据邀请人id 查询其邀请的好友申请成功的所有贷款--总数
	 * @param @param id
	 * @param @return  
	 * @return List<InviteeBean>  
	 * @throws
	 * @date 2015年10月12日 下午3:36:14
	 */
	public Integer queryLoansByIvtIdCount(Long id);

	/**
	  * @author gkm
	  * @Description: 查询被邀请人总数
	  * @param @param id
	  * @param @return  
	  * @return Object  
	  * @throws
	  * @date 2015年10月15日 下午3:57:11
	  */
	
	public Integer queryInviterTotal(Long id);

	/**
	  * @author gkm
	  * @Description: 查询奖励总数
	  * @param @param id
	  * @param @return  
	  * @return Object  
	  * @throws
	  * @date 2015年10月15日 下午4:09:43
	  */
	public Integer queryAwardTotal(Long id);
	
}
