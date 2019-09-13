package com.metallica.notification;

import com.metallica.notification.model.Trade;
import com.metallica.notification.repository.TradeRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
import org.json.JSONException;

@Component
public class Receiver {

    @Autowired
    private TradeRepository tradeRepository;

    @RabbitListener
    public void receiveMessage(String message) {
        try {
            JSONObject payload = new JSONObject(message);
            int tradeId = payload.getInt("tradeId");
            System.out.println("Received Trade ID: " + tradeId + " For processing");
            System.out.println("Saving into processed trades");
            tradeRepository.save(new Trade(tradeId, "PROCESSED"));
        } catch (JSONException jsonException) {
            System.out.println("Exception in parsing queue payload to JSON: " + jsonException.getLocalizedMessage());
        }
    }
}