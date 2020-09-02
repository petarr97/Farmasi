package controler;

import java.awt.BorderLayout;
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

public class StavkeControler implements ActionListener {
	ApplicationView view = null;
	TableView centerView = null;
	TableModel tableModel = null;
	ToolbarView toolbar = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());
		centerView = view.getCenterView();

		if (e.getActionCommand().equals("prikaz")) {
			Korisnik.getInstance().setTrenutnaTabela("stavke");
			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			ResultSet rs = ProcedureClass.getInstance().procedura2("{call UCITAJ_STAVKE_NARUDZBE}");

			view.remove(view.getToolbarView());
			view.toolbarView = new ToolbarView();
			view.add(view.toolbarView, BorderLayout.NORTH);

			toolbar = view.getToolbarView();
			toolbar.podesiToolbar();
			toolbar.psotaviFilterStavke();
			toolbar.dodajListenere();

			view.setState(new WorkingOnTableState(view));

			centerView.removeAll();
			centerView.repaint();

			String[] columnNames = new String[5];
			columnNames[0] = "#ID";
			columnNames[1] = "Narudzba ID";
			columnNames[2] = "Proizvod ID";
			columnNames[3] = "Kolicina";
			columnNames[4] = "Cijena";

			try {
				while (rs.next()) {
					ArrayList<String> pomocna = new ArrayList<String>();

					pomocna.add(rs.getString(1));
					pomocna.add(rs.getString(2));
					pomocna.add(rs.getString(3));
					pomocna.add(rs.getString(4));
					pomocna.add(rs.getString(5));

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
			DodavanjeFrame df = new DodavanjeFrame();
			df.dodavanjeStavkeNarudzbe();
			df.show();

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
