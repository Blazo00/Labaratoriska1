package mk.finki.ukim.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class EventBooking {

    private String eventName;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberofTickets;

    public long getNumberofTickets() {
        return numberofTickets;
    }

}
