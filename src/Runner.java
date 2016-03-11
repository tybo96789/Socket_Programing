
import client.Client;
import server.Server;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author tba_m
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
                   new Server(InetAddress.getByName(args[1]));
                }catch(Exception e)
                {
                    System.err.println(e.toString());
                    try {
                        new Server(InetAddress.getLocalHost());
                    } catch (UnknownHostException ex) {
                        System.err.println("Unable to bind to localhost!");
                        System.exit(0);
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
                    System.err.println(e.toString());
                    try {
                        new Server(InetAddress.getLocalHost());
                    } catch (Exception ex) {
                        System.err.println("Unable to bind to localhost!");
                        System.exit(0);
                    }
                }
                    

                break;
            }
            default:
            {
                System.err.println("Unknown Command. Terminating");
                System.exit(0);
            }
        }
    }

}
