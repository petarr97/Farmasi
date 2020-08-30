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
import state.ReadyState;
import state.WorkingOnTableState;
import view.ApplicationView;
import view.Brisanje;
import view.DodavanjeFrame;
import view.TableView;

public class OsobljeControler implements ActionListener {
	ApplicationView view = null;
	TableView centerView = null;
	public boolean zabrana = true;
	TableModel tableModel = null;

	@Override
	public void actionPerformed(ActionEvent e) {

		Korisnik.getInstance().setTrenutnaTabela("osoblje");
		view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());

		centerView = view.getCenterView();
		centerView.removeAll();
		centerView.repaint();
		view.setState(new WorkingOnTableState(view));

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
			view.setState(new ReadyState(view));
			zabrana = false;
		} else if (e.getActionCommand().equals("brisanje")) {

			Brisanje brisanje = new Brisanje();
			brisanje.brisanjeSmjestajneJedinice();
			brisanje.show();
			centerView.removeAll();
			view.setState(new ReadyState(view));
			zabrana = false;
		}
		if (zabrana)
			JOptionPane.showMessageDialog(centerView,
					"Korisniku " + Korisnik.getInstance().username + " nema privilegije za ovaj dio sistema!!!");
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
