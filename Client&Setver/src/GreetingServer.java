import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.*;
import java.io.*;

public class GreetingServer extends Thread {
    private ServerSocket serverSocket;

    public String XMLtoSTR(String input_data) {
        String line = "";
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource input_source = new InputSource();
            input_source.setCharacterStream(new StringReader(input_data));
            Document doc = dBuilder.parse(input_source);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("messager");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    line += "\nFirstNick : "
                            + eElement
                            .getElementsByTagName("firstNickname")
                            .item(0)
                            .getTextContent();
                    line += "\nSecondNick : "
                            + eElement
                            .getElementsByTagName("secondNickname")
                            .item(0)
                            .getTextContent();
                    line += "\nMessage : "
                            + eElement
                            .getElementsByTagName("message")
                            .item(0)
                            .getTextContent();
                    line += "\nDate : "
                            + eElement
                            .getElementsByTagName("date")
                            .item(0)
                            .getTextContent();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }

    public void run() {

        try {
            System.out.println("Waiting for client on port " +
                    serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();

            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            while (true) {
                DataInputStream in = new DataInputStream(server.getInputStream());
                String s = in.readUTF();
                System.out.println(s);

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF(XMLtoSTR(s));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static void main(String[] args) {
        int port = 7777;
        try {
            Thread t = new GreetingServer(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}