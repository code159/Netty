package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {
	private static int port=8888;

	public static void main(String[] args) throws IOException {
		ServerSocket ss=null;
		Socket socket=null;
		try{
			ss=new ServerSocket(port);
			while(true){
				socket=ss.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ss!=null){
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ss=null;
			}
		}		
	}

}

class TimeServerHandler implements Runnable{
	private Socket socket;
	
	public TimeServerHandler(Socket s){
		this.socket=s;
	}

	public void run() {
		BufferedReader in=null;
		PrintWriter out=null;
		try {
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			String receive=null;
			String send=null;
			while(true){
				if(in==null){
					break;
				}
				receive=in.readLine();
				if(receive==null){
					break;
				}
				send=receive.equals("DATE")?("当前日期是："+new Date(System.currentTimeMillis())):"BAD COMMAND";
				out.println(send);				
			}
		} catch (IOException e) {
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
