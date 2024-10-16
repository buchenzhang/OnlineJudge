package com.yizhi.oj.web.controller;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.Judge;
import com.yizhi.oj.service.CommitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zing
 * @Date: 2023/04/09/12:01
 * @Description:
 */
@RestController
@Api(tags = "判题控制器")
@RequestMapping("/judge")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommitController {

    private final CommitService judgeService;

    @ApiOperation("根据题目id分页查看全部提交记录")
    @GetMapping("/oneprocommit")
    public ResponseResult oneProCommit(@RequestParam("pid") Long pid,
                                       @RequestParam("page")Integer page,
                                       @RequestParam("pageSize")Integer pageSize){
        return judgeService.oneProCommit(pid,page,pageSize);
    }

    @ApiOperation("分页查看全部提交记录")
    @GetMapping("/allprocommit")
    public ResponseResult allProCommit(@RequestParam("page")Integer page,
                                       @RequestParam("pageSize")Integer pageSize){
        return judgeService.allProCommit(page,pageSize);
    }

    @ApiOperation("提交")
    @PostMapping("/commit")
    public ResponseResult commit(@RequestBody Judge judge){
        return judgeService.commit(judge);
    }

}
