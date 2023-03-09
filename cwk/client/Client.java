import java.io.*;
import java.net.*;

public class Client 
{
	public void connect(){

		try
		{
			Socket cSocket = new Socket("localhost", 6000);
			BufferedReader reader = new BufferedReader(
										new InputStreamReader(
											cSocket.getInputStream() ) );
			String message = reader.readLine();
			System.out.println(message);
			cSocket.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
		
	}


	public static void main( String[] args )
	{
		Client client = new Client();
		client.connect();
	}
}