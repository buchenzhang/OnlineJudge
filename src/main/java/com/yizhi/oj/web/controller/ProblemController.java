package com.yizhi.oj.web.controller;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.Problem;
import com.yizhi.oj.repository.entity.ProblemCase;
import com.yizhi.oj.service.ProblemService;
import com.yizhi.oj.spider.SpiderProblem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/08/20:05
 * @Description:
 */
@RestController
@Api(tags = "题目控制器")
@RequestMapping("/problem")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProblemController {
    private final ProblemService problemService;
    private final SpiderProblem spiderProblem;

    @ApiOperation("批量添加题目--dev环境使用，上线请注释")
    @GetMapping("/admin/addpro")
    public ResponseResult addpro(){
        return spiderProblem.prepare();
    }


    @ApiOperation("根据id查询题目")
    @GetMapping("/getbyid")
    public ResponseResult getById(@RequestParam("id") Long id){
        return problemService.getById(id);
    }


    @ApiOperation("根据id查看全部测试数据的url")
    @GetMapping("/allproblemcase")
    public ResponseResult allProCase(@RequestParam("id") Long id){
        return problemService.allProCase(id);
    }

    @ApiOperation("根据id或者题目名称或者内容搜索公开题目")
    @GetMapping("/getone")
    public ResponseResult getOne(@RequestParam("msg") String msg,
                                 @RequestParam("page") Integer page,
                                 @RequestParam("pageSize") Integer pageSize){
        return problemService.getByITT(msg,page,pageSize);
    }

    @ApiOperation("添加题目")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody Problem problem){
        return problemService.add(problem);
    }

    @ApiOperation("修改题目")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Problem problem){
        return problemService.update(problem);
    }

    @ApiOperation("根据id删除题目")
    @GetMapping("/delete")
    public ResponseResult delete(@RequestParam("id") Long id){
        return problemService.delete(id);
    }

    @ApiOperation("分页查询公开题目--按照修改时间排序")
    @GetMapping("/page")
    public ResponseResult page(@RequestParam("page")Integer page,
                               @RequestParam("pageSize") Integer pageSize){
        return problemService.page(page,pageSize);
    }

    @ApiOperation("分页查询全部题目--按照修改时间排序")
    @GetMapping("/page/all")
    public ResponseResult pageAll(@RequestParam("page")Integer page,
                               @RequestParam("pageSize") Integer pageSize){
        return problemService.pageAll(page,pageSize);
    }

    @ApiOperation("上传指定id的测试数据")
    @PostMapping("/addPros")
    public ResponseResult addPros(@RequestBody ProblemCase problemCaseList){
        return problemService.addPros(problemCaseList);
    }

    @ApiOperation("删除测试点")
    @GetMapping("/deleteByIds")
    public ResponseResult deleteCaseByIds(@RequestParam("ids") Long ids){
        return problemService.deleteCaseByIds(ids);
    }



}
