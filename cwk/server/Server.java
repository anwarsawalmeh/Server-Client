
import java.io.*;
import java.net.*;
import java.util.*;

public class Server 
{

    public void runServer(){
        auctionProtocol sp =  new auctionProtocol();
        try
        {
            ServerSocket servSocket = new ServerSocket(6000);

            while(true)
            {
                // accepts when the client makes a connection
                Socket sock = servSocket.accept();

                InputStream inStream = sock.getInputStream();

                byte[] buffer = new byte[1024];
                int bytesRead = inStream.read(buffer);
                String message = new String(buffer, 0, bytesRead);
                
                // split up the string
                String[] splitCommand = message.split(" ");

                PrintWriter writer = new PrintWriter(sock.getOutputStream());

                if(splitCommand[0].equals("show")){
                    writer.println(sp.displayItems());
                    writer.close();
                }

                if(splitCommand[0].equals("item")){
                    if(splitCommand.length > 1){
                        InetAddress inet = sock.getInetAddress();
                        String address = inet.getHostName();
                        writer.println(sp.addItem(splitCommand[1],0.00,address));
                        writer.close();
                        Date date = new Date();  
                        System.out.println("\nDate " + date.toString() );
                        System.out.println("Connection made from " + inet.getHostName());
                        
                    }else{
                        writer.println("Client must input an item");
                        writer.close();

                    }
                }

                if(splitCommand[0].equals("bid")){
                    if(splitCommand.length == 1){
                        writer.println("Client must input an item");
                        writer.close();
                    } else if(splitCommand.length == 2){
                        writer.println("Client must make a bid");
                        writer.close();
                    } else if(splitCommand.length == 3){
                        writer.println(sp.placeBid());
                        writer.close();
                    }
                }
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
	public static void main( String[] args )
	{
        Server server = new Server();
        server.runServer();
	}
}
