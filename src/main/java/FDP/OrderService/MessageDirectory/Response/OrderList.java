package FDP.OrderService.MessageDirectory.Response;

import FDP.OrderService.MessageDirectory.Shared.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martina on 04/10/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderList
{
    public List<Order> getItems() {
        return items;
    }

    public void setItems(List<Order> items) {
        this.items = items;
    }

    public OrderList()
    {
        items = new ArrayList<>();
    }

    public List<Order> items;
}
