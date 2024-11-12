package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import conta.model.Conta;
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

		int opcao;
		
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
			
			Conta conta = null;
			
			switch (opcao) {
				case 1:
					System.out.println("Conta Corrente[1] ou Poupança[2]?");
					opcao = scan.nextInt();
					switch(opcao) {
					case 1:
						ContaCorrente contaCorrente = new ContaCorrente();
						System.out.println("Digite o limite da conta: ");
						
						contaCorrente.setLimite(scan.nextFloat());
						contaCorrente.setTipo(1);
						scan.nextLine();
						
						conta = contaCorrente;
						break;
					case 2:
						ContaPoupanca contaPoupanca = new ContaPoupanca();
						System.out.println("Digite o dia do Aniversário da conta: ");
						contaPoupanca.setAniversario(scan.nextInt());
						contaPoupanca.setTipo(2);
						scan.nextLine();
						
						conta = contaPoupanca;
						break;
					}
					System.out.print("Agencia: ");
					conta.setAgencia(scan.nextInt());
					
					scan.nextLine();
					System.out.print("Titular: ");
					conta.setTitular(scan.nextLine());
					
					System.out.print("Saldo: ");
					conta.setSaldo(scan.nextFloat());
					
					conta.setNumero(contas.gerarNumero());
					contas.cadastrar(conta);
					
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
					System.out.print("Atualizar dados da Conta de Codigo: ");
					Conta item = contas.pegarContaPorNumero(scan.nextInt());
					
					contas.atualizar(item);
					
					keyPress();
					break;
				case 5:
					System.out.print("Apagar a Conta de Codigo: ");
					contas.deletar(scan.nextInt());
					scan.nextLine();
					keyPress();
					break;
				//Daqui para baixo ainda está sendo usado os metodos das contas já que os exercicios ainda não pediram para implementar tudo.
				case 6:
					System.out.print("Saque Conta de Codigo: ");
					conta = contas.pegarContaPorNumero(scan.nextInt());
					
					System.out.print("Quantia: ");
					if(conta.sacar(scan.nextFloat()))
						System.out.printf(Locale.US, "Saque feito com sucesso! Saldo Atual: %.2f \n", conta.getSaldo());
					
					keyPress();
					break;
				case 7:
					System.out.print("Depósito na Conta de Codigo: ");
					conta = contas.pegarContaPorNumero(scan.nextInt());
					
					System.out.print("Valor: ");
					conta.depositar(scan.nextFloat());
					
					System.out.printf(Locale.US, "Deposito feito com sucesso! Saldo atual: %.2f \n", conta.getSaldo());
					keyPress();
					break;
				case 8:
					System.out.println("Transferência entre as Contas: ");
					System.out.print("Devedor: ");
					Conta devedor = contas.pegarContaPorNumero(scan.nextInt());
					
					System.out.print("Recebedor: ");
					
					Conta recebedor = contas.pegarContaPorNumero(scan.nextInt());
					
					System.out.print("Valor: ");
					float valor = scan.nextFloat();
					
					if(devedor.pagar(recebedor, valor)) System.out.println("Transferencia bem Sucedida!");
					else System.out.println("Saldo Insuficiente!");

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