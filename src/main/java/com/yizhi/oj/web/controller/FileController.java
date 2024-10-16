package com.yizhi.oj.web.controller;

import com.google.gson.Gson;
import com.qiniu.storage.Configuration;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.config.QiuNiuOss;
import com.yizhi.oj.repository.dto.TestDTO;
import com.yizhi.oj.repository.entity.ProblemCase;
import com.yizhi.oj.service.ProblemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @Author: Zing
 * @Date: 2023/04/05/16:54
 * @Description:
 */
@RestController
@Api(tags = "文件控制器")
@RequestMapping("/file")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileController {

    private final QiuNiuOss qiuNiuOss;
    private final ProblemService problemService;

    @ApiOperation("上传一个测试点数据")
    @PostMapping("/upload/input/in")
    public ResponseResult uploadIn(@RequestBody TestDTO testDTO) throws IOException {
        String inputFileName = UUID.randomUUID() +".in";
        String inputUrl = qiuNiuOss.uploadQiniu(testDTO.getInput().getBytes(), inputFileName);
        String outputFileName = UUID.randomUUID() +".out";
        String outputUrl = qiuNiuOss.uploadQiniu(testDTO.getOutput().getBytes(), outputFileName);
        ProblemCase problemCase = new ProblemCase();
        problemCase.setPid(testDTO.getPid());
        problemCase.setInput(inputUrl);
        problemCase.setOutput(outputUrl);
        problemService.addPros(problemCase);
        return new ResponseResult<>(200,"添加成功");
    }

    @ApiOperation("上传一个测试点输入或者输出")
    @PostMapping("/upload/input")
    public ResponseResult uploadInput(@RequestParam("input")MultipartFile input) throws IOException {

        if (input.getSize() > 900000){
            return new ResponseResult(200,"文件大于9000kb");
        }
        //获得原始文件后缀
        String originalFilename = input.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName = UUID.randomUUID().toString() + suffix;
        String url = qiuNiuOss.uploadQiniu(input.getBytes(), fileName);
        return new ResponseResult<>(200,url);
    }






}
