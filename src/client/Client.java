package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

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
                if(data.equalsIgnoreCase("hello")){
                    System.out.println("Reading File");
                    for (Integer fileData : readFile("TESTING.txt"))
                        out.println(fileData);
                    out.println(-1);
                    while(true)
                    {
                        if(!(data = in.readLine()).equals("-1"))
                        {
                            System.out.println(data);
                            break;
                        }
                    }
                    out.println("-1");
                    isDone = true;
                }
            }
            
            
        }catch(Exception e)
        {
            System.err.println("Socket Closed!");
            System.exit(1);
        }
        finally{
            
        }
        
        
    }
    
    private static int[] readFile(String fileName)
    {
        ArrayList<Integer> data = new ArrayList<Integer>();
        FileInputStream fileIn;
        File file = null;
        try {
            file = new File(fileName);
            fileIn = new FileInputStream(file);
            int temp = 0;
            data.add((int)file.length());
            while((temp = fileIn.read()) != -1)
            {
                data.add(temp);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File Not found!");
            System.exit(0);
        } catch (IOException ex) {
            System.err.println("Error Reading File!");
            System.exit(0);
        }
        int[] byteData = new int[data.size()];
        for (int i = 0; i < data.size(); i++)
            byteData[i] = data.get(i);
        return byteData;
    }
    
    

}
