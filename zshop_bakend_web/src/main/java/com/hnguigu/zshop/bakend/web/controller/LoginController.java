package com.hnguigu.zshop.bakend.web.controller;

import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.common.util.VerifyCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author 徐子楼
 */
@Controller
@RequestMapping("/backend")
public class LoginController {


    @RequestMapping("loginUI")
    public String loginUI() {
        return "login";
    }

    @RequestMapping("main")
    public String main() {
        return "main";
    }

    /**
     * x
     * 验证码图片
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute("verifyCode", verifyCode);
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    @RequestMapping("login")
    @ResponseBody
    public ResponseResult login(String username, String password, HttpSession session, String verifyCode) {

        //获得验证码
        String code = (String) session.getAttribute("verifyCode");
        //首先判断验证码是否正确
        if (StringUtils.isEmpty(verifyCode) || !StringUtils.equals(verifyCode, code)) {
            return ResponseResult.fail("请输入正确的验证码");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return ResponseResult.fail("没有此用户!");
        } catch (IncorrectCredentialsException ice) {
            return ResponseResult.fail("密码不正确!");
        } catch (LockedAccountException lae) {
            return ResponseResult.fail("账户已锁定!");
        } catch (ExcessiveAttemptsException eae) {
            return ResponseResult.fail("用户名或密码错误次数过多!");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            ae.printStackTrace();
            return ResponseResult.fail("用户名或密码有误,请重新输入!");
        }

        if (subject.isAuthenticated()) {
            return ResponseResult.success();
        } else {
            token.clear();
            return ResponseResult.fail("系统正在维护请稍后重试!");
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/backend/loginUI";
    }
}
