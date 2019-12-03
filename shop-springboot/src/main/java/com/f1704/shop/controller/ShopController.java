package com.f1704.shop.controller;

import com.f1704.shop.model.Shop;
import com.f1704.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/shop/")
public class ShopController {
  @Autowired
  private ShopService shopService;
  @RequestMapping("list")
  public Object getList(Shop shop,Integer pageNo,Integer pageSize){
    return shopService.getList(shop,pageNo,pageSize);
  }
  @RequestMapping("insert")
  public Object add(@RequestBody Shop shop){
    return shopService.insert(shop);
  }
  @RequestMapping("detail")
  public Object getDetailById(Integer id){
    return shopService.getDetailById(id);
  }
  @RequestMapping("delByIds")
  public Object delByIds(String ids){
    return shopService.delByIds(ids);
  }
}
