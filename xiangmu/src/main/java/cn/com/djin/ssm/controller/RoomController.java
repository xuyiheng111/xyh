package cn.com.djin.ssm.controller;

import cn.com.djin.ssm.entity.Rooms;
import cn.com.djin.ssm.utils.FileUploadUtil;
import cn.com.djin.ssm.utils.QiniuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 *   房屋的控制器层
 */
@Controller
@RequestMapping("/rooms")
public class RoomController extends BaseController<Rooms> {
    //上传房屋的封面图片
    @RequestMapping("/uploadRoomPic")
    public @ResponseBody
    Map<String,Object> uploadRoomPic(MultipartFile myFile){
//        return FileUploadUtil.upload(myFile,path);
        return QiniuUtil.upload(myFile);
    }
}
