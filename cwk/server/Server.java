import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class Server{
    public static void main(String args[]){
        AuctionProtocol sp =  new AuctionProtocol();
        ServerSocket server = null;
        ExecutorService service = null;

        try{
            server = new ServerSocket(6000);
            service = Executors.newFixedThreadPool(30);
            
            File logFile = new File("log.txt");
            logFile.delete();
	        logFile.createNewFile();
            
            while(true){
            Socket client = server.accept();
            service.execute(new Runnable(){
                public void run(){
                    new ClientHandler(client, sp).runServer();
                }
            });
           
        }
        }catch(IOException e){
            System.err.println("Could not listen to port: 6000");
            System.exit(-1);
        }

        

    }
}
