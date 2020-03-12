package com.Sun.o2o.web.superadmin;

import com.Sun.o2o.entity.Area;
import com.Sun.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listArea(){
        logger.info("====strat======");
        long stratTime = System.currentTimeMillis();
        Map<String,Object> modelMap = new HashMap<>();
        List<Area> list = new ArrayList<>();
        try{
            list = areaService.getAreaList();
            //此处map key的命名主要和即将要使用的一个前端框架有关.
            modelMap.put("rows",list);
            modelMap.put("total",list.size());
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        long endTime = System.currentTimeMillis();
//        {}是占位符
        logger.error("test error");
        logger.debug("costTime:[{}ms]",endTime-stratTime);
        logger.info("====end======");
        return modelMap;
    }
}
