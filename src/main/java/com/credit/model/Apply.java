package com.credit.model;

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

    public Apply(String id, String applyname, Integer applyage, String applyidcard, String applytype, String guarantorname, String applymember, String applydate, Double applymoney) {
        this.id = id;
        this.applyname = applyname;
        this.applyage = applyage;
        this.applyidcard = applyidcard;
        this.applytype = applytype;
        this.guarantorname = guarantorname;
        this.applymember = applymember;
        this.applydate = applydate;
        this.applymoney = applymoney;
    }

    public Apply() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApplyname() {
        return applyname;
    }

    public void setApplyname(String applyname) {
        this.applyname = applyname == null ? null : applyname.trim();
    }

    public Integer getApplyage() {
        return applyage;
    }

    public void setApplyage(Integer applyage) {
        this.applyage = applyage;
    }

    public String getApplyidcard() {
        return applyidcard;
    }

    public void setApplyidcard(String applyidcard) {
        this.applyidcard = applyidcard == null ? null : applyidcard.trim();
    }

    public String getApplytype() {
        return applytype;
    }

    public void setApplytype(String applytype) {
        this.applytype = applytype == null ? null : applytype.trim();
    }

    public String getGuarantorname() {
        return guarantorname;
    }

    public void setGuarantorname(String guarantorname) {
        this.guarantorname = guarantorname == null ? null : guarantorname.trim();
    }

    public String getApplymember() {
        return applymember;
    }

    public void setApplymember(String applymember) {
        this.applymember = applymember == null ? null : applymember.trim();
    }

    public String getApplydate() {
        return applydate;
    }

    public void setApplydate(String applydate) {
        this.applydate = applydate == null ? null : applydate.trim();
    }

    public Double getApplymoney() {
        return applymoney;
    }

    public void setApplymoney(Double applymoney) {
        this.applymoney = applymoney;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id='" + id + '\'' +
                ", applyname='" + applyname + '\'' +
                ", applyage=" + applyage +
                ", applyidcard='" + applyidcard + '\'' +
                ", applytype='" + applytype + '\'' +
                ", guarantorname='" + guarantorname + '\'' +
                ", applymember='" + applymember + '\'' +
                ", applydate='" + applydate + '\'' +
                ", applymoney=" + applymoney +
                '}';
    }
}