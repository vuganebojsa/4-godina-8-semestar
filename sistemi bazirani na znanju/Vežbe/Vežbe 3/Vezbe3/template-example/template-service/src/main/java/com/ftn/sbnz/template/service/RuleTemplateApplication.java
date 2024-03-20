package com.ftn.sbnz.template.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.builder.KieScanner;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = { "com.ftn.sbnz.template.model", "com.ftn.sbnz.template.kjar" })
public class RuleTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuleTemplateApplication.class, args);
	}

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("com.ftn.sbnz", "template-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);

		return kContainer;
	}
}
