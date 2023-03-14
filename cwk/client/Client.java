import java.io.*;
import java.net.*;

public class Client 
{
	public void connect(String input){
			try{
			// Socket that takes in the IP address and selects the port
			Socket cSocket = new Socket("localhost", 6000);
			// Print Writer, takes in arguments
			PrintWriter clientWriter = new PrintWriter(cSocket.getOutputStream(), true);
			// allows to write 
			clientWriter.println(input);
			BufferedReader reader = new BufferedReader(
										new InputStreamReader(
											cSocket.getInputStream()));

			// gets the line from the server writer
			String message = reader.readLine();
			System.out.println(message);
			cSocket.close();
			}catch(IOException e){
				System.out.println(e);
			}
	}

	public static void main( String[] args )
	{
		Client client = new Client();
		StringBuilder com = new StringBuilder();
		for(int count = 0; count < args.length; count++){
			com = com.append(args[count]);
			com = com.append(" ");
		}
		String command = com.toString();
		client.connect(command);
	}
}