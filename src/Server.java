import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server;
    Socket client;
    int v1;
    int v2;
    String symbol;
    BufferedReader in;
    DataOutputStream out;

    public Socket listening() throws IOException{
        server = new ServerSocket(8080); //creazione server

        System.out.println("server in ascolto su " + server.getLocalPort());
        client = server.accept(); //inizio accettazione connessionei

        in = new BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
        out = new DataOutputStream(client.getOutputStream());

        return client;
    }

    public void send() throws IOException {
        String[] parts = in.readLine().split(" "); //lettura messaggio da client e split
        double res;

        v1 = Integer.parseInt(parts[0]);
        v2 = Integer.parseInt(parts[1]);
        symbol = parts[2];

        //switchCase per gestione operazioni
        switch (symbol) {
            case "+":
                out.writeBytes(String.valueOf(v1 + v2) + "\n");
                break;
            case "-":
                out.writeBytes(String.valueOf(v1 - v2) + "\n");
                break;
            case "*":
                out.writeBytes(String.valueOf(v1 * v2) + "\n");
                break;
            case "/":
                res = (double) v1 / v2;
                out.writeBytes(String.valueOf(res) + "\n");
                break;
            default:
                out.writeBytes("Operatore non valido");
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        Server s = new Server();
        s.listening();
        s.send();
    }
}
