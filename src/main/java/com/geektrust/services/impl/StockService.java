package com.geektrust.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.config.ApplicationConstants;
import com.geektrust.exceptions.FundNotFoundException;
import com.geektrust.services.IStockService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class StockService implements IStockService {

    @Override
    public List<String> getStocksByFundName(String fundName) throws FundNotFoundException {
        // Get All stocks present in the mutual fund
        JSONArray stocksArray = getStocksFromApi(fundName);

        List<String> stocksList = new ArrayList<>();

        if (stocksArray == null)
            throw new FundNotFoundException("FUND_NOT_FOUND");

        for (int i = 0; i < stocksArray.size(); i++)
            stocksList.add((String) stocksArray.get(i));

        return stocksList;
    }

    private JSONArray getStocksFromApi(String fundName) {
        JSONArray stocksInFundArray = null;

        try {
            HttpURLConnection connection =
                    (HttpURLConnection) new URL(ApplicationConstants.STOCK_DATA_URL).openConnection();
            InputStream inputStream = connection.getInputStream();

            JSONObject jsonObject = generateJsonObjectResponse(inputStream);
            JSONArray fundsList = (JSONArray) jsonObject.get("funds");

            for (int i = 0; i < fundsList.size(); i++) {
                JSONObject fundWithStocks = (JSONObject) fundsList.get(i);
                if (fundWithStocks.get("name").equals(fundName))
                    stocksInFundArray = (JSONArray) fundWithStocks.get("stocks");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stocksInFundArray;
    }

    private JSONObject generateJsonObjectResponse(InputStream inputStream) {

        JSONObject jsonObject = new JSONObject();

        try (BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder responseBuilder = new StringBuilder();
            String responseLine;
            while ((responseLine = bufferedReader.readLine()) != null) {
                responseBuilder.append(responseLine);
            }

            String jsonResponse = responseBuilder.toString();

            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
