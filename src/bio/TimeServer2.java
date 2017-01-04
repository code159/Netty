package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//αNIO ʱ������ 
//ʹ���̳߳��Ż�����ˣ�ʹ��Դռ�ÿɿأ�����ϵͳ���������Դ���BIO���е���������
public class TimeServer2 {
	private static int port=8888;

	public static void main(String[] args) throws IOException {
		ServerSocket ss=null;
		Socket socket=null;
		
		ExecutorService executor=Executors.newFixedThreadPool(3);
		
		try{
			ss=new ServerSocket(port);
			while(true){
				socket=ss.accept();
				Thread s=new Thread(new TimeServerHandler(socket));
				executor.submit(s);
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
			if(executor!=null){
				executor.shutdown();
				executor=null;
			}
		}		
	}

}
