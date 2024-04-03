package Sockets;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import static Sockets.PinguLib.readString;


public class Chat {

    private Socket sock;
    private boolean isServer;
    private boolean initializationSuccessful;

    public static void main(String[] args) {
        new Chat().run();
    }

    public void run() {
        initializationSuccessful = false;
        initializeConnection();
        if (!initializationSuccessful) {
            return;
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
             PrintWriter out = new PrintWriter(sock.getOutputStream(), true)) {
            if (isServer) {
                System.out.println("Verbindung hergestellt! Du kannst nun etwas senden.");
            } else {
                System.out.println("Verbindung hergestellt! Erwarte Nachricht vom Server.");
            }
            chatInTurns(out, in);
            System.out.println("Beenden.");

        } catch (IOException e) {
            System.out.println("Verbindungsfehler, Beenden.");
        } finally {
            terminateConnection();
        }
    }

    private void initializeConnection() {
        while (true) {
            System.out.println("Gib <port> an, um den Chatserver zu starten oder "
                    + "<host>:<port>, um dich mit einem laufenden Server zu verbinden.");
            System.out.println("Gib zum Beenden exit ein");
            String input = readString("");
            if (input.equals("exit")) {
                System.out.println("Beenden.");
                return;
            }

            try {
                if (isServerInput(input)) {
                    int port = Integer.parseInt(input);
                    startServer(port);
                } else {
                    String host = getHost(input);
                    int port = getPort(input);
                    connectToServer(host, port);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Port ungültig/keine Zahl, versuche es erneut!");
            } catch (UnknownHostException e) {
                System.out.println("Host unbekannt, versuche es erneut!");
            } catch (BindException e) {
                System.out.println("Port Binding fehlgeschlagen, Prozess läuft bereits?");
            } catch (ConnectException e) {
                System.out.println("Verbindung abgelehnt, versuche es erneut!");
            } catch (IOException e) {
                System.out.println("Ein-/Ausgabefehler, versuche es erneut!");
            }
        }
        initializationSuccessful = true;
    }

    private void startServer(int port) throws IOException {

        ServerSocket server = new ServerSocket(port);
        System.out.println("Warte auf Verbindung...");
        sock = server.accept();
        server.close();
        isServer = true;

    }

    private void connectToServer(String host, int port) throws IOException {
        sock = new Socket(host, port);
        isServer = false;
    }

    private void chatInTurns(PrintWriter out, BufferedReader in) throws IOException {
        boolean ourTurn = isServer;
        while (true) {
            if (ourTurn) {

                String input = new Scanner(System.in).nextLine();
                if(input.equals("exit")){
                    //out stands for the println version of the PrintWriter - meaning the printing the output on the
                    //other side's console.
                    out.println("exit");
                    break;
                }
                out.println(input);


            } else {

                String fromClient = in.readLine();
                if(fromClient.equals("exit")){
                    //write prints the stuff on THIS console with sout
                    System.out.println("exit empfangen");
                    break;
                }
                System.out.println(fromClient);
                // 1. Read a line from the other side and print it to the console.
                // 2. If the line is "exit", break the loop.
            }
            ourTurn = !ourTurn;
        }
    }

    private void terminateConnection(){
        try {
            sock.close();
        }catch (IOException e){
            System.out.println("Verbindungsabbau fehlgeschlagen");
        }
        // 1. Terminate the connection by closing the socket.
    }

    private static boolean isServerInput(String input) {
        return !input.contains(":");
    }

    private static String getHost(String input) {
        return input.substring(0, input.indexOf(':'));
    }

    private static int getPort(String input) throws NumberFormatException {
        return Integer.parseInt(input.substring(input.indexOf(':') + 1));
    }

}
