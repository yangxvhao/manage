package com.credit.service.impl;

import com.credit.dao.ApplyMapper;
import com.credit.model.Apply;
import com.credit.service.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @date：2017/5/8
 * @author:yangxvhao
 */
@Service
public class IApplyServiceImpl implements IApplyService {

    @Autowired(required = false)
    ApplyMapper applyMapper;

    @Override
    public int deleteByApplyMember(String applyMember) {
        return this.applyMapper.deleteByApplyMember(applyMember);
    }

    @Override
    public int deleteAll() {
        return this.applyMapper.deleteAll();
    }

    @Override
    public int insert(Apply record) {
        return this.applyMapper.insert(record);
    }

    @Override
    public int insertSelective(Apply record) {
        return this.applyMapper.insertSelective(record);
    }

    @Override
    public Apply selectByPrimaryKey(String id) {
        return this.applyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Apply record) {
        return this.applyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Apply record) {
        return this.applyMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Object> selectAll() {
        return this.applyMapper.selectAll();
    }

    @Override
    public List<Object> selectByApplyMember(String applyMember) {
        return this.applyMapper.selectByApplyMember(applyMember);
    }

    @Override
    public List<Object> selectByDynamic(String role,String applyMember,String applyType, String applyTimeStart,
                                        String applyTimeEnd, String applyMoneyMin, String applyMoneyMax,String status,
                                        String result) {
        return this.applyMapper.selectByDynamic(role,applyMember,applyType,applyTimeStart,
                applyTimeEnd,applyMoneyMin,applyMoneyMax,status,result);
    }


}
