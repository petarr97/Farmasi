package controler;

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
import view.DodavanjeFrame;
import view.MenuBarView;
import view.TableView;
import view.ToolbarView;

public class ToolbarControler implements ActionListener {
	ApplicationView view = null;
	TableView centerView = null;
	TableModel tableModel = null;

	public ToolbarControler(ToolbarView toolbar) {

		view = (ApplicationView) SwingUtilities.getWindowAncestor(toolbar);
		centerView = view.getCenterView();

	}

	public ToolbarControler(MenuBarView menuBar) {

		view = (ApplicationView) SwingUtilities.getWindowAncestor(menuBar);
		centerView = view.getCenterView();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		view.setState(new WorkingOnTableState(view));
		DodavanjeFrame dodavanje = new DodavanjeFrame();
		if (e.getActionCommand().equals("addRow")) {

			view.setState(new ReadyState(view));

			if (Korisnik.getInstance().getTrenutnaTabela().equals("poslovniSistem")
					&& Korisnik.getInstance().getDodavanje() == 1) {
				dodavanje.dodajPoslovniSistem();
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("smjestajneJednice")
					&& Korisnik.getInstance().getDodavanje() == 1) {
				dodavanje.dodavanjeSmjestajneJedinice();
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("korisnik")
					&& Korisnik.getInstance().getTipKorisnika() == 1 && Korisnik.getInstance().getDodavanje() == 1) {
				dodavanje.dodavanjeZaposlenog();
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("rezervacije")) {
				dodavanje.dodavanjeRezervacije();
				dodavanje.show();
			} else
				JOptionPane.showMessageDialog(centerView, "Nemate pravo pristupa ovom dijelu sistema!!!");
		} else if (e.getActionCommand().equals("deleteRow")) {
			if (Korisnik.getInstance().getTrenutnaTabela().equals("poslovniSistem")) {
				JOptionPane.showMessageDialog(centerView, "Ne postoji mogucnost brisanja poslovnog sistema");
			}
			if (Korisnik.getInstance().getTrenutnaTabela().equals("smjestajneJednice")
					&& Korisnik.getInstance().getBrisanje() == 1) {
				int column = 0;
				int row = centerView.getResultSetTable().getSelectedRow();
				String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

				ArrayList<Integer> rezervacije = vratiRezSmjestajneJedinice(Integer.valueOf(value));

				int i = 0;
				while (i < rezervacije.size()) {
					ProcedureClass.procedura2("{ call IzbrisiRezervaciju(?)}", rezervacije.get(i++));

				}

				int result = JOptionPane.showConfirmDialog(centerView,
						"Da li ste sigurni da zelite da obrisete smjestajnu jednicu?");
				if (result == JOptionPane.YES_OPTION) {

					String procedura = "{call DeleteSmjestajneJedinicePoslovnogSistema(?)}";
					ProcedureClass.getInstance().procedura2(procedura, Integer.valueOf(value));
					procedura = "{call SmjestajnaJedinicaProcedura(?, ?, ?, ?, ?, ?, ?)}";
					ResultSet rs = ProcedureClass.getInstance().procedura2(procedura, Integer.valueOf(value), 0, 0, 0,
							0, "", "Delete");

					view.getCenterView().newModel.removeRow(row);
					view.getCenterView().revalidate();
					view.getCenterView().repaint();
				}
				// preurediti procedure
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("korisnik")
					&& Korisnik.getInstance().getBrisanje() == 1) {
				int column = 0;
				int row = centerView.getResultSetTable().getSelectedRow();
				String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

				int confirm = JOptionPane.showConfirmDialog(centerView,
						"Da li ste sigurni da zelite da izbrisete korisnika?");
				if (confirm == JOptionPane.YES_OPTION) {
					String procedura = "{call DeleteKorisnik(?)}";
					ProcedureClass.getInstance().procedura2(procedura, Integer.valueOf(value));

					view.getCenterView().newModel.removeRow(row);
					view.getCenterView().revalidate();
					view.getCenterView().repaint();
				}

			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("rezervacije")) {
				int column = 0;
				int row = centerView.getResultSetTable().getSelectedRow();
				String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

				int confirm = JOptionPane.showConfirmDialog(centerView,
						"Da li ste sigurni da zelite da izbrisete rezervaciju?");
				if (confirm == JOptionPane.YES_OPTION) {
					ProcedureClass.procedura2("{ call IzbrisiRezervaciju(?)}", Integer.valueOf(value));

					view.getCenterView().newModel.removeRow(row);
					view.getCenterView().revalidate();
					view.getCenterView().repaint();
				}

			} else
				JOptionPane.showMessageDialog(centerView, "Nemate pravo pristupa ovom dijelu sistema!!!");
		} else if (e.getActionCommand().equals("next")) {
			int row = centerView.getResultSetTable().getSelectedRow();
			int rows = centerView.getResultSetTable().getRowCount() - 1;

			if (row == rows) {
				centerView.getResultSetTable().setRowSelectionInterval(0, 0);

			} else
				centerView.getResultSetTable().setRowSelectionInterval(row + 1, row + 1);

			row = centerView.getResultSetTable().getSelectedRow();
			view.getInfoPanel().rows.setText("Selektovani red: " + (row + 1) + "/" + (rows + 1));

		} else if (e.getActionCommand().contentEquals("prev")) {
			int row = centerView.getResultSetTable().getSelectedRow();
			int lastRow = centerView.getResultSetTable().getRowCount() - 1;

			if (row == 0) {
				centerView.getResultSetTable().setRowSelectionInterval(lastRow, lastRow);
			} else
				centerView.getResultSetTable().setRowSelectionInterval(row - 1, row - 1);

			row = centerView.getResultSetTable().getSelectedRow();
			view.getInfoPanel().rows.setText("Selektovani red: " + (row + 1) + "/" + (lastRow + 1));
		} else if (e.getActionCommand().contentEquals("editRow") && Korisnik.getInstance().getIzmjena() == 1) {

			int column = 0;
			int row = centerView.getResultSetTable().getSelectedRow();
			String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

			if (Korisnik.getInstance().getTrenutnaTabela().equals("poslovniSistem")) {
				dodavanje.dodajPoslovniSistem();
				dodavanje.podesiVrijednostiPoslovnogSistema(Integer.valueOf(value));
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("smjestajneJednice")) {
				dodavanje.dodavanjeSmjestajneJedinice();
				dodavanje.podesiVrijednostiSmjestajneJedinice(Integer.valueOf(value));
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("korisnik")) {
				dodavanje.dodavanjeZaposlenog();
				dodavanje.podesiVrijednostiKorisnika(Integer.valueOf(value));
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("rezervacije")) {
				JOptionPane.showMessageDialog(centerView, "Ne postoji mogucnost editovanja rezervacije!!!");
			}
		} else
			JOptionPane.showMessageDialog(centerView, "Nemate pravo pristupa ovom dijelu sistema!!!");

	}

	public ArrayList<Integer> vratiRezSmjestajneJedinice(int rez) {
		ArrayList<Integer> rezervacijeSj = new ArrayList<Integer>();
		ResultSet rs = ProcedureClass.getInstance().procedura2("{ call VratiSveRezSmjestajneJedinice(?)}", rez);

		try {
			while (rs.next()) {
				rezervacijeSj.add(Integer.valueOf(rs.getString(1)));
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}

		return rezervacijeSj;
	}
}
