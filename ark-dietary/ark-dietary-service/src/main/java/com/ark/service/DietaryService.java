package com.ark.service;

import com.ark.mapper.DietaryMapper;
import com.ark.pojo.Dietary;
import com.ark.pojo.ResultPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class DietaryService {
    @Autowired
    private DietaryMapper dietaryMapper;

    /**
     * 有key就按条件查询，没有就查询所有消耗品信息
     * 对查询结果进行分页
     */
    public ResultPage<Dietary> queryDietary(Long page, Long rows, String key) {

        PageHelper.startPage(page.intValue(),rows.intValue());

        Example example = new Example(Dietary.class);
        Example.Criteria criteria = example.createCriteria();
        //判断是否有key
        if (key != null){
            criteria.andLike("name","%" + key + "%");
        }
        List<Dietary> dietaries = dietaryMapper.selectByExample(example);

        PageInfo<Dietary> info = new PageInfo<>(dietaries);
        //生成pageResult对象
        ResultPage<Dietary> resultPage = new ResultPage<>();
        resultPage.setTotal(info.getTotal());
        resultPage.setTotalPage(Long.valueOf(info.getPages()));
        resultPage.setItems(info.getList());
        return resultPage;
    }

    public Boolean insertDietary(Dietary dietary) {
        int result = dietaryMapper.insertSelective(dietary);
        if (result == 1)
            return true;
        return false;
    }

    public Boolean updateDietary(Dietary dietary) {
        int result = dietaryMapper.updateByPrimaryKeySelective(dietary);
        if (result == 1)
            return true;
        return false;
    }

    public Boolean deleteDietary(Integer id) {
        int result = dietaryMapper.deleteByPrimaryKey(id);
        if (result == 1)
            return true;
        return false;

    }
}