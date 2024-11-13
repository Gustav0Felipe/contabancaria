package controller;

import java.util.HashMap;
import java.util.Optional;

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
		
		listaContas.forEach((k, v) ->  v.visualizar());
	
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.put(conta.getNumero(),conta);
		System.out.println("\nA conta de numero: " + conta.getNumero() + " foi criada com sucesso!");
	}

	@Override
	public void atualizar(Conta conta) {
		Optional<Conta> contaBuscar = Optional.ofNullable(pegarContaPorNumero(numero));
		
		if(contaBuscar.isPresent()) {
			listaContas.replace(contaBuscar.get().getNumero(), contaBuscar.get());
			System.out.println("\nA Conta numero: " + contaBuscar.get().getNumero() + " foi atualizada com sucesso!");
		}else
			System.out.println("\nA Conta numero: " + contaBuscar.get().getNumero() + " não foi encontrada!");
				
	}

	@Override
	public void deletar(int numero) {
		Optional<Conta> conta = Optional.ofNullable(pegarContaPorNumero(numero));
		
		
		if(conta.isPresent()) {
			if(listaContas.remove(numero, conta.get())) {
				System.out.println("\nA conta numero: " + numero + " foi deletada com sucesso!");
			}else
				System.out.println("\nA conta numero: " + numero + " não foi encontrada!");

		}
	}

	@Override
	public void sacar(int numero, float valor) {
		Optional<Conta> conta = Optional.ofNullable(pegarContaPorNumero(numero));
		
		if(conta.isPresent()) {
			if(conta.get().sacar(valor)) System.out.println("\nO saque na conta numero: " + numero + " foi efetuado com sucesso!");;
		}else
			System.out.println("\nA conta numero: " + numero + " não foi encontrada!");
	}

	@Override
	public void depositar(int numero, float valor) {
		Optional<Conta> conta = Optional.ofNullable(pegarContaPorNumero(numero));
		
		if(conta.isPresent()) {
			conta.get().depositar(valor);
			System.out.println("\nO Depósito na Conta numero: " + numero + " foi efetuado com sucesso!");
		}else System.out.println("\nA Conta numero: " + numero + " não foi encontrada ou a Conta destino não é uma Conta Corrente!");
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		Optional<Conta> contaOrigem = Optional.ofNullable(pegarContaPorNumero(numeroOrigem));
		Optional<Conta> contaDestino = Optional.ofNullable(pegarContaPorNumero(numeroDestino));
		
		if(contaOrigem.isPresent() && contaDestino.isPresent()) {
			if(contaOrigem.get().sacar(valor) == true) {
				contaDestino.get().depositar(valor);
				System.out.println("\nA Transferência foi efetuada com sucesso!");
			}else System.out.println("\nA Conta de Origem e/ou Destino não foram encontradas!");
		}
	}
	
	public int gerarNumero() {
		return ++ numero;
	}
	
	
}
