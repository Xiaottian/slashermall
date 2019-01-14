package com.slasher.mall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.slasher.mall.bean.BaseSaleAttr;
import com.slasher.mall.bean.SpuInfo;
import com.slasher.mall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SpuManageController  {

    @Reference
    private ManageService manageService;

    @RequestMapping("spuListPage")
    public String spuListPage(){
        return "spuListPage";
    }

    @RequestMapping("spuList")
    @ResponseBody
    public List<SpuInfo> getSpuList(String catalog3Id){
        // 创建spuInfo对象
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        List<SpuInfo> spuInfoList = manageService.getSpuInfoList(spuInfo);
        return spuInfoList;
    }

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<BaseSaleAttr> getBaseSaleAttrList(){
        List<BaseSaleAttr> baseSaleAttrList = manageService.getBaseSaleAttrList();
        return baseSaleAttrList;
    }

    // 保存属性
    @RequestMapping(value = "saveSpuInfo",method = RequestMethod.POST)
    @ResponseBody
    public void saveSpuInfo(SpuInfo spuInfo){
        // 调用服务层方法
        manageService.saveSpuInfo(spuInfo);
    }



}
