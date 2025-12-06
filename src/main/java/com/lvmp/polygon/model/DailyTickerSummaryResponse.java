package com.lvmp.polygon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyTickerSummaryResponse {
    public String close;
    public String status;
    public String error;
    public String message;
}
