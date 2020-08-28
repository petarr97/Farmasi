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
import view.Izmjena;
import view.TableView;

public class ZaposleniControler implements ActionListener {
	ApplicationView view = null;
	TableView centerView = null;
	boolean zabrana = true;
	TableModel tableModel = null;

	@Override
	public void actionPerformed(ActionEvent e) {

		Korisnik.getInstance().setTrenutnaTabela("korisnik");
		view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());
		centerView = view.getCenterView();
		centerView.removeAll();
		centerView.repaint();

		view.setState(new WorkingOnTableState(view));

		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("prikaz")) {

			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			String[] columnNames = null;

			ResultSet rs = ProcedureClass.getInstance().procedura2("{call VratiKorisnikeSistema}");

			columnNames = new String[5];
			columnNames[0] = "#ID";
			columnNames[1] = "Tip zaposlenog";
			columnNames[2] = "Ime";
			columnNames[3] = "Prezime";
			columnNames[4] = "Maticni broj";

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
		} else if (e.getActionCommand().equals("brisanje") && Korisnik.getInstance().getBrisanje() == 1) {
			Brisanje brisanje = new Brisanje();
			brisanje.brisanjeKorisnika();
			brisanje.show();
			view.setState(new ReadyState(view));
			zabrana = false;
		} else if (e.getActionCommand().equals("dodavanje") && Korisnik.getInstance().getDodavanje() == 1
				&& Korisnik.getInstance().getTipKorisnika() == 1) {
			DodavanjeFrame dodajKorisnik = new DodavanjeFrame();
			dodajKorisnik.dodavanjeZaposlenog();
			dodajKorisnik.show();
			view.setState(new ReadyState(view));
			zabrana = false;

		} else if (e.getActionCommand().equals("izmjena") && Korisnik.getInstance().getIzmjena() == 1
				&& Korisnik.getInstance().getTipKorisnika() == 1) {
			Izmjena izmjena = new Izmjena(view.getInfoPanel());
			izmjena.show();
			view.setState(new ReadyState(view));

			zabrana = false;
		}
		if (zabrana)
			JOptionPane.showMessageDialog(centerView,
					"Korisnik " + Korisnik.getInstance().username + " nema privilegije za ovaj dio sistema!!!");

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
