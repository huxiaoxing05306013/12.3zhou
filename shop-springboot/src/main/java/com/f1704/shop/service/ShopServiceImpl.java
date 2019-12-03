package com.f1704.shop.service;

import com.f1704.shop.dao.ShopDao;
import com.f1704.shop.model.Shop;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
  @Autowired
  private ShopDao shopDao;

  @Override
  public PageInfo getList(Shop shop, Integer pageNo, Integer pageSize) {
    PageHelper.startPage(pageNo,pageSize);
    List<Shop> shopList=shopDao.selectList(shop);
    return new PageInfo(shopList);
  }

  @Override
  public boolean insert(Shop shop) {
    if(shop.getId()==null){
      shopDao.insert(shop);
    }else{
      shopDao.update(shop);
    }
    return true;
  }

  @Override
  public Shop getDetailById(Integer id) {
    return shopDao.getDetailById(id);
  }

  @Override
  public boolean delByIds(String ids) {
    return shopDao.delByIds(ids)>0;
  }
}
