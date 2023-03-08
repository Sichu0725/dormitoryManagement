package com.gbsw.dormitory.management.dormitorymanagementserver;

import com.gbsw.dormitory.management.dormitorymanagementserver.nfc.NfcMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DormitoryManagementServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(DormitoryManagementServerApplication.class, args);

		// todo NFC작동 되는지 확인
		NfcMain nfc = new NfcMain();
		nfc.run();

	}

}
