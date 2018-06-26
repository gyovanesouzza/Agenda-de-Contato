package br.gov.sp.agendacontatos.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableRowSorter;

import br.gov.sp.agendacontatos.business.ContatoBO;
import br.gov.sp.agendacontatos.controller.AgendaDeContatosController;
import br.gov.sp.agendacontatos.model.ContatoTableModel;
import br.gov.sp.agendacontatos.util.LookAndFeel;
import br.gov.sp.agendacontatos.validator.ApenasLetras;
import br.gov.sp.agendacontatos.validator.ApenasNumero;
import br.gov.sp.agendacontatos.vo.ContatoVO;

public class ViewContatos extends JFrame {

	private static final long serialVersionUID = -3406261205299664510L;

	AgendaDeContatosController controller = new AgendaDeContatosController();
	ContatoBO contatoBO = new ContatoBO();
	ContatoVO contatoVO = new ContatoVO();
	ContatoTableModel contatoTableModel = new ContatoTableModel();
	
	private JPanel contentPane;
	private JTable table;
	public JFormattedTextField txtId;
	private JFormattedTextField txtNome;
	private JFormattedTextField txtDdd;
	private JFormattedTextField txtTelefone;
	public JLabel lblCampoObrigadorioId;
	private JLabel lblCampoObrigadorioNome;
	private JLabel lblCampoObrigadorioDdd;
	private JLabel lblCampoObrigadorioTelefone;
	

	public ViewContatos() {
		setFont(new Font("Bauhaus 93", Font.PLAIN, 18));
		setResizable(false);
		initComponent();

	}

	public static void main(String[] args) {
		new LookAndFeel().Layout();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewContatos frame = new ViewContatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initComponent() {
		setTitle("Super Agenda 3.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblListaDeContatos = new JLabel("Super Agenda 3.0");
		lblListaDeContatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeContatos.setFont(new Font("Brush Script MT", Font.PLAIN, 33));
		lblListaDeContatos.setBounds(10, 9, 598, 28);
		contentPane.add(lblListaDeContatos);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 48, 598, 209);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblConsulta = new JLabel("ID: ");
		lblConsulta.setFont(new Font("Arial", Font.PLAIN, 15));
		lblConsulta.setBounds(10, 31, 66, 18);
		panel.add(lblConsulta);

		txtId = new JFormattedTextField();
		txtId.setDocument(new ApenasNumero());
		txtId.setFont(new Font("Arial", Font.PLAIN, 15));
		txtId.setBounds(81, 31, 60, 18);
		txtId.setToolTipText("* Campo Obrigadorio");
		txtId.setColumns(10);
		panel.add(txtId);

		JButton btnBuscar = new JButton("Pesquisar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					pesquisaContatos();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(panel, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBuscar.setToolTipText("Pesquisar Contatos");
		btnBuscar.setBounds(477, 51, 111, 23);
		panel.add(btnBuscar);

		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNome.setBounds(10, 70, 66, 18);
		panel.add(lblNome);

		txtNome = new JFormattedTextField();

		txtNome.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNome.setColumns(10);
		txtNome.setDocument(new ApenasLetras());
		txtNome.setToolTipText("* Campo Obrigadorio");
		txtNome.setBounds(81, 71, 132, 18);
		panel.add(txtNome);

		JLabel lblDdd = new JLabel("DDD:");
		lblDdd.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDdd.setBounds(10, 110, 66, 18);
		panel.add(lblDdd);

		txtDdd = new JFormattedTextField();

		txtDdd.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDdd.setDocument(new ApenasNumero());
		txtDdd.setColumns(10);
		txtDdd.setBounds(81, 111, 60, 18);
		panel.add(txtDdd);

		JLabel lblTelefone = new JLabel("Telefone: ");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTelefone.setBounds(10, 151, 66, 18);
		panel.add(lblTelefone);

		txtTelefone = new JFormattedTextField();

		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTelefone.setColumns(10);
		txtTelefone.setDocument(new ApenasNumero());
		txtTelefone.setBounds(81, 151, 132, 18);
		panel.add(txtTelefone);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alterarContato();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnAlterar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnAlterar.setToolTipText("Alterar Contato");
		btnAlterar.setBounds(477, 90, 111, 23);
		panel.add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					excluirContato();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 15));
		btnExcluir.setToolTipText("Excluir Contato");
		btnExcluir.setBounds(477, 130, 111, 23);
		panel.add(btnExcluir);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					salvarContato();
				} catch (HeadlessException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCadastrar.setBounds(477, 11, 111, 23);
		btnCadastrar.setToolTipText("Cadastrar Contato");
		panel.add(btnCadastrar);

		lblCampoObrigadorioId = new JLabel("");
		lblCampoObrigadorioId.setHorizontalAlignment(SwingConstants.LEFT);
		lblCampoObrigadorioId.setForeground(Color.RED);
		lblCampoObrigadorioId.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCampoObrigadorioId.setBounds(151, 34, 316, 18);
		lblCampoObrigadorioId.setVisible(false);
		panel.add(lblCampoObrigadorioId);

		lblCampoObrigadorioNome = new JLabel("");
		lblCampoObrigadorioNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblCampoObrigadorioNome.setForeground(Color.RED);
		lblCampoObrigadorioNome.setVisible(false);
		lblCampoObrigadorioNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCampoObrigadorioNome.setBounds(223, 75, 244, 18);
		panel.add(lblCampoObrigadorioNome);

		lblCampoObrigadorioDdd = new JLabel("");
		lblCampoObrigadorioDdd.setHorizontalAlignment(SwingConstants.LEFT);
		lblCampoObrigadorioDdd.setForeground(Color.RED);
		lblCampoObrigadorioDdd.setVisible(false);
		lblCampoObrigadorioDdd.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCampoObrigadorioDdd.setBounds(151, 113, 316, 18);
		panel.add(lblCampoObrigadorioDdd);

		lblCampoObrigadorioTelefone = new JLabel("");
		lblCampoObrigadorioTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		lblCampoObrigadorioTelefone.setForeground(Color.RED);
		lblCampoObrigadorioTelefone.setVisible(false);
		lblCampoObrigadorioTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCampoObrigadorioTelefone.setBounds(223, 154, 244, 18);
		panel.add(lblCampoObrigadorioTelefone);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampo();
			}
		});
		btnLimpar.setToolTipText("Excluir Contato");
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnLimpar.setBounds(477, 175, 111, 23);
		panel.add(btnLimpar);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 268, 598, 322);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 578, 300);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					dadosNoCampo();

				}
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setModel(contatoTableModel);
		table.setRowSorter(new TableRowSorter<ContatoTableModel>(contatoTableModel));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(2).setPreferredWidth(19);
		scrollPane.setViewportView(table);
	}

	private void salvarContato() throws HeadlessException, SQLException {
		ContatoVO contatoVO = new ContatoVO();

		transfererDados(contatoVO);

		if (controller.salvarContato(contatoVO, txtId, txtNome, txtDdd, txtTelefone, lblCampoObrigadorioId,
				lblCampoObrigadorioNome, lblCampoObrigadorioDdd, lblCampoObrigadorioTelefone)) {
			limparCampo();
			JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso", "Cadastro com sucesso",
					JOptionPane.PLAIN_MESSAGE);
		}

	}

	private void transfererDados(ContatoVO contatoVO) {

		contatoVO.setId(0);
		contatoVO.setNome("");
		contatoVO.setDdd("");
		contatoVO.setTelefone("");

		if (!txtId.getText().isEmpty()) {
			contatoVO.setId(Integer.parseInt(txtId.getText()));
		}
		contatoVO.setNome(txtNome.getText());
		contatoVO.setDdd(txtDdd.getText());
		contatoVO.setTelefone(txtTelefone.getText());
	}

	private void dadosNoCampo() {
		txtId.setText((String) table.getValueAt(table.getSelectedRow(), 0).toString());
		txtNome.setText((String) table.getValueAt(table.getSelectedRow(), 1));
		txtDdd.setText((String) table.getValueAt(table.getSelectedRow(), 2));
		txtTelefone.setText((String) table.getValueAt(table.getSelectedRow(), 3));
		transfererDados(contatoVO);

	}

	private void limparCampo() {
		lblCampoObrigadorioId.setVisible(false);
		lblCampoObrigadorioNome.setVisible(false);
		lblCampoObrigadorioDdd.setVisible(false);
		lblCampoObrigadorioTelefone.setVisible(false);

		txtId.setBackground(new Color(255, 255, 255));
		txtNome.setBackground(new Color(255, 255, 255));
		txtDdd.setBackground(new Color(255, 255, 255));
		txtTelefone.setBackground(new Color(255, 255, 255));

		txtId.setText("");
		txtNome.setText("");
		txtDdd.setText("");
		txtTelefone.setText("");
	}

	private void alterarContato() throws SQLException {

		if (table.getSelectedRow() != -1) {

			Object[] options = { "Confirmar", "Cancelar" };
			int Confirm = JOptionPane.showOptionDialog(null, "Clique Confirmar para Alterar", "Informação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (Confirm == 0) {
				transfererDados(contatoVO);
				int id = (Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));

				if (controller.alterarContato(id, contatoVO, txtId, txtNome, txtDdd, txtTelefone, lblCampoObrigadorioId,
						lblCampoObrigadorioNome, lblCampoObrigadorioDdd, lblCampoObrigadorioTelefone) == 1) {
					limparCampo();
					JOptionPane.showMessageDialog(null, "Contato Alterado com sucesso", "Alteração",
							JOptionPane.INFORMATION_MESSAGE);
				} 

			} else {
				limparCampo();

				JOptionPane.showMessageDialog(null, "Alteção não efetuada", "Alteração",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um Contato para alterar");
		}
	}

	private void pesquisaContatos() throws SQLException {
		transfererDados(contatoVO);
		List<ContatoVO> retorno = controller.pesquisarContatos(table, contatoVO);
		controller.carregarTabela(table, retorno, contatoVO);
	}

	private void excluirContato() throws SQLException {

		if (table.getSelectedRow() != -1) {

			Object[] options = { "Confirmar", "Cancelar" };
			int Confirm = JOptionPane.showOptionDialog(null, "Clique Confirmar para Exluir", "Informação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (Confirm == 0) {
				int id = ((int) table.getValueAt(table.getSelectedRow(), 0));

				if (controller.excluirContato(table, id) == 1) {
					JOptionPane.showMessageDialog(null, "Excluido com sucesso!", "Excluido",
							JOptionPane.INFORMATION_MESSAGE);
					limparCampo();

				}
			} else {
				JOptionPane.showMessageDialog(null, "exclusão não efetuada!");

			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um Contato para excluir");
		}

	}

}
