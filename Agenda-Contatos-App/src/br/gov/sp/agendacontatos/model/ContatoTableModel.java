package br.gov.sp.agendacontatos.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.gov.sp.agendacontatos.vo.*;

public class ContatoTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ContatoVO> dados = new ArrayList<>();

	private String[] colunas = { "ID", "Nome", "DDD", "Telefone"};

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.size();

	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		switch (coluna) {

		case 0:
			return dados.get(linha).getId();
		case 1:
			return dados.get(linha).getNome();
		case 2:
			return dados.get(linha).getDdd();
		case 3:
			return dados.get(linha).getTelefone();
	
		}

		return null;
	}

	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		switch (coluna) {

		case 0:
			dados.get(linha).setId(Integer.parseInt((String) valor));
			break;
		case 1:
			dados.get(linha).setNome((String) valor);
			break;
		case 2:
			dados.get(linha).setDdd((String) valor);
			break;
		case 3:
			dados.get(linha).setTelefone((String) valor);
			break;
		
		}
		this.fireTableRowsUpdated(linha, linha);
	}

	public void addRow(ContatoVO c) {
		this.dados.add(c);
		this.fireTableDataChanged();
	}

	public void removeRow(int linha) {
		this.dados.remove(linha);
		this.fireTableRowsDeleted(linha, linha);
	}

}
