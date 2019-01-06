package com.slasher.mall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class BaseSaleAttr implements Serializable {

    @Id
    @Column
    String id ;

    @Column
    String name;
}
