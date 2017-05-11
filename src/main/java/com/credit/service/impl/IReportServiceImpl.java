package com.credit.service.impl;

import com.credit.dao.ReportMapper;
import com.credit.model.Report;
import com.credit.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date：2017/5/10
 * @author:yangxvhao
 */
@Service
public class IReportServiceImpl implements IReportService {

    @Autowired(required = false)
    ReportMapper reportMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return this.reportMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Report record) {
        return this.reportMapper.insert(record);
    }

    @Override
    public int insertSelective(Report record) {
        return this.reportMapper.insertSelective(record);
    }

    @Override
    public Report selectByPrimaryKey(Integer id) {
        return this.reportMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Report record) {
        return this.reportMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Report record) {
        return this.reportMapper.updateByPrimaryKey(record);
    }

    @Override
    public Report selectByApplyId(String Applyid) {
        return this.reportMapper.selectByApplyId(Applyid);
    }

    @Override
    public List<Object> selectAll() {
        return this.reportMapper.selectAll();
    }
}
