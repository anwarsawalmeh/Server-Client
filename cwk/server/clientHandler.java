import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;


public class clientHandler extends Thread 
{
    private Socket socket = null;
    private auctionProtocol sp;
    public clientHandler(Socket socket, auctionProtocol sp){
        super("clientHandler");
        this.socket = socket;
        this.sp = sp;
    }

    
    public void runServer(){
        try
        {
            //ServerSocket servSocket = new ServerSocket(6000);
            
            // Create a file everytime the server is run 
            // Deletes previous Log.txt files if present
            File logFile = new File("log.txt");
            logFile.delete();
	        logFile.createNewFile();
        
            Date date = new Date();
            
            while(!socket.isClosed())
            {
                // accepts when the client makes a connection
                // Socket socket = servSocket.accept();

                // Gets the input passed through the socket
                InputStream inStream = socket.getInputStream();

                // Buffered reader reads the arguments passed through
                BufferedReader clientInput = new BufferedReader(new InputStreamReader(inStream));

                // Take the input and split it up
                String command = clientInput.readLine();
                String[] message = command.split(" ");

                // Allows writing to the client files
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                FileWriter Fwriter = new FileWriter("log.txt", true);
                if(message[0].equals("show")){
                    writer.println(sp.displayItems());
                    writer.close();
                }

                if(message[0].equals("item")){
                    if(message.length > 1){
                        InetAddress inet = socket.getInetAddress();
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
                        InetAddress inet = socket.getInetAddress();
                        String bidStatus = sp.placeBid(message[1],
                        Double.parseDouble(message[2]), inet.getHostAddress());
                        writer.println(bidStatus);
                        
                        if(bidStatus.equals("Accepted.")){
                            Fwriter.write(date.toString()+" | "+inet.getHostAddress()+" | "+Double.parseDouble(message[2])+"\r\n");
                        }
                        
                        writer.close();
                    }
                   
                }
                Fwriter.close();
                
            }
            
        }
        catch(IOException e){
            System.out.println(e);
        }
        

    }
	// public static void main( String[] args )
	// {
    //     Server server = new Server();
    //     server.runServer();
	// }
}