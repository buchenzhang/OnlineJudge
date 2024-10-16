package com.yizhi.oj.service.impl;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.mapper.FeedBackMapper;
import com.yizhi.oj.repository.entity.FeedBack;
import com.yizhi.oj.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zing
 * @Date: 2023/04/18/12:45
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackMapper feedBackMapper;

    @Override
    public ResponseResult add(FeedBack feedBack) {
        feedBack.setState(0);
        feedBack.setDateTime(new Date(System.currentTimeMillis()));
        feedBackMapper.insert(feedBack);
        return new ResponseResult<>(200,"感谢您的反馈");
    }

    @Override
    public ResponseResult dispose(Long id) {
        feedBackMapper.updateState(id);
        return new ResponseResult<>(200,"处理成功");
    }

    @Override
    public ResponseResult page(Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        List<FeedBack> feedBackList = feedBackMapper.getByPage(page, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("info",feedBackList);
        res.put("total",feedBackMapper.getAll());
        return new ResponseResult<>(200,res);
    }
}
