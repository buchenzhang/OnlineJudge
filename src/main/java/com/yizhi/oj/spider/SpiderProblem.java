package com.yizhi.oj.spider;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.Problem;
import com.yizhi.oj.repository.mapper.ProblemMapper;
import com.yizhi.oj.service.ProblemService;
import com.yizhi.oj.service.impl.ProblemServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class SpiderProblem {

    @Autowired
    private ProblemMapper problemMapper;
    static String baseUrl = "https://acm.taifua.com/bzoj/p/";
    // 开始页码
    static int start = 1010;
    // 结束页码
    static int end = 1010;

    public ResponseResult prepare(){
        Problem problem;

        for (int i = start; i <= end; i++) {
            problem = new Problem();
            String url = baseUrl + i + ".html";
            try {
                Document doc = Jsoup.connect(url).get();
                Element titleElement = doc.selectFirst("h1.content-heading");
                String title = titleElement.text();
                problem.setTitle(title);

                Element contentElement = doc.selectFirst("div.card-inner");
                Elements paragraphs = contentElement.select("p");

                problem.setDescription(paragraphs.get(1).text());
                problem.setInput(paragraphs.get(4).text());
                problem.setOutput(paragraphs.get(7).text());

                // 获取所有<p>标签
                Elements pers = contentElement.select("pre");
                problem.setInputExamples(pers.get(0).text());
                problem.setOutputExamples(pers.get(1).text());
                problem.setHint(paragraphs.get(9).text());
                problem.setAuth(1);
                problem.setGmtCreate(new Date(System.currentTimeMillis()));

                System.out.println(problem.toString());
                problemMapper.insert(problem);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseResult<>(200,"添加成功");
    }
}
