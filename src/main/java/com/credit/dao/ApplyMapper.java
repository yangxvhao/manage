package com.credit.dao;

import com.credit.model.Apply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);

    List<Object> selectAll();

    List<Object> selectByApplyMember(String applyMember);

    //传入多个参数要用@Param
    List<Object> selectByDynamic(
                                 @Param("role")String role,
                                 @Param("applyMember")String applyMember,
                                 @Param("applyType") String applyType,
                                 @Param("applyTimeStart")String applyTimeStart,
                                 @Param("applyTimeEnd")String applyTimeEnd,
                                 @Param("applyMoneyMin")String applyMoneyMin,
                                 @Param("applyMoneyMax")String applyMoneyMax);
}