package com.credit.model;

import lombok.Data;

@Data
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

    @Override
    public int compareTo(Flow o) {
        return this.getFlowprice().compareTo(o.getFlowprice());
    }
}