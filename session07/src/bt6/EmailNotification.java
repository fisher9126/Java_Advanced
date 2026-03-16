package bt6;

public class EmailNotification implements NotificationService {

    public void send(String message){
        System.out.println("Gui email: " + message);
    }
}