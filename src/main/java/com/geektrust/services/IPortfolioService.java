package com.geektrust.services;

import java.util.List;
import com.geektrust.entities.Fund;

public interface IPortfolioService {

    List<Fund> createPortfolio(List<String> funds);

    List<String> calculatePortfolioOverlap(String fundToCompare);
}
