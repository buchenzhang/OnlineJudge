package com.yizhi.oj.service.impl;

import com.alibaba.fastjson.JSON;
import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.dto.JudgeDTO;
import com.yizhi.oj.repository.dto.SingleJudgeResultDTO;
import com.yizhi.oj.repository.dto.SolutionDTO;
import com.yizhi.oj.repository.entity.*;
import com.yizhi.oj.repository.mapper.*;
import com.yizhi.oj.repository.vo.JudgeConditionVO;
import com.yizhi.oj.service.CommitService;
import com.yizhi.oj.service.JudgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Zing
 * @Date: 2023/04/09/11:56
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommitServiceImpl implements CommitService {

    private final JudgeMapper judgeMapper;
    private final ProblemCaseMapper problemCaseMapper;
    private final JudgeService judgeService;
    private final ContestListMapper contestListMapper;
    private final ContestMapper contestMapper;

    @Override
    public ResponseResult commit(Judge judge) {
        // 初始化准备
        JudgeDTO judgeDTO = prepare(judge);
        Judge processedJudge;
        JudgeConditionVO res = judgeService.judge(judgeDTO);
        // 处理返回数据
        processedJudge = processing(judge, res);
        if (judge.getIsContest() == 1){ //EX
            // 处理排行榜
            processLeaderboard(processedJudge);
        }
        judgeMapper.insert(processedJudge);
        return new ResponseResult<>(200,"提交成功",processedJudge.getStatus());
    }



    @Override
    public ResponseResult oneProCommit(Long pid,Integer page,Integer pageSize) {
        page = (page - 1) * pageSize;
        List<Judge> byPidPage = judgeMapper.getByPidPage(pid, page, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("info",byPidPage);
        res.put("total",judgeMapper.getAllByPid(pid));
        return new ResponseResult<>(200,res);
    }

    @Override
    public ResponseResult allProCommit(Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        List<Judge> allPage = judgeMapper.getAllPage(page, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("info",allPage);
        res.put("total",judgeMapper.getAll());
        return new ResponseResult<>(200,res);
    }

    private void processLeaderboard(Judge processedJudge) {
        Contest contest = contestMapper.selectById(processedJudge.getCid());
        Date now = new Date(System.currentTimeMillis());
        // 比赛结束 不更新榜单
        if (contest.getEndTime().before(now)){
            return;
        }
        // 更新榜单 wa罚时20 ac罚时比赛到现在的分钟 acm赛制
        ContestList contestList = contestListMapper.getByCidAndUid(processedJudge.getCid(), processedJudge.getUid());
        Integer time = (int) (now.getTime() - contest.getStartTime().getTime()) / 60000;
        if (contestList == null){
            contestList = new ContestList();
            BeanUtils.copyProperties(processedJudge,contestList);
            if ("ACCEPT".equals(processedJudge.getStatus())){
                contestList.setSolved(1);
                contestList.setTime(time);
            }else {
                contestList.setSolved(0);
                contestList.setTime(20);
            }
            contestListMapper.insert(contestList);
        }else{
            if ("ACCEPT".equals(processedJudge.getStatus())){
                contestList.setSolved(contestList.getSolved() + 1);
                contestList.setTime(contestList.getTime() + time);
            }else {
                contestList.setTime(contestList.getTime() + 20);
            }
            contestListMapper.updateById(contestList);
        }
    }

    /**
     * 处理返回的数据
     * @param judge 请求体对象
     * @param judgeConditionVO 判题机返回结果
     * @return 请求体
     */
    private Judge processing(Judge judge,JudgeConditionVO judgeConditionVO){
        Integer time = 0;
        Integer memory = 0;
        // 全部测试数据的返回信息
        judge.setErrorMessage(judgeConditionVO.getExtraInfo().toString());
        judge.setSubmissionId(judgeConditionVO.getSubmissionId());
        // 记录提交记录 观察是否每个测试点都通过，如果是的就返回成功，否则返回错误结果
        for (SingleJudgeResultDTO judgeResult : judgeConditionVO.getJudgeResults()) {
            // 某个测试点出现问题
            if (judgeResult.getCondition() != 0){
                // 本次测试数据的状态 错误了不应该有其他属性
                judge.setStatus(judgeResult.getMessage());
                return judge;
            }
            time += Integer.parseInt(judgeResult.getRealTimeCost());
            memory += Integer.parseInt(judgeResult.getMemoryCost());
        }
        judge.setStatus(judgeConditionVO.getJudgeResults().get(0).getMessage());
        judge.setTime(time);
        judge.setMemory(memory);
        return judge;
    }

    /**
     * 转换类型 获取全部测试数据
     * @param judge
     * @return
     */
    private JudgeDTO prepare(Judge judge){
        JudgeDTO judgeDTO = new JudgeDTO();
        judgeDTO.setSubmissionCode(judge.getCode());
//        judgeDTO.setRealTimeLimit(judge.getTime());
//        judgeDTO.setCpuTimeLimit(judge.getTime());
        judgeDTO.setMemoryLimit(30000);
        judgeDTO.setOutputLimit(10000);
        judgeDTO.setLanguage(judge.getLanguage());
        judgeDTO.setJudgePreference("OI");
        List<SolutionDTO> solutionDTOList = new ArrayList<>();
        // 获取全部测试数据
        for (ProblemCase problemCase : problemCaseMapper.selectByPid(judge.getPid())) {
//            log.info(problemCase.toString());
            SolutionDTO solutionDTO = new SolutionDTO();
            solutionDTO.setStdIn(problemCase.getInput());
            solutionDTO.setExpectedStdOut(problemCase.getOutput());
            solutionDTOList.add(solutionDTO);
        }
        judgeDTO.setSolutions(solutionDTOList);
        judge.setSubmitTime(new Date(System.currentTimeMillis()));
        return judgeDTO;
    }


}
