package bt5;

public class TimeoutManager implements Runnable {

    private TicketPool pool;

    public TimeoutManager(TicketPool pool) {
        this.pool = pool;
    }

    public void run() {

        while (true) {

            pool.releaseExpired();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}