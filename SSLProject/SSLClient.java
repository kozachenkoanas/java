import java.io.*;
import java.security.KeyStore;
import javax.net.ssl.*;
public class SSLClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8443;
        char[] password = "123456".toCharArray();
        try {
            // Загружаем truststore клиента
            KeyStore trustStore = KeyStore.getInstance("JKS");
            FileInputStream trustStoreFile = new FileInputStream("client-truststore.jks");
            trustStore.load(trustStoreFile, password);
            // Менеджер доверенных сертификатов
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);
            // SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,trustManagerFactory.getTrustManagers(),null);
            // Подключение
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            SSLSocket socket = (SSLSocket)socketFactory.createSocket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Привет сервер");
            String response = in.readLine();
            System.out.println("Ответ сервера: " + response);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}