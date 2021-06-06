package com.imooc.controller.frontend;

import com.imooc.entity.dto.MainPageInfoDTO;
import com.imooc.entity.dto.Result;
import com.imooc.service.combine.HeadLineShopCategoryCombineService;
import lombok.Data;
import org.simplefrawork.core.annotation.Controller;
import org.simplefrawork.inject.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Data
public class MainPageController {
    @Autowired(name = "HeadLineShopCategoryCombineServiceImpl")
    private HeadLineShopCategoryCombineService service;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse res) {
       return service.getMainPageInfo();
    }
}
