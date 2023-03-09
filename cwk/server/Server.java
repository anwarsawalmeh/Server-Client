import java.io.*;
import java.net.*;
import java.util.*;
public class Server 
{
    public void runServer(){

        try
        {
            ServerSocket servSocket = new ServerSocket(6000);

            while(true)
            {
                Socket sock = servSocket.accept();
                InetAddress inet = sock.getInetAddress();
                Date date = new Date();
                System.out.println("\nDate " + date.toString() );
                System.out.println("Connection made from " + inet.getHostName());

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