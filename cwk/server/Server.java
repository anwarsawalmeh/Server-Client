import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class Server{
    public static void main(String args[]){
        auctionProtocol sp =  new auctionProtocol();
        ServerSocket server = null;
        ExecutorService service = null;

        try{
            server = new ServerSocket(6000);
            service = Executors.newFixedThreadPool(30);
            while(true){
            Socket client = server.accept();
            service.execute(new Runnable(){
                public void run(){
                    new clientHandler(client, sp).runServer();
                }
            });
           
        }
        }catch(IOException e){
            System.err.println("Could not listen to port: 6000");
            System.exit(-1);
        }

        

    }
}
