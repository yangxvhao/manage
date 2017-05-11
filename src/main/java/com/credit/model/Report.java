package com.credit.model;

import lombok.Data;

@Data
public class Report {
    private Integer id;

    private String applyid;

    private Double flowcost;

    public Report(Integer id, String applyid, Double flowcost) {
        this.id = id;
        this.applyid = applyid;
        this.flowcost = flowcost;
    }

    public Report() {
        super();
    }


}