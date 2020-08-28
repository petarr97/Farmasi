package style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CustomTableHeader extends JLabel implements TableCellRenderer {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public CustomTableHeader() {
		setFont(new Font("Calibri", Font.BOLD, 22));
		setOpaque(true);
		setForeground(Color.WHITE);
		setBackground(Color.decode("#01A9DB"));
		setHorizontalAlignment(JLabel.CENTER);
		setBorder(BorderFactory.createEtchedBorder());
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText(value.toString());
		return this;
	}
}
