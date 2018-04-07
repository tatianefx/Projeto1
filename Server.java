import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

class Server
{
	public static void main(String argv[]) throws IOException {

		ServerSocket socket = new ServerSocket(5056);

		
		
		while(true)
		{

			Socket s = null;
			System.out.println("\n--- Aguardando conexao ---\n");
			try
			{
				s = socket.accept();
				System.out.println("Um novo cliente se conectou : " + s);

				DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Criando uma nova thread para o cliente...");

                Thread t = new ClientHandler(s, dis, dos);

                t.start();
			}
			catch (Exception e)
			{
				s.close();
                e.printStackTrace();
			}
		}	
	}

}