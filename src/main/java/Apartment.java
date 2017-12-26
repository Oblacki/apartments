import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author Matej Dolenc
 */

public class Apartment {

    private String id;
    private String numOfBeds;
    private String customerId;
    private List<String> guestIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(String numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getGuestIds() { return guestIds; }

    public void setGuestIds(List<String> guestIds) { this.guestIds = guestIds; }

}
