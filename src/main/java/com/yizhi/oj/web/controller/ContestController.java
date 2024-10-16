package com.yizhi.oj.web.controller;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.Contest;
import com.yizhi.oj.repository.entity.ContestProblem;
import com.yizhi.oj.service.ContestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/12/14:53
 * @Description:
 */
@RestController
@Api(tags = "竞赛控制器")
@RequestMapping("/contest")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContestController {

    private final ContestService contestService;

    @ApiOperation("获取比赛的密码")
    @GetMapping("/authentication")
    public ResponseResult authentication(@RequestParam("pwd") String pwd,
                                         @RequestParam("id") Integer id){
        return contestService.authentication(pwd,id);
    }

    @ApiOperation("分页查询比赛信息")
    @GetMapping("/page")
    public ResponseResult page(@RequestParam("page") Integer page,
                               @RequestParam("pageSize") Integer pageSize){
        return contestService.page(page,pageSize);
    }

    @ApiOperation("查询某个比赛的题目")
    @GetMapping("/proByCid")
    public ResponseResult proByCid(@RequestParam("cid") Long cid){
        return contestService.proByCid(cid);
    }

    @ApiOperation("删除比赛的题目")
    @GetMapping("/delPro")
    public ResponseResult delProByCid(@RequestParam("id")Integer id){
        return contestService.deleteProByCid(id);
    }

    @ApiOperation("添加比赛的题目")
    @PostMapping("/addproblems")
    public ResponseResult addProblems(@RequestBody ContestProblem problem){
        return contestService.addProblems(problem);
    }

    @PostMapping("/creat")
    @ApiOperation("创建比赛")
    public ResponseResult create(@RequestBody Contest contest){
        return contestService.creat(contest);
    }

    @GetMapping("/delete")
    @ApiOperation("根据id取消比赛")
    public ResponseResult delete(@RequestParam("id") Long id){
        return contestService.delete(id);
    }

    @PostMapping("/update")
    @ApiOperation("修改比赛信息")
    public ResponseResult update(@RequestBody Contest contest){
        return contestService.update(contest);
    }

}
