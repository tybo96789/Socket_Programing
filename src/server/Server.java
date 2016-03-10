package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tba_m
 */
public class Server {
    final static int PORT_NUM = 80;
    final InetAddress inetAddress;
    ServerSocket socks;

    public Server(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
        try {
            this.socks = new ServerSocket(PORT_NUM);
        } catch (IOException ex) {
            System.err.println("Unable to bind to port: " + PORT_NUM);
            System.exit(1);
        }
        Socket clientSocks = null;
        try{
            clientSocks = socks.accept();
        }catch(IOException e)
        {
            System.err.println("Accept Failed");
            System.exit(1);
        }
        
        System.out.println("Connection Established");
        PrintWriter out = null;
        BufferedReader in = null;
        try{
        out = new PrintWriter(clientSocks.getOutputStream(),true);
        in = new BufferedReader( new InputStreamReader(clientSocks.getInputStream()));
        
        }catch(IOException e)
        {
            System.err.println("Error getting to streams");
            System.exit(1);
        }
        
        
    }
}
