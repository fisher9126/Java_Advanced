package bt6;

public class BookingCounter implements Runnable {

    private String name;
    private TicketPool roomA;
    private TicketPool roomB;

    public BookingCounter(String name, TicketPool a, TicketPool b) {
        this.name = name;
        this.roomA = a;
        this.roomB = b;
    }

    public void run() {

        while (true) {

            String ticket = roomA.sell();

            if (ticket == null)
                ticket = roomB.sell();

            if (ticket == null)
                return;

            System.out.println(name + " bán " + ticket);

            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}