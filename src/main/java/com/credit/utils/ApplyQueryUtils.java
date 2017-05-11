package com.credit.utils;

import com.credit.model.Apply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @date：2017/5/9
 * @author:yangxvhao
 */
public class ApplyQueryUtils {

    private Logger logger= LoggerFactory.getLogger(ApplyQueryUtils.class);

    public static List<Object> QueryByDynamic(List<Object> list,String applyType,
                                              String applyTimeStart,String applyTimeEnd,
                                              String applyMoneyMin,String applyMoneyMax){
        List<Object> newList=null;
        for (Object object:list){
            Apply apply=(Apply)object;
            if(apply.getApplytype().equals(applyType)){

            }
        }

        return newList;
    }
}
