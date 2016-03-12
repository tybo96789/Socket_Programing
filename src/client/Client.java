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
        PrintWriter out = null;
        BufferedReader in = null;
        
//        BufferedReader infromusr = null;
        
        try{
            socks = new Socket(this.address,this.port);
            out = new PrintWriter(socks.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socks.getInputStream()));
            
//            infromusr = new BufferedReader(new InputStreamReader(System.in));
            
        }catch(IOException e)
        {
            System.err.println("Unable to connect server at " + this.address + ":" + this.port);
            System.exit(1);
        }
        
        System.out.println("Connected @ " + socks.getInetAddress() +":"+ socks.getPort() );
        
        try{
//            while(true)
//            {
////                out.writeBytes(infromusr.readLine());
//                
//                out.println("Aloha");
//                System.out.println(in.readLine());
//            }
            
            boolean isDone = false;
            String data = null;
            while(true && !isDone)
            {
                out.println("Aloha");
                data = in.readLine();
                System.out.println(data);
                if(data.equalsIgnoreCase("hello"))isDone = true;
            }
            
            
        }catch(Exception e)
        {
            System.err.println("Socket Closed!");
            System.exit(1);
        }
        finally{
            
        }
        
        
    }
    
    

}
