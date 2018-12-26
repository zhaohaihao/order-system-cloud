package com.hilox.order.form;

import lombok.Data;

/**
 * Created by Hilox on 2018/12/26 0026.
 */
@Data
public class CategoryForm {

    /** 类目id **/
    private Integer id;

    /** 类目名称 **/
    private String name;

    /** 类目编号 **/
    private Integer code;
}
