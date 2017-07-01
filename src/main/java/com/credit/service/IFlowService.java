package com.credit.service;

import com.credit.model.Flow;

import java.util.List;

/**
 * @date：2017/5/9
 * @author:yangxvhao
 */
public interface IFlowService {
    int deleteByPrimaryKey(Integer id);

    int deleteAll();

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);

    List<Object> selectAll();

    List<Object> selectByApplyId(String applyId);

    List<Object> selectByDynamic(String flowResult, String applyId);
}
