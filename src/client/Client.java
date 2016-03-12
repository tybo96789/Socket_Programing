package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 *
 * @author tba_m
 */
public class Client {
    
    private final InetAddress address;
    private final int port;

    public Client(InetAddress byName, int parseInt) {
        this.address = byName;
        this.port = parseInt;
        
        Socket socks = null;
        DataOutputStream out = null;
        BufferedReader in = null;
        try{
            socks = new Socket(this.address,this.port);
            out = new DataOutputStream(socks.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socks.getInputStream()));
        }catch(IOException e)
        {
            System.err.println("Unable to connect server at " + this.address + ":" + this.port);
            System.exit(1);
        }
        
        System.out.println("Connected @ " + socks.getInetAddress() +":"+ socks.getPort() );
        
        try{
            while(true)
            {
                System.out.println(in.readLine());
            }
            
//            boolean isDone = false;
//            String data = null;
//            while(true && !isDone)
//            {
//                data = in.readLine();
//                System.out.println(data);
//                out.writeBytes("Aloha");
//                isDone = true;
//            }
            
            
        }catch(Exception e)
        {
            System.err.println("Socket Closed!");
            System.exit(1);
        }
        finally{
            
        }
        
        
    }
    
    

}
