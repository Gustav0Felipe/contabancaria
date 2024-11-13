package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;
import controller.ContaController;

public class Menu {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		ContaController contas = new ContaController();

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaCorrente cp1 = new ContaCorrente(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);

		ContaCorrente cp2 = new ContaCorrente(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);

		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		while (true) {

			System.out.println(Cores.BLACK_BACKGROUND + Cores.TEXT_WHITE + "*".repeat(53) + "\n"
								+" ".repeat(53) 
								+"\n                BANCO DO BRAZIL COM Z                \n"
								+" ".repeat(53) + "\n"
								+"*".repeat(53) + "\n"
								+" ".repeat(53) + "\n"
								+"              1 - Criar Conta                        \n"
								+"              2 - Listar todas as Contas             \n"
								+"              3 - Buscar Conta por Numero            \n"
								+"              4 - Atualizar Dados da Conta           \n"
								+"              5 - Apagar Conta                       \n"
								+"              6 - Sacar                              \n"
								+"              7 - Depositar                          \n"
								+"              8 - Transferir valores entre Contas    \n"
								+"              9 - Sair                               \n"
								+" ".repeat(53) + "\n"
								+"*".repeat(53));
			System.out.print(Cores.TEXT_RESET + "Entre com a opção desejada: ");
		
			try {
				opcao = scan.nextInt();
				scan.nextLine();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				scan.nextLine();
				opcao=0;
			}
			if (opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
                 scan.close();
				System.exit(0);
			}
			
			switch (opcao) {
				case 1:
					numero = contas.gerarNumero();

					System.out.print("Agencia: ");
					agencia = scan.nextInt();
					
					System.out.println("Conta Corrente[1] ou Poupança[2]?");
					tipo = scan.nextInt();

					scan.nextLine();
					System.out.print("Titular: ");
					titular = scan.nextLine();
					
					System.out.print("Saldo: ");
					saldo = scan.nextFloat();
					
					
					switch(tipo) {
					case 1:
						System.out.println("Digite o limite da conta: ");
						
						limite = scan.nextFloat();
						contas.cadastrar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
						break;
					case 2:
						System.out.println("Digite o dia do Aniversário da conta: ");
						aniversario = scan.nextInt();
						contas.cadastrar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
						break;
					}
					keyPress();
					break;
				case 2:
					System.out.println("\nListar todas as Contas: ");
					
					contas.listarTodas();
					keyPress();
					break;
				case 3:
					System.out.print("Consultar dados da Conta - por número: ");
					contas.procurarPorNumero(scan.nextInt());
					keyPress();
					break;
				case 4:
					System.out.println("Atualizar dados da Conta \n");
					System.out.println("Digite o número da Conta: ");
					numero = scan.nextInt();
					var buscaConta = contas.pegarContaPorNumero(numero);

					if(buscaConta != null) {
						
						tipo = buscaConta.getTipo();
						
						System.out.println("Digite o Numero da Agência: ");
						agencia = scan.nextInt();
						
						System.out.println("Digite o Nome do Titular: ");
						scan.skip("\\R?");
						 titular = scan.nextLine();

						System.out.println("Digite o Saldo da Conta (R$): ");
						saldo = scan.nextFloat();
					
						
						switch(tipo) {
						case 1 -> {
							System.out.println("Digite o Limite de Credito(R$): ");
							limite = scan.nextFloat();
							
							contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
							}
						case 2 -> {
							System.out.println("Digite o dia do Aniversário da Conta: ");
							aniversario = scan.nextInt();
							
							contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
							}
						default -> {
							System.out.println("Tipo de Conta Invalido.");
							}
						}
					}else System.out.println("A Conta não foi Encontrada!");
				
					keyPress();
					break;
				case 5:
					System.out.print("Apagar a Conta\n");
					System.out.print("Digite o número da conta: ");
					contas.deletar(scan.nextInt());
					keyPress();
					break;
				case 6:
					System.out.print("Saque\n");
					System.out.println("Digite o Numero da Conta: ");
					numero = scan.nextInt();
					
					
					do {
						System.out.print("Digite o Valor do Saque (R$): ");
						valor = scan.nextFloat();
					}while(valor <= 0);
					
					contas.sacar(numero, valor);
					
					keyPress();
					break;
				case 7:
					System.out.print("Depósito \n");
					System.out.print("Digite o Numero da Conta: ");
					numero = scan.nextInt();
					
					
					do {
						System.out.println("Digite o Valor do Depósito (R$): ");
						valor = scan.nextFloat();
					}while(valor <= 0);
					
					contas.depositar(numero, valor);
					keyPress();
					break;
				case 8:
					System.out.println("Transferência entre Contas: ");
					System.out.print("Digite o Numero da Conta de Origem: ");
					numero = scan.nextInt();
					
					System.out.print("Recebedor: ");
					numeroDestino = scan.nextInt();
					
					do {
					System.out.print("Digite o Valor da Transferencia (R$): ");
					valor = scan.nextFloat();
					}while(valor <= 0);
				
					contas.transferir(numero, numeroDestino, valor);

					keyPress();
					break;
				default:
					System.out.println("\nOpção Inválida!\n");
					keyPress();
					break;
			}
		}
	}
    
	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Gustavo Felipe ");
		System.out.println("Gustavo Felipe - Gustavo.custodio55@hotmail.com");
		System.out.println("github.com/Gustav0Felipe");
		System.out.println("*********************************************************");
	}
	
	
	public static void keyPress() {
		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();
	
		} catch (IOException e) {
	
			System.out.println("Você pressionou uma tecla diferente de enter!");
			}
		}
}