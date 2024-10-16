package com.yizhi.oj.web.controller;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.FeedBack;
import com.yizhi.oj.service.FeedBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zing
 * @Date: 2023/04/18/12:46
 * @Description:
 */
@Api(tags = "反馈控制器")
@RequestMapping("/feedback")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeedBackController {

    private final FeedBackService feedBackService;

    @ApiOperation("分页查询反馈信息")
    @GetMapping("/page")
    public ResponseResult page(@RequestParam("page")Integer page,
                               @RequestParam("pageSize")Integer pageSize){
        return feedBackService.page(page,pageSize);
    }

    @ApiOperation("添加一条反馈")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody FeedBack feedBack){
        return feedBackService.add(feedBack);
    }

    @ApiOperation("处理一条反馈")
    @GetMapping("/dispose")
    public ResponseResult dispose(@RequestParam("id") Long id){
        return feedBackService.dispose(id);
    }
}
