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
import view.ToolbarView;

public class NarduzbeControler implements ActionListener {

	ApplicationView view = null;
	TableView centerView = null;
	boolean zabrana = true;
	TableModel tableModel = null;
	ToolbarView toolbar = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		Korisnik.getInstance().setTrenutnaTabela("narudzbe");
		view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());
		toolbar = view.getToolbarView();
		toolbar.postaviFilterZaNarudzbe();
		toolbar.repaint();
		toolbar.revalidate();

		centerView = view.getCenterView();
		centerView.removeAll();
		centerView.repaint();

		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("prikaz")) {
			view.setState(new WorkingOnTableState(view));

			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			String[] columnNames = null;

			ResultSet rs = ProcedureClass.getInstance().procedura2("{call UCITAJ_NARUDZBE}");

			columnNames = new String[9];
			columnNames[0] = "#ID";
			columnNames[1] = "Tip placanja";
			columnNames[2] = "RadnikID";
			columnNames[3] = "KupacID";
			columnNames[4] = "Datum";
			columnNames[5] = "Status";
			columnNames[6] = "Iznos bez pdv";
			columnNames[7] = "PDV";
			columnNames[8] = "Iznos sa PDV";

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
					pomocna.add(rs.getString(8));
					pomocna.add(rs.getString(9));
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
			dodavanje.dodavanjeNarudzbe();
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
