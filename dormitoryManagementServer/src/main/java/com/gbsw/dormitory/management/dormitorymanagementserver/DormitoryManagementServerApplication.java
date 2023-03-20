package com.gbsw.dormitory.management.dormitorymanagementserver;

import com.gbsw.dormitory.management.dormitorymanagementserver.nfc.NfcMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DormitoryManagementServerApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(DormitoryManagementServerApplication.class, args);

		// todo NFC작동 되는지 확인
		// 스프링과 독립되게 작동하기 위해 쓰레드에 등록하여 사용
//		NfcMain nfc = new NfcMain();
//		Thread nfc_thread = new Thread(nfc);
//		nfc_thread.start();
		

	}

}
