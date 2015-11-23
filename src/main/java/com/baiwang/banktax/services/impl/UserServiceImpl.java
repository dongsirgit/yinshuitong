package com.baiwang.banktax.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.dao.IUserMapper;
import com.baiwang.banktax.services.iface.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserMapper userDao;

    /**
     * 插入新记录
     * <p>
     * Title: insertSelective
     * </p>
     * <p>
     * Description: 注册用户
     * </p>
     * 
     * @param user
     * @see com.baiwang.banktax.services.iface.IUserService#insertSelective(com.baiwang.banktax.beans.Cuser)
     */
    @Override
    public void insertSelective(Cuser user) {
        userDao.insertSelective(user);
    }

    /**
     * 
     * 根据用户名查询对应记录
     * 
     * @return
     * @see com.baiwang.banktax.services.iface.IUserService#selectByPrimaryKey(java.lang.String)
     */
    @Override
    public Cuser selectByPrimaryKey(String user_name) {

        return userDao.selectByPrimaryKey(user_name);

    }

    /**
     * 根据Id删除对应记录
     * 
     * @param id
     * @see com.baiwang.banktax.services.iface.IUserService#deleteById(int)
     */
    @Override
    public void deleteById(long id) {

        userDao.deleteById(id);

    }

    /**
     * 根据Id修改对应元素
     * 
     * @param user
     * @see com.baiwang.banktax.services.iface.IUserService#updateByIdSelective(com.baiwang.banktax.beans.Cuser)
     */
    /*
     * @Override public void updateByIdSelective(Cuser user) {
     * 
     * userDao.updateByIdSelective(user); }
     */

    /**
     * <p>
     * Title: updatePassById
     * </p>
     * <p>
     * Description:
     * </p>
     * 根据ID重置密码
     * 
     * @param user
     * @see com.baiwang.banktax.services.iface.IUserService#updateByUsername(com.baiwang.banktax.beans.Cuser)
     */
    @Override
    public void updatePassById(Cuser user) {

        userDao.updatePassById(user);

    }

    /**
     * <p>
     * Title: selectByNameAndMail
     * </p>
     * <p>
     * Description:
     * </p>
     * 
     * @param 根据用户名和邮箱,查询用户
     * @param mail
     * @return
     * @see com.baiwang.banktax.services.iface.IUserService#selectByNameAndMail(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Cuser selectByNameAndMail(Cuser user) {

        return userDao.selectByNameAndMail(user);

    }

    /**
     * <p>
     * Title: updateByPrimaryKeySelective
     * </p>
     * <p>
     * Description:根据ID更新登录时间及Ip
     * </p>
     * 
     * @param user
     * @see com.baiwang.banktax.services.iface.IUserService#updateByPrimaryKeySelective(com.baiwang.banktax.beans.Cuser)
     */
    @Override
    public void updateLogById(Cuser user) {
        userDao.updateLogById(user);
    }

    /**
     * <p>
     * Title: selectById
     * </p>
     * <p>
     * Description:
     * </p>
     * 
     * @param id
     * @return
     * @see com.baiwang.banktax.services.iface.IUserService#selectById(long)
     */
    @Override
    public Cuser selectById(long id) {

        return userDao.selectById(id);

    }

    /**
     * <p>
     * Title: selectByMail
     * </p>
     * <p>
     * Description: 查询邮箱是否注册过
     * </p>
     * 
     * @param mail
     * @return
     * @see com.baiwang.banktax.services.iface.IUserService#selectByMail(java.lang.String)
     */
    @Override
    public int selectByMail(String mail) {

        return userDao.selectByMail(mail);

    }

    /**
     * <p>
     * Title: selectByNameAndPass
     * </p>
     * <p>
     * Description: 验证用户名及密码
     * </p>
     * 
     * @param user
     * @return
     * @see com.baiwang.banktax.services.iface.IUserService#selectByNameAndPass(com.baiwang.banktax.beans.Cuser)
     */
    @Override
    public Cuser selectByNameAndPass(Cuser user) {

        return userDao.selectByNameAndPass(user);

    }

    /**
     * 
     * <p>
     * Title: selectUserByMail
     * </p>
     * <p>
     * Description: 根据邮箱获取用户对象
     * </p>
     * 
     * @param mail
     * @return 查询得到的用户
     * @see com.baiwang.banktax.services.iface.IUserService#selectUserByMail(java.lang.String)
     */
    @Override
    public Cuser selectUserByMail(String mail) {

        return userDao.selectUserByMail(mail);

    }

    @Override
    public int selectMaxUid() {
        
        return userDao.selectMaxUid();
        
    }

}
