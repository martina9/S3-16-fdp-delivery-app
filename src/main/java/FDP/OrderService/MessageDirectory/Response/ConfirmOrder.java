package FDP.OrderService.MessageDirectory.Response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Martina on 04/10/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmOrder
{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int  id;
}
