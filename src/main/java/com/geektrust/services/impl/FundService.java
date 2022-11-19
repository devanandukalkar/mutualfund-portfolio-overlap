package com.geektrust.services.impl;

import java.util.List;
import com.geektrust.entities.Fund;
import com.geektrust.repositories.IFundRepository;
import com.geektrust.services.IFundService;
import com.geektrust.services.IStockService;

public class FundService implements IFundService {

    private final IFundRepository fundRepository;
    private final IStockService stockService;

    public FundService(IFundRepository fundRepository, IStockService stockService) {
        this.fundRepository = fundRepository;
        this.stockService = stockService;
    }

    @Override
    public Fund createFund(String fundName) {
        // Get stocks in the fund
        List<String> stocksInFund = stockService.getStocksByFundName(fundName);
        Fund fundToCreate = new Fund(fundName, stocksInFund);
        return fundRepository.save(fundToCreate);
    }

    @Override
    public List<String> getStocksByFundInPortfolio(String fundName) {
        Fund fund = fundRepository.findByName(fundName);
        return fund.getStocksInFund();
    }

    @Override
    public void addStockInFund(String fundName, String stockName) {
        Fund fund = fundRepository.findByName(fundName);
        fund.addStockInFund(stockName);
        fundRepository.save(fund);
    }

}
