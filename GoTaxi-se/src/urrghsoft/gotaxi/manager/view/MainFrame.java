package urrghsoft.gotaxi.manager.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import urrghsoft.gotaxi.manager.tablemodel.CorridaTableModel;
import urrghsoft.gotaxi.manager.tablemodel.UnidadeComboModel;
import urrghsoft.gotaxi.manager.util.TaxiGOServices;
import urrghsoft.gotaxi.taxigoweb.entidades.Corrida;
import urrghsoft.gotaxi.taxigoweb.entidades.Unidade;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton btnAtualizar = null;
	private JPanel pnlCorridas = null;
	private JScrollPane jScrollPane = null;
	private JTable tblPendentes = null;
	private JPanel pnlDetalhes = null;
	private JLabel lblPassageiro = null;
	private JLabel lblHoraSolicitacao = null;
	private JLabel lblUnidade = null;
	private JLabel lblEndereco = null;
	private JTextField txtPassageiro = null;
	private JTextField txtHoraSolicitacao = null;
	private JTextField txtEndereco = null;
	private JComboBox cmbUnidades = null;
	private TaxiGOServices service = new TaxiGOServices();
	private JButton btnAtualizarCorrida = null;
	private JTextField txtID = null;
	private Timer timer = new Timer();  //  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 */
	public MainFrame() {
		super();
		initialize();
		prepareTable();
		prepareCombo();
        timer.scheduleAtFixedRate(new FillTableTask(), 5000, 10000); //10 segundos
	}
	
	
	private void prepareTable(){
		tblPendentes = getTblPendentes();
		CorridaTableModel model = new CorridaTableModel(service.getCorridas());
		tblPendentes.setModel(model);
		
		tblPendentes.getColumnModel().getColumn(0).setPreferredWidth(60);
		tblPendentes.getColumnModel().getColumn(1).setPreferredWidth(36);
		tblPendentes.getColumnModel().getColumn(2).setPreferredWidth(77);
		tblPendentes.getColumnModel().getColumn(3).setPreferredWidth(90);
		tblPendentes.getColumnModel().getColumn(4).setPreferredWidth(60);
		
		tblPendentes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPendentes.setRowSelectionAllowed(true); 
		tblPendentes.setVisible(true);
		
	}
	
	private void prepareCombo(){
		
		List<Unidade> list = service.getUnidades();
		UnidadeComboModel cmbModel = new UnidadeComboModel(list);
		getCmbUnidades().setModel(cmbModel);
		
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(995, 455);
		this.setContentPane(getJContentPane());
		this.setTitle("TaxiGO - Gerenciados de corridas");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnAtualizar(), null);
			jContentPane.add(getPnlCorridas(), null);
			jContentPane.add(getPnlDetalhes(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes btnAtualizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAtualizar() {
		if (btnAtualizar == null) {
			btnAtualizar = new JButton();
			btnAtualizar.setBounds(new Rectangle(826, 374, 131, 27));
			btnAtualizar.setText("Atualizar dados");
			btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					prepareTable();
				}
			});
		}
		return btnAtualizar;
	}

	/**
	 * This method initializes pnlCorridas	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlCorridas() {
		if (pnlCorridas == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			pnlCorridas = new JPanel();
			pnlCorridas.setLayout(new GridBagLayout());
			pnlCorridas.setBounds(new Rectangle(1, 130, 976, 234));
			pnlCorridas.setBorder(BorderFactory.createTitledBorder(null, "Corridas Pendentes", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pnlCorridas.add(getJScrollPane(), gridBagConstraints);
		}
		return pnlCorridas;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTblPendentes());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tblPendentes	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTblPendentes() {
		if (tblPendentes == null) {
			tblPendentes = new JTable();
			tblPendentes.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Corrida info = ((CorridaTableModel)tblPendentes.getModel()).getValueAt(tblPendentes.getSelectedRow());
					txtPassageiro.setText(info.getPassageiro().getEmail());
					txtHoraSolicitacao.setText(info.getHoraSolicitacao().toString());
					txtEndereco.setText(info.getLogradouro());
					txtID.setText(String.valueOf(tblPendentes.getSelectedRow()));
				}
			});
		}
		return tblPendentes;
	}


	/**
	 * This method initializes pnlDetalhes	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlDetalhes() {
		if (pnlDetalhes == null) {
			lblEndereco = new JLabel();
			lblEndereco.setBounds(new Rectangle(15, 70, 127, 16));
			lblEndereco.setText("Endere�o origem");
			lblUnidade = new JLabel();
			lblUnidade.setBounds(new Rectangle(16, 93, 126, 16));
			lblUnidade.setText("Unidade");
			lblHoraSolicitacao = new JLabel();
			lblHoraSolicitacao.setBounds(new Rectangle(16, 46, 126, 16));
			lblHoraSolicitacao.setText("Hora Solicitacao");
			lblPassageiro = new JLabel();
			lblPassageiro.setText("Passageiro");
			lblPassageiro.setBounds(new Rectangle(16, 22, 127, 16));
			pnlDetalhes = new JPanel();
			pnlDetalhes.setLayout(null);
			pnlDetalhes.setBounds(new Rectangle(2, 1, 975, 129));
			pnlDetalhes.setBorder(BorderFactory.createTitledBorder(null, "Detalhes", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pnlDetalhes.add(lblPassageiro, null);
			pnlDetalhes.add(lblHoraSolicitacao, null);
			pnlDetalhes.add(lblUnidade, null);
			pnlDetalhes.add(lblEndereco, null);
			pnlDetalhes.add(getTxtPassageiro(), null);
			pnlDetalhes.add(getTxtHoraSolicitacao(), null);
			pnlDetalhes.add(getTxtEndereco(), null);
			pnlDetalhes.add(getCmbUnidades(), null);
			pnlDetalhes.add(getBtnAtualizarCorrida(), null);
			pnlDetalhes.add(getTxtID(), null);
		}
		return pnlDetalhes;
	}


	/**
	 * This method initializes txtPassageiro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtPassageiro() {
		if (txtPassageiro == null) {
			txtPassageiro = new JTextField();
			txtPassageiro.setBounds(new Rectangle(196, 20, 440, 20));
			txtPassageiro.setEditable(false);
		}
		return txtPassageiro;
	}


	/**
	 * This method initializes txtHoraSolicitacao	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtHoraSolicitacao() {
		if (txtHoraSolicitacao == null) {
			txtHoraSolicitacao = new JTextField();
			txtHoraSolicitacao.setBounds(new Rectangle(148, 45, 336, 20));
			txtHoraSolicitacao.setEditable(false);
		}
		return txtHoraSolicitacao;
	}


	/**
	 * This method initializes txtEndereco	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEndereco() {
		if (txtEndereco == null) {
			txtEndereco = new JTextField();
			txtEndereco.setBounds(new Rectangle(148, 69, 581, 20));
			txtEndereco.setEditable(false);
		}
		return txtEndereco;
	}


	/**
	 * This method initializes cmbUnidades	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbUnidades() {
		if (cmbUnidades == null) {
			cmbUnidades = new JComboBox();
			cmbUnidades.setBounds(new Rectangle(148, 92, 301, 25));
		}
		return cmbUnidades;
	}


	/**
	 * This method initializes btnAtualizarCorrida	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAtualizarCorrida() {
		if (btnAtualizarCorrida == null) {
			btnAtualizarCorrida = new JButton();
			btnAtualizarCorrida.setBounds(new Rectangle(473, 94, 160, 23));
			btnAtualizarCorrida.setText("Atualizar corrida");
			btnAtualizarCorrida.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int id = Integer.parseInt(txtID.getText());
					Corrida corrida = ((CorridaTableModel)tblPendentes.getModel()).getValueAt(id);
					Unidade unidade = (Unidade)cmbUnidades.getSelectedItem();
					corrida.setUnidade(unidade);
					corrida.setStatus("Confirmada");
					try {
						service.atualizarCorrida(corrida);
						JOptionPane.showMessageDialog(null,"Corrida atualizada com sucesso !");
						prepareTable();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Erro ao atualizar corrida ! [" + e1.getLocalizedMessage() + "]");
					}
				}
			});
		}
		return btnAtualizarCorrida;
	}


	/**
	 * This method initializes txtID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtID() {
		if (txtID == null) {
			txtID = new JTextField();
			txtID.setBounds(new Rectangle(150, 20, 41, 21));
			txtID.setEditable(false);
		}
		return txtID;
	}
	
	
	class FillTableTask extends TimerTask {
        public void run() {
            prepareTable();
        }
    }


}  //  @jve:decl-index=0:visual-constraint="10,10"
