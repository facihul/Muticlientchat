package multiclasschat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class loginclass {
	
	 public static void main(String[] args){
	       JFrame login = new JFrame("login");
	       JPanel panel = new JPanel();
	       JTextField loginName = new JTextField(30);
	       JButton enter = new JButton("Login");
	       
	       panel.add(loginName);
	       panel.add(enter);
	       login.setSize(300,100);
	       login.add(panel);
	       login.setVisible(true);
	       login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
		enter.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e){
	    		   try {
					
					ChatClient clientObj = new ChatClient(loginName.getText());
					
					login.setVisible(false);
					login.dispose();
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		   
	    		   
	    	   }
	    	   
	       });
	       
	
	        
	    }
	  

}
