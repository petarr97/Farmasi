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
import toolbarpackage.toolbarOff;
import toolbarpackage.toolbarOn;
import view.ApplicationView;
import view.DodavanjeFrame;
import view.TableView;
import view.ToolbarView;

public class MjestoControler implements ActionListener {
	ApplicationView view = null;
	TableView centerView = null;
	boolean zabrana = true;
	TableModel tableModel = null;
	ToolbarView toolbar = null;

	@Override
	public void actionPerformed(ActionEvent e) {

		view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());

		view.remove(view.getToolbarView());
		view.toolbarView = new ToolbarView();
		view.add(view.toolbarView, BorderLayout.NORTH);

		toolbar = view.getToolbarView();
		toolbar.podesiToolbar();
		toolbar.postaviFilterZaMjesto();
		toolbar.dodajListenere();

		centerView = view.getCenterView();
		centerView.removeAll();
		centerView.repaint();
		view.setState(new toolbarOn(view));
		Korisnik.getInstance().setTrenutnaTabela("mjesto");
		if (e.getActionCommand().equals("prikaz")) {

			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			String[] columnNames = null;

			ResultSet rs = ProcedureClass.getInstance().procedura2("{call UCITAJ_MJESTO}");

			columnNames = new String[2];
			columnNames[0] = "#ID";
			columnNames[1] = "Naziv";

			int br_redova = 0;

			try {

				int i = 0;
				while (rs.next()) {
					ArrayList<String> pomocna = new ArrayList<String>();

					pomocna.add(rs.getString(1));
					pomocna.add(rs.getString(2));

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
			DodavanjeFrame dm = new DodavanjeFrame();
			dm.dodavanjeMjesta();
			dm.show();

			centerView.removeAll();
			view.setState(new toolbarOff(view));
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
