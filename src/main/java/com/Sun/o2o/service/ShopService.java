package com.Sun.o2o.service;

import com.Sun.o2o.dto.ShopExecution;
import com.Sun.o2o.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
