import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) {
        int counter = 0;
        String IP;
        try{
            Socket socket = new Socket("127.0.0.1",9999);

            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage_1 = "";
            String clientMessage_2 = "";
            String clientMessage_3 = "";
            String clientMessage_4 = "";
            String clientMessage_5 = "";
            String clientMessage_6 = "";

            while(!clientMessage_1.equals("Сверчки")){
                Thread.sleep(3000);

                counter += 1;

                InetSocketAddress IPSocket = (InetSocketAddress)socket.getRemoteSocketAddress();

                try (final DatagramSocket datagramSocket = new DatagramSocket()) {
                    datagramSocket.connect(InetAddress.getByName("9.9.9.9"), 12345);
                    IP = datagramSocket.getLocalAddress().getHostAddress();
                }

                MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

                clientMessage_1 = String.format("%.10f\r\n", (double)memoryMXBean.getHeapMemoryUsage().getInit() /1073741824);
                clientMessage_2 = String.format("%.10f\r\n", (double)memoryMXBean.getHeapMemoryUsage().getUsed() /1073741824);
                clientMessage_3 = String.format("%.10f\r\n", (double)memoryMXBean.getHeapMemoryUsage().getMax() /1073741824);
                clientMessage_4 = String.format("%.10f\r\n", (double)memoryMXBean.getHeapMemoryUsage().getCommitted() /1073741824);
                clientMessage_5 = "/" + IP + "\r\n";
                clientMessage_6 = IPSocket + "\r\n";

                System.out.println("Данные №" + counter + " отправлены ");

                //Отправка данных от клиентика к серверочку
                outStream.writeUTF(clientMessage_1);
                outStream.writeUTF(clientMessage_2);
                outStream.writeUTF(clientMessage_3);
                outStream.writeUTF(clientMessage_4);
                outStream.writeUTF(clientMessage_5);
                outStream.writeUTF(clientMessage_6);
                outStream.flush();
            }

            outStream.close();
            outStream.close();
            socket.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
