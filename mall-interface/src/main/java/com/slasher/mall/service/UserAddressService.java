package com.slasher.mall.service;

import com.slasher.mall.bean.UserAddress;

import java.util.List;

public interface UserAddressService {
    //通过用户的id查询用户的地址

    List<UserAddress> getUserAddressList(String userId);
}
