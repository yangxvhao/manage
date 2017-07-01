package com.credit.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @date：2017/5/17
 * @author:yangxvhao
 */
@Data
public class SubList {

    List<String []> list=new ArrayList<String[]>();
    String [] strings=new String[7];
}
