package com.furesky.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class fileName {
	public static void main(String[] args) {
		DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");
		String localDateTime = LocalDateTime.now().format(formatter);
		System.out.println(localDateTime);
	}

}
