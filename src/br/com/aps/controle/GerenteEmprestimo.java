package br.com.aps.controle;

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


public class GerenteEmprestimo {
	
	List<Emprestimo> listaEmprestimo = new ArrayList<Emprestimo>();
	Map<String, Acervo> listaAcervo = new HashMap<String, Acervo>();
	List<String> disponiveis = new ArrayList<String>();

	
	public List<Emprestimo> listaEp(){
		return listaEmprestimo;
	}
	
	public Map<String, Acervo> getListaAcervo() {
		return listaAcervo;
	}
	
	public void realizaEmprestimo(Emprestimo emprestimo){
	       
	    if(emprestimo == null){
	        throw new Excecao("Empréstimo não pode ser null");
	    }
	    
	    if(emprestimo.getListaAcervo() == null || emprestimo.getListaAcervo().isEmpty()){
	        throw new Excecao("Nao ha livros");
	    }
	    
	    if(emprestimo.getPessoa() == null){
	        throw new Excecao("Pessoa que realiza um Empréstimo não pode ser null");
	    }
	    
	    if(emprestimo.getPessoa() instanceof Aluno){
	        for(int i = 0; i < emprestimo.getListaAcervo().size(); i++){
	            if(emprestimo.getListaAcervo().get(i) instanceof Periodico){
	                throw new Excecao("Aluno, nao pode pegar periodico");
	            }
	        }
	    }
	    
	    if(emprestimo.getListaAcervo().size() > Aluno.QUANTIDADE_ACERVO_EMPRESTIMO){
	        throw new Excecao("Aluno, nao pode pegar mais de três livros.");
	    }
	    
	    if((emprestimo.getPessoa() instanceof Funcionario) && (emprestimo.getListaAcervo().size() > 5)){
	        throw new Excecao("Funcionario, nao pode pegar mais de cinco livros.");
	    }

	    listaEmprestimo.add(emprestimo);
	    
	}
	
	public void devolverEmprestimo(Emprestimo p){
		if(p.getDataDevolucao().after(p.getDataPrevistaDevolucao())){
			throw new Excecao("Emprestimo com pendencia de multa");		
		}
		listaEmprestimo.remove(p);
	}
	
	public void devolverEmprestimoForaDoPrazoEComMultaPaga(Emprestimo p){
		calcular_E_Pagar_Multa(p);
		listaEmprestimo.remove(p);
	}

	public boolean pagarMulta(float valor){
		return true;
	}
	
	public void calcular_E_Pagar_Multa(Emprestimo p){
		if(p.getDataDevolucao().after(p.getDataPrevistaDevolucao())){
			int quantdia = dataDiff(p.getDataDevolucao(), p.getDataPrevistaDevolucao());
			float valorMulta = (float) (quantdia * 0.50);
			pagarMulta(valorMulta);
		}
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
