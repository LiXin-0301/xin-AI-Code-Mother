package com.lixin.xinaicodemother.service;

import com.lixin.xinaicodemother.model.dto.user.UserQueryRequest;
import com.lixin.xinaicodemother.model.vo.LoginUserVO;
import com.lixin.xinaicodemother.model.vo.UserVO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.lixin.xinaicodemother.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 *  服务层。
 *
 * @author <a href="https://github.com/LiXin-0301">LixinCode</a>
 */
public interface UserService extends IService<User> {

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 获取脱敏后用户信息
     *
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏后用户信息（分页）
     *
     * @param 
     * @return
     */
    List<UserVO> getUserVOList(List<User> user);

    /**
     * 用户注销
     *
     * @param request
     * @return 注销是否成功
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 根据查询条件构造用户查询参数
     *
     * @param userQueryRequest
     * @return 用户查询适配格式
     */
    QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);


    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    String getEncryptPassword(String userPassword);


}
