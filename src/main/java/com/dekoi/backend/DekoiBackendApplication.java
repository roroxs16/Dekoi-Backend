package com.dekoi.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DekoiBackendApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	public static void main(String[] args) {
		SpringApplication.run(DekoiBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		String  password ="000ac28ea465";
//		
//		for (int i=0; i<3;i++) {
//			String passEncode=bCrypt.encode(password);
//			System.out.println(passEncode);
//		}
//		
		
	}

}
