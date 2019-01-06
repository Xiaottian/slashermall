package com.slasher.mall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SpuImage implements Serializable {

    @Column
    @Id
    private String id;
    @Column
    private String spuId;
    @Column
    private String imgName;
    @Column
    private String imgUrl;
}
