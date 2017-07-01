package com.credit.model;

import lombok.Data;

@Data
public class Apply {
    private String id;

    private String applyname;

    private Integer applyage;

    private String applyidcard;

    private String applytype;

    private String guarantorname;

    private String applymember;

    private String applydate;

    private Double applymoney;

    private Double checkmoney;

    private String status;

    private String result;

    public Apply(String id, String applyname, Integer applyage, String applyidcard, String applytype,
                 String guarantorname, String applymember, String applydate, Double applymoney,
                 String result,Double checkmoney,String status) {
        this.id = id;
        this.applyname = applyname;
        this.applyage = applyage;
        this.applyidcard = applyidcard;
        this.applytype = applytype;
        this.guarantorname = guarantorname;
        this.applymember = applymember;
        this.applydate = applydate;
        this.applymoney = applymoney;
        this.checkmoney=checkmoney;
        this.status=status;
        this.result=result;
    }

    public Apply(){
        super();
    }
}