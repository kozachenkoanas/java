package good;

public class EmailSender implements MessageSender{
    @Override
    public void send(String to, String message) {
        System.out.println("Sending email to " + to + ": " + message);
    }
}
