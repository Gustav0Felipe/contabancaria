package conta;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import conta.util.Cores;

public class Menu {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		HashMap<Integer, Conta> contas = new HashMap<Integer, Conta>();
		int codigo = 1;
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
			System.out.println(
					contas.keySet()
					);
			switch (opcao) {
				case 1:
					conta = new Conta();
					
					System.out.print("Agencia: ");
					conta.setAgencia(scan.nextInt());
					
					System.out.print("Tipo: ");
					conta.setTipo(scan.nextInt());
					
					scan.nextLine();
					System.out.print("Titular: ");
					conta.setTitular(scan.nextLine());
					
					System.out.print("Saldo: ");
					conta.setSaldo(scan.nextFloat());
					
					conta.setNumero(codigo);
					contas.put(codigo, conta);
					codigo++;
					break;
				case 2:
					contas.get(1);
					System.out.println("\nListar todas as Contas: ");
					
					for(Conta item :contas.values()) {
						System.out.printf(Locale.US, item.getNumero() + " " + item.getTitular() + " %.2f \n", item.getSaldo());
					}
					break;
				case 3:
					System.out.print("Consultar dados da Conta - por número: ");
					contas.get(scan.nextInt()).visualizar();;
					break;
				case 4:
					System.out.print("Atualizar dados da Conta de Codigo: ");
					encontrar = scan.nextInt();
					scan.nextLine();
						
					Conta item = contas.get(encontrar);
					System.out.print("Novo Nome: ");
					item.setTitular(scan.nextLine());
							
					System.out.print("Novo Saldo: ");
					item.setSaldo(scan.nextFloat());
					
					break;
				case 5:
					System.out.print("Apagar a Conta de Codigo: ");
					contas.remove(scan.nextInt());
					break;
				case 6:
					System.out.print("Saque Conta de Codigo: ");
					conta = contas.get(scan.nextInt());
					
					System.out.print("Quantia: ");
					if(conta.sacar(scan.nextFloat()))
						System.out.printf(Locale.US, "Saque feito com sucesso! Saldo Atual: %.2f \n", conta.getSaldo());
				
					break;
				case 7:
					System.out.print("Depósito na Conta de Codigo: ");
					encontrar = scan.nextInt();
					conta = contas.get(encontrar);

					System.out.print("Valor: ");
					conta.depositar(scan.nextFloat());
					
					System.out.printf(Locale.US, "Deposito feito com sucesso! Saldo atual: %.2f \n", conta.getSaldo());
					break;
				case 8:
					System.out.println("Transferência entre as Contas: ");
					System.out.print("Devedor: ");
					Conta devedor = contas.get(scan.nextInt());

					System.out.print("Recebedor: ");
					
					Conta recebedor = contas.get(scan.nextInt());
					
					System.out.print("Valor: ");
					float valor = scan.nextFloat();
					
					if(devedor.pagar(recebedor, valor)) System.out.println("Transferencia bem Sucedida!");
					else System.out.println("Saldo Insuficiente!");

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