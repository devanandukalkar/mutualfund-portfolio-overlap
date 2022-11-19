package com.geektrust.repositories;

import com.geektrust.entities.Fund;

public interface IFundRepository {
    
    Fund save(Fund fund);
    
    Fund findByName(String fundName);
}
