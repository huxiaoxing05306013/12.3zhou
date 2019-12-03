package com.f1704.shop.service;

import com.f1704.shop.model.Shop;
import com.github.pagehelper.PageInfo;

public interface ShopService {
  PageInfo getList(Shop shop, Integer pageNo, Integer pageSize);

  boolean insert(Shop shop);

  Shop getDetailById(Integer id);

  boolean delByIds(String ids);
}
