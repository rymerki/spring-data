package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private Boolean system = true;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de CARGO deseja executar?");
			System.out.println("0 - sair");
			System.out.println("1 - busca funcionario nome");
//			System.out.println("2 - atualizar");
//			System.out.println("3 - visualizar");
//			System.out.println("4 - deletar");
//			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioSalarioMaiorData(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome você deseja pesquisar?");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome você deseja pesquisar?");
		String nome = scanner.next();
		
		System.out.println("Qual data deseja pesquisar?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.println("Qual salaraio deseja pesquisar?");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);
	}
}
