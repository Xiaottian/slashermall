package com.slasher.mall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.slasher.mall.bean.SkuInfo;
import com.slasher.mall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {

    @Reference
    private ManageService manageService;

    /*restful风格 skuId=商品的id号*/
    @RequestMapping("/{skuId}.html")
    public String skuInfo(@PathVariable(value = "skuId")String skuId, Model model){

        /*根据skuId查询商品详细信息，以及商品对应的skuImg的信息*/
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
        /*保存对象,在页面中显示*/
        model.addAttribute("skuInfo",skuInfo);
        return "item";
    }
}
