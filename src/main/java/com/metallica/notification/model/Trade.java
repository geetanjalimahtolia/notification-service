package com.metallica.notification.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trades")
public class Trade {

    @Id
    public int id;

    public String status;

    public Trade (int id, String status) {
        this.id = id;
        this.status = status;
    }
}
