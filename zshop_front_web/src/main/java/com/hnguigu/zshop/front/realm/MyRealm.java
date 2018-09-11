package com.hnguigu.zshop.front.realm;

import com.hnguigu.zshop.domain.Customer;
import com.hnguigu.zshop.service.CustomerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-10 08:02
 **/
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private CustomerService customerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        Customer customer = customerService.getCustomerByNanme(username);

        if (customer != null) {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(customer.getLoginName(), customer.getPassword(), customer.getName());
            setSession("user", customer);
            return info;
        }
        return null;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     */
    private void setSession(Object key, Object value) {

        Subject currentUser = SecurityUtils.getSubject();

        if (null != currentUser) {
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

}
