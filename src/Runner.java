
import client.Client;
import server.Server;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Tyler Atiburcio
 */
public class Runner {
    
    public static void main(String[] args) {
        if(args.length == 0)
        {
            try {
                new Client(InetAddress.getLocalHost(),80);
            } catch (UnknownHostException ex) {
                System.err.println("Unable to get Localhost");
                System.exit(1);
            }
        }
        switch(args[0].trim().toLowerCase())
        {
            case "server":
            case "s":
            case "-server":
            case "-s":
            {
                try{
                   new Server(InetAddress.getByName(args[1]),Integer.parseInt(args[2]));
                }catch(UnknownHostException e)
                {
                    System.err.println("Unable to bind to address!\nUsing Default Values");
                    try {
                        new Server(InetAddress.getLocalHost());
                    } catch (UnknownHostException ex) {
                        System.err.println("Unable to bind to localhost!");
                        System.exit(0);
                    }
                }catch(ArrayIndexOutOfBoundsException e)
                {
                    
                    try {
                        new Server(InetAddress.getByName(args[1]));
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        try{
                            System.err.println("No Address supplied!\nUsing Default Values");
                            new Server(InetAddress.getLocalHost());
                        }catch(UnknownHostException e1)
                        {
                            System.err.println("Unable to bind to localhost!");
                            System.exit(0);
                        }
                    } catch (UnknownHostException ex) {
                        try {
                            new Server(InetAddress.getLocalHost());
                        } catch (UnknownHostException e2) {
                            System.err.println("Unable to bind to localhost!");
                            System.exit(0);
                    }
                    }
                }
                break;
            }
            case "client":
            case "c":
            case "-client":
            case "-c":
            {
                try{
                   new Client(InetAddress.getByName(args[1]),Integer.parseInt(args[2]));
                }catch(UnknownHostException e)
                {
                    System.err.println("Unable to find specifed address!");
                    try {
                        new Client(InetAddress.getLocalHost(),80);
                    } catch (Exception ex) {
                        System.err.println("Unable to bind to localhost!");
                        System.exit(0);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    System.err.println("No address/port supplied\nUsing Default Values");
                    try {
                        new Client(InetAddress.getLocalHost(),80);
                    } catch (UnknownHostException ex) {
                        System.err.println("Unable to bind to localhost!");
                        System.exit(0);
                    }
                }
                break;
            }
            default:
            {
                System.out.println("Usage: [Client/Server] [Options]");
                System.out.println("Server: \"-s\" [Binding Address] [Binding Port]");
                System.out.println("Client: \"-c\" [Server Address] [Server Port]");
                System.exit(0);
            }
        }
    }

}
