package br.gov.sp.agendacontatos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.gov.sp.agendacontatos.vo.ContatoVO;

public class ContatoDAO {

	private static Connection connection;

	public ContatoDAO() {
		connection = DAOConnectionFactory.getConnection();

	}

	public boolean inserir(ContatoVO contatoVO) {
		boolean cadastrou = false;
		try {
			String comandoSql = "INSERT INTO contato (id, nome, ddd, telefone) VALUES (?,?,?,?)";

			PreparedStatement preparedStatement = ContatoDAO.connection.prepareStatement(comandoSql);

			preparedStatement.setInt(1, contatoVO.getId());
			preparedStatement.setString(2, contatoVO.getNome());
			preparedStatement.setString(3, contatoVO.getDdd());
			preparedStatement.setString(4, contatoVO.getTelefone());

			int cont = preparedStatement.executeUpdate();
			if (cont == 1) {
				cadastrou = true;

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return cadastrou;
	}

	public boolean duplicadoTelefone(ContatoVO contatoVO, int tipo) throws SQLException {
		boolean retorno = false;
		int x = 0;
		String comandoSQL = "select ddd,telefone from contato where ddd = ? and telefone = ? ";

		PreparedStatement preparedStatement = ContatoDAO.connection.prepareStatement(comandoSQL);
		preparedStatement.setString(1, contatoVO.getDdd());
		preparedStatement.setString(2, contatoVO.getTelefone());

		ResultSet resultSet = preparedStatement.executeQuery();
		if (tipo == 0) {

			while (resultSet.next()) {

				retorno = true;
			}
		} else if (tipo == 1) {

			while (resultSet.next()) {
				if (x == 2) {

					retorno = true;
				}
				++x;
			}
		}
		return retorno;

	}

	public boolean pesquisarContatoTodos() throws SQLException {

		boolean retorno = true;

		String comandoSQL = "select id, nome, ddd, telefone from contato";

		PreparedStatement preparedStatement = ContatoDAO.connection.prepareStatement(comandoSQL);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			retorno = false;
		}

		return retorno;

	}

	public static List<ContatoVO> buscarContatos(ContatoVO cVO) throws SQLException {

		List<ContatoVO> retorno = new ArrayList<ContatoVO>();

		String comandoSQL = "select id, nome, ddd, telefone from contato";
		boolean montaWhere = true;

		if (cVO.getId() > 0) {
			comandoSQL += " where id = ? ";
			montaWhere = false;
		}

		if (!cVO.getNome().isEmpty()) {

			if (montaWhere == true) {
				comandoSQL += " where Nome= ? ";
				montaWhere = false;

			} else {
				comandoSQL += " and Nome= ? ";

			}
		}
		if (!cVO.getDdd().isEmpty()) {

			if (montaWhere) {
				comandoSQL += " where ddd= ? ";
				montaWhere = false;

			} else {
				comandoSQL += " and ddd= ? ";

			}
		}
		if (!cVO.getTelefone().isEmpty()) {

			if (montaWhere) {
				comandoSQL += " where Telefone= ? ";

			} else {
				comandoSQL += " and Telefone= ? ";

			}
		}
		PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
		int indice = 0;

		if (cVO.getId() != 0) {
			preparedStatement.setInt(++indice, cVO.getId());
		}
		if (!cVO.getNome().isEmpty()) {

			preparedStatement.setString(++indice, cVO.getNome());
		}
		if (!cVO.getDdd().isEmpty()) {

			preparedStatement.setString(++indice, cVO.getDdd());
		}
		if (!cVO.getTelefone().isEmpty()) {

			preparedStatement.setString(++indice, cVO.getTelefone());
		}

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			ContatoVO contatoVO = new ContatoVO();

			contatoVO.setId(resultSet.getInt("id"));
			contatoVO.setNome(resultSet.getString("nome"));
			contatoVO.setDdd(resultSet.getString("ddd"));
			contatoVO.setTelefone(resultSet.getString("telefone"));

			retorno.add(contatoVO);
		}

		return retorno;

	}

	public int deleteContato(int id) throws SQLException {

		String comandoSQL = "delete from contato where id = ?";
		PreparedStatement preparedStatement = ContatoDAO.connection.prepareStatement(comandoSQL);
		preparedStatement.setInt(1, id);

		return preparedStatement.executeUpdate();

	}

	public int alterarContato(int id, ContatoVO contatoVO) throws SQLException {

		String comandoSQL = "UPDATE contato set id = ? , nome = ? , ddd = ? , telefone = ? where id = ?";
		PreparedStatement preparedStatement = ContatoDAO.connection.prepareStatement(comandoSQL);

		preparedStatement.setInt(1, contatoVO.getId());
		preparedStatement.setString(2, contatoVO.getNome());
		preparedStatement.setString(3, contatoVO.getDdd());
		preparedStatement.setString(4, contatoVO.getTelefone());
		preparedStatement.setInt(5, id);

		return preparedStatement.executeUpdate();

	}

}
