package com.credit.service;

import com.credit.model.Apply;

import java.util.List;

/**
 * @date：2017/5/8
 * @author:yangxvhao
 */
public interface IApplyService {

    int deleteByApplyMember(String applyMember);

    int deleteAll();

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);

    List<Object> selectAll();

    List<Object> selectByApplyMember(String applyMember);

    List<Object> selectByDynamic(String role,String applyMember,String applyType,String applyTimeStart,String applyTimeEnd,
                                 String applyMoneyMin,String applyMoneyMax,String status,String result);
}
