import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client
{
	public static void main(String argv[]) throws IOException 
	{

		try
        {
            Scanner scn = new Scanner(System.in);
             
            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");
     
            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);
     
            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
 			String valor, chave, operacao;
            while (true) 
            {
                System.out.println(dis.readUTF());
                operacao = scn.nextLine();
                dos.writeUTF(operacao);


                switch (operacao) {
                	case "CREATE":
                		System.out.println(dis.readUTF());
		                chave = scn.nextLine();
		                dos.writeUTF(chave);

		                System.out.println(dis.readUTF());
		                valor = scn.nextLine();
		                dos.writeUTF(valor);
                	break;

                	case "READ":
                		System.out.println(dis.readUTF());
		                chave = scn.nextLine();
		                dos.writeUTF(chave);
                	break;

                	case "UPDATE":

                	break;

                	case "DELETE":

                	break;

                	default:
                		System.out.println(dis.readUTF());
                	break;
                }
                 
                // If client sends exit,close this connection 
                // and then break from the while loop
                if(operacao.equals("Exit"))
                {
                    System.out.println("Terminando essa conexao : " + s);
                    s.close();
                    System.out.println("**DESCONECTADO**");
                    break;
                }
                

                String received = dis.readUTF();
                System.out.println(received);
            }

            scn.close();
            dis.close();
            dos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}