package conta;

public class Conta {

	public int codigo;
	public String nome;
	public float saldo;
	
	
	Conta(){};
	
	Conta(int codigo, String nome, float saldo){
		this.codigo = codigo;
		this.nome = nome;
		this.saldo = saldo;
	}
	
	public void atualizar(String nome, float saldo){
		this.nome = nome;
		this.saldo = saldo;
	}
	
	public boolean saque(float valor) {
		if(saldo >= valor) {
			this.saldo = saldo - valor;
			return true;
		}
		else return false;
	}
	
	public void deposito(float valor) {
		this.saldo += valor;
	}

	public boolean pagar(Conta conta, float valor) {
		if(saldo >= valor) {
			this.saldo -= valor;
			conta.saldo += valor;
			return true;
		}
		else return false;
			
	}
}
