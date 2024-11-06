package conta;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import conta.util.Cores;

public class Menu {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		List<Conta> contas = new LinkedList<Conta>();
		int codigo = 1;
		int opcao;

		while (true) {

			System.out.println(Cores.CYAN_BACKGROUND + Cores.TEXT_BLACK + "*".repeat(53) + "\n"
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
			System.out.print("Entre com a opção desejada:                          ");
			opcao = scan.nextInt();
			scan.nextLine();
			
			if (opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
                 scan.close();
				System.exit(0);
			}
			
			Conta conta = new Conta();
			int encontrar = 0;
			
			switch (opcao) {
				case 1:
					System.out.print("Nome: ");
					conta = new Conta();
					conta.nome = scan.nextLine();
					
					System.out.print("Saldo: ");
					conta.saldo = scan.nextFloat();
					
					conta.codigo = codigo;
					codigo++;
					contas.add(conta);
					break;
				case 2:
					System.out.println("\nListar todas as Contas: ");
					for(Conta item :contas) {
						System.out.printf(Locale.US, item.codigo + " " + item.nome + " %.2f \n", item.saldo);
					}
					
					break;
				case 3:
					System.out.println("Consultar dados da Conta - por número: ");
					encontrar = scan.nextInt();
					for(Conta item: contas) {
						if(item.codigo == encontrar) 
							System.out.printf(Locale.US, item.codigo + " " + item.nome + " %.2f \n", item.saldo);
					}

					break;
				case 4:
					System.out.println("Atualizar dados da Conta de Codigo: ");
					encontrar = scan.nextInt();
					scan.nextLine();
					
					for(Conta item: contas) {
						if(item.codigo == encontrar) {
							System.out.print("Novo Nome: ");
							String nome = scan.nextLine();
							
							System.out.print("Novo Saldo: ");
							float saldo = scan.nextFloat();
							item.atualizar(nome, saldo);
						}
					}
					break;
				case 5:
					System.out.print("Apagar a Conta de Codigo: ");
					encontrar = scan.nextInt();
					for(Conta item: contas) {
						if(item.codigo == encontrar) {
							contas.remove(item);
						}
					}
					break;
				case 6:
					System.out.print("Saque Conta de Codigo: ");
					encontrar = scan.nextInt();
					System.out.print("Quantia: ");
					float saque = scan.nextFloat();
					for(Conta item: contas) {
						if(item.codigo == encontrar) {
							if(item.saque(saque)) System.out.println("Saque feito com sucesso!, Saldo atual: " + item.saldo);
							else System.out.println("Saldo Insuficiente: " + item.saldo);;
						}
					}
					break;
				case 7:
					System.out.print("Depósito na Conta de Codigo: ");
					encontrar = scan.nextInt();
					System.out.print("Valor: ");
					float deposito = scan.nextFloat();
					for(Conta item: contas) {
						if(item.codigo == encontrar) {
							item.deposito(deposito);
							System.out.println("Deposito feito com sucesso!, Saldo atual: " + item.saldo);
						}
					}
					break;
				case 8:
					System.out.println("Transferência entre as Contas: ");
					System.out.print("Devedor: ");
					Conta devedor = new Conta();
					devedor.codigo = scan.nextInt();

					System.out.print("Recebedor: ");
					
					int recebedor = scan.nextInt();
					
					System.out.print("Valor: ");
					float valor = scan.nextFloat();
					
					for(Conta item: contas) {
						if(item.codigo == devedor.codigo) devedor = item;
					}
					for(Conta item: contas) {
						if(item.codigo == recebedor) {
							if(devedor.pagar(item, valor)) System.out.println("Transferencia bem Sucedida!");
							else System.out.println("Saldo Insuficiente!");
						}
					}
					break;
				default:
					System.out.println("\nOpção Inválida!\n");
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
}