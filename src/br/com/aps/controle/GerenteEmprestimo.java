package br.com.aps.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.aps.entidade.Acervo;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Emprestimo;
import br.com.aps.entidade.Funcionario;
import br.com.aps.entidade.Periodico;
import br.com.aps.excecao.Excecao;


public class GerenteEmprestimo implements Serializable{
	
	private List<String> disponiveis = new ArrayList<String>();
	double valorMulta = 0;

	
	public void realizaEmprestimo(Emprestimo emprestimo){
	       
	    if(emprestimo == null){
	        throw new Excecao("Empréstimo não pode ser null");
	    }
	    
	    if(emprestimo.getAcervo() == null){
	        throw new Excecao("Nao há Acervo");
	    }
	    
	    if(emprestimo.getPessoa() == null){
	        throw new Excecao("Pessoa que realiza um Empréstimo não pode ser null");
	    }
	    
	    if(emprestimo.getPessoa() instanceof Aluno){
	    	for(int i = 0; i<emprestimo.getPessoa().getListaEmprestimo().size(); i++){
	            if(emprestimo.getPessoa().getListaEmprestimo() instanceof Periodico){
	            	throw new Excecao("Aluno, nao pode pegar periodico");
	            }
	        }
	    }
	    
	    if(emprestimo.getPessoa().getListaEmprestimo().size() > Aluno.QUANTIDADE_EMPRESTIMO){
	        throw new Excecao("Aluno, nao pode pegar mais de três livros.");
	    }
	    
	    if((emprestimo.getPessoa() instanceof Funcionario) && (emprestimo.getPessoa().getListaEmprestimo().size() > 5)){
	        throw new Excecao("Funcionario, nao pode pegar mais de cinco livros.");
	    }

	    
	    GerentePersistencia.getInstance().getListaEmprestimos().add(emprestimo);
		GerentePersistencia.persistir();
	    
	}
	
	public void devolverEmprestimo(Emprestimo emprestimo){
		if((emprestimo.getPessoa() instanceof Aluno) || (emprestimo.getPessoa() instanceof Funcionario)){
			if(verificaMulta(emprestimo)== true){
				calcularMulta(emprestimo);
				if(pagarMulta(emprestimo, valorMulta) == true){
					GerentePersistencia.getInstance().getListaEmprestimos().remove(emprestimo);
					GerentePersistencia.persistir();
				}
				throw new Excecao("Emprestimo com pendencia de multa, é necessário pagar o valor da multa");
			}	
		}else{
			GerentePersistencia.getInstance().getListaEmprestimos().remove(emprestimo);
			GerentePersistencia.persistir();
		}
	}
	

	public boolean verificaMulta(Emprestimo empr){
		if(empr.getDataDevolucao().after(empr.getDataPrevistaDevolucao())){
			int quantdia = dataDiff(empr.getDataDevolucao(), empr.getDataPrevistaDevolucao());
			if(quantdia > 0){
				throw new Excecao("Emprestimo com pendencia de multa");
			}
		}
		return false;
	}
	
	public double calcularMulta(Emprestimo emprestimo){
		if(emprestimo.getDataDevolucao().after(emprestimo.getDataPrevistaDevolucao())){
			int quantdia = dataDiff(emprestimo.getDataDevolucao(), emprestimo.getDataPrevistaDevolucao());
				valorMulta = quantdia * 0.50;
		}
		return valorMulta;
	}
	
	public boolean pagarMulta(Emprestimo p, double valor){
		return true;
	}
		
	public List<Emprestimo> getListEmprestimo(){
		return GerentePersistencia.getInstance().getListaEmprestimos();
	}

		
	public static int dataDiff(java.util.Date dataLow, java.util.Date dataHigh){  
		  
	     GregorianCalendar startTime = new GregorianCalendar();  
	     GregorianCalendar endTime = new GregorianCalendar();  
	       
	     GregorianCalendar curTime = new GregorianCalendar();  
	     GregorianCalendar baseTime = new GregorianCalendar();  
	  
	     startTime.setTime(dataLow);  
	     endTime.setTime(dataHigh);  
	       
	     int dif_multiplier = 1;  
	       
	     // Verifica a ordem de inicio das datas  
	     if( dataLow.compareTo( dataHigh ) < 0 ){  
	         baseTime.setTime(dataHigh);  
	         curTime.setTime(dataLow);  
	         dif_multiplier = 1;  
	     }else{  
	         baseTime.setTime(dataLow);  
	         curTime.setTime(dataHigh);  
	         dif_multiplier = -1;  
	     }  
	       
	     int result_years = 0;  
	     int result_months = 0;  
	     int result_days = 0;  
	  
	     // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando  
	     // no total de dias. Ja leva em consideracao ano bissesto  
	     while( curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR) ||  
	            curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)  )  
	     {  
	           
	         int max_day = curTime.getActualMaximum( GregorianCalendar.DAY_OF_MONTH );  
	         result_months += max_day;  
	         curTime.add(GregorianCalendar.MONTH, 1);  
	           
	     }  
	       
	     // Marca que Ã© um saldo negativo ou positivo  
	     result_months = result_months*dif_multiplier;  
	       
	       
	     // Retirna a diferenca de dias do total dos meses  
	     result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));  
	       
	     return result_years+result_months+result_days;  
	}
	
}
