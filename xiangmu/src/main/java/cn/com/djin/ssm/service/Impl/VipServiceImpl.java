package cn.com.djin.ssm.service.Impl;

import cn.com.djin.ssm.entity.Vip;
import cn.com.djin.ssm.service.VipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class VipServiceImpl extends BaseServiceImpl<Vip> implements VipService {
}
