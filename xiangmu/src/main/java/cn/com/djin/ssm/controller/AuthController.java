package cn.com.djin.ssm.controller;

import cn.com.djin.ssm.entity.Authority;
import cn.com.djin.ssm.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 *   权限的控制器
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController<Authority> {

    @RequestMapping("/toIndex")
    public String toIndex(Model model, HttpSession session){
        //根据登陆的用户信息查询出其拥有的权限，放入model对象中带入到平台首页
        User loginUser = (User) session.getAttribute("loginUser");
        try {
            model.addAttribute("listMap",authService.findAuthByLogin(loginUser));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }


}
