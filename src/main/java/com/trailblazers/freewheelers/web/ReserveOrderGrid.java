package com.trailblazers.freewheelers.web;


import com.trailblazers.freewheelers.model.ReserveOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReserveOrderGrid {

    private Map<Long, ReserveOrder> reserveOrderMap;

    public ReserveOrderGrid() {
        reserveOrderMap = new HashMap<Long, ReserveOrder>();
    }

    public ReserveOrderGrid(List<ReserveOrder> orders) {
        this();

        for (ReserveOrder reserveOrder : orders) {

            reserveOrderMap.put(reserveOrder.getOrder_id(), reserveOrder);

        }
    }

    public List<ReserveOrder> getListOfOrders() {
        return new ArrayList<ReserveOrder>(reserveOrderMap.values());
    }

    public Map<Long, ReserveOrder> getReserveOrderMap() {
        return reserveOrderMap;
    }

    public void setReserveOrderMap(Map<Long, ReserveOrder> reserveOrderMap) {
        this.reserveOrderMap = reserveOrderMap;
    }
}
