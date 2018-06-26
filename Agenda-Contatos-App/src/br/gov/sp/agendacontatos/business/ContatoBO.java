package br.gov.sp.agendacontatos.business;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.gov.sp.agendacontatos.dao.ContatoDAO;
import br.gov.sp.agendacontatos.validator.*;
import br.gov.sp.agendacontatos.vo.ContatoVO;

public class ContatoBO {
	ContatoDAO contatoDAO = new ContatoDAO();

	public boolean materContato(ContatoVO contatoVO, JFormattedTextField txtId, JFormattedTextField txtNome,
			JFormattedTextField txtDdd, JFormattedTextField txtTelefone, JLabel lblCampoObrigadorioId,
			JLabel lblCampoObrigadorioNome, JLabel lblCampoObrigadorioDdd, JLabel lblCampoObrigadorioTelefone) throws HeadlessException, SQLException {

		boolean retorno = false;

		boolean obrigadorio = new CampoValitor().validarCampo(txtId, txtNome, txtDdd, txtTelefone,
				lblCampoObrigadorioId, lblCampoObrigadorioNome, lblCampoObrigadorioDdd, lblCampoObrigadorioTelefone);

		if (obrigadorio) {
			if (duplicadoTelefone(contatoVO,0)) {
				JOptionPane.showMessageDialog(null, "O Telefone já está Cadastro", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				retorno = contatoDAO.inserir(contatoVO);
			}
		}

		return retorno;
	}

	public int deleteContato(int id) throws SQLException {

		return contatoDAO.deleteContato(id);
	}

	public int alterarContato(int id, ContatoVO contatoVO, JFormattedTextField txtId, JFormattedTextField txtNome,
			JFormattedTextField txtDdd, JFormattedTextField txtTelefone, JLabel lblCampoObrigadorioId,
			JLabel lblCampoObrigadorioNome, JLabel lblCampoObrigadorioDdd, JLabel lblCampoObrigadorioTelefone)
			throws SQLException {
		
		int retorno = 0;

		boolean obrigadorio = new CampoValitor().validarCampo(txtId, txtNome, txtDdd, txtTelefone,
				lblCampoObrigadorioId, lblCampoObrigadorioNome, lblCampoObrigadorioDdd, lblCampoObrigadorioTelefone);

		if (obrigadorio) {
			if (duplicadoTelefone(contatoVO,1)) {
				JOptionPane.showMessageDialog(null, "O Telefone já está Cadastro", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				retorno = contatoDAO.alterarContato(id, contatoVO);
			}
		}
		return retorno;
	}

	public boolean duplicadoTelefone(ContatoVO contatoVO, int tipo) throws SQLException {

		return contatoDAO.duplicadoTelefone(contatoVO, tipo);

	}
	

	public List<ContatoVO> buscaContatos(JTable tabela, ContatoVO cVO) throws SQLException {

		List<ContatoVO> retorno = ContatoDAO.buscarContatos(cVO);

		return retorno;
	}

	public void dadosTabela(boolean encontrado, ContatoVO cVO) {

		if (!encontrado) {
			String mensagem = "O Contato ";
			boolean mens = true;
			if (cVO.getId() > 0) {
				mensagem += " do ID : " + cVO.getId();
				mens = false;
			}
			if (!cVO.getNome().isEmpty()) {
				if (mens) {
					mensagem += " do Nome : " + cVO.getNome();
					mens = false;

				} else {
					mensagem += " com o Nome : " + cVO.getNome();
				}
			}
			if (!cVO.getDdd().isEmpty()) {
				if (mens) {
					mensagem = "Os Contatos com DDD : " + cVO.getDdd();
					mens = false;

				} else {
					mensagem += ", o DDD : " + cVO.getDdd();
				}
			}
			if (!cVO.getTelefone().isEmpty()) {
				if (mens) {
					mensagem = "Os Contatos com o Telefone : " + cVO.getTelefone();
				} else {
					mensagem += ",  o Telefone : " + cVO.getTelefone();
				}
			}

			mensagem += " não foi Encontrado";
			JOptionPane.showMessageDialog(null, mensagem);

		}
	}

	public boolean bancoVazio() throws SQLException {
		return contatoDAO.pesquisarContatoTodos();
	}

}
