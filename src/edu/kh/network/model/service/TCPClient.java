package edu.kh.network.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
	
	
	public void clientStart() {
		
		// 1. 서버의 IP주소와 서버가 정한 포트번호를 매개변수로 하여 클라이언트용 소켓 객체 생성
		String serverIP = "127.0.0.1"; // loop back ip ( 내 컴퓨터를 가리키는 ip주소 )
		int port = 8500; // 서버 소켓이 기다리고 있는 포트 번호 
		
		/* 필요한 변수 선언 */ 
		Socket clientSocket = null; // 서버와 연결할 클라이언트용 소켓을 저장할 변수
		
		BufferedReader br = null; // 서버 -> 클라이언트로 읽어오는 보조 스트림
		
		PrintWriter pw = null; // 클라이언트 -> 서버로 출력하는 보조 스트림 
		
		try {
			// 2. 서버와의 입출력 스트림 오픈
			
			System.out.println("[Client]");
			
			clientSocket = new Socket(serverIP, port) ;  // IP와 Port 번호를 전달?
			//java.net.Socket.Socket(String host, int port) throws UnknownHostException, IOException
			// 찾을 수 없는 IP둘다 써야 함. 
			// 3. 보조 스트림을 통해 성능 개선
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new PrintWriter(clientSocket.getOutputStream());
			
			// 4. 스트림을 통해 읽고 쓰기
			// 4-1) 서버 접속 성공 시
			//     서버가 출력한 "[서버 접속 성공]" 메세지 읽어오기 
			String message = br.readLine();
			System.out.println("서버로부터 받은 메세지 : " + message);
			
			// 4-2) 클라이언트 -> 서버로 메세지 전송 
			Scanner sc = new Scanner(System.in);
			System.out.println("입력 : ");
			String input = sc.nextLine();
			 
			pw.println(input);
			pw.flush();
			
		}catch(Exception e) { // 31번째 줄에서 찾을 수 없는 IP랑 IOException의 예외를 예상하기 때문에 최상위 예외처리를 이용함. 
			
			e.printStackTrace();
			
		}finally {
			
			// 5. 통신종료
			try {
				
				if(pw != null) pw.close();
				if(br != null) br.close();
				if(clientSocket != null) clientSocket.close();
				
				
			}catch(IOException e) {
				
				e.printStackTrace();
				
			}
			
			
		}
		
	
		
	
		
		
	}

	
	
	

}
