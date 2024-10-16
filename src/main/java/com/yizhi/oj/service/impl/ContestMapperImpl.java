package com.yizhi.oj.service.impl;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.dto.ProblemDTO;
import com.yizhi.oj.repository.mapper.ContestMapper;
import com.yizhi.oj.repository.mapper.ContestProblemMapper;
import com.yizhi.oj.repository.mapper.ProblemMapper;
import com.yizhi.oj.repository.entity.Contest;
import com.yizhi.oj.repository.entity.ContestProblem;
import com.yizhi.oj.repository.entity.Problem;
import com.yizhi.oj.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zing
 * @Date: 2023/04/12/14:52
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContestMapperImpl implements ContestService {

    private final ContestMapper contestMapper;
    private final ContestProblemMapper contestProblemMapper;
    private final ProblemMapper problemMapper;

    @Override
    public ResponseResult creat(Contest contest) {
        contest.setDuration(contest.getEndTime().getTime() - contest.getStartTime().getTime());
        contestMapper.insert(contest);
        return new ResponseResult<>(200,"创建成功");
    }

    @Override
    public ResponseResult delete(Long id) {
        contestMapper.deleteById(id);
        return new ResponseResult<>(200,"取消成功");
    }

    @Override
    public ResponseResult update(Contest contest) {
        contestMapper.updateById(contest);
        return new ResponseResult<>(200,"修改成功");
    }

    /**
     * 添加比赛题目
     * @param problem 题目id
     * @return 添加的数量
     */
    @Override
    public ResponseResult addProblems(ContestProblem problem) {
        // 查看是否添加这道题目
        Integer byCidPid = contestProblemMapper.getByCidPid(problem.getCid(), problem.getPid());
        if (byCidPid >= 1){
            return new ResponseResult<>(200,"已添加该题目");
        }
        Integer count = contestProblemMapper.insertPro(problem);
        return new ResponseResult<>(200,"成功添加" + count + "道题目");
    }

    @Override
    public ResponseResult deleteProByCid(Integer id) {
        int count = contestProblemMapper.delByPidCid(id);
        return new ResponseResult<>(200,"删除成功");
    }

    @Override
    public ResponseResult proByCid(Long cid) {
        List<ProblemDTO> res = problemMapper.getByCid(cid);
        return new ResponseResult<>(200,res);
    }

    @Override
    public ResponseResult page(Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        List<Contest> info = contestMapper.getByPage(page, pageSize);
        Integer total = contestMapper.getAll();
        Map<String,Object> res = new HashMap<>(2);
        res.put("info",info);
        res.put("total",total);
        return new ResponseResult<>(200,res);
    }

    @Override
    public ResponseResult authentication(String pwd,Integer id) {
        Contest contest = contestMapper.selectById(id);
        if (contest.getPwd().equals(pwd)){
            return new ResponseResult<>(200,"密码正确");
        }
        return new ResponseResult<>(300,"密码错误");
    }

}
