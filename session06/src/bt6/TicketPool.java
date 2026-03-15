package bt6;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    private String room;
    private Queue<String> tickets = new LinkedList<>();

    public TicketPool(String room, int n) {

        this.room = room;

        for (int i = 1; i <= n; i++) {
            tickets.add(room + "-" + String.format("%03d", i));
        }
    }

    public synchronized String sell() {
        return tickets.poll();
    }
}