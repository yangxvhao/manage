package com.credit.dao;

import com.credit.model.Apply;

import java.util.List;

public interface ApplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);

    List<Object> selectAll();
}