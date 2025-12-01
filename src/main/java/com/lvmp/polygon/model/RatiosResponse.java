package com.lvmp.polygon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatiosResponse {
    public List<FinancialItem> results;
    public String status;
    public String error;
    public String message;
}