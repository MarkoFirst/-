import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class GreetingClient {

    public static void main(String[] args) {

        String serverName = "127.0.0.1";
        int port = 7777;
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            while (true) {

                OutputStream outToServer = client.getOutputStream();
                Scanner name = new Scanner(System.in);
                String firstNickname = "Marko";
                String secondNickname = name.nextLine();
                String message = name.nextLine();

                Date date = new Date(System.currentTimeMillis());;

                DataOutputStream out = new DataOutputStream(outToServer);

                String xml = "<?xml version = \"1.0\"?>\n" +
                        "<class>\n" +
                        "   <messager rollno = \"393\">\n" +
                        "      <firstNickname>" + firstNickname + "</firstNickname>\n" +
                        "      <secondNickname>" + secondNickname + "</secondNickname>\n" +
                        "      <message>" + message + "</message>\n" +
                        "      <date>" + date + "</date>\n" +
                        "   </messager>\n" +
                        "   \n" +
                        "</class>";

                out.writeUTF(xml);
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);

                System.out.println("Server says " + in.readUTF());
            }
            // client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

