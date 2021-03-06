import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buffer = new byte[256];

    public Server() throws SocketException {
        socket = new DatagramSocket(5544);
    }


    public void run() {
        running = true;
        while (running) {
            try {
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);

                InetAddress address = receivePacket.getAddress();
                int port = receivePacket.getPort();


                String received = new String(receivePacket.getData(), 0, receivePacket.getLength());  //смещение для буфера,по которому ищется первый byte,
                // затем начиная с него считываем количество byte длиной length.

                if (received.equals("end")) {
                    running = false;
                    continue;
                }
                DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(sendPacket);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
