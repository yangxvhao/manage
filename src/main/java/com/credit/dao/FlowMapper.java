package com.credit.dao;

import com.credit.model.Flow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);

    List<Object> selectAll();

    List<Object> selectByApplyId(String applyId);

    List<Object> selectByDynamic(@Param("flowResult")String flowResult,
                                 @Param("applyId")String applyId);
}