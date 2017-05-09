package com.credit.service;

import com.credit.model.Apply;

import java.util.List;

/**
 * @date：2017/5/8
 * @author:yangxvhao
 */
public interface IApplyService {

    int deleteByPrimaryKey(String id);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);

    List<Object> selectAll();
}
