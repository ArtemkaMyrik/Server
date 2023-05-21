import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.net.Socket;

class ServerClientThread extends Thread {
    Socket serverClient;
    ServerClientThread(Socket inSocket){
        serverClient = inSocket;
    }
    public void run(){
        try{
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());

            String clientMessage_1 = "";
            String clientMessage_2 = "";
            String clientMessage_3 = "";
            String clientMessage_4 = "";
            String clientMessage_5 = "";
            String clientMessage_6 = "";


            while(!clientMessage_1.equals("Сверчки")){

                clientMessage_1 = inStream.readUTF();
                clientMessage_2 = inStream.readUTF();
                clientMessage_3 = inStream.readUTF();
                clientMessage_4 = inStream.readUTF();
                clientMessage_5 = inStream.readUTF();
                clientMessage_6 = inStream.readUTF();

                try (FileWriter writer = new FileWriter("ЦП.txt.txt", false)){
                    writer.write("Показатели использования java в памяти:\r\n");
                    writer.write(clientMessage_1);
                    writer.write(clientMessage_2);
                    writer.write(clientMessage_3);
                    writer.write(clientMessage_4);

                    writer.write("Показатели IP:\r\n");
                    writer.write(clientMessage_5);
                    writer.write(clientMessage_6);
                }
            }

            inStream.close();
            outStream.close();
            serverClient.close();

        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            System.out.println("Клиентик покинул чат сверчки...");
        }
    }
}