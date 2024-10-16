package com.yizhi.oj.service.impl;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.dto.OneWeekJudgeDTO;
import com.yizhi.oj.repository.mapper.JudgeMapper;
import com.yizhi.oj.repository.mapper.ProblemMapper;
import com.yizhi.oj.repository.mapper.UserMapper;
import com.yizhi.oj.service.ChartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zing
 * @Date: 2023/05/17/16:57
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChartServiceImpl implements ChartService {

    private final JudgeMapper judgeMapper;
    private final UserMapper userMapper;
    private final ProblemMapper problemMapper;

    @Override
    public ResponseResult weekByDay() {
        List<OneWeekJudgeDTO> oneWeekJudgeDTOList = judgeMapper.weekByDay();
        // 转为两个list
        Map<String,Object> res = new HashMap<>(2);
        List<String> date = new ArrayList<>(7);
        List<Integer> count = new ArrayList<>(7);
        for (OneWeekJudgeDTO o :
                oneWeekJudgeDTOList) {
            date.add(o.getDate());
            count.add(o.getCount());
        }
        res.put("date",date);
        res.put("count",count);
        return new ResponseResult<>(200,res);
    }

    @Override
    public ResponseResult oneChart(Integer pid) {
        Map<Object,Object> res = new HashMap<>(2);
        res.put("WA",judgeMapper.getOneNotPass(pid));
        res.put("AC",judgeMapper.getByIdStatus(pid));
        return new ResponseResult<>(200,res);
    }

    @Override
    public ResponseResult sysInfo() {
        List<Integer> res = new ArrayList<>(3);
        res.add(0,userMapper.selectAll());
        res.add(1,problemMapper.selectAll());
        res.add(2,judgeMapper.getAll());
        return new ResponseResult<>(200,res);
    }
}
