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
import view.Brisanje;
import view.DodavanjeFrame;
import view.TableView;

public class RezervacijeControler implements ActionListener {
	ApplicationView view = null;
	TableView centerView = null;
	TableModel tableModel = null;

	@Override
	public void actionPerformed(ActionEvent e) {

		view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());
		centerView = view.getCenterView();
		if (e.getActionCommand().equals("prikaz")) {

			Korisnik.getInstance().setTrenutnaTabela("rezervacije");

			centerView.removeAll();
			centerView.repaint();
			view.setState(new WorkingOnTableState(view));

			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			String[] columnNames = null;

			ResultSet rs = ProcedureClass.getInstance().procedura2("{call vratiSveRezervacije}");

			columnNames = new String[7];
			columnNames[0] = "#ID";
			columnNames[1] = "Naziv PS";
			columnNames[2] = "Broj SJ";
			columnNames[3] = "Ime";
			columnNames[4] = "Prezime";
			columnNames[5] = "Status";
			columnNames[6] = "Interval rezervacije";

			int br_redova = 0;

			try {

				int i = 0;
				while (rs.next()) {
					ArrayList<String> pomocna = new ArrayList<String>();

					pomocna.add(rs.getString(1));
					pomocna.add(rs.getString(2));
					pomocna.add(rs.getString(4));
					pomocna.add(rs.getString(8));
					pomocna.add(rs.getString(9));
					pomocna.add(rs.getString(5));
					pomocna.add(rs.getString(6) + " - " + rs.getString(7));

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
		} else if (e.getActionCommand().equals("dodavanje")) {
			DodavanjeFrame dodavanje = new DodavanjeFrame();
			dodavanje.dodavanjeRezervacije();
			dodavanje.show();
			centerView.removeAll();

			view.setState(new ReadyState(view));

		} else if (e.getActionCommand().equals("otkazivanje")) {
			Brisanje brisanje = new Brisanje();
			brisanje.postaviOtkazivanjeRezervacije();
			brisanje.show();

			centerView.removeAll();
			view.setState(new ReadyState(view));

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
