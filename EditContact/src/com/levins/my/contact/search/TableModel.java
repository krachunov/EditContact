package com.levins.my.contact.search;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.levins.my.contact.Employee;

@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {
	private static final int COLUMNS_COUNT = 7;
	private List<Employee> listToTable;

	public List<Employee> getListToTable() {
		return listToTable;
	}

	public void setListToTable(List<Employee> listToTable) {
		this.listToTable = listToTable;
		fireTableDataChanged();
	}

	public int getColumnCount() {
		return COLUMNS_COUNT;
	}

	public int getRowCount() {

		return (listToTable != null ? listToTable.size() : 0);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Employee user = listToTable.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return user.getName();
		case 1:
			return user.getPost();
		case 2:
			return user.getDepartment();
		case 3:
			return user.getEmail();
		case 4:
		case 5:
			return user.getPhone();
		case 6:
			return user.getPost();
		}
		return null;

	}

	public String getRecord(int index) {
		Employee record = listToTable.get(index);
		fireTableDataChanged();
		return record.toString();
	}

	/**
	 * 
	 * @return true if table is empty
	 */
	public boolean isEmpty() {
		if (this.listToTable == null || this.listToTable.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
