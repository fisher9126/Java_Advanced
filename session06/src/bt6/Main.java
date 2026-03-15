package bt6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        ExecutorService pool = Executors.newFixedThreadPool(3);

        pool.execute(new BookingCounter("Quầy1", roomA, roomB));
        pool.execute(new BookingCounter("Quầy2", roomA, roomB));
        pool.execute(new BookingCounter("Quầy3", roomA, roomB));

        pool.shutdown();
    }
}