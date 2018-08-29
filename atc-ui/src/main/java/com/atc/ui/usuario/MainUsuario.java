package com.atc.ui.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.atc.app.FrameAbstract;
import com.atc.model.Usuario;
import com.atc.service.usuario.UsuarioMainService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class MainUsuario extends FrameAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2674462508567491441L;
	private UsuarioMainService mainFrameService;
	private TableUsuario mainTable;
	private JPanel optionPanel;
	private SearchUsuario searchPanel;
	private JButton ingresarButton;
	private JButton atrasButton;
	private JButton detallesButton;
	private AddUsuario addUsuario;

	public MainUsuario() {
		super(StringsConstants.MAINUSUARIO_TITULO);
		initialize();
		initializeVariables();
		constructLayout();
		refreshTable();
		setVisible(true);
	}

	public void refreshTable() {
		List<Usuario> usuarios = this.mainFrameService.getAllUsuarios();
		this.mainTable.setTableModel(usuarios);
		mainTable.updateTable();
	}

	public void refreshTable(List<Usuario> usuarios) {
		this.mainTable.setTableModel(usuarios);
		mainTable.updateTable();
	}

	private void constructLayout() {
		setLayout(new BorderLayout());
		optionPanel.setLayout(new FlowLayout());
		optionPanel.add(atrasButton);
		optionPanel.add(detallesButton);
		optionPanel.add(ingresarButton);
		add(mainTable, BorderLayout.CENTER);
		add(optionPanel, BorderLayout.SOUTH);
		add(searchPanel, BorderLayout.NORTH);
	}

	private void initializeVariables() {
		this.mainFrameService = new UsuarioMainService();
		this.mainTable = new TableUsuario();
		this.optionPanel = new JPanel();
		this.ingresarButton = new JButton(StringsConstants.AGREGAR_USUARIO);
		this.ingresarButton.addActionListener(this);
		this.atrasButton = new JButton(StringsConstants.BACK);
		this.atrasButton.addActionListener(this);
		this.detallesButton = new JButton(StringsConstants.VER_DETALLE);
		this.detallesButton.addActionListener(this);
		this.addUsuario = new AddUsuario(this);
		this.searchPanel = new SearchUsuario(this);
	}

	private void initialize() {
		setSize(NumberConstants.MAINEMPLEADOS_WIDTH, NumberConstants.MAINEMPLEADOS_HEIGHT);
		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.ingresarButton) {
			this.addUsuario.setVisible(true);
		} else if (event.getSource() == this.atrasButton) {
			setVisible(false);
		} else if (event.getSource() == this.detallesButton) {
			Usuario usuario = mainTable.getSelected();
			if (usuario != null) {
				new DetailUsuario(this, usuario);
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
			}
		}
	}

}
