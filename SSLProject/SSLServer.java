import java.io.*;
import java.security.KeyStore;
import javax.net.ssl.*;
public class SSLServer {
    public static void main(String[] args) {
        int port = 8443;
        char[] password = "123456".toCharArray();
        try {
            // Загружаем server.jks
            KeyStore keyStore = KeyStore.getInstance("JKS");
            FileInputStream keyStoreFile = new FileInputStream("server.jks");
            keyStore.load(keyStoreFile, password);
            // Менеджер ключей
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, password);
            // SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(),null,null
            );
            // SSL сервер
            SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket)serverSocketFactory.createServerSocket(port);
            System.out.println("SSL сервер запущен");
            // Ожидание клиента
            SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
            System.out.println("Клиент подключился");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            String message = in.readLine();
            System.out.println("Сообщение: " + message);
            out.println("Соединение защищено SSL");
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




