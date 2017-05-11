package com.credit.dao;

import com.credit.model.Report;

import java.util.List;

public interface ReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report record);

    Report selectByApplyId(String Applyid);

    List<Object> selectAll();
}