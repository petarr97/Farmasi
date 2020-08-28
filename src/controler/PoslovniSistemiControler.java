package controler;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Procedure.ProcedureClass;
import model.Korisnik;
import model.TableModel;
import state.ReadyState;
import state.WorkingOnTableState;
import view.ApplicationView;
import view.DodavanjeFrame;
import view.TableView;

public class PoslovniSistemiControler implements ActionListener {

	ApplicationView view = null;
	TableView centerView = null;
	boolean zabrana = true;
	TableModel tableModel = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		Korisnik.getInstance().setTrenutnaTabela("poslovniSistem");
		view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());
		centerView = view.getCenterView();
		centerView.removeAll();
		centerView.repaint();

		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("prikaz")) {
			view.setState(new WorkingOnTableState(view));

			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			String[] columnNames = null;

			ResultSet rs = ProcedureClass.getInstance().procedura2("{call VratiPoslovneSisteme}");

			columnNames = new String[7];
			columnNames[0] = "#ID";
			columnNames[1] = "Naziv";
			columnNames[2] = "Drzava";
			columnNames[3] = "Grad";
			columnNames[4] = "Adresa";
			columnNames[5] = "email";
			columnNames[6] = "Reg. br.";

			int br_redova = 0;

			try {

				int i = 0;
				while (rs.next()) {
					ArrayList<String> pomocna = new ArrayList<String>();

					pomocna.add(rs.getString(1));
					pomocna.add(rs.getString(2));
					pomocna.add(rs.getString(3));
					pomocna.add(rs.getString(4));
					pomocna.add(rs.getString(5));
					pomocna.add(rs.getString(6));
					pomocna.add(rs.getString(7));

					data.add(pomocna);
					// pomocna.clear();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			view.getCenterView().removeAll();
			view.getCenterView().createTable();
			createModel(data, columnNames);
			zabrana = false;
		} else if (e.getActionCommand().equals("dodavanje")) {
			view.setState(new ReadyState(view));
			DodavanjeFrame dodavanje = new DodavanjeFrame();
			dodavanje.dodajPoslovniSistem();
			dodavanje.show();
		}

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
