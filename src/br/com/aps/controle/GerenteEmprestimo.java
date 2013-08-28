package br.com.aps.controle;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import br.com.aps.entidade.Acervo;
import br.com.aps.entidade.Emprestimo;
import br.com.aps.entidade.Livro;
import br.com.aps.entidade.Situacao;
import br.com.aps.excecao.Excecao;
import br.com.aps.util.Validador;

public class GerenteEmprestimo implements Serializable {

	double valorMulta = 0;

	public void realizaEmprestimo(Emprestimo emprestimo) {
		
		Validador.validadorEmprestimo(emprestimo);
		GerentePersistencia.getInstance().getListaEmprestimos().add(emprestimo);
		emprestimo.getAcervo().setSituacao(Situacao.LOCADO);
		GerentePersistencia.persistir();
	}

	public void devolverEmprestimo(Emprestimo devolucao) {
		
		if((devolucao.getPessoa().getTipoPessoa() == devolucao.getPessoa().getTipoPessoa().ALUNO) ||
				(devolucao.getPessoa().getTipoPessoa() == devolucao.getPessoa().getTipoPessoa().FUNCIONARIO)){
			if (verificaMulta(devolucao) == true) {
				calcularMulta(devolucao);
				if (pagarMulta(devolucao, valorMulta) == true) {
					GerentePersistencia.getInstance().getListaEmprestimos()
							.remove(devolucao);
					devolucao.getAcervo().setSituacao(Situacao.DISPONIVEL);
					GerentePersistencia.persistir();
				}
				throw new Excecao("Emprestimo com pendencia de multa, é necessário pagar o valor da multa");
			}
		} else {
			GerentePersistencia.getInstance().getListaEmprestimos()
					.remove(devolucao);
			devolucao.getAcervo().setSituacao(Situacao.DISPONIVEL);
			GerentePersistencia.persistir();
		}
	}

	public LinkedList<Emprestimo> getListEmprestimo() {
		return GerentePersistencia.getInstance().getListaEmprestimos();
	}
	
	private boolean verificaMulta(Emprestimo empr) {
		if (empr.getDataDevolucao().after(empr.getDataPrevistaDevolucao())) {
			int quantdia = dataDiff(empr.getDataDevolucao(),
					empr.getDataPrevistaDevolucao());
			if (quantdia > 0) {
				throw new Excecao("Emprestimo com pendencia de multa");
			}
		}
		return false;
	}

	private double calcularMulta(Emprestimo emprestimo) {
		if (emprestimo.getDataDevolucao().after(
				emprestimo.getDataPrevistaDevolucao())) {
			int quantdia = dataDiff(emprestimo.getDataDevolucao(),
					emprestimo.getDataPrevistaDevolucao());
			valorMulta = quantdia * 0.50;
		}
		return valorMulta;
	}

	private boolean pagarMulta(Emprestimo p, double valor) {
		return true;
	}

	private static int dataDiff(java.util.Date dataLow, java.util.Date dataHigh) {

		GregorianCalendar startTime = new GregorianCalendar();
		GregorianCalendar endTime = new GregorianCalendar();

		GregorianCalendar curTime = new GregorianCalendar();
		GregorianCalendar baseTime = new GregorianCalendar();

		startTime.setTime(dataLow);
		endTime.setTime(dataHigh);

		int dif_multiplier = 1;

		// Verifica a ordem de inicio das datas
		if (dataLow.compareTo(dataHigh) < 0) {
			baseTime.setTime(dataHigh);
			curTime.setTime(dataLow);
			dif_multiplier = 1;
		} else {
			baseTime.setTime(dataLow);
			curTime.setTime(dataHigh);
			dif_multiplier = -1;
		}

		int result_years = 0;
		int result_months = 0;
		int result_days = 0;

		// Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import
		// acumulando
		// no total de dias. Ja leva em consideracao ano bissesto
		while (curTime.get(GregorianCalendar.YEAR) < baseTime
				.get(GregorianCalendar.YEAR)
				|| curTime.get(GregorianCalendar.MONTH) < baseTime
						.get(GregorianCalendar.MONTH)) {

			int max_day = curTime
					.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			result_months += max_day;
			curTime.add(GregorianCalendar.MONTH, 1);

		}

		// Marca que Ã© um saldo negativo ou positivo
		result_months = result_months * dif_multiplier;

		// Retirna a diferenca de dias do total dos meses
		result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime
				.get(GregorianCalendar.DAY_OF_MONTH));

		return result_years + result_months + result_days;
	}

}
