import java.io.*;
import java.net.*;

public class Client 
{
	public void connect(String command, String item, int bid){
		System.out.println(command+" "+" "+item+" "+ bid);
		if(command == "show"){
			try{
			Socket cSocket = new Socket("localhost", 6000);
			BufferedReader reader = new BufferedReader(
										new InputStreamReader(
											cSocket.getInputStream()));
			String message = reader.readLine();
			System.out.println(message);
			cSocket.close();
			}catch(IOException e){
				System.out.println(e);
			}
			
		}
		
		
	}

	public static void main( String[] args )
	{
		Client client = new Client();
		if(args.length == 1){
			client.connect(args[0], null, -1);
		}
		else if(args.length == 2){
			client.connect(args[0], args[1], -1);
		}
		else if(args.length == 3){
			client.connect(args[0], args[1], Integer.valueOf(args[2]));
		}
	}
}