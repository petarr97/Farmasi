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

public class ProizvodiControler implements ActionListener {
	ApplicationView view = null;
	TableView centerView = null;
	boolean zabrana = true;
	TableModel tableModel = null;
	ToolbarView toolbar = null;

	@Override
	public void actionPerformed(ActionEvent e) {

		Korisnik.getInstance().setTrenutnaTabela("proizvodi");
		view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());

		view.remove(view.getToolbarView());
		view.toolbarView = new ToolbarView();
		view.add(view.toolbarView, BorderLayout.NORTH);

		toolbar = view.getToolbarView();
		toolbar.podesiToolbar();
		toolbar.postaviFilterZaProizvode();
		toolbar.dodajListenere();

		centerView = view.getCenterView();
		centerView.removeAll();
		centerView.repaint();

		view.setState(new WorkingOnTableState(view));

		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("prikaz")) {

			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			String[] columnNames = null;

			ResultSet rs = ProcedureClass.getInstance().procedura2("{call UCITAJ_PROIZVODE}");

			columnNames = new String[4];
			columnNames[0] = "#ID";
			columnNames[1] = "Naziv proizvoda";
			columnNames[2] = "Cijena";
			columnNames[3] = "PDV Stopa";

			int br_redova = 0;

			try {

				int i = 0;
				while (rs.next()) {
					ArrayList<String> pomocna = new ArrayList<String>();

					pomocna.add(rs.getString(1));
					pomocna.add(rs.getString(2));
					pomocna.add(rs.getString(3));
					pomocna.add(rs.getString(4));

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
		} else if (e.getActionCommand().equals("dodavanje")) {
			DodavanjeFrame dodajKorisnik = new DodavanjeFrame();
			dodajKorisnik.dodavanjeProizvoda();
			dodajKorisnik.show();

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
