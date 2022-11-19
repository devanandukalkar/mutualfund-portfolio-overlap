package com.geektrust.repositories.impl;

import java.util.HashMap;
import java.util.Map;
import com.geektrust.entities.Fund;
import com.geektrust.repositories.IFundRepository;

public class FundRepository implements IFundRepository {

    private final Map<String, Fund> fundMap;
    private Integer autoIncrementId = 1;

    public FundRepository() {
        fundMap = new HashMap<>();
    }

    public FundRepository(Map<String, Fund> fundMap) {
        this.fundMap = fundMap;
        this.autoIncrementId = fundMap.size() + 1;
    }

    @Override
    public Fund save(Fund fund) {
        if (fund.getFundId() == null) {
            Fund fundObj = new Fund(autoIncrementId, fund.getFundName(), fund.getStocksInFund());
            fundMap.put(fund.getFundName(), fundObj);
            autoIncrementId++;
            return fundObj;
        }

        fundMap.put(fund.getFundName(), fund);
        return fund;
    }

    @Override
    public Fund findByName(String fundName) {
        return fundMap.get(fundName);
    }
}
