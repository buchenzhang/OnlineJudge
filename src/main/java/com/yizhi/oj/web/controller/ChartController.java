package com.yizhi.oj.web.controller;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.service.ChartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zing
 * @Date: 2023/05/17/16:53
 * @Description:
 */
@RestController
@Api(tags = "图表控制器")
@RequestMapping("/chart")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class ChartController {

    private final ChartService chartService;

    @ApiOperation("查询全部用户，全部题目和答题数量")
    @GetMapping("/sys/info")
    public ResponseResult sysInfo(){
        return chartService.sysInfo();
    }

    @ApiOperation("获取一道题的通过率")
    @GetMapping("/one/chart")
    public ResponseResult onrChart(@RequestParam("pid") Integer pid){
        return chartService.oneChart(pid);
    }

    @ApiOperation("分天查询最近一周的提交数量")
    @GetMapping("/oneweek")
    public ResponseResult weekByDay(){
        return chartService.weekByDay();
    }

}
