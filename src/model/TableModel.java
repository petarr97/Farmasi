/***********************************************************************
 * Module:  TableModel.java
 * Author:  Petar
 * Purpose: Defines the Class TableModel
 ***********************************************************************/

package model;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {

	public TableModel(String[][] data, String[] columns) {
		super(data, columns);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	};

}