package com.imooc.controller.superadmin;

import com.imooc.entity.bo.HeadLine;
import com.imooc.entity.dto.Result;
import com.imooc.service.solo.HeadLineService;
import org.simplefrawork.core.annotation.Controller;
import org.simplefrawork.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class HeadLineOperationController {
    @Autowired(name = "HeadLineServiceImpl")
    private HeadLineService service;

    public Result<Boolean> addHeadLine(HttpServletRequest req, HttpServletResponse res) {
        return  service.addHeadLine(new HeadLine());
    };
    public Result<Boolean> removeHeadLine(HttpServletRequest req, HttpServletResponse res){
        return service.removeHeadLine(1);
    };
    public Result<Boolean> modifyHeadLine(HttpServletRequest req, HttpServletResponse res) {
        return  service.modifyHeadLine(new HeadLine());
    };
    public Result<HeadLine> queryHeadLineById(HttpServletRequest req, HttpServletResponse res) {
       return service.queryHeadLineById(1);
    };
    public Result<List<HeadLine>> queryHeadLine(HttpServletRequest req, HttpServletResponse res) {
        return service.queryHeadLine(null, 1, 100);
    };

}
