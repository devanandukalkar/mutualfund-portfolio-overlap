package com.geektrust.commands;

import java.util.List;
import com.geektrust.exceptions.FundNotFoundException;
import com.geektrust.services.IPortfolioService;

public class CalculateOverlapCommand implements ICommand {

    private final IPortfolioService portfolioService;

    public CalculateOverlapCommand(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String fundToCompare = tokens.get(1);
        try {
            List<String> overlaps = portfolioService.calculatePortfolioOverlap(fundToCompare);
            for (String overlap : overlaps) {
                System.out.println(overlap);
            }
        } catch (FundNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
