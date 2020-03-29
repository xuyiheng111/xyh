package cn.com.djin.ssm.interceptor;

import cn.com.djin.ssm.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //根据request请求对象得到session容器
        HttpSession session = request.getSession();
        //从session中取到登陆的用户
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser!=null){  //登陆
            return true;  //放行，继续执行其他的拦截器
        }else {   //未登陆
            request.setAttribute("loginUIMsg","loginUIMsg");  //存提示
            request.getRequestDispatcher("/model/loginUI").forward(request,response);  //转发到登陆页面
            return false;  //阻止请求继续向下执行，将请求拦截下来
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
