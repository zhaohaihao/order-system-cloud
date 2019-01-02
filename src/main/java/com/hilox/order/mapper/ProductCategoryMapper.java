package com.hilox.order.mapper;

import com.hilox.order.model.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * 商品类目Mapper
 * Created by Hilox on 2019/1/2 0002.
 */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(name, code) values (#{name, jdbcType=VARCHAR}, #{code, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(name, code) values (#{name, jdbcType=VARCHAR}, #{code, jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where code = #{code}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "code", property = "code"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    ProductCategory findByCode(Integer code);

    @Update("update product_category set name = #{name} where code = #{code}")
    int updateByCode(@Param("name") String name, @Param("code") Integer code);

    @Update("update product_category set name = #{name} where code = #{code}")
    int updateByObject(ProductCategory productCategory);
}
