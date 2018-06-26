package br.gov.sp.agendacontatos.validator;

import java.awt.Color;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.gov.sp.agendacontatos.exception.GyovaneException;

public class CampoValitor {
	static boolean retorno = true;
	final static int ZERO = 0;
	final static int TAMANHO_MAXIMO_DDD = 3;
	final static int TAMANHO_MINIMO_TELEFONE = 8;
	final static int TAMANHO_MAXIMO_TELEFONE = 9;

	public boolean validarCampo(JFormattedTextField txtId, JFormattedTextField txtNome, JFormattedTextField txtDdd,
			JFormattedTextField txtTelefone, JLabel lblCampoObrigadorioId, JLabel lblCampoObrigadorioNome,
			JLabel lblCampoObrigadorioDdd, JLabel lblCampoObrigadorioTelfone) {

		try {
			if (campoId(txtId, lblCampoObrigadorioId) && campoNome(txtNome, lblCampoObrigadorioNome)
					&& campoDdd(txtDdd, lblCampoObrigadorioDdd)
					&& campoTelefone(txtTelefone, lblCampoObrigadorioTelfone)) {
				retorno = true;
			} else {
				retorno = false;

			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (GyovaneException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

		}

		return retorno;

	}

	public static boolean campoId(JFormattedTextField txtId, JLabel lblCampoObrigadorioId)
			throws NumberFormatException {

		if (txtId.getText().trim().isEmpty() == true) {
			txtId.setBackground(new Color(255, 102, 102));
			lblCampoObrigadorioId.setText("* Campo Obrigadorio");
			lblCampoObrigadorioId.setVisible(true);
			retorno = false;
		} else if (Integer.parseInt(txtId.getText()) <= ZERO) {
			lblCampoObrigadorioId.setText("ID não pode se igual ZERO");
			lblCampoObrigadorioId.setVisible(true);
			retorno = false;
		} else {
			txtId.setBackground(new Color(255, 255, 255));
			lblCampoObrigadorioId.setVisible(false);
			retorno = true;
		}

		return retorno;
	}

	public static boolean campoNome(JFormattedTextField txtNome, JLabel lblCampoObrigadorioNome)
			throws GyovaneException {
		if (txtNome.getText().trim().isEmpty() == true) {
			txtNome.setBackground(new Color(255, 102, 102));
			lblCampoObrigadorioNome.setText("* Campo Obrigadorio");
			lblCampoObrigadorioNome.setVisible(true);
			retorno = false;
		} else {
			txtNome.setBackground(new Color(255, 255, 255));
			lblCampoObrigadorioNome.setVisible(false);
			retorno = true;
		}
		return retorno;

	}

	public static boolean campoDdd(JFormattedTextField txtDdd, JLabel lblCampoObrigadorioDdd) throws GyovaneException {
		if (txtDdd.getText().trim().isEmpty() == true) {
			txtDdd.setBackground(new Color(255, 102, 102));
			lblCampoObrigadorioDdd.setText("* Campo Obrigadorio");
			lblCampoObrigadorioDdd.setVisible(true);
			retorno = false;
		} else if (txtDdd.getText().length() != TAMANHO_MAXIMO_DDD) {
			lblCampoObrigadorioDdd.setText("Informar um DDD valido");
			txtDdd.setBackground(new Color(255, 102, 102));
			lblCampoObrigadorioDdd.setVisible(true);

		} else {
			txtDdd.setBackground(new Color(255, 255, 255));
			lblCampoObrigadorioDdd.setVisible(false);
			retorno = true;
		}
		return retorno;
	}

	public static boolean campoTelefone(JFormattedTextField txtTelefone, JLabel lblCampoObrigadorioTelfone)
			throws GyovaneException {

		if (txtTelefone.getText().trim().isEmpty() == true) {
			txtTelefone.setBackground(new Color(255, 102, 102));
			lblCampoObrigadorioTelfone.setText("* Campo Obrigadorio");
			lblCampoObrigadorioTelfone.setVisible(true);
			retorno = false;
		} else if (txtTelefone.getText().length() != TAMANHO_MINIMO_TELEFONE
				&& txtTelefone.getText().length() != TAMANHO_MAXIMO_TELEFONE) {

			lblCampoObrigadorioTelfone.setText("Informar um Numero de Tel/Cel valido");
			lblCampoObrigadorioTelfone.setVisible(true);
			retorno = false;
		} else {
			txtTelefone.setBackground(new Color(255, 255, 255));
			lblCampoObrigadorioTelfone.setVisible(false);
			retorno = true;
		}
		return retorno;
	}
}
