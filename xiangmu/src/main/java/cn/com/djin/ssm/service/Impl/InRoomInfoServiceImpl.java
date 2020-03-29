package cn.com.djin.ssm.service.Impl;

import cn.com.djin.ssm.entity.InRoomInfo;
import cn.com.djin.ssm.service.InRoomInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *   房屋入住信息业务层实现类
 */
@Service
@Transactional(readOnly = false)
public class InRoomInfoServiceImpl extends BaseServiceImpl<InRoomInfo> implements InRoomInfoService {

}
