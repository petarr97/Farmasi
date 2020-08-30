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

			if (Korisnik.getInstance().getTrenutnaTabela().equals("narudzbe")) {
				dodavanje.dodavanjeNarudzbe();
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("osoblje")) {
				// dodavanje.dodavanjeSmjestajneJedinice();
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("kupci")) {
				// dodavanje.dodavanjeZaposlenog();
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("proizvodi")) {
				// dodavanje.dodavanjeRezervacije();
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("stavke")) {
				// dodavanje.dodavanjeRezervacije();
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("mjesto")) {
				// dodavanje.dodavanjeRezervacije();
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("tip")) {
				// dodavanje.dodavanjeRezervacije();
				dodavanje.show();
			}
		} else if (e.getActionCommand().equals("deleteRow")) {
			if (Korisnik.getInstance().getTrenutnaTabela().equals("narudzbe")) {

				int column = 0;
				int row = centerView.getResultSetTable().getSelectedRow();
				String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

				int i = 0;
				int result = JOptionPane.showConfirmDialog(centerView,
						"Da li ste sigurni da zelite da obrisete narudzbu?");
				if (result == JOptionPane.YES_OPTION) {

					String procedura = "{call OBRISI_NARUDZBU(?)}";
					ProcedureClass.getInstance().procedura2(procedura, Integer.valueOf(value));

					view.getCenterView().newModel.removeRow(row);
					view.getCenterView().revalidate();
					view.getCenterView().repaint();
				}
			}
			if (Korisnik.getInstance().getTrenutnaTabela().equals("osoblje")) {
				int column = 0;
				int row = centerView.getResultSetTable().getSelectedRow();
				String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

				int i = 0;
				int result = JOptionPane.showConfirmDialog(centerView,
						"Da li ste sigurni da zelite da obrisete korisnika?");
				if (result == JOptionPane.YES_OPTION) {

					String procedura = "{call OBRISI_RADNIKA(?)}";
					ProcedureClass.getInstance().procedura2(procedura, Integer.valueOf(value));

					view.getCenterView().newModel.removeRow(row);
					view.getCenterView().revalidate();
					view.getCenterView().repaint();
				}
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("kupci")) {
				int column = 0;
				int row = centerView.getResultSetTable().getSelectedRow();
				String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

				int confirm = JOptionPane.showConfirmDialog(centerView,
						"Da li ste sigurni da zelite da izbrisete kupca?");
				if (confirm == JOptionPane.YES_OPTION) {

					ProcedureClass.procedura2("{ call OBRISI_KUPCA(?)}", Integer.valueOf(value));
					view.getCenterView().newModel.removeRow(row);
					view.getCenterView().revalidate();
					view.getCenterView().repaint();

				}
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("proizvodi")) {
				int column = 0;
				int row = centerView.getResultSetTable().getSelectedRow();
				String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

				int confirm = JOptionPane.showConfirmDialog(centerView,
						"Da li ste sigurni da zelite da izbrisete proizvod?");
				if (confirm == JOptionPane.YES_OPTION) {

					ProcedureClass.procedura2("{ call OBRISI_PROIZVOD(?)}", Integer.valueOf(value));
					view.getCenterView().newModel.removeRow(row);
					view.getCenterView().revalidate();
					view.getCenterView().repaint();

				}

			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("stavke")) {
				int column = 0;
				int row = centerView.getResultSetTable().getSelectedRow();
				String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

				int confirm = JOptionPane.showConfirmDialog(centerView,
						"Da li ste sigurni da zelite da izbrisete stavku?");
				if (confirm == JOptionPane.YES_OPTION) {

					ProcedureClass.procedura2("{ call OBRISI_STAVKE_NARUDZBE(?)}", Integer.valueOf(value));
					view.getCenterView().newModel.removeRow(row);
					view.getCenterView().revalidate();
					view.getCenterView().repaint();

				}
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("mjesto")) {
				int column = 0;
				int row = centerView.getResultSetTable().getSelectedRow();
				String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

				int confirm = JOptionPane.showConfirmDialog(centerView,
						"Da li ste sigurni da zelite da izbrisete mjesto?");
				if (confirm == JOptionPane.YES_OPTION) {

					ProcedureClass.procedura2("{ call OBRISI_MJESTO(?)}", Integer.valueOf(value));
					view.getCenterView().newModel.removeRow(row);
					view.getCenterView().revalidate();
					view.getCenterView().repaint();

				}
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("tip")) {
				int column = 0;
				int row = centerView.getResultSetTable().getSelectedRow();
				String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

				int confirm = JOptionPane.showConfirmDialog(centerView,
						"Da li ste sigurni da zelite da izbrisete mjesto?");
				if (confirm == JOptionPane.YES_OPTION) {

					ProcedureClass.procedura2("{ call OBRISI_TIP_PLACANJA(?)}", Integer.valueOf(value));
					view.getCenterView().newModel.removeRow(row);
					view.getCenterView().revalidate();
					view.getCenterView().repaint();

				}
			}
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
		} else if (e.getActionCommand().contentEquals("editRow")) {

			int column = 0;
			int row = centerView.getResultSetTable().getSelectedRow();
			String value = centerView.getResultSetTable().getModel().getValueAt(row, column).toString();

			if (Korisnik.getInstance().getTrenutnaTabela().equals("narudzbe")) {
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("mjesto")) {
				dodavanje.dodavanjeMjesta();
				dodavanje.podesiDodavanjeMjesta(value, centerView, row);
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("korisnik")) {
				// dodavanje.dodavanjeZaposlenog();
				// dodavanje.podesiVrijednostiKorisnika(Integer.valueOf(value));
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("tip")) {
				dodavanje.dodavanjeTipaPlacanja();
				dodavanje.podesiTipPlacanja(value, centerView, row);
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("proizvodi")) {
				dodavanje.dodavanjeProizvoda();
				dodavanje.podesiProizvod(value, centerView, row);
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("stavke")) {
				dodavanje.dodavanjeStavkeNarudzbe();
				dodavanje.podesiStavke(value, centerView, row);
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("kupci")) {
				dodavanje.dodavanjeKupca();
				dodavanje.podesiKupca(value, centerView, row);
				dodavanje.show();
			} else if (Korisnik.getInstance().getTrenutnaTabela().equals("osoblje")) {
				dodavanje.dodavanjeRadnika();
				dodavanje.podesiOsoblje(value, centerView, row);
				dodavanje.show();
			}
		} else if (e.getActionCommand().equals("filterNarudzbeIme")) {
			String status = view.getToolbarView().filter.getText();
			System.out.println(status);
			if (!status.equals(""))
				for (int i = 0; i < centerView.newModel.getRowCount(); i++) {
					if (!status.equals(centerView.newModel.getValueAt(i, 5))) {
						centerView.newModel.removeRow(i);
						i--;
					}
				}
		}
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
