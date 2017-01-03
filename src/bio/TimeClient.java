package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TimeClient {
	private static int remotePort=8888;
	private static int localPort=9999;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket(InetAddress.getLocalHost(),remotePort,InetAddress.getLocalHost(),localPort);
		BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		Scanner s=new Scanner(System.in);
		String send=null;
		String receive=null;
		try{
			while(true){
				System.out.println(" ‰»Î√¸¡Ó£∫");
				send=s.nextLine();
				out.println(send);
				receive=in.readLine();
				System.out.println("receive:"+receive);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket=null;
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in=null;
			}
			if(out!=null){
				out.close();
				out=null;
			}
		}
		
	}

}
