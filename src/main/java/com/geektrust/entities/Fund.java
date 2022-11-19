package com.geektrust.entities;

import java.util.List;

public class Fund {

    private final Integer fundId;
    private final String fundName;
    private final List<String> stocksInFund;

    public Fund(Integer fundId, String fundName, List<String> stocksInFund) {
        this.fundId = fundId;
        this.fundName = fundName;
        this.stocksInFund = stocksInFund;
    }

    public Fund(String fundName, List<String> stocksInFund) {
        this(null, fundName, stocksInFund);
    }

    public Integer getFundId() {
        return fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public List<String> getStocksInFund() {
        return stocksInFund;
    }

    public void addStockInFund(String stock) {
        this.stocksInFund.add(stock);
    }

    @Override
    public String toString() {
        return "Fund [fundId=" + fundId + ", fundName=" + fundName + ", stocksInFund="
                + stocksInFund + "]";
    }
}
