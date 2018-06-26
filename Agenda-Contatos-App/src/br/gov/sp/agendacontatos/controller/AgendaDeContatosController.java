package br.gov.sp.agendacontatos.controller;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.gov.sp.agendacontatos.business.ContatoBO;
import br.gov.sp.agendacontatos.dao.ContatoDAO;
import br.gov.sp.agendacontatos.model.ContatoTableModel;
import br.gov.sp.agendacontatos.vo.ContatoVO;

@SuppressWarnings("unused")
public class AgendaDeContatosController {
	ContatoBO contatoBO = new ContatoBO();

	public boolean salvarContato(ContatoVO contatoVO, JFormattedTextField txtId, JFormattedTextField txtNome,
			JFormattedTextField txtDdd, JFormattedTextField txtTelefone, JLabel lblCampoObrigadorioId,
			JLabel lblCampoObrigadorioNome, JLabel lblCampoObrigadorioDdd, JLabel lblCampoObrigadorioTelefone) throws HeadlessException, SQLException {

		boolean retorno = false;

		retorno = contatoBO.materContato(contatoVO, txtId, txtNome, txtDdd, txtTelefone, lblCampoObrigadorioId,
				lblCampoObrigadorioNome, lblCampoObrigadorioDdd, lblCampoObrigadorioTelefone);

		return retorno;
	}

	public int alterarContato(int id, JTable table, ContatoVO contatoVO, JFormattedTextField txtId, JFormattedTextField txtNome,
			JFormattedTextField txtDdd, JFormattedTextField txtTelefone, JLabel lblCampoObrigadorioId,
			JLabel lblCampoObrigadorioNome, JLabel lblCampoObrigadorioDdd, JLabel lblCampoObrigadorioTelefone)
			throws SQLException {

		int retorno = contatoBO.alterarContato(id, contatoVO, txtId, txtNome, txtDdd, txtTelefone,
				lblCampoObrigadorioId, lblCampoObrigadorioNome, lblCampoObrigadorioDdd, lblCampoObrigadorioTelefone);
		
		ContatoTableModel modelo = (ContatoTableModel) table.getModel();

		if(retorno == 1) {
			limparTabela(table, modelo);
		}
		return retorno;

	}

	public int excluirContato(JTable tabela, int id) throws SQLException {
		ContatoTableModel modelo = (ContatoTableModel) tabela.getModel();

		limparTabela(tabela, modelo);
		return contatoBO.deleteContato(id);

	}
	
	public List<ContatoVO> pesquisarContatos(JTable tabela, ContatoVO contatoVO) throws SQLException {

		List<ContatoVO> retorno = contatoBO.buscaContatos(tabela, contatoVO);

		return retorno;
	}

	public void carregarTabela(JTable tabela, List<ContatoVO> retorno, ContatoVO contatoVO) throws SQLException {
		ContatoTableModel modelo = (ContatoTableModel) tabela.getModel();
		boolean encontrado = false;

		if (!contatoBO.bancoVazio()) {
			limparTabela(tabela, modelo);

			for (ContatoVO c : retorno) {
				modelo.addRow(c);
				encontrado = true;
			}

			contatoBO.dadosTabela(encontrado, contatoVO);
		} else {
			JOptionPane.showMessageDialog(null, "Lista de Contato Vazia");

		}

	}

	public void limparTabela(JTable tabela, ContatoTableModel modelo) {
		while (tabela.getRowCount() > 0)
			modelo.removeRow(0);
	}

}
