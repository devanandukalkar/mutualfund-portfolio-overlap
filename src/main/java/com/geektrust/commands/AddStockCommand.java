package com.geektrust.commands;

import java.util.List;
import com.geektrust.services.IFundService;

public class AddStockCommand implements ICommand {

    private final IFundService fundService;

    public AddStockCommand(IFundService fundService) {
        this.fundService = fundService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String fundName = tokens.get(1);

        StringBuilder stringBuilder = new StringBuilder();
        // To tackle spaces in stock name
        for (int i = 2; i < tokens.size(); i++) {
            stringBuilder.append(tokens.get(i)).append(" ");
        }
        
        String stockName = stringBuilder.toString().trim();
        fundService.addStockInFund(fundName, stockName);
    }

}
