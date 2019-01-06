package com.slasher.mall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SpuSaleAttrValue implements Serializable {


    @Id
    @Column
    String id ;

    @Column
    String spuId;

    @Column
    String saleAttrId;

    @Column
    String saleAttrValueName;
}

