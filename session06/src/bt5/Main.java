package bt5;

public class Main {

    public static void main(String[] args) {

        TicketPool pool = new TicketPool();

        for (int i = 1; i <= 10; i++) {
            pool.tickets.add(new Ticket("T-" + i));
        }

        new Thread(new TimeoutManager(pool)).start();
    }
}