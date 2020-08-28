package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Procedure.ProcedureClass;
import model.Element;

public class Brisanje extends JFrame {
	JPanel panel = null;
	public ArrayList<Element> tipPodataka = new ArrayList<Element>();
	public JComboBox<String> comboBox = null;
	public static String labela = null;
	public static JLabel brisanjeLbl = null;
	public String[] sj = null;
	public ArrayList<Integer> zap_ID = new ArrayList<Integer>();
	public JButton deleteButton = null;
	public String[] rez = null;
	public JLabel imeLbl = null;
	public JLabel datumLbl = null;

	public Brisanje() {
		setLayout(null);

		setContentPane(panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image;
				try {
					image = ImageIO.read(new File("./img/login.jpg"));
					g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		setUndecorated(true);
		setLocation(new Point(100, 100));
		setSize(new Dimension(350, 200));
		setBackground(Color.blue);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel.setLayout(null);
		panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		panel.setLocation(new Point(0, 0));
		panel.setSize(new Dimension(350, 200));
		panel.setBackground(Color.decode("#98B4D4"));

		JButton closeButton = new JButton("X");
		closeButton.setFont(new Font("Calibri", Font.BOLD, 20));
		closeButton.setSize(new Dimension(50, 50));
		closeButton.setLocation(new Point(300, 0));
		closeButton.setOpaque(false);
		closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		brisanjeLbl = new JLabel();
		brisanjeLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		brisanjeLbl.setLocation(new Point(15, 10));
		brisanjeLbl.setSize(new Dimension(300, 50));

		deleteButton = new JButton("IZBRISI");
		deleteButton.setAlignmentX(CENTER_ALIGNMENT);
		deleteButton.setMnemonic(KeyEvent.VK_ENTER);
		deleteButton.setLocation(new Point(60, 150));
		deleteButton.setSize(new Dimension(180, 40));
		deleteButton.setUI(new StyledButtonUI());

		panel.add(deleteButton);
		panel.add(closeButton);
		panel.add(brisanjeLbl);

	}

	// postavljanje izgleda prozora
	public void brisanjeSmjestajneJedinice() {
		brisanjeLbl.setText("<html>Izaberite smjestajnu jedinicu<br>koju zelite da izbrisete</html>");
		postaviSmjestajneJedinice();

		sj = new String[tipPodataka.size()];
		int i = 0;
		for (Element el : tipPodataka) {
			sj[i++] = el.naziv;
		}

		comboBox = new JComboBox<String>(sj);
		comboBox.setSelectedIndex(1);
		comboBox.setSize(new Dimension(300, 30));
		comboBox.setLocation(new Point(15, 70));

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				IzbrisiSmjestajnuJedinicu();
				dispose();
				tipPodataka.clear();
			}
		});

		panel.add(comboBox);
	}

	// postavljanje modela Smjestajnih jedinica
	public void postaviSmjestajneJedinice() {
		ResultSet rs = ProcedureClass.getInstance().procedura2("{call VratiSveSmjestajneJedinice}");
		try {
			while (rs.next()) {
				try {
					String naziv = rs.getString(2) + ", Broj :" + rs.getString(1);
					tipPodataka.add(new Element(naziv, Integer.valueOf(rs.getString(7))));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// metoda za brisanje sj iz baze
	public void IzbrisiSmjestajnuJedinicu() {

		ArrayList<Integer> rez = vratiRezSmjestajneJedinice();

		int i = 0;
		while (i < rez.size()) {
			ProcedureClass.procedura2("{ call IzbrisiRezervaciju(?)}", rez.get(i++));

		}
		String procedura = "{call DeleteSmjestajneJedinicePoslovnogSistema(?)}";
		ProcedureClass.getInstance().procedura2(procedura, tipPodataka.get(comboBox.getSelectedIndex()).id);
		procedura = "{call SmjestajnaJedinicaProcedura(?, ?, ?, ?, ?, ?, ?)}";
		ResultSet rs = ProcedureClass.getInstance().procedura2(procedura,
				tipPodataka.get(comboBox.getSelectedIndex()).id, 0, 0, 0, 0, "", "Delete");

	}

	public ArrayList<Integer> vratiRezSmjestajneJedinice() {
		ArrayList<Integer> rezervacijeSj = new ArrayList<Integer>();
		ResultSet rs = ProcedureClass.getInstance().procedura2("{ call VratiSveRezSmjestajneJedinice(?)}",
				tipPodataka.get(comboBox.getSelectedIndex()).id);

		try {
			while (rs.next()) {
				rezervacijeSj.add(Integer.valueOf(rs.getString(1)));
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rezervacijeSj;
	}

	// postavljanje izgleda prozora
	public void brisanjeKorisnika() {
		brisanjeLbl.setText("<html>Izaberite korisnika kojeg<br>zelite da obrisete</hmtl>");
		postaviKorisnike();

		sj = new String[tipPodataka.size()];
		int i = 0;
		for (Element el : tipPodataka) {
			sj[i++] = i + " - " + el.naziv;
		}

		comboBox = new JComboBox<String>(sj);
		comboBox.setSelectedIndex(0);
		comboBox.setSize(new Dimension(300, 30));
		comboBox.setLocation(new Point(15, 70));

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				IzbrisiKorisnika();
				dispose();
				zap_ID.clear();
				tipPodataka.clear();
			}
		});

		panel.add(comboBox);
	}

	// metoda za brisanje korisnika iz baze
	protected void IzbrisiKorisnika() {

		String procedura = "{call DeleteKorisnik(?)}";
		ProcedureClass.getInstance().procedura2(procedura, tipPodataka.get(comboBox.getSelectedIndex()).id);

	}

	// postavljanje modela korisnika iz baze
	private void postaviKorisnike() {
		ResultSet rs = ProcedureClass.getInstance().procedura2("{call VratiKorisnikeSistema}");
		try {
			while (rs.next()) {
				try {
					String naziv = rs.getString(3) + " " + rs.getString(4);
					tipPodataka.add(new Element(naziv, Integer.valueOf(rs.getString(1))));
					zap_ID.add(Integer.valueOf(rs.getString(6)));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// postavaljnje modela rezervacija iz baze
	private void setRezervacije() {
		ResultSet rs = ProcedureClass.getInstance().procedura2("{call VratiSveRezervacije}");
		try {
			while (rs.next()) {
				try {
					tipPodataka.add(new Element("Soba broj : " + rs.getString(4), Integer.valueOf(rs.getString(1))));
					tipPodataka.get(tipPodataka.size() - 1).setPs_naziv(rs.getString(2));
					tipPodataka.get(tipPodataka.size() - 1).setDatum(rs.getString(6) + " - " + rs.getString(7));
					tipPodataka.get(tipPodataka.size() - 1).setIme(rs.getString(8) + " " + rs.getString(9));
					System.out.println(rs.getString(8) + " " + rs.getString(9));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void postaviOtkazivanjeRezervacije() {

		setSize(new Dimension(350, 300));
		panel.setSize(new Dimension(350, 300));
		brisanjeLbl.setText("<html>Izaberite rezervaciju koju<br> zelite da obrisete</html>");

		imeLbl = new JLabel();
		imeLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		imeLbl.setLocation(new Point(15, 100));
		imeLbl.setSize(new Dimension(300, 50));

		datumLbl = new JLabel();
		datumLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		datumLbl.setLocation(new Point(15, 130));
		datumLbl.setSize(new Dimension(300, 50));

		setRezervacije();

		rez = new String[tipPodataka.size()];
		int i = 0;
		for (Element el : tipPodataka) {
			rez[i++] = i + " - " + el.getPs_naziv() + "  " + el.naziv;
		}

		comboBox = new JComboBox<String>(rez);
		comboBox.setSize(new Dimension(300, 30));
		comboBox.setSelectedIndex(0);
		comboBox.setLocation(new Point(15, 70));
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imeLbl.setText("Rezervaisao: " + tipPodataka.get(comboBox.getSelectedIndex()).getIme());
				datumLbl.setText(tipPodataka.get(comboBox.getSelectedIndex()).getDatum());

			}
		});
		imeLbl.setText(tipPodataka.get(comboBox.getSelectedIndex()).getIme());
		datumLbl.setText(tipPodataka.get(comboBox.getSelectedIndex()).getDatum());

		panel.add(imeLbl);
		panel.add(datumLbl);
		panel.add(comboBox);

		deleteButton.setLocation(new Point(60, 230));
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				otkaziRezervaciju();
				dispose();
				tipPodataka.clear();
			}
		});
	}

	public void otkaziRezervaciju() {
		ProcedureClass.procedura2("{ call IzbrisiRezervaciju(?)}",
				tipPodataka.get(comboBox.getSelectedIndex()).getId());
	}
}
