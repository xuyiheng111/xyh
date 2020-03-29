package cn.com.djin.ssm.service.Impl;

import cn.com.djin.ssm.entity.Authority;
import cn.com.djin.ssm.entity.User;
import cn.com.djin.ssm.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   权限业务层实现类
 */
@Service
@Transactional(readOnly = false)
public class AuthServiceImpl extends BaseServiceImpl<Authority> implements AuthService {

    //根据角色id和parent查询（一级二级）权限数据
    @Override
    public List<Map<String,Object>> findAuthByLogin(User loginUser) throws Exception {
//        loginUser = new User();
//        loginUser.setRoleId(1);
        //1.新建一个一级权限以及其包含的二级权限的listMap集合(所有的权限)
        List<Map<String,Object>> listMap = new ArrayList<Map<String, Object>>();
        //2.查询出此用户拥有的一级权限
        List<Authority> firstAuths = authorityMapper.selAuthByRoleIdAndParent(loginUser.getRoleId(),0);
//        System.out.println(loginUser);
//        System.out.println(loginUser.getRoleId());
//        System.out.println(firstAuths);
        //3.通过循环操作一级权限得到一级权限和二级权限的Map集合
        for (Authority firstAuth:firstAuths) {
            //4.新建Map集合，装一级权限和二级权限
            Map<String,Object> dataMap = new HashMap<String, Object>();
            dataMap.put("pName",firstAuth.getAuthorityName());  //装一级权限的名字
            dataMap.put("pId",firstAuth.getId());  //装一级权限的id
            //5.将此一级权限所拥有的二级权限查询出来
            List<Authority> secAuths = authorityMapper.selAuthByRoleIdAndParent(loginUser.getRoleId(), firstAuth.getId());
            //6.将查询出的二级权限装入到Map中
            dataMap.put("secAuths",secAuths);
            //7.将构建的dataMap装入到listMap集合(所有的权限)
//            System.out.println(dataMap);
            listMap.add(dataMap);
        }
        return listMap;
    }
}
