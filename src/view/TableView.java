package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import style.CustomTableHeader;

public class TableView extends JPanel implements ObserverInterface {

	public JTable resultSetTable = null;
	public model.TableModel newModel = null;

	public JTable getResultSetTable() {
		return resultSetTable;
	}

	public void setResultSetTable(JTable resultSetTable) {
		this.resultSetTable = resultSetTable;
	}

	public TableView() {

		setBackground(Color.decode("#98B4D4"));
		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(240, 240, 240));
		setBorder(border);

		resultSetTable = new JTable();

	}

	public void createTable() {

		resultSetTable = new JTable();

		resultSetTable.getTableHeader().setFont(new Font(Font.SERIF, Font.BOLD, 18));
		resultSetTable.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		resultSetTable.setRowHeight(25);
		resultSetTable.setAlignmentY(CENTER_ALIGNMENT);
		resultSetTable.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 262,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 180);

		JScrollPane sp = new JScrollPane(resultSetTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setPreferredSize(new Dimension(resultSetTable.getWidth() + 10, resultSetTable.getHeight() + 10));
//		sp.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 252,
//				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 18));
//		sp.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 252,
//				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 18);

		add(sp);
		resultSetTable.getTableHeader().setDefaultRenderer(new CustomTableHeader());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image;
		try {
			image = ImageIO.read(new File("./img/pozadina.jpg"));
			Image newImage = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
			g.drawImage(newImage, 0, 0, this); // see javadoc for more info on the parameters

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void urediTabelu() {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int column = 0; column < resultSetTable.getColumnCount(); column++) {
			TableColumn tableColumn = resultSetTable.getColumnModel().getColumn(column);
			int preferredWidth = tableColumn.getMinWidth();
			int maxWidth = tableColumn.getMaxWidth();

			for (int row = 0; row < resultSetTable.getRowCount(); row++) {
				TableCellRenderer cellRenderer = resultSetTable.getCellRenderer(row, column);
				Component c = resultSetTable.prepareRenderer(cellRenderer, row, column);
				int width = c.getPreferredSize().width + resultSetTable.getIntercellSpacing().width;
				preferredWidth = Math.max(preferredWidth, width);

				// We've exceeded the maximum width, no need to check other rows

				if (preferredWidth >= maxWidth) {
					preferredWidth = maxWidth;
					break;
				}
			}

			for (int i = 0; i < resultSetTable.getModel().getRowCount(); i++)
				resultSetTable.setRowHeight(i, 40);

			resultSetTable.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
			resultSetTable.setRowSelectionInterval(0, 0);
			tableColumn.setPreferredWidth(preferredWidth);
		}
	}

	@Override
	public void updateAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		resultSetTable.setModel(newModel);
		urediTabelu();
	}

	@Override
	public void subscribe(Component component) {
		// TODO Auto-generated method stub

	}

}
