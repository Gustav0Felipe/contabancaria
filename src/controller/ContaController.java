package controller;

import java.util.HashMap;
import java.util.Scanner;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository{

	HashMap<Integer, Conta> listaContas = new HashMap<Integer, Conta>();
	int numero = 0;
		
	@Override
	public void procurarPorNumero(int numero) {
		listaContas.get(numero).visualizar();
	}

	public Conta pegarContaPorNumero(int numero) {
		return listaContas.get(numero);
	}
	
	@Override
	public void listarTodas() {
		for(var conta : listaContas.values()) {
			conta.visualizar();
		}
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.put(conta.getNumero(),conta);
		System.out.println("\nA conta de numero: " + conta.getNumero() + " foi criada com sucesso!");
	}

	@Override
	public void atualizar(Conta conta) {
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
			
		System.out.print("Novo Nome: ");
		conta.setTitular(scan.nextLine());
				
		System.out.print("Novo Saldo: ");
		conta.setSaldo(scan.nextFloat());
		scan.close();
	}

	@Override
	public void deletar(int numero) {
		listaContas.remove(numero);
		
	}

	@Override
	public void sacar(int numero, float valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depositar(int numero, float valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		// TODO Auto-generated method stub
		
	}
	
	public int gerarNumero() {
		return ++ numero;
	}
	
	
}
