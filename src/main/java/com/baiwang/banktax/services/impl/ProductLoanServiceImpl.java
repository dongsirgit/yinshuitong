package com.baiwang.banktax.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.ProductLoan;
import com.baiwang.banktax.dao.ProductLoanMapper;
import com.baiwang.banktax.services.iface.IProductLoanService;
/**
 * 
  * @ClassName: ProductLoanServiceImpl
  * @Description: TODO
  * @author Yinhua
  * @date 2015年8月4日 上午10:25:41
 */
@Service("productLoanService")
public class ProductLoanServiceImpl implements IProductLoanService {
	@Resource
	private ProductLoanMapper productLoanDao;

/**
 * 插入新记录【用户前台不用】
  * <p>Title: insertSelective</p>
  * <p>Description: </p>
  * @param pl (ProductLoan)
  * @see com.baiwang.banktax.services.iface.IProductLoanService#insertSelective(com.baiwang.banktax.beans.ProductLoan)
 */
	@Override
	public void insertSelective(ProductLoan pl) {
		productLoanDao.insertSelective(pl);
	}
/**
 * 
  *根据ID查询对应记录
  * @return
  * @see com.baiwang.banktax.services.iface.IProductLoanService#selectByPrimaryKey(Integer)
 */
	@Override
	public ProductLoan selectByPrimaryKey(Integer pid) {
		ProductLoan pl=productLoanDao.selectByPrimaryKey(pid);
		return pl;
	}

	/**
	  * 根据Id删除对应记录【用户前台不用】
	  * @param id
	  * @see com.baiwang.banktax.services.iface.IProductLoanService#deleteById(int)
	  */
	@Override
	public void deleteById(int id) {
		productLoanDao.deleteByPrimaryKey(id);
	}

	/**
	  *根据Id修改对应元素【用户前台不用】
	  * @param user
	  * @see com.baiwang.banktax.services.iface.IProductLoanService#updateByIdSelective(com.baiwang.banktax.beans.ProductLoan)
	  */
	@Override
	public void updateByIdSelective(ProductLoan pl) {
		productLoanDao.updateByPrimaryKeySelective(pl);
	}
	
	/**
	  *根据银行列出贷款产品
	  * @param bank
	  * @see com.baiwang.banktax.services.iface.IProductLoanService#selectByBank(String)
	  */
	@Override
	public List<ProductLoan> selectByBank(String bank) {
		// TODO Auto-generated method stub
		return productLoanDao.selectByBank(bank);
	}
	

}
