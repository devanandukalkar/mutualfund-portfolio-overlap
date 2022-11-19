package com.geektrust.commands;

public enum CommandKeyword {
    
    CURRENT_PORTFOLIO_COMMAND("CURRENT_PORTFOLIO"),
    CALCULATE_OVERLAP_COMMAND("CALCULATE_OVERLAP"),
    ADD_STOCK_COMMAND("ADD_STOCK");

    private final String name;

    CommandKeyword(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
