package br.com.aps.util;

public class Validador {

	public static boolean validadorCPF(String cpf){
		if(cpf.length()==14){
			return true;
		}else{
			return false;
		}
	}
	 
}
