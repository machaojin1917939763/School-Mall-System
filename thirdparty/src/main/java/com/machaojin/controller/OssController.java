package com.machaojin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;

import javax.annotation.Resource;

/**
 * @author machaojin
 */
@RestController
@RequestMapping("/third-party")
public class OssController {

    @Resource
    public OSSClient ossClient;

    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;

    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;


    @RequestMapping("/oss/policy")
    public Map<String, String> doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKey = "yourAccessKeySecret";
//        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
//        // 填写Bucket名称，例如examplebucket。
//        String bucket = "examplebucket";
//        // 填写Host地址，格式为https://bucketname.endpoint。
        String host = "https://"+bucket+".oss-cn-hangzhou.aliyuncs.com";
        // 设置上传回调URL，即回调服务器地址，用于处理应用服务器与OSS之间的通信。OSS会在文件上传完成后，把文件上传信息通过此回调URL发送给应用服务器。
//        String callbackUrl = "https://192.168.0.0:8888";
        // 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下。
        String dir = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        // 创建ossClient实例。
        try {

            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessId", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            return respMap;
        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        }
       return null;
    }

}
