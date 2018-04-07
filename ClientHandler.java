import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

class ClientHandler extends Thread
{

	String operacao;
	
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) 
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() 
    {
        String received;
        String toreturn;
        Integer chave;
		String valor;
        while (true) 
        {
            try {
 
                // Ask user what he wants
                dos.writeUTF("\n[CREATE][READ][UPDATE][DELETE] | [EXIT]\n");
                 
                // receive the answer from client
                received = dis.readUTF();

                if(received.equals("EXIT"))
                { 
                    System.out.println("Cliente " + this.s + " deseja terminar conexao...");
                    System.out.println("Terminando...");
                    this.s.close();
                    System.out.println("Desconectado");
                    System.out.println("\n--- Aguardando conexao ---\n");
                    break;
                }

                switch (received) {
                	case "CREATE" :
                		dos.writeUTF("Digite a chave da INSERCAO: ");
                		chave = Integer.parseInt(dis.readUTF());
                		dos.writeUTF("Digite o valor da INSERCAO: ");
                		valor = dis.readUTF();

                		Mapa.mapa.put(chave,valor);

                		toreturn = "SERVIDOR: mapa inserido com sucesso!";
                		dos.writeUTF(toreturn);
                	break;

                	case "READ" :
                		dos.writeUTF("Digite a chave da LEITURA: ");
                		chave = Integer.parseInt(dis.readUTF());

                		dos.writeUTF("Valor da chave: " + Mapa.mapa.get(chave));
                	break;

                    case "UPDATE":

                    break;

                    case "DELETE":

                    break;

                    default:
                        dos.writeUTF("Valor invalido");
                    break;
                }

                
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();
             
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}