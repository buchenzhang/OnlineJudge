package com.yizhi.oj.service.impl;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.config.QiuNiuOss;
import com.yizhi.oj.repository.entity.Judge;
import com.yizhi.oj.repository.mapper.JudgeMapper;
import com.yizhi.oj.repository.mapper.ProblemCaseMapper;
import com.yizhi.oj.repository.mapper.ProblemMapper;
import com.yizhi.oj.repository.entity.Problem;
import com.yizhi.oj.repository.entity.ProblemCase;
import com.yizhi.oj.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.ObjectName;
import java.awt.event.WindowAdapter;
import java.util.*;

/**
 * @Author: Zing
 * @Date: 2023/04/08/20:09
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProblemServiceImpl implements ProblemService {

    private final ProblemMapper problemMapper;
    private final QiuNiuOss qiuNiuOss;
    private final ProblemCaseMapper problemCaseMapper;

    @Override
    public ResponseResult add(Problem problem) {
        // 设置默认值
        problem.setTimeLimit(1);
        problem.setMemoryLimit(1);
        problem.setStackLimit(128);
        Date date = new Date(System.currentTimeMillis());
        problem.setGmtCreate(date);
        problem.setGmtModified(date);
        problemMapper.insert(problem);
        return new ResponseResult<>(200,"添加成功");
    }

    @Override
    public ResponseResult update(Problem problem) {
        Date date = new Date(System.currentTimeMillis());
        problem.setGmtCreate(date);
        problem.setGmtModified(date);
        problemMapper.updateById(problem);
        return new ResponseResult<>(200,"修改成功");
    }

    @Override
    public ResponseResult delete(Long id) {
        problemMapper.deleteById(id);
        return new ResponseResult<>(200,"删除成功");
    }

    @Override
    public ResponseResult page(Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        List<Problem> problemList = problemMapper.selectByPage(page, pageSize);
        Map<Object,Object> res = new HashMap<>();
        res.put("total",problemMapper.selectAll());
        res.put("info",problemList);
        return new ResponseResult<>(200,res);
    }

    @Override
    public ResponseResult addPros(ProblemCase problemCaseList) {
        List<ProblemCase> existsCase = problemCaseMapper.selectByPid(problemCaseList.getPid());
        if (existsCase.size() + 1 > 10){
            return new ResponseResult<>(200,"已经存在" + existsCase.size() + "个测试点，最多添加" + (10 - existsCase.size()) + "个测试点");
        }
        Date date = new Date(System.currentTimeMillis());
        problemCaseList.setGmtCreate(date);
        problemCaseList.setGmtModified(date);
        Integer res = problemCaseMapper.addProCases(problemCaseList);
        return new ResponseResult<>(200,"成功添加" + res + "个测试点");
    }


    @Override
    public ResponseResult deleteCaseByIds(Long ids) {
        ProblemCase problemCase = problemCaseMapper.selectById(ids);
        qiuNiuOss.deleteFileFromQiNiu(problemCase.getInput());
        qiuNiuOss.deleteFileFromQiNiu(problemCase.getOutput());
        int count = problemCaseMapper.deleteById(ids);
        return new ResponseResult<>(200,"成功删除" + count + "个测试点");
    }

    @Override
    public ResponseResult getByITT(String msg,Integer page,Integer pageSize) {
        page = (page - 1) * pageSize;
        List<Problem> problemList = problemMapper.getByITT(msg,page,pageSize);
        if (problemList == null){
            return new ResponseResult(400,"查无此题");
        }
        Map<Object,Object> res = new HashMap<>();
        res.put("total",problemMapper.selectAllByITT(msg));
        res.put("info",problemList);
        return new ResponseResult<>(200,res);
    }

    @Override
    public ResponseResult allProCase(Long id) {
        List<ProblemCase> problemCaseList = problemCaseMapper.selectByPid(id);
        return new ResponseResult<>(200,problemCaseList);
    }

    @Override
    public ResponseResult pageAll(Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        List<Problem> problemList = problemMapper.selectByPageAll(page, pageSize);
        Map<Object,Object> res = new HashMap<>();
        res.put("total",problemMapper.selectAll());
        res.put("info",problemList);
        return new ResponseResult<>(200,res);
    }

    @Override
    public ResponseResult getById(Long id) {
        return new ResponseResult<>(200,problemMapper.selectById(id));
    }


}
