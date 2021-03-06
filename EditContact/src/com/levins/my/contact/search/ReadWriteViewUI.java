package com.levins.my.contact.search;

import java.awt.EventQueue;

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

import com.levins.my.contact.ContactRecord;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

@SuppressWarnings("serial")
public class ReadWriteViewUI extends JFrame {

	private ReadWriteModel model;
	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	private JButton btnDelete;
	private List<ContactRecord> listToTable;

	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public List<ContactRecord> getListToTable() {
		return listToTable;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadWriteViewUI frame = new ReadWriteViewUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReadWriteViewUI() {
		model = new ReadWriteModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		tableModel = new TableModel();
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		GridBagConstraints gbc_btnFind = new GridBagConstraints();
		gbc_btnFind.insets = new Insets(0, 0, 5, 0);
		gbc_btnFind.gridx = 0;
		gbc_btnFind.gridy = 0;
		contentPane.add(btnFind, gbc_btnFind);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteRow();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 3;
		contentPane.add(btnDelete, gbc_btnDelete);
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

	public void deleteRow() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			tableModel.deleteRecord(selectedRow);
		}
	}

}
