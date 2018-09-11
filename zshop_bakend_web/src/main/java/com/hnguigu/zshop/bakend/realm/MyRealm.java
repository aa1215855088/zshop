package com.hnguigu.zshop.bakend.realm;

import com.hnguigu.zshop.domain.Sysuser;
import com.hnguigu.zshop.service.CustomerService;
import com.hnguigu.zshop.service.SysuserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-04 15:23
 **/
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysuserService sysuserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) super.getAvailablePrincipal(principalCollection);
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        DefaultPasswordService passwordService = new DefaultPasswordService();

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        Sysuser sysuser = this.sysuserService.getSysuserByName(username);
        if (sysuser != null) {
            // 以下信息从数据库中获取
            // （1）principal：认证的实体信息，可以是email，也可以是数据表对应的用户的实体类对象
            Object principal = username;
            // （2）credentials：密码
            Object credentials = sysuser.getPassword();
            // （3）realmName：当前realm对象的name，调用父类的getName()方法即可
            String realmName = getName();
            // （4）盐值：取用户信息中唯一的字段来生成盐值，避免由于两个用户原始密码相同，加密后的密码也相同
            ByteSource credentialsSalt = ByteSource.Util.bytes(username);

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt,
                    realmName);
            this.setSession("currentUser", sysuser);
            return info;
        } else {
            return null;
        }

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
