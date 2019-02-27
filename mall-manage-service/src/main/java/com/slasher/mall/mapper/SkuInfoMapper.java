package com.slasher.mall.mapper;

import com.slasher.mall.bean.SkuInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SkuInfoMapper extends Mapper<SkuInfo> {

    public List<SkuInfo> selectSkuInfoListBySpu(long spuId);
}
