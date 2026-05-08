package good;

public class SmsSender implements MessageSender{
    @Override
    public void send(String to, String message) {
        System.out.println("Sending SMS to " + to + ": " + message);
    }
}
