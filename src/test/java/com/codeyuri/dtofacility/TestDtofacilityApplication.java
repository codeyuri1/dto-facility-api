package com.codeyuri.dtofacility;

import org.springframework.boot.SpringApplication;

public class TestDtofacilityApplication {

	public static void main(String[] args) {
		SpringApplication.from(DtofacilityApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
