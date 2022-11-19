package com.geektrust.commands;

import java.util.ArrayList;
import java.util.List;
import com.geektrust.entities.Fund;
import com.geektrust.services.IPortfolioService;

public class CurrentPortfolioCommand implements ICommand {

    private final IPortfolioService portfolioService;

    public CurrentPortfolioCommand(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Override
    public void invoke(List<String> tokens) {
        List<String> funds = new ArrayList<>();
        
        for (int i = 1; i < tokens.size(); i++) 
            funds.add(tokens.get(i));
        
        List<Fund> addedFunds = portfolioService.createPortfolio(funds);
        if (addedFunds.isEmpty())
            throw new RuntimeException("Funds could not be added to portfolio");
    }
    
}
