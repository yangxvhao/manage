package com.credit.service.impl;

import com.credit.dao.FlowMapper;
import com.credit.model.Flow;
import com.credit.service.IFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date：2017/5/9
 * @author:yangxvhao
 */
@Service
public class IFlowServiceImpl implements IFlowService {

    @Autowired(required = false)
    FlowMapper flowMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return this.flowMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteAll() {
        return this.flowMapper.deleteAll();
    }

    @Override
    public int insert(Flow record) {
        return this.flowMapper.insert(record);
    }

    @Override
    public int insertSelective(Flow record) {
        return this.flowMapper.insertSelective(record);
    }

    @Override
    public Flow selectByPrimaryKey(Integer id) {
        return this.flowMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Flow record) {
        return this.flowMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Flow record) {
        return this.flowMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Object> selectAll() {
        return this.flowMapper.selectAll();
    }

    @Override
    public List<Object> selectByApplyId(String applyId) {
        return this.flowMapper.selectByApplyId(applyId);
    }

    @Override
    public List<Object> selectByDynamic(String flowResult, String applyId) {
        return this.flowMapper.selectByDynamic(flowResult,applyId);
    }
}
