package com.imooc.controller.superadmin;

import com.imooc.entity.bo.ShopCategory;
import com.imooc.entity.dto.Result;
import com.imooc.service.solo.ShopCategoryService;
import org.simplefrawork.core.annotation.Controller;
import org.simplefrawork.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
public class ShopCategoryOperationController {
    @Autowired(name = "ShopCategoryServiceImpl")
    private ShopCategoryService service;

    public Result<Boolean> addShopCategory(HttpServletRequest req, HttpServletResponse res){
        return  service.addShopCategory(new ShopCategory());
    };
    public Result<Boolean> removeShopCategory(HttpServletRequest req, HttpServletResponse res) {
        return service.removeShopCategory(1);
    };
    public Result<Boolean> modifyShopCategory(HttpServletRequest req, HttpServletResponse res) {
        return  service.modifyShopCategory(new ShopCategory());
    };
    public Result<ShopCategory> queryShopCategoryById(int shopCategoryId){
        return service.queryShopCategoryById(1);
    };
    Result<List<ShopCategory>> queryShopCategory(HttpServletRequest req, HttpServletResponse res){
        return service.queryShopCategory(null, 1, 100);
    };

}
