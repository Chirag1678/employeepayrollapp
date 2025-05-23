package com.bridgelabz.employeepayroll;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class EmployeePayrollApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EmployeePayrollApplication.class, args);
		log.info("Employee Payroll App started in {} Environment", context.getEnvironment().getProperty("environment"));
		log.info("Employee Payroll Service User is {}", context.getEnvironment().getProperty("spring.datasource.username"));
	}

}
