
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
            new Client();
        }
        switch(args[0].trim().toLowerCase())
        {
            case "server":
            case "s":
            {
                if(args[1] != null)
                {
                    try{
                       new Server(InetAddress.getByName(args[1]));
                    }catch(UnknownHostException e)
                    {
                        System.err.println(e.toString());
                        try {
                            new Server(InetAddress.getLocalHost());
                        } catch (UnknownHostException ex) {
                            System.err.println("Unable to bind to localhost!");
                            System.exit(0);
                        }
                    }
                    
                }
                break;
            }
            case "client":
            case "c":
            {
                new Client();
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
