
package multiclasschat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mdfacihulazam
 */
public class multiclientchat {

     static Vector  ClientSockets;
     static Vector  LoginNames;
     int port =  3333;
     
    public static void main(String[] args) throws IOException {
       
        multiclientchat server = new multiclientchat();
        
    }
  
    multiclientchat() throws IOException{
     
    ServerSocket server = new ServerSocket(port);
    ClientSockets = new Vector();
    LoginNames = new Vector();
    
    while (true){
     Socket client = server.accept();
     AcceptClient acceptclient = new AcceptClient(client); 
    }
    
    
    } 

     class AcceptClient extends Thread{
        Socket ClientSocket;
        DataInputStream DataIn;
        DataOutputStream DataOut;
        
        
        public AcceptClient(Socket client) throws IOException {
            
            ClientSocket = client;
            DataIn = new DataInputStream(ClientSocket.getInputStream());
            DataOut = new DataOutputStream(ClientSocket.getOutputStream());
            
            String LoginName= DataIn.readUTF();
            LoginNames.add(LoginName);
            ClientSockets.add(ClientSocket);
            start();
                 
        }
        
       public void run(){
        while(true){
            try {
                String msgClient = DataIn.readUTF();
                StringTokenizer st =  new StringTokenizer(msgClient);
                String LoginName = st.nextToken();
                String msgType = st.nextToken();
                
               for(int i = 0; i<LoginNames.size(); i++){
                 Socket psocket =(Socket)ClientSockets.elementAt(i);
                 DataOutputStream pout = new DataOutputStream(psocket.getOutputStream());
                 pout.writeUTF(LoginName + "has loged in !");
               
               } 
                
            } catch (IOException ex) {
                Logger.getLogger(multiclientchat.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
       
       
       } 
        
    }
    
    
}
