package com.gbsw.dormitory.management.dormitorymanagementserver;

		import com.gbsw.dormitory.management.dormitorymanagementserver.nfc.NfcMain;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DormitoryManagementServerApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(DormitoryManagementServerApplication.class, args);

		// 스프링과 독립되게 작동하기 위해 쓰레드에 등록하여 사용
//		Thread nfc_thread = null;
//		try {
//			NfcMain nfc = new NfcMain();
//			nfc_thread = new Thread(nfc);
//			nfc_thread.start();
//		} catch (Exception e) {
//			System.out.println("nfc 장치가 인식되지 않았습니다.");
//			assert nfc_thread != null;
//			nfc_thread.interrupt();	
//		}


	}

}
