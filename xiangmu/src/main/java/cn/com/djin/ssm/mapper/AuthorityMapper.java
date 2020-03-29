package cn.com.djin.ssm.mapper;

import cn.com.djin.ssm.entity.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMapper extends BaseMapper<Authority>{

    //根据角色id和parent查询（一级二级）权限数据
    List<Authority> selAuthByRoleIdAndParent(@Param("roleId") Integer roleId, @Param("parent") Integer parent) throws Exception;

}