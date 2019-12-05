package com.ark.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {
    /**
     * 0xxxx 为非法侵入
     * 1xxxx 为上传文件服务的异常(upload-service)
     * 2xxxx 为dietary的异常信息
     */
    ILLEGAL_ENTER_SERVER(00000,"非法进入服务器"),
    UPLOAD_IMAGE_FAIL(10000,"图片上传失败"),
    UPLOAD_IMAGE_NOT_EXIST(10001,"图片不存在"),
    TRANSFER_IMAGE_ERROR(10002,"图片接收失败"),
    DIETARY_INSERT_SUCCESS(20000,"Dietary添加成功"),
    DIETARY_INSERT_UNSUCCESSFUL(20001,"Dietary未添加成功"),
    DIETARY_UPDATE_SUCCESS(20002,"Dietary修改成功"),
    DIETARY_UPDATE_UNSUCCESSFUL(20003,"Dietary未修改成功"),
    DIETARY_DELETE_SUCCESS(20004,"Dietary删除成功"),
    DIETARY_DELETE_UNSUCCESSFUL(20005,"Dietary未删除成功")
    ;

    private Integer code;
    private String msg;
}
