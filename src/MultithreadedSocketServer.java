import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedSocketServer {
    public static void main(String[] args) {
        try{
            ServerSocket server=new ServerSocket(9999);

            System.out.println("Сервер запущен");

            while(true){
                Socket serverClient = server.accept(); // сервер принимает запрос на подключение клиента
                System.out.println(" >>Вы подключились, поток данных запущен...");
                ServerClientThread sct = new ServerClientThread(serverClient); // отправляем запрос в отдельный поток
                sct.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}