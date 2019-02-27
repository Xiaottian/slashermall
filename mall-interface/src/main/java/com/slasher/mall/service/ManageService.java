package com.slasher.mall.service;

import com.slasher.mall.bean.*;

import java.util.List;

public interface ManageService {

    //查询一级分类的所有
    List<BaseCatalog1> getCatalog1();

    //根据一级分类的id查询二级分类
    public List<BaseCatalog2> getCatalog2(String catalog1Id);

    //根据二级分类的id查询三级分类
    public List<BaseCatalog3> getCatalog3(String catalog2Id);

    //根据三级分类的id查询属性
    public List<BaseAttrInfo> getAttrList(String catalog3Id);

    //保存数据分发
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);
    // 获取属性值数据
    BaseAttrInfo getAttrInfo(String attrId);
    // 查询spu列表信息
    List<SpuInfo> getSpuInfoList(SpuInfo spuInfo);
    // 查询所有销售属性列表
    public List<BaseSaleAttr> getBaseSaleAttrList();
    // 保存spuInfo的信息
    void saveSpuInfo(SpuInfo spuInfo);

    SkuInfo getSkuInfo(String skuId);
}
