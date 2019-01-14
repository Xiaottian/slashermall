package com.slasher.mall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.slasher.mall.bean.*;
import com.slasher.mall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AttrManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping("attrListPage")
    public String getAttrListPage() {
        return "attrListPage";
    }


    @RequestMapping(value = "getCatalog1",method = RequestMethod.POST)
    @ResponseBody
    public List<BaseCatalog1> getCatalog1() {
        return manageService.getCatalog1();
    }

    @RequestMapping(value = "getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        return manageService.getCatalog2(catalog1Id);
    }

    @RequestMapping(value = "getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        return manageService.getCatalog3(catalog2Id);
    }

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<BaseAttrInfo> attrInfoList(String catalog3Id){
        return manageService.getAttrList(catalog3Id);
    }


    @RequestMapping(value = "saveAttrInfo",method = RequestMethod.POST)
    @ResponseBody
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo){
        manageService.saveAttrInfo(baseAttrInfo);
    }

    // 返回空没有任何显示的消息
    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<BaseAttrValue> getAttrValueList(String attrId){
        //根据attrId进行查询
        BaseAttrInfo attrInfo = manageService.getAttrInfo(attrId);
        return attrInfo.getAttrValueList();
    }
}
