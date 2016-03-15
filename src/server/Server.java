package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Tyler Atiburcio
 */
public class Server {
    private final static int DEFAULT_PORT_NUM = 80;
    private InetAddress inetAddress;
    private ServerSocket socks;
    
    
    public Server(InetAddress inetAddress)
    {
        new Server(inetAddress,DEFAULT_PORT_NUM);
    }

    public Server(InetAddress inetAddress, int port) {
        this.inetAddress = inetAddress;
        try {
            this.socks = new ServerSocket(port,0,this.inetAddress);
        } catch (IOException ex) {
            System.err.println("Unable to bind to port: " + port);
            System.exit(1);
        }
        
        System.out.println("Ready...");
        
        Socket clientSocks = null;
        try{
            clientSocks = socks.accept();
        }catch(IOException e)
        {
            System.err.println("Accept Failed");
            System.exit(1);
        }
        
        System.out.println("Connection Established @" + clientSocks.getInetAddress() +":" + clientSocks.getPort());
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
        
        String data = null;
        boolean isDone = false;
        try{

        while(true && !isDone)
        {
            data = in.readLine();
            System.out.println(data);
            if(data.equalsIgnoreCase("aloha"))
            {
                out.println("Hello");
                int fileData = 0;
                ArrayList<Integer> dataBytes = new ArrayList<Integer>();
                while((fileData = Integer.parseInt(in.readLine()))!= -1)
                {
                    dataBytes.add(fileData);
                }
                System.out.println("Data recieved:" + dataBytes.get(0) + " Bytes");
                out.println("Your file named \""+writeFile(dataBytes)+"\" with the size "+ dataBytes.get(0)+" bytes has been uploaded correctly");
                if((data = in.readLine()).equals("-1")) isDone = true;
            }
        }
        }catch(IOException e)
        {
            System.err.println("Socket IO error");
        }
        finally
        {
            System.out.println("Connection closed @" + clientSocks.getInetAddress() +":" + clientSocks.getPort());
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                System.err.println("Failed to Close streams"); 
                System.exit(1);
            }
        }
        
        
    }
    
    private static File writeFile(ArrayList<Integer> data)
    {
        File file = new File("Server" + new Random().nextInt(10) + "" + new Random().nextInt(10) + "" + new Random().nextInt(10) + ".txt");
        FileOutputStream fileOut = null;
        try {
             fileOut = new FileOutputStream(file);
            for (int i = 1; i < data.size(); i++) {
                fileOut.write(data.get(i));
            }

        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        } catch (IOException ex) {
            System.err.println("Error writing file!");
        }
        finally{
            try {
                fileOut.flush(); fileOut.close();
            } catch (IOException ex) {
                System.err.println("Error writing file!");
            }
        }
        return file;
        
    }
}
