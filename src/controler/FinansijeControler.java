package controler;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Procedure.ProcedureClass;
import model.Korisnik;
import model.TableModel;
import view.ApplicationView;
import view.TableView;

public class FinansijeControler implements ActionListener {
	ApplicationView view = null;
	TableView centerView = null;
	TableModel tableModel = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Korisnik.getInstance().tipKorisnika == 1 || Korisnik.getInstance().tipKorisnika == 2) {
			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			ResultSet rs = ProcedureClass.getInstance().procedura2("{call FinansijskoStanje}");
			view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());

			centerView = view.getCenterView();
			centerView.removeAll();
			centerView.repaint();

			String[] columnNames = new String[3];
			columnNames[0] = "Naziv poslovne jedinice";
			columnNames[1] = "Registracioni broj poslovne jedinice";
			columnNames[2] = "Finansijsko stanje";

			try {
				while (rs.next()) {
					ArrayList<String> pomocna = new ArrayList<String>();

					pomocna.add(rs.getString(1));
					pomocna.add(rs.getString(2));
					pomocna.add(Double.valueOf(rs.getString(3)) + " KM");

					data.add(pomocna);
					// pomocna.clear();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			centerView.removeAll();
			centerView.createTable();
			createModel(data, columnNames);
		} else
			JOptionPane.showMessageDialog(centerView,
					"Korisnik " + Korisnik.getInstance().getUsername() + "nema pravo pristupa ovom dijelu sistema!!!");
	}

	public void createModel(ArrayList<ArrayList<String>> data, String[] columns) {

		String[][] data1 = new String[data.size()][data.get(0).size()];
		for (int i = 0; i < data.size(); i++) {
			int brojac_stringova = 0;
			for (String string : data.get(i)) {
				data1[i][brojac_stringova++] = string;
			}
		}
		centerView.newModel = new TableModel(data1, columns);
		centerView.update();
	}
}
