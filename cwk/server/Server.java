
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
            File logFile = new File("log.txt");
	        logFile.createNewFile();
            Date date = new Date();
            while(true)
            {
                // accepts when the client makes a connection
                Socket sock = servSocket.accept();

                // Gets the input passed through the socket
                InputStream inStream = sock.getInputStream();

                // Buffered reader reads the arguments passed through
                BufferedReader clientInput = new BufferedReader(new InputStreamReader(inStream));

                // Take the input and split it up
                String command = clientInput.readLine();
                String[] message = command.split(" ");

                // Allows writing to the client files
                PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
                
                if(message[0].equals("show")){
                    writer.println(sp.displayItems()+"\n");
                    writer.close();
                }

                if(message[0].equals("item")){
                    if(message.length > 1){
                        InetAddress inet = sock.getInetAddress();
                        String address = inet.getHostName();
                        writer.println(sp.addItem(message[1],0.00,address));
                        writer.close();
                        // format the date
                        System.out.println("\nDate " + date.toString() );
                        System.out.println("Connection made from " + inet.getHostName());

                    }else{
                        writer.println("Client must input an item");
                        writer.close();

                    }
                }

                if(message[0].equals("bid")){
                    if(message.length == 1){
                        writer.println("Client must input an item");
                        writer.close();
                    } else if(message.length == 2){
                        writer.println("Client must make a bid");
                        writer.close();
                    } else if(message.length == 3){
                        InetAddress inet = sock.getInetAddress();
                        String bidStatus = sp.placeBid(message[1],
                        Double.parseDouble(message[2]), inet.getHostName());
                        writer.println(bidStatus);
                        
                        if(bidStatus.equals("Accepted.")){
                            FileWriter Fwriter = new FileWriter("log.txt");
                            Fwriter.write(date.toString()+" | "+inet.getHostName()+" | "+message[0]);
                            Fwriter.close();
                        }

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
