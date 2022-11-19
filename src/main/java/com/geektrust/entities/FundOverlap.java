package com.geektrust.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FundOverlap {
    
    private String fundToCompare;
    private String fundInPortfolio;
    private Double fundOverlapPercentage;

    public String getFundToCompare() {
        return fundToCompare;
    }

    public String getFundInPortfolio() {
        return fundInPortfolio;
    }

    public Double getFundOverlapPercentage() {
        return fundOverlapPercentage;
    }

    public void setFundToCompare(String fundToCompare) {
        this.fundToCompare = fundToCompare;
    }

    public void setFundInPortfolio(String fundInPortfolio) {
        this.fundInPortfolio = fundInPortfolio;
    }

    public void setFundOverlapPercentage(Double fundOverlapPercentage) {
        this.fundOverlapPercentage = fundOverlapPercentage;
    }

    public double applyFundOverlapFormula(int stocksCountInFirstFund, int commonStocksCount, int stocksCountInSecondFund) {
        // Formula
        // Overlap (A,B) = 2*(No of common stocks in A & B)/ (No of stocks in A + No of stocks in B) * 100
        double calculatedOverlap = ((double) (2 * commonStocksCount) / (double) (stocksCountInFirstFund + stocksCountInSecondFund)) * 100;
        return roundOffOverlapValue(calculatedOverlap);
    }

    private double roundOffOverlapValue(double calculatedOverlap) {
        BigDecimal decimalValue = new BigDecimal(Double.toString(calculatedOverlap));
        int decimalPlaces = 2;
        decimalValue = decimalValue.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return decimalValue.doubleValue();
    }
}
