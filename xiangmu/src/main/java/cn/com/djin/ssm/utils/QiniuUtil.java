package cn.com.djin.ssm.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QiniuUtil {

    //设置好账号的ACCESS_KEY和SECRET_KEY;这两个登录七牛账号里面可以找到 
    static String ACCESS_KEY = "6EXDUqaAoHhJDEJbrF0sgNSSX6Qmg9_mXMG5uKgn";
    static String SECRET_KEY = "5yqVBEv-DQ_0Rwjq50FndUUf6qbNFJB8Du_Ff4vB";
    //要上传的空间;对应到七牛上（自己建文件夹 注意设置公开）  
    static String bucketname = "xyh-image";
    //上传文件的路径 ;本地要上传文件路径  
    //String FilePath = "E:\\BaiduNetdiskDownload\\imgResources\\46991d0e-b77c-428f-b925-08cf08dd2c93.png";
    static final String yupath = "q1rz4ccg3.bkt.clouddn.com";
    //密钥配置
    static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象  
    static UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone2()));
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了  
    public static String getUpToken(){
        return auth.uploadToken(bucketname);  
    }  
  
    //普通上传
    public static Map<String,Object> upload(MultipartFile myFile){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
        //上传到七牛后保存的文件名
        String key = UUID.randomUUID().toString().replace("-", "");
        //调用put方法上传  
        Response res = uploadManager.put(myFile.getBytes(),key,getUpToken());
        //打印返回的信息  
        System.out.println(res.bodyString());
            //上传后的文件名字(文件的访问路径)
            String newFileName = "http://"+ yupath +"/"+ key;
            map.put("newFileName",newFileName);
            map.put("code",0);  //上传的成功结果
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",200);  //上传的失败结果
        }
        return map;
    }  

}  
	

