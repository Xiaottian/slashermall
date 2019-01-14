package com.slasher.mall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.slasher.mall.bean.*;
import com.slasher.mall.mapper.*;
import com.slasher.mall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;


    @Override
    public List<BaseCatalog1> getCatalog1() {
        List<BaseCatalog1> baseCatalog1List = baseCatalog1Mapper.selectAll();
        return baseCatalog1List;
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);

        List<BaseCatalog2> baseCatalog2List = baseCatalog2Mapper.select(baseCatalog2);
        return baseCatalog2List;

    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);

        List<BaseCatalog3> baseCatalog3List = baseCatalog3Mapper.select(baseCatalog3);
        return baseCatalog3List;

    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);

        List<BaseAttrInfo> baseAttrInfoList = baseAttrInfoMapper.select(baseAttrInfo);
        return baseAttrInfoList;

    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        // 说明value_name的值没有拿到！
        // 保存数据：编辑数据放到一起来处理。
        // 是否有主键,操作都是指的是平台属性操作
        if (baseAttrInfo.getId() != null && baseAttrInfo.getId().length() > 0) {
            // 有主键 ，则修改
            baseAttrInfoMapper.updateByPrimaryKey(baseAttrInfo);
        } else {
            //没有主键则需要添加，注意一下，当没有主键的时候，数据库的id要设置为null，如果不设置有可能会出现空字符串
            if (baseAttrInfo.getId().length() == 0) {
                baseAttrInfo.setId(null);
            }
            //开始插入数据
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }

        //操作属性值，先将属性值清空
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValue);

        //开始操作属性值列表
        if (baseAttrInfo.getAttrValueList() != null && baseAttrInfo.getAttrValueList().size() > 0) {
            //循环数据
            for (BaseAttrValue attrValue : baseAttrInfo.getAttrValueList()) {
                if (attrValue.getId().length() == 0) {
                    attrValue.setId(null);
                }
                attrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(attrValue);
            }
        }

    }

    @Override
    public BaseAttrInfo getAttrInfo(String attrId) {
        //创建属性对象
        BaseAttrInfo attrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);
        //创建属性值对象
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        //根据attrId字段查询对象
        baseAttrValue.setAttrId(attrInfo.getId());
        List<BaseAttrValue> attrValueList = baseAttrValueMapper.select(baseAttrValue);
        //给属性对象中的属性值集合赋值
        attrInfo.setAttrValueList(attrValueList);
        return attrInfo;
    }

    @Override
    public List<SpuInfo> getSpuInfoList(SpuInfo spuInfo) {
        List<SpuInfo> spuInfoList = spuInfoMapper.select(spuInfo);
        return spuInfoList;
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        // 保存数据，spuinfo，spuimage，spusaleattr，spusaleattrvalue。
        // 保存，更新一起玩。
        if (spuInfo.getId() != null && spuInfo.getId().length() > 0) {
            spuInfoMapper.updateByPrimaryKey(spuInfo);
        } else {
            // 判断key
            if (spuInfo.getId() != null && spuInfo.getId().length() == 0) {
                spuInfo.setId(null);
            }
            spuInfoMapper.insertSelective(spuInfo);
        }

        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (spuImageList != null && spuImageList.size() > 0) {
            // 先删除，在插入
            SpuImage spuImage = new SpuImage();
            spuImage.setSpuId(spuInfo.getId());
            spuImageMapper.delete(spuImage);
            for (SpuImage image : spuImageList) {
                // "" 设置id 为null ，启动自增长
                if (image.getId() != null && image.getId().length() == 0) {
                    image.setId(null);
                }
                // 坑！
                // 因为前台页面传递的数据没有spuId 所以设置 spuId
                image.setSpuId(spuInfo.getId());
                spuImageMapper.insertSelective(image);
            }
        }

        // 先找到SpuSaleAttrList
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (spuSaleAttrList != null && spuSaleAttrList.size() > 0){
            // 属性，属性值 先删除，再插入
            SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
            spuSaleAttr.setSpuId(spuInfo.getId());
            spuSaleAttrMapper.delete(spuSaleAttr);

            SpuSaleAttrValue spuSaleAttrValue = new SpuSaleAttrValue();
            spuSaleAttrValue.setSpuId(spuInfo.getId());
            spuSaleAttrValueMapper.delete(spuSaleAttrValue);
            for (SpuSaleAttr saleAttr : spuSaleAttrList) {
                if (saleAttr.getId() != null && saleAttr.getId().length() == 0) {
                    saleAttr.setId(null);
                }
                // 因为前台页面传递的数据没有spuId 所以设置 spuId
                saleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insertSelective(saleAttr);

                // 插入属性值！
                List<SpuSaleAttrValue> spuSaleAttrValueList = saleAttr.getSpuSaleAttrValueList();
                for (SpuSaleAttrValue saleAttrValue : spuSaleAttrValueList) {
                    if (saleAttrValue.getId() != null && saleAttrValue.getId().length() == 0) {
                        saleAttrValue.setId(null);
                    }
                    // 因为前台页面传递的数据没有spuId 所以设置 spuId
                    saleAttrValue.setSpuId(spuInfo.getId());
                    spuSaleAttrValueMapper.insertSelective(saleAttrValue);
                }
            }
        }

    }
}
