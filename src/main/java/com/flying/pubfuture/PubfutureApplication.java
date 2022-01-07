package com.flying.pubfuture;

import java.util.stream.LongStream;

import com.flying.model.Conta;
import com.flying.repository.ContaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"test"})
@ComponentScan({"com.flying.controller"})
@EnableJpaRepositories("com.flying.repository")
@EntityScan("com.flying.model")
@EnableAutoConfiguration
public class PubfutureApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubfutureApplication.class, args);
	}

	// Criando dados mockados para testar o banco de dados.
	@Bean
    CommandLineRunner init(ContaRepository repository) {
        return args -> {
            repository.deleteAll();
            Conta conta = new Conta();
            conta.setSaldo(10.99);
            conta.setInstituicaoFinanceira("Instituição Financeira");
            conta.setTipoConta("Corrente");

            repository.save(conta);
        };
    }

}