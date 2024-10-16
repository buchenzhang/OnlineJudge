package com.yizhi.oj.config;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zing
 * @Date: 2023/04/18/12:56
 * @Description:
 */
@Configuration
public class QiuNiuOss {
    private final static String accessKey = "ni5Pt4ZIkdNT6tvvHhR0PZM2SWgUVnJbU3geA4T2";
    private final static String secretKey = "FPhDL1srkB21LbcqJPq8oXfTnEfmTNr8llBR5yg6";
    private final static String bucket = "nitoj";
    // 外链默认域名
    private final static String DOMAIN = "http://oss.yizhi-oj.top";


    /**
     * 根据外链删除七牛云文件
     * @param fileName 外链
     * @return
     */
    public Integer deleteFileFromQiNiu(String fileName) {
        String key = fileName.replaceFirst(DOMAIN+'/',"");
        System.out.println(key);
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Region.huadong());
        Auth auth = Auth.create(accessKey,secretKey);
        BucketManager bucketManager = new BucketManager(auth,cfg);
        try {
            Response delete = bucketManager.delete(bucket,key);
            return delete.statusCode;
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 上传文件到七牛云
     * @param bytes
     * @param fileName
     * @return 外链地址
     * @throws QiniuException
     */
    public String uploadQiniu(byte[] bytes, String fileName) throws QiniuException {
        //构造一个带指定Zone对象的配置类
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Region.huadong());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        Response response = uploadManager.put(bytes, key, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet =
                new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return DOMAIN + "/" + putRet.key;
    }
}
