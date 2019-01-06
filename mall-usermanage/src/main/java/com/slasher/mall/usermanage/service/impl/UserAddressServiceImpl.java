package com.slasher.mall.usermanage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.slasher.mall.bean.UserAddress;
import com.slasher.mall.service.UserAddressService;
import com.slasher.mall.usermanage.mapper.UserAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        //创建用户对象
        UserAddress userAddress = new UserAddress();
        //将用户的id传递给对象
        userAddress.setUserId(userId);
        //使用通用mapper查出信息
        List<UserAddress> addressList = userAddressMapper.select(userAddress);
        return addressList;
    }
}
