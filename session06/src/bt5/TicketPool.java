package bt5;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    List<Ticket> tickets = new ArrayList<>();

    public synchronized Ticket holdTicket() {

        for (Ticket t : tickets) {

            if (!t.isHeld) {

                t.isHeld = true;
                t.expireTime = System.currentTimeMillis() + 5000;

                return t;
            }
        }

        return null;
    }

    public synchronized void releaseExpired() {

        long now = System.currentTimeMillis();

        for (Ticket t : tickets) {

            if (t.isHeld && now > t.expireTime) {

                System.out.println("Vé " + t.id + " hết hạn giữ");

                t.isHeld = false;
            }
        }
    }
}