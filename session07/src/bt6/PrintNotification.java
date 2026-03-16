package bt6;

public class PrintNotification implements NotificationService {

    public void send(String message){
        System.out.println("In hoa don: " + message);
    }
}