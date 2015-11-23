/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baiwang.banktax.beans.ApplyLoan;
import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.InviteeBean;
import com.baiwang.banktax.beans.InviterAwardBean;
import com.baiwang.banktax.beans.Puser;
import com.baiwang.banktax.dao.ApplyLoanMapper;
import com.baiwang.banktax.services.iface.IApplyLoanService;

/**
  * @ClassName: ApplyLoanServiceImpl
  * @Description: 贷款基本信息
  * @author gkm
  * @date 2015年8月3日 上午10:16:15
  */
@Service
public class ApplyLoanServiceImpl implements IApplyLoanService {
	
	@Resource
	private ApplyLoanMapper applyLoanDao;
	

	/**
	  * @Description: 查询
	  * @param @param id
	  * @param @param uid
	  * @param @return  
	  * @return ApplyLoan  
	  * @throws
	  * @version 2
	  */
	@Override
	public ApplyLoan queryApplyLoan(Long id, Long uid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("uid",uid);
		
		return applyLoanDao.queryApplyLoan(map);
	}
	
	/**
	  * @Description: 提交
	  * @param @param id
	  * @param @param uid
	  * @param @return  
	  * @return int  
	  * @throws
	  * @version 2
	  */
	@Transactional
	public int submit(Long id, Long uid){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("uid",uid);
		int suc = applyLoanDao.submit(map);
		if(suc == 1){
			//邀请码
			Cuser cuser = applyLoanDao.getCuser(uid);
			if(null != cuser && null != cuser.getInviteUid() && 0 != cuser.getInviteUid()){
				suc = applyLoanDao.insertRebate(id,uid,cuser.getInviteUid());
			}
			return suc;
		}
		return suc;
	}
	
	/**
	  * @Description: 逻辑删除贷款申请单
	  * @param @param id
	  * @param @return  
	  * @return int  
	  * @throws
	  * @version 2
	  */
	public int deleteApplyLoan(Long id){
		return applyLoanDao.delApplyLoan(id);
	}
	
	
	/**
	  * @Description: 查询贷款申请单4浏览
	  * @param @param id
	  * @param @param uid
	  * @param @return  
	  * @return ApplyLoan  
	  * @throws 
	  * @version 2
	  */
	@Override
	public ApplyLoan queryApplyLoan4show(Long id, Long uid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("uid",uid);
		return applyLoanDao.queryApplyLoan4show(map);
	}
	
	/**
	  * @Description: 查询用户信息
	  * @param @param id
	  * @param @return  
	  * @return Cuser  
	  * @throws
	  * @version 2
	  */
	public Cuser getCuser(Long id){
		return applyLoanDao.getCuser(id);
	}
	
	/**
	  * @Description: 完善企业信息
	  * @param @param cuser
	  * @param @return  
	  * @return int  
	  * @throws
	  * @version 2
	  */
	public int saveUserInfo(Cuser cuser){
		return applyLoanDao.updateUserInfo(cuser);
	}
	
	/**
	  * @Description: 根据纳税号 查询企业信息
	  * @param @param taxSn
	  * @param @return  
	  * @return Cuser  
	  * @throws
	  * @version 2
	  */
	public Cuser getCuserByTaxSn(Cuser cuser){
		return applyLoanDao.getCuserByTaxSn(cuser);
	}
	/**
	  * @Description:根据企业名称查询企业信息
	  * @param @param cuser
	  * @param @return  
	  * @return Cuser  
	  * @throws
	  * @version 2
	  */
	public Cuser getCuserByCorpName(Cuser cuser){
		return applyLoanDao.getCuserByCorpName(cuser);
	}
	
	/**
	  * @author gkm
	  * @Description: 保存企业申请贷款 2.0
	  * @param @param loan
	  * @param @return  
	  * @return int  
	  * @throws
	  * @date 2015年9月6日 下午2:03:43
	  * @version 2.0
	  */
	public int saveLoan(ApplyLoan loan){
		if(null != loan && null != loan.getId() && 0 != loan.getId()){
			return applyLoanDao.updateLoan(loan);
		}
		
		return applyLoanDao.saveLoan(loan);
	}
	
	/**
	  * @author Administrator
	  * @Description: 根据身份证号查询 个人信息
	  * @param @param puser
	  * @param @return  
	  * @return Puser  
	  * @throws
	  * @date 2015年9月7日 上午10:53:34
	  * @version 2.0
	  */
	public Puser getPuserByIdcard(Puser puser){
		return applyLoanDao.getPuserByIdcard(puser);
	}
	
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
	public int saveUserInfo(Puser puser){
		return applyLoanDao.updateUserInfoPerson(puser);
	}
	
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
	public Puser getPuser(Long id){
		return applyLoanDao.getPuser(id);
	}

	/**
	  * <p>Title: queryInviter</p>
	  * <p>企业用户查询邀请的人: </p>
	  * @param id
	  * @param page
	  * @param pageSize
	  * @return
	  * @see com.baiwang.banktax.services.iface.IApplyLoanService#queryInviter(java.lang.Long, int, int)
	  */
	@Override
	public List<InviteeBean> queryInviter(Long id, int page, int pageSize) {
		
		List<InviteeBean> list = applyLoanDao.queryInviter(id, page, pageSize);
		if(null != list){
			for(int i = 0; i<list.size(); i++){
				if(applyLoanDao.querySucLoan(list.get(i).getId())>0){
					list.get(i).setSucLoan("是");
				}else{
					list.get(i).setSucLoan("否");
				}
			}
		}
		return list;
	}

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
	@Override
	public List<InviteeBean> queryLoansByIvtId(Long id, int page, int pageSize) {
		
		return applyLoanDao.queryLoansByIvtId(id, page, pageSize);
	}
	/**
	 * @author ldm
	 * @Description: 根据邀请人id 查询其邀请的好友申请成功的所有贷款--总数（分页用）
	 * @param @param id
	 * @param @return  
	 * @return List<InviteeBean>  
	 * @throws
	 * @date 2015年10月12日 下午4:25:31
	 */
	@Override
	public Integer queryLoansByIvtIdCount(Long id) {
		return applyLoanDao.queryLoansByIvtIdCount(id);
	}

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
	public List<InviterAwardBean> queryInviterAward(Long id, int page, int pageSize) {
		
		return applyLoanDao.queryInviterAward(id, page, pageSize);
	}

	/**
	  * @author gkm
	  * @Description: 查询被邀请人总数
	  * @param @param id
	  * @param @return  
	  * @return Object  
	  * @throws
	  * @date 2015年10月15日 下午3:57:11
	  */
	public Integer queryInviterTotal(Long id){
		Integer total = applyLoanDao.queryInviterTotal(id);
		
		return total==null?0:total;
	}

	/**
	  * @author gkm
	  * @Description: 查询奖励总数
	  * @param @param id
	  * @param @return  
	  * @return Object  
	  * @throws
	  * @date 2015年10月15日 下午4:09:43
	  */
	public Integer queryAwardTotal(Long id){
		Integer total = applyLoanDao.queryAwardTotal(id);
		
		return total==null?0:total;
	}
	
}
