package com.Sun.o2o.service.impl;

import com.Sun.o2o.dao.ShopDao;
import com.Sun.o2o.dto.ShopExecution;
import com.Sun.o2o.entity.Shop;
import com.Sun.o2o.enums.ShopStateEnum;
import com.Sun.o2o.exceptions.ShopOperationException;
import com.Sun.o2o.service.ShopService;
import com.Sun.o2o.util.ImageUtil;
import com.Sun.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        //1.控制判断
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            //2.给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //3.添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if(effectedNum <= 0){
                //RuntimeException才可以回滚事物,而Exception不会回滚
                throw new ShopOperationException("店铺创建失败");
            }else{
                if(shopImg != null){
                    try{
                        addShopImg(shop,shopImg);
                    }catch(Exception e){
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if(effectedNum <= 0){
                        throw new ShopOperationException("更新图片地址失败");
                    }

                }
            }
        }catch(Exception e){
            throw new ShopOperationException("addShop error" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, File shopImg) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg,dest);
        shop.setShopImg(shopImgAddr);
    }
}
