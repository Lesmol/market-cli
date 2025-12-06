package com.lvmp.polygon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class StockReturnsResponse {
    public double currentPrice;
    public double oneMonthReturn;
    public double sixMonthReturn;
    public double yearToDate;
}
