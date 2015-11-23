package com.baiwang.banktax.services.iface;

import java.util.List;

import com.baiwang.banktax.beans.ProductLoan;
/**
 * 
  * @ClassName: IProductLoanService
  * @Description: TODO 贷款产品的基本操作（还需几个查询）
  * @author Yinhua
  * @date 2015年8月4日 上午10:24:43
 */
public interface IProductLoanService {
	public void insertSelective(ProductLoan pl);  
	public ProductLoan selectByPrimaryKey(Integer pid);
	public List<ProductLoan> selectByBank(String bank);
	public void deleteById(int id);
	public void updateByIdSelective(ProductLoan pl);
}
