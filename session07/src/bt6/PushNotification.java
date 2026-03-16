package bt6;

public class PushNotification implements NotificationService {

    public void send(String message){
        System.out.println("Push notification: " + message);
    }
}