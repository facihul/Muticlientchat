package multiclasschat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.*;

public class ChatClient extends JFrame implements Runnable {

	Socket socket;
	JTextArea ta;
	JTextField tf;
	JButton send;
	JButton logout;
	int port = 3330;
	Thread thread;

	DataInputStream din;
	DataOutputStream dout;

	String LoginName;

	ChatClient(String login) throws UnknownHostException, IOException {
		super(login);
		LoginName = login;

		ta = new JTextArea(18, 50);
		tf = new JTextField(50);
		send = new JButton("send");
		logout = new JButton("logout");

		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dout.writeUTF(LoginName + "" + "Data" + tf.getText().toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dout.writeUTF(LoginName + "" + "Data" + tf.getText().toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		socket = new Socket("localhost", port);

		din = new DataInputStream(socket.getInputStream());
		dout = new DataOutputStream(socket.getOutputStream());

		dout.writeUTF(LoginName);
		dout.writeUTF(LoginName + " " + "LOGIN");

		thread = new Thread(this);
		thread.start();
		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub
		setSize(600, 400);

		JPanel panel = new JPanel();

		panel.add(new JScrollPane(ta));
		panel.add(tf);
		panel.add(send);
		panel.add(logout);

		add(panel);

		setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				ta.append("\n" + din.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



}


