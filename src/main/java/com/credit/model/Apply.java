package com.credit.model;


import lombok.Data;

import java.util.Date;

/**
 * @date：06
 * @author:fushuai
 */
@Data
public class Apply {
    private String applyName;
    private String guarantorName;
    private Date applyTime;
    private Double applySum;

}
