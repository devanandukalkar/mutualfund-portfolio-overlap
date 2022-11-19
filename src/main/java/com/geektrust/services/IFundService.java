package com.geektrust.services;

import java.util.List;
import com.geektrust.entities.Fund;

public interface IFundService {
    
    Fund createFund(String fundName);

    List<String> getStocksByFundInPortfolio(String fundName);

    void addStockInFund(String fundName, String stockName);
}
