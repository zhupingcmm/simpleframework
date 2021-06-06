package com.imooc.service.combine.impl;

import com.imooc.entity.bo.HeadLine;
import com.imooc.entity.bo.ShopCategory;
import com.imooc.entity.dto.MainPageInfoDTO;
import com.imooc.entity.dto.Result;
import com.imooc.service.combine.HeadLineShopCategoryCombineService;
import com.imooc.service.solo.HeadLineService;
import com.imooc.service.solo.ShopCategoryService;
import org.simplefrawork.core.annotation.Service;
import org.simplefrawork.inject.annotation.Autowired;

import java.util.List;

@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
    @Autowired(name = "HeadLineServiceImpl")
    private HeadLineService headLineService;
    @Autowired(name = "ShopCategoryServiceImpl")
    private ShopCategoryService shopCategoryService;
    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult= headLineService.queryHeadLine(headLineCondition, 1, 4);

        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);

        return mergeMainPageInfoResult(headLineResult, shopCategoryResult);
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        MainPageInfoDTO dto = new MainPageInfoDTO();
        List<HeadLine> headLineData = headLineResult.getData();
        dto.setHeadLineList(headLineData);
        List<ShopCategory> shopCategoryData = shopCategoryResult.getData();
        dto.setShopCategoryList(shopCategoryData);
        return null;
    }
}
