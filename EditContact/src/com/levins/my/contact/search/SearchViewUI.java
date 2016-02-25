package com.levins.my.contact.search;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTable;

import com.levins.my.contact.Employee;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SearchViewUI extends JFrame {
	private SearchModel model;
	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	private List<Employee> listToTable;
	private JTextField searchUserTextField;
	private JLabel lblUserPortal;
	private JLabel lblUserEgn;
	private JTextField egnTextField;
	private ResourceBundle currentBundle;
	SearchViewUI currentObject;

	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public List<Employee> getListToTable() {
		return listToTable;
	}

	/**
	 * Create the frame.
	 * 
	 * @param insis
	 */
	public SearchViewUI() {
		currentObject = this;

		model = new SearchModel();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 94, 208, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		tableModel = new TableModel();
		JButton btnFind = new JButton(currentBundle.getString("Find"));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		lblUserPortal = new JLabel(currentBundle.getString("User WebPortal"));
		GridBagConstraints gbc_lblUserPortal = new GridBagConstraints();
		gbc_lblUserPortal.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserPortal.anchor = GridBagConstraints.EAST;
		gbc_lblUserPortal.gridx = 0;
		gbc_lblUserPortal.gridy = 0;
		contentPane.add(lblUserPortal, gbc_lblUserPortal);

		searchUserTextField = new JTextField();
		GridBagConstraints gbc_searchUserTextField = new GridBagConstraints();
		gbc_searchUserTextField.insets = new Insets(0, 0, 5, 5);
		gbc_searchUserTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchUserTextField.gridx = 1;
		gbc_searchUserTextField.gridy = 0;
		contentPane.add(searchUserTextField, gbc_searchUserTextField);
		searchUserTextField.setColumns(10);
		GridBagConstraints gbc_btnFind = new GridBagConstraints();
		gbc_btnFind.anchor = GridBagConstraints.WEST;
		gbc_btnFind.insets = new Insets(0, 0, 5, 0);
		gbc_btnFind.gridx = 2;
		gbc_btnFind.gridy = 0;
		contentPane.add(btnFind, gbc_btnFind);

		lblUserEgn = new JLabel(currentBundle.getString("User EGN"));
		GridBagConstraints gbc_lblUserEgn = new GridBagConstraints();
		gbc_lblUserEgn.anchor = GridBagConstraints.EAST;
		gbc_lblUserEgn.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserEgn.gridx = 0;
		gbc_lblUserEgn.gridy = 1;
		contentPane.add(lblUserEgn, gbc_lblUserEgn);

		egnTextField = new JTextField();
		GridBagConstraints gbc_egnTextField = new GridBagConstraints();
		gbc_egnTextField.insets = new Insets(0, 0, 5, 5);
		gbc_egnTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_egnTextField.gridx = 1;
		gbc_egnTextField.gridy = 1;
		contentPane.add(egnTextField, gbc_egnTextField);
		egnTextField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

	}

	public File openFile(String textToButton) {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showDialog(this, textToButton);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return file;
		}
		fileChooser.setVisible(true);
		return null;
	}
}
