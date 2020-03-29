package cn.com.djin.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/model")
public class ModelController {
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/toShowInRoomInfo")
    public String InRoomInfo(){
        return "InRoomInfo/ShowInRoomInfo";
    }

    //去到入住信息显示页面
    @RequestMapping("/toShowOrders")
    public String toShowOrders(){
        return "orders/showOrders";
    }

    //去到入住信息显示页面
    @RequestMapping("/toSaveInRoomInfo")
    public String toSaveInRoomInfo(){
        return "InRoomInfo/saveInRoomInfo";
    }

    //去到消费记录信息显示页面
    @RequestMapping("/toShowRoomSale")
    public String toShowRoomSale(){
        return "roomSale/showRoomSale";
    }

    //去到消费记录信息显示页面
    @RequestMapping("/toShowVip")
    public String toShowVip(){
        return "vip/showVip";
    }

    //去到消费记录信息显示页面
    @RequestMapping("/toSaveVip")
    public String toSaveVip(){
        return "vip/saveVip";
    }

    //去到房间信息显示页面
    @RequestMapping("/toShowRooms")
    public String toShowRooms(){
        return "rooms/showRooms";
    }

    //去到房间信息显示页面
    @RequestMapping("/loginUI")
    public String loginUI(){
        return "login/login";
    }

    //去到房间信息显示页面
    @RequestMapping("/toShowRole")
    public String toShowRole(){
        return "role/showRole";
    }
}
