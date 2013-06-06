package br.com.aps.controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.aps.entidade.Acervo;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Emprestimo;
import br.com.aps.entidade.Funcionario;
import br.com.aps.entidade.Periodico;
import br.com.aps.entidade.Pessoa;
import br.com.aps.excecao.Excecao;


public class GerenteEmprestimo {
		
	Map<Pessoa, Emprestimo> listaEmprestimo = new HashMap<Pessoa, Emprestimo>();
	Map<String, Acervo> listaAcervo = new HashMap<String, Acervo>();
	List<String> disponiveis = new ArrayList<String>();
	
	
	public void realizaEmprestimo(Emprestimo emprestimo){
		List<Acervo> temp = new ArrayList<Acervo>();
	
		if((emprestimo.getPessoa() instanceof Aluno) && (emprestimo.getListaAcervo().size() > Aluno.QUANTIDADE_ACERVO_EMPRESTIMO) || 
				(emprestimo.getPessoa() instanceof Funcionario) && (emprestimo.getListaAcervo().size() > 5))
			throw new Excecao("Aluno, naoo pode pegar mais de três livros.");
		
			if((emprestimo.getPessoa() instanceof Aluno) && (emprestimo.getListaAcervo() instanceof Periodico))
				throw new Excecao("Aluno, nao pode pegar periodico");
		
		for(Acervo aux : emprestimo.getListaAcervo()){
			if(this.listaAcervo.equals(aux))
				throw new Excecao("Livro Alugado");
			
			if(this.disponiveis.contains(aux))
				temp.add(listaAcervo.get(aux));
				this.disponiveis.remove(aux);				
		}
			
		listaEmprestimo.put(emprestimo.getPessoa(), new Emprestimo(emprestimo.getPessoa(), temp));
	}
	
	
	public String livrosAlugadosPorAluno(Aluno aluno) {
		String aux = "";
		Emprestimo emp = listaEmprestimo.get(aluno);
		int cont = 1;
		for(Acervo temp : emp.getListaAcervo()){
			aux += cont +":"+ temp.getId();
			cont++;
		}
		return aux;	
	}

	public String livrosAlugadosPorFuncionario(Funcionario funcionario) {
		String aux = "";
		Emprestimo emp = listaEmprestimo.get(funcionario);
		int cont = 1;
		for(Acervo temp : emp.getListaAcervo()){
			aux += cont +":"+ temp.getId();
			cont++;
		}
		return aux;	
	}
	
	public void devolverAcervo(Emprestimo emprestimo){//olhar este método!!!
		if(calcularMulta(emprestimo) == 0);{
			listaEmprestimo.remove(emprestimo.getListaAcervo());
		}
		throw new Excecao("Multa pendente");
	}
	
	
	public float calcularMulta(Emprestimo emprestimo){
		int diferencaEmDias = 0;
		float valorMulta = 0;
		if(emprestimo.getDataDevolucao().after(emprestimo.getDataPrevistaDevolucao())){
			diferencaEmDias = calcula2("" + emprestimo.getDataDevolucao().getDay() + "/" + emprestimo.getDataDevolucao().getMonth() + "/" + emprestimo.getDataDevolucao().getYear(), emprestimo.getDataPrevistaDevolucao().getDay() + "/" + emprestimo.getDataPrevistaDevolucao().getMonth() + "/" + emprestimo.getDataPrevistaDevolucao().getYear());
			valorMulta=(float) (diferencaEmDias*0.50);
		}
		return valorMulta;
	}
	
	
    public static int calcula2(String dia1, String dia2){  
        
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");  
        Date data3= null;  
        Date data4 = null;  
        long m1 = 0;  
        long m2 = 0;  
        try{  
              data3 = (Date) fmt.parse(dia1);  
              data4 = (Date) fmt.parse(dia2);  
             }  
        catch(Exception e){}  
        Calendar data1 = new GregorianCalendar();  
        data1.setTime(data3);  
        Calendar data2 = new GregorianCalendar();  
        data2.setTime(data4);  
        
        
        
        m1 = data1.getTimeInMillis();  
        m2 = data2.getTimeInMillis();  
        return (int) ((m2 - m1) / (24*60*60*1000));  
     }  
	
}
