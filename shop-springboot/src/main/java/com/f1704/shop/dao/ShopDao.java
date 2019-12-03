package com.f1704.shop.dao;

import com.f1704.shop.model.Shop;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopDao {
  List<Shop> selectList(@Param("shop") Shop shop);

  int insert(@Param("shop") Shop shop);
  int update(@Param("shop") Shop shop);
  @Select("select * from shop where id=#{id}")
  Shop getDetailById(@Param("id") Integer id);
  @Delete("delete from shop where id in (${ids})")
  int delByIds(@Param("ids") String ids);
}
