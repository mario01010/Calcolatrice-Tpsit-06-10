import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.InputMismatchException;

public class Client {
    String host = "localhost"; //client
    int port = 8080; //porta
    int v1;
    int v2;
    String symbol;
    BufferedReader in;
    DataOutputStream out;
    Socket client;
    BufferedReader t; //tastiera

    public Socket connection() throws Exception {
        client = new Socket(host, port); //connessione
        System.out.println("Client connesso");
        t = new BufferedReader(new InputStreamReader(System.in));

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new DataOutputStream(client.getOutputStream());

        return client;
    }

    public void send() throws IOException {
        boolean ok = true; //controllo valori
        do{
            try{
                System.out.println("Inserisci il primo valore: ");
                v1 = Integer.parseInt(t.readLine());
                ok = false;
            } catch (NumberFormatException |InputMismatchException e){
                ok = true;
            }
        } while (ok);

        ok = true;

        do{
            try{
                System.out.println("Inserisci il secondo valore: ");
                v2 = Integer.parseInt(t.readLine());
                ok = false;
            } catch (NumberFormatException |InputMismatchException e){
                ok = true;
            }
        } while (ok);

        ok = true;

        do{
            try{
                System.out.println("Inserisci il simbolo: ");
                symbol = t.readLine();
                ok = false;
            } catch(NumberFormatException | InputMismatchException | SocketException e){
                ok = true;
            }
        }  while (ok);

        out.writeBytes(v1 + " " + v2 + " " + symbol + "\n"); //invio server

        System.out.println(v1 + " " + symbol + " "+ v2 + " = " + in.readLine()); //output server
    }

    public static void main(String[] args) throws Exception {
        Client c = new Client();
        c.connection();
        c.send();
    }

}
