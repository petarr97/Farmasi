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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Procedure.ProcedureClass;
import model.Element;

public class DodavanjeFrame extends JFrame {

	public JPanel panel = null;

	public static String[] listaPoslovnihSistema = null;
	public static ArrayList<Element> data = new ArrayList<Element>();
	public ArrayList<Element> tipPlacanja = new ArrayList<Element>();
	public ArrayList<Element> radnici = new ArrayList<Element>();
	public ArrayList<Element> kupci = new ArrayList<Element>();
	public ArrayList<Element> gradovi = new ArrayList<Element>();

	public JComboBox<String> tipPlacanjaCb = null;
	public JComboBox<String> kupciCb = null;
	public JTextField cijena = null;
	public JComboBox<String> radniciCb = null;
	public JTextField brojSJ = null;
	public JTextArea napomena = null;
	public JTextField status = null;
	public JTextField datum = null;
	public JTextField imeOca = null;
	public JTextField maticniBroj = null;
	public JComboBox<String> pol = null;
	public JTextField password = null;
	public JTextField username = null;
	public JTextField fiksni = null;
	public JTextField mobilni = null;
	public JTextField email = null;
	public JTextField www = null;
	public JTextField adresa = null;
	public JComboBox<String> smjestajneJediniceCB = null;
	public ArrayList<Element> smjestajneJediniceLista = new ArrayList<Element>();
	public JFormattedTextField datumOd = null;
	public JFormattedTextField datumDo = null;
	JButton confirmButton = null;
	JTextField regBr = null;
	JTextField pib = null;
	JComboBox<String> drzaveComboBox = null;
	JComboBox<String> gradoviComboBox = null;

	String mode = "";
	int editId;

	private int defautBr;
	private String defaultUsername;

	public DodavanjeFrame() {

		setLayout(null);

		setContentPane(panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image;
				try {
					image = ImageIO.read(new File("./img/login.jpg"));
					Image newImage = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
					g.drawImage(newImage, 0, 0, this); // see javadoc for more info on the parameters

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		setLocation(new Point(100, 100));
		setSize(new Dimension(600, 600));
		setBackground(Color.blue);

		panel.setLayout(null);
		panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		panel.setLocation(new Point(0, 0));
		panel.setSize(new Dimension(600, 600));
		panel.setBackground(Color.decode("#98B4D4"));

		JButton closeButton = new JButton("X");
		closeButton.setFont(new Font("Calibri", Font.BOLD, 20));
		closeButton.setSize(new Dimension(50, 50));
		closeButton.setLocation(new Point(530, 0));
		closeButton.setOpaque(false);
		closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// data.clear();
				// tip.clear();
				dispose();
			}
		});
		panel.add(closeButton);

		// add(panel);

	}

	// kreiranje prozora za dodavanje sj
	public void dodavanjeNarudzbe() {
		setSize(new Dimension(400, 500));

		JLabel izborPoslovnogSistema = new JLabel("Tip placanja");
		izborPoslovnogSistema.setFont(new Font("Calibri", Font.PLAIN, 20));
		izborPoslovnogSistema.setLocation(new Point(100, 70));
		izborPoslovnogSistema.setSize(new Dimension(600, 30));
		panel.add(izborPoslovnogSistema);

		String[] tip = getTipPlacanja();
		tipPlacanjaCb = new JComboBox<String>(tip);
		tipPlacanjaCb.setSelectedIndex(0);
		tipPlacanjaCb.setSize(new Dimension(150, 20));
		tipPlacanjaCb.setLocation(new Point(100, 100));
		panel.add(tipPlacanjaCb);

		JLabel kupciLbl = new JLabel("Kupac");
		kupciLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		kupciLbl.setLocation(new Point(100, 130));
		kupciLbl.setSize(new Dimension(600, 30));
		panel.add(kupciLbl);

		String[] kupci = getKupci();
		kupciCb = new JComboBox<String>(kupci);
		kupciCb.setSelectedIndex(0);
		kupciCb.setSize(new Dimension(150, 20));
		kupciCb.setLocation(new Point(100, 160));
		panel.add(kupciCb);

		JLabel radniciLbl = new JLabel("Radnik");
		radniciLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		radniciLbl.setLocation(new Point(100, 190));
		radniciLbl.setSize(new Dimension(600, 30));
		panel.add(radniciLbl);

		String[] radnici = getRadnici();
		radniciCb = new JComboBox<String>(radnici);
		radniciCb.setSelectedIndex(0);
		radniciCb.setSize(new Dimension(150, 20));
		radniciCb.setLocation(new Point(100, 220));
		panel.add(radniciCb);

		JLabel statusLbl = new JLabel("Status");
		statusLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		statusLbl.setLocation(new Point(100, 250));
		statusLbl.setSize(new Dimension(700, 30));
		panel.add(statusLbl);

		status = new JTextField();
		status.setSize(new Dimension(200, 30));
		status.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		status.setLocation(new Point(100, 280));
		panel.add(status);

		JLabel datumLbl = new JLabel("Datum");
		datumLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		datumLbl.setLocation(new Point(100, 315));
		datumLbl.setSize(new Dimension(700, 30));
		panel.add(datumLbl);

		datum = new JTextField();
		datum.setSize(new Dimension(200, 30));
		datum.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		datum.setLocation(new Point(100, 350));
		panel.add(datum);
//
//		JLabel tipLbl = new JLabel("Izaberite tip smjestajne jedinice");
//		tipLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
//		tipLbl.setLocation(new Point(100, 200));
//		tipLbl.setSize(new Dimension(700, 30));
//
//		i = 0;
//		for (Element el : tip) {
//			tipJedinice[i++] = el.naziv;
//		}
//
//		tipSmjestajneJediniceCb = new JComboBox<String>(tipJedinice);
//		tipSmjestajneJediniceCb.setSelectedIndex(0);
//		tipSmjestajneJediniceCb.setSize(new Dimension(150, 20));
//		tipSmjestajneJediniceCb.setLocation(new Point(100, 230));
//
//		JLabel brojlbl = new JLabel("Unesite broj smjestajne jedinice");
//		brojlbl.setFont(new Font("Calibri", Font.PLAIN, 20));
//		brojlbl.setLocation(new Point(100, 260));
//		brojlbl.setSize(new Dimension(700, 30));
//
//		brojSJ = new JTextField();
//		brojSJ.setSize(new Dimension(200, 30));
//		brojSJ.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
//		brojSJ.setLocation(new Point(100, 290));
//		brojSJ.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				char c = e.getKeyChar();
//				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
//					getToolkit().beep();
//					e.consume();
//				}
//			}
//		});
//
//		JLabel napomenaLbl = new JLabel("Unesite napomenu");
//		napomenaLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
//		napomenaLbl.setLocation(new Point(100, 330));
//		napomenaLbl.setSize(new Dimension(700, 30));
//
//		napomena = new JTextArea();
//		napomena.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
//		napomena.setSize(new Dimension(200, 70));
//		napomena.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
//		napomena.setLocation(new Point(100, 360));
//
		confirmButton = new JButton("POTVRDI");
		confirmButton.setAlignmentX(CENTER_ALIGNMENT);
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setLocation(new Point(110, 400));
		confirmButton.setSize(new Dimension(180, 40));
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertNarudzba();
			}
		});
		panel.add(confirmButton);

		// panel.add(confirmButton);
		// panel.add(napomenaLbl);
		// panel.add(napomena);
		// panel.add(brojSJ);
		// panel.add(brojlbl);
		// panel.add(tipLbl);
		// panel.add(tipSmjestajneJediniceCb);
		// panel.add(cijenaLbL);
		// panel.add(cijena);
		// panel.add(poslovniSistemi);
	}

	// popunjavanje modela za tip placanja combo box
	public String[] getTipPlacanja() {
		ResultSet rs = ProcedureClass.procedura2("{ call UCITAJ_TIP_PLACANJA }");
		ArrayList<String> tip = new ArrayList<String>();
		try {
			while (rs.next()) {
				tipPlacanja.add(new Element(rs.getString(2), rs.getInt(1)));
				tip.add(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] tipNiz = new String[tip.size()];
		int i = 0;
		for (String string : tip) {
			tipNiz[i++] = string;
		}
		return tipNiz;

	}

	// popunjavanje modela za kupce combo box
	public String[] getKupci() {
		ResultSet rs = ProcedureClass.procedura2("{ call UCITAJ_KUPCE }");
		ArrayList<String> kupci = new ArrayList<String>();
		try {
			int i = 0;
			while (rs.next()) {
				String ime = (i++) + " " + rs.getString(3) + " " + rs.getString(4);
				this.kupci.add(new Element(ime + rs.getString(4), rs.getInt(1)));
				kupci.add(ime);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] tipNiz = new String[kupci.size()];
		int i = 0;
		for (String string : kupci) {
			tipNiz[i++] = string;
		}
		return tipNiz;

	}

	// popunjavanje modela za radnike combo box
	public String[] getRadnici() {
		ResultSet rs = ProcedureClass.procedura2("{ call UCITAJ_RADNIKE }");
		ArrayList<String> radnici = new ArrayList<String>();
		try {
			int i = 0;
			while (rs.next()) {
				String ime = (i++) + " " + rs.getString(3) + " " + rs.getString(4);
				this.radnici.add(new Element(ime + rs.getString(4), rs.getInt(1)));
				radnici.add(ime);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] tipNiz = new String[radnici.size()];
		int i = 0;
		for (String string : radnici) {
			tipNiz[i++] = string;
		}
		return tipNiz;
	}

	// ubacivanje narudzbe u bazu

	public void insertNarudzba() {

		long date = new java.util.Date().getTime();
		java.sql.Date sqlDate = new java.sql.Date(date);

		ProcedureClass.procedura2("{ call INSERT_NARUDZBA(?,?,?,?,?,?,?,?)}",
				tipPlacanja.get(tipPlacanjaCb.getSelectedIndex()).id, radnici.get(radniciCb.getSelectedIndex()).id,
				kupci.get(kupciCb.getSelectedIndex()).getId(), sqlDate, status.getText(), 0, 0, 0);
	}
}
