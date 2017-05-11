package com.credit.model;

public class Flow implements Comparable<Flow>{
    private Integer id;

    private String applyid;

    private String flowname;

    private Integer flowscale;

    private String flowresult;

    private Double flowprice;

    public Flow(Integer id, String applyid, String flowname, Integer flowscale, String flowresult, Double flowprice) {
        this.id = id;
        this.applyid = applyid;
        this.flowname = flowname;
        this.flowscale = flowscale;
        this.flowresult = flowresult;
        this.flowprice = flowprice;
    }

    public Flow() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplyid() {
        return applyid;
    }

    public void setApplyid(String applyid) {
        this.applyid = applyid == null ? null : applyid.trim();
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname == null ? null : flowname.trim();
    }

    public Integer getFlowscale() {
        return flowscale;
    }

    public void setFlowscale(Integer flowscale) {
        this.flowscale = flowscale;
    }

    public String getFlowresult() {
        return flowresult;
    }

    public void setFlowresult(String flowresult) {
        this.flowresult = flowresult == null ? null : flowresult.trim();
    }

    public Double getFlowprice() {
        return flowprice;
    }

    public void setFlowprice(Double flowprice) {
        this.flowprice = flowprice;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "id=" + id +
                ", applyid='" + applyid + '\'' +
                ", flowname='" + flowname + '\'' +
                ", flowscale=" + flowscale +
                ", flowresult='" + flowresult + '\'' +
                ", flowprice=" + flowprice +
                '}';
    }

    @Override
    public int compareTo(Flow o) {
        return this.getFlowprice().compareTo(o.getFlowprice());
    }
}