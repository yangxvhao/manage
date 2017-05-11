package com.credit.service;

import com.credit.model.Report;

import java.util.List;

/**
 * @date：2017/5/10
 * @author:yangxvhao
 */
public interface IReportService {

    int deleteByPrimaryKey(Integer id);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report record);

    Report selectByApplyId(String Applyid);

    List<Object> selectAll();
}
