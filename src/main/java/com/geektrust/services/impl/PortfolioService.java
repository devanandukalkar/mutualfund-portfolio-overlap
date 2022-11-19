package com.geektrust.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.geektrust.config.ApplicationConstants;
import com.geektrust.entities.Fund;
import com.geektrust.entities.Portfolio;
import com.geektrust.entities.FundOverlap;
import com.geektrust.services.IFundService;
import com.geektrust.services.IPortfolioService;
import com.geektrust.services.IStockService;

public class PortfolioService implements IPortfolioService {

    public static final Portfolio portfolio = new Portfolio();
    private final IFundService fundService;
    private final IStockService stockService;


    public PortfolioService(IFundService fundService, IStockService stockService) {
        this.fundService = fundService;
        this.stockService = stockService;
    }

    @Override
    public List<Fund> createPortfolio(List<String> funds) {
        for (String fund : funds) {
            Fund fundObj = fundService.createFund(fund);
            portfolio.addFundsInPortfolio(fundObj);
        }

        return portfolio.getFundsInPortfolio();
    }

    @Override
    public List<String> calculatePortfolioOverlap(String fundToCompare) {

        List<String> stocksInFirstFund = stockService.getStocksByFundName(fundToCompare);
        int stocksCountInFirstFund = stocksInFirstFund.size();

        List<FundOverlap> fundOverlapList = findOverlappingStocksInFunds(fundToCompare, stocksInFirstFund, stocksCountInFirstFund);
        List<String> portfolioOverlapList = new ArrayList<>();
        for (FundOverlap fundOverlap : fundOverlapList) {
            portfolioOverlapList.add(fundOverlap.getFundToCompare() + " " + fundOverlap.getFundInPortfolio() + " " + String.format("%.2f", fundOverlap.getFundOverlapPercentage()) + "%");
        }
        return portfolioOverlapList;
    }

    private List<FundOverlap> findOverlappingStocksInFunds(String fundToCompare, List<String> stocksInFirstFund, int stocksCountInFirstFund) {

        List<FundOverlap> overlapList = new ArrayList<>();

        for (Fund fund : portfolio.getFundsInPortfolio()) {
            FundOverlap fundOverlap = new FundOverlap();
            // First Fund
            String fundInPortfolio = fund.getFundName();
            List<String> stocksInSecondFund = fundService.getStocksByFundInPortfolio(fundInPortfolio);

            // Common stocks between 2 funds
            int commonStocksCount = findNumberOfCommandStocks(stocksInFirstFund, stocksInSecondFund);

            int stocksCountInSecondFund = stocksInSecondFund.size();
            double overlapPercentage = fundOverlap.applyFundOverlapFormula(stocksCountInFirstFund, commonStocksCount, stocksCountInSecondFund);

            // Ignore zero overlapped funds
            if (overlapPercentage == ApplicationConstants.ZERO_OVERLAP) continue;

            fundOverlap.setFundToCompare(fundToCompare);
            fundOverlap.setFundInPortfolio(fundInPortfolio);
            fundOverlap.setFundOverlapPercentage(overlapPercentage);
            overlapList.add(fundOverlap);
        }

        return overlapList;
    }

    private int findNumberOfCommandStocks(List<String> stocksInFirstFund, List<String> stocksInSecondFund) {

        List<String> commonStocks = stocksInFirstFund.stream().filter(stocksInSecondFund::contains).collect(Collectors.toList());
        return commonStocks.size();
    }
}
