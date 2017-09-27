package com.fastdeliveryservice.service;

import java.io.Serializable;

/**
 * Created by Martina on 27/09/2017.
 */
public class OrderInfo implements Serializable {

    public OrderInfo()
    {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public OrderInfo(int id)
    {
        this.Id = id;
    }

    public int Id;


        @Override
        public String toString() {
            return "OrderInfo{" +
                    "id='" + Id  +
                    '}';
        }
    }

