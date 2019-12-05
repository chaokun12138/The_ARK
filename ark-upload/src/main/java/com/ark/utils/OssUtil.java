package com.ark.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.ark.config.OssConfig;
import com.ark.enums.ExceptionEnum;
import com.ark.exception.ArkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class OssUtil {
    @Autowired
    private OssConfig ossConfig;



    public String upload(File file){

        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        String bucketName = ossConfig.getBucketName();
        String hostName = ossConfig.getHostName();

        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);

        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                CreateBucketRequest create = new CreateBucketRequest(bucketName);
                //设置创建新bucket的权限 （公共读）
                create.setCannedACL(CannedAccessControlList.PublicRead);
                //创建新bucket
                ossClient.createBucket(create);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentTime = dateFormat.format(new Date());
            String key = hostName + "/"
                    + currentTime
                    + "/"
                    + (UUID.randomUUID().toString().replace("-","")
                    + "-"
                    + file.getName()
                        );
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,key,file);
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
            if (result != null){
                //上传成功
                return key;
            }
        }catch (Exception e){
            throw new ArkException(ExceptionEnum.UPLOAD_IMAGE_FAIL);
        }finally {
            if (ossClient != null){
                ossClient.shutdown();
            }
        }
        return null;
    }
}
