package com.ark.controller;

import com.ark.enums.ExceptionEnum;
import com.ark.exception.ArkException;
import com.ark.pojo.Dietary;
import com.ark.pojo.ExceptionResult;
import com.ark.pojo.ResultPage;
import com.ark.service.DietaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dietary")
public class DietaryController {

    @Autowired
    private DietaryService dietaryService;

    /**
     * 请求url:
     * 请求方法:get
     * 请求参数:无
     * 返回结果: ResponseEntity<ResultPage<Dietary>>
     * 有key就按条件查询，没有就查询所有消耗品信息
     * 对查询结果进行分页
     */
    @GetMapping("/query")
    public ResponseEntity<ResultPage<Dietary>> queryDietary(
            @RequestParam(value = "page",defaultValue = "1")Long page,
            @RequestParam(value = "rows",defaultValue = "5")Long rows,
            @RequestParam(value = "key", required = false) String key){
        ResultPage<Dietary> resultPage = dietaryService.queryDietary(page, rows, key);
        return ResponseEntity.ok(resultPage);
    }
    /**
     * 请求url:
     * 请求方法:post
     * 请求参数: Dietary对象
     * 返回结果: ResponseEntity<ExceptionResult>
     * 添加新的dietary
     */
    @PostMapping("/add")
    public ResponseEntity<ExceptionResult> insertDietary(@RequestBody(required = false) Dietary dietary){
        Boolean result = dietaryService.insertDietary(dietary);
        if (result){
            ExceptionResult exceptionResult = new ExceptionResult(ExceptionEnum.DIETARY_INSERT_SUCCESS);
            return ResponseEntity.ok(exceptionResult);
        }
        ExceptionResult exceptionResult = new ExceptionResult(ExceptionEnum.DIETARY_INSERT_UNSUCCESSFUL);
        return ResponseEntity.ok(exceptionResult);
    }
    /**
     * 请求url:
     * 请求方法: put
     * 请求参数: Dietary对象
     * 返回结果: ResponseEntity<ExceptionResult>
     * 修改已有的dietary
     */
    @PutMapping("/update")
    public ResponseEntity<ExceptionResult> updateDietary(@RequestBody(required = false) Dietary dietary){
        //判断是否有id
        if (dietary.getId() == null)
            //没有就是非法动用接口
            throw new ArkException(ExceptionEnum.ILLEGAL_ENTER_SERVER);
        Boolean result = dietaryService.updateDietary(dietary);
        if (result){
            ExceptionResult exceptionResult = new ExceptionResult(ExceptionEnum.DIETARY_UPDATE_SUCCESS);
            return ResponseEntity.ok(exceptionResult);
        }
        ExceptionResult exceptionResult = new ExceptionResult(ExceptionEnum.DIETARY_UPDATE_UNSUCCESSFUL);
        return ResponseEntity.ok(exceptionResult);
    }
    /**
     * 请求url:
     * 请求方法: delete
     * 请求参数: Integer id
     * 返回结果: ResponseEntity<ExceptionResult>
     * 根据id，删除已有的dietary
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ExceptionResult> deleteDietary(@PathVariable(value = "id")Integer id){
        if (id == null)
            throw new ArkException(ExceptionEnum.ILLEGAL_ENTER_SERVER);
        Boolean result = dietaryService.deleteDietary(id);
        if (result){
            ExceptionResult exceptionResult = new ExceptionResult(ExceptionEnum.DIETARY_DELETE_SUCCESS);
            return ResponseEntity.ok(exceptionResult);
        }
        ExceptionResult exceptionResult = new ExceptionResult(ExceptionEnum.DIETARY_DELETE_UNSUCCESSFUL);
        return ResponseEntity.ok(exceptionResult);
    }
}
