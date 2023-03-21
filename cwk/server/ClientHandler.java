import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.time.LocalDate;
import java.time.LocalTime;



public class ClientHandler extends Thread 
{
    private Socket socket = null;
    private AuctionProtocol sp;
    private PrintWriter writer;
    // Passing in the socket and the AuctionProtocol object from Server
    public ClientHandler(Socket socket, AuctionProtocol sp){
        super("ClientHandler");
        this.socket = socket;
        this.sp = sp;
    }

    // Runs the server and deals with all the client messages and protocols
    public void runServer(){
        try
        {
            // Date & Time for file and server
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            // Iterates as long as the socket is open
            while(!socket.isClosed())
            {
                // Gets the input passed through the socket
                InputStream inStream = socket.getInputStream();

                // Buffered reader reads the arguments passed through
                BufferedReader clientInput = new BufferedReader(new InputStreamReader(inStream));

                // Take the input and split it up
                String command = clientInput.readLine();
                String[] message = command.split(" ");

                // Allows writing to the client files
                writer = new PrintWriter(socket.getOutputStream(), true);
                FileWriter Fwriter = new FileWriter("log.txt", true);

                // If statements to know what to do
                if(message[0].equals("show")){
                    if(sp.sp.isEmpty()){
			                writer.println("There are currently no items in this auction.");
		            }
                    for(String items: sp.sp.keySet()){
                        // formats String into a StringBuilder
                        AuctionProtocol.info itemsDetails = sp.sp.get(items);
                        // prints for Server and writes to Client
                        System.out.print(items+ " "+itemsDetails.getBid()+" "+itemsDetails.getIpAddress()+" \n");
                        writer.print(items+ " : "+itemsDetails.getBid()+" : "+itemsDetails.getIpAddress()+",");
                        
                    }
                }

                if(message[0].equals("item")){
                    if(message.length > 1){
                        InetAddress inet = socket.getInetAddress();
                        String address = inet.getHostName();
                        String addItemS = sp.addItem(message[1],0.00,address);
                        writer.println(addItemS);
                        // format the date
                        System.out.println("\nDate " + date.toString() );
                        System.out.println("Connection made from " + inet.getHostName());
                        // Formatting and writing for file
                        Fwriter.write(date.toString()+" | "+time.toString()+" | "+inet.getHostAddress()+" | "+message[0]+"\r\n");
                    
                    }else{
                        writer.println("Client must input an item");

                    }
                }

                if(message[0].equals("bid")){
                    // validations
                    if(message.length == 1){
                        writer.println("Client must input an item");
                
                    } else if(message.length == 2){
                        writer.println("Client must make a bid");
                        
                    } else if(message.length == 3){
                        InetAddress inet = socket.getInetAddress();
                        String bidStatus = sp.placeBid(message[1],
                        Double.parseDouble(message[2]), inet.getHostAddress());
                        writer.println(bidStatus);
                        // Formatting and writing for file
                        Fwriter.write(date.toString()+" | "+time.toString()+" | "+inet.getHostAddress()+" | "+message[0]+"\r\n");
                         
                        
                    }
                }
                // checks if valids command line argument
                if(!message[0].equals("show") && !message[0].equals("item") && !message[0].equals("bid")){
                    writer.println("Please input a valid command: (show) or (item <string>) or (bid <item> <value>)");
                }
                // closes file writer and print writer
                Fwriter.close();
                writer.close();
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}