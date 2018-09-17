package com.hnguigu.zshop.front.web.interceptor;

import com.hnguigu.zshop.domain.Customer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-14 09:00
 **/
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Customer user = (Customer) session.getAttribute("user");
        if (user != null) {
            return true;
        }
        request.setAttribute("login", "yes");
        request.getRequestDispatcher("/zshop/home").forward(request, response);
        return false;
    }
}
