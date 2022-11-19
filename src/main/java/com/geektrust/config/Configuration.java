package com.geektrust.config;

import com.geektrust.commands.AddStockCommand;
import com.geektrust.commands.CalculateOverlapCommand;
import com.geektrust.commands.CommandKeyword;
import com.geektrust.commands.CommandRegistry;
import com.geektrust.commands.CurrentPortfolioCommand;
import com.geektrust.repositories.IFundRepository;
import com.geektrust.repositories.impl.FundRepository;
import com.geektrust.services.IFundService;
import com.geektrust.services.IPortfolioService;
import com.geektrust.services.IStockService;
import com.geektrust.services.impl.FundService;
import com.geektrust.services.impl.PortfolioService;
import com.geektrust.services.impl.StockService;

public class Configuration {

    // Create a singleton pattern
    private static final Configuration instance = new Configuration();

    private Configuration() {}

    public static Configuration getInstance() {
        return instance;
    }

    // Initialize Repositories
    private final IFundRepository fundRepository = new FundRepository();

    // Initialize Services
    private final IStockService stockService = new StockService();
    private final IFundService fundService = new FundService(fundRepository, stockService);
    private final IPortfolioService portfolioService = new PortfolioService(fundService, stockService);

    // Initialize Commands
    private final CurrentPortfolioCommand currentPortfolioCommand =
            new CurrentPortfolioCommand(portfolioService);
    private final CalculateOverlapCommand calculateOverlapCommand =
            new CalculateOverlapCommand(portfolioService);
    private final AddStockCommand addStockCommand = new AddStockCommand(fundService);

    // Initialize command registry
    private final CommandRegistry commandRegistry = new CommandRegistry();

    // Register Commands
    private void registerCommands() {
        commandRegistry.registerCommand(CommandKeyword.CURRENT_PORTFOLIO_COMMAND.getName(),
                currentPortfolioCommand);
        commandRegistry.registerCommand(CommandKeyword.CALCULATE_OVERLAP_COMMAND.getName(),
                calculateOverlapCommand);
        commandRegistry.registerCommand(CommandKeyword.ADD_STOCK_COMMAND.getName(),
                addStockCommand);
    }


    public CommandRegistry getCommandRegistry() {
        registerCommands();
        return commandRegistry;
    }
}
