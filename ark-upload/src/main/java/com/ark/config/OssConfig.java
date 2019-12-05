package com.ark.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class OssConfig {

    @Value("${Oss.check.endPoint}")
    private String endpoint;

    @Value("${Oss.check.accessKeyId}")
    private String accessKeyId;

    @Value("${Oss.check.accessKeySecret}")
    private String accessKeySecret;

    @Value("${Oss.login.bucketName}")
    private String bucketName;

    @Value("${Oss.login.urlPrefix}")
    private String urlPrefix;

    @Value("${Oss.login.fileHost}")
    private String hostName;
}
