package com.levins.my.contact.search;

import java.util.List;
import java.util.Queue;

import javax.swing.table.AbstractTableModel;

import com.levins.my.contact.ContactRecord;

@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {
	private static final int COLUMNS_COUNT = 7;
	private List<ContactRecord> listToTable;

	public List<ContactRecord> getListToTable() {
		return listToTable;
	}

	public void setListToTable(List<ContactRecord> listToTable) {
		this.listToTable = listToTable;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {

		return COLUMNS_COUNT;
	}

	@Override
	public int getRowCount() {

		return (listToTable != null ? listToTable.size() : 0);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ContactRecord singleContact = listToTable.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return singleContact.getName();
		case 1:
			return singleContact.getPhone();
		case 2:
			return singleContact.getEmail();
		}
		return null;
	}

	public void deleteRecord(int index) {
		listToTable.remove(index);
		fireTableDataChanged();
	}
}
