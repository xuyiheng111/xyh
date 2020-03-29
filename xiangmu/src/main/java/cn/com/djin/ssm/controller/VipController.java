package cn.com.djin.ssm.controller;

import cn.com.djin.ssm.entity.Vip;
import cn.com.djin.ssm.service.Impl.BaseServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vip")
public class VipController extends BaseController<Vip> {
}
