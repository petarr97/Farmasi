package controler;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

public class OsobljeControler implements ActionListener {
	ApplicationView view = null;
	TableView centerView = null;
	public boolean zabrana = true;
	TableModel tableModel = null;
	public ToolbarView toolbar = null;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void actionPerformed(ActionEvent e) {

		Korisnik.getInstance().setTrenutnaTabela("osoblje");
		view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());

		// postavljanje novog toolbara za tabelu osoblje i kreiranje filtera
		view.remove(view.getToolbarView());
		view.toolbarView = new ToolbarView();
		view.add(view.toolbarView, BorderLayout.NORTH);

		toolbar = view.getToolbarView();
		toolbar.podesiToolbar();
		toolbar.dodajListenere();
		toolbar.postaviFilterZaOsobje();
		toolbar.repaint();
		toolbar.revalidate();

		centerView = view.getCenterView();
		centerView.removeAll();
		centerView.repaint();
		view.setState(new toolbarOn(view));

		if (e.getActionCommand().equals("prikaz")) {

			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			String[] columnNames = null;

			ResultSet rs = ProcedureClass.getInstance().procedura2("{call UCITAJ_RADNIKE}");

			columnNames = new String[9];
			columnNames[0] = "#ID";
			columnNames[1] = "Mjesto ID";
			columnNames[2] = "Ime";
			columnNames[3] = "Prezime";
			columnNames[4] = "Br. telefona";
			columnNames[5] = "Email";
			columnNames[6] = "Adresa";
			columnNames[7] = "Datum rodjenja";
			columnNames[8] = "Datum zaposlenja";

			java.sql.Date sqlDate = null;

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

					Date datum;
					try {
						datum = formatter.parse(rs.getString(8));
						long date = datum.getTime();
						sqlDate = new java.sql.Date(date);

						pomocna.add(sqlDate.toString());

						datum = formatter.parse(rs.getString(9));
						date = datum.getTime();
						sqlDate = new java.sql.Date(date);
						pomocna.add(sqlDate.toString());

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					data.add(pomocna);
					// pomocna.clear();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			centerView.removeAll();
			centerView.createTable();
			view.getTableBrowserView().postaviFilterZaRadnike();
			createModel(data, columnNames);
			zabrana = false;

		} else if (e.getActionCommand().equals("dodavanje")) {

			DodavanjeFrame dSj = new DodavanjeFrame();
			dSj.dodavanjeRadnika();
			dSj.show();
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
