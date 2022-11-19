package com.geektrust.services;

import java.util.List;
import com.geektrust.exceptions.FundNotFoundException;

public interface IStockService {

    List<String> getStocksByFundName(String fundName) throws FundNotFoundException;
}