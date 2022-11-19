package com.geektrust.entities;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

    private final List<Fund> fundsInPortfolio;

    public Portfolio() {
        fundsInPortfolio = new ArrayList<>();
    }

    public List<Fund> getFundsInPortfolio() {
        return fundsInPortfolio;
    }

    public void addFundsInPortfolio(Fund fund) {
        this.fundsInPortfolio.add(fund);
    }
}
