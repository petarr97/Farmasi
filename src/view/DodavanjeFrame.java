package view;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mindfusion.common.Convert;

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
	public ArrayList<Element> narudzbe = new ArrayList<Element>();
	public ArrayList<Element> proizvodi = new ArrayList<Element>();

	public JTextField ime = null;
	public JTextField prezime = null;
	public JTextField datumRodjenja = null;
	public JTextField datumZaposlenja = null;

	public JComboBox<String> proizvodiCb = null;
	public JComboBox<String> narduzbeCb = null;
	public JComboBox<String> gradoviCb = null;
	public JComboBox<String> tipPlacanjaCb = null;
	public JComboBox<String> kupciCb = null;
	public JTextField cijena = null;
	public JComboBox<String> radniciCb = null;
	public JTextField broj = null;
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
				dispose();
			}
		});
		panel.add(confirmButton);

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

	// popunjavanje modela za mjesta combo box
	public String[] getMjesta() {
		ResultSet rs = ProcedureClass.procedura2("{ call UCITAJ_MJESTO }");
		ArrayList<String> mjesto = new ArrayList<String>();
		try {
			int i = 0;
			while (rs.next()) {
				String ime = (i++) + rs.getString(2);
				this.gradovi.add(new Element(rs.getString(2), rs.getInt(1)));
				mjesto.add(ime);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] tipNiz = new String[mjesto.size()];
		int i = 0;
		for (String string : mjesto) {
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

	// kreiranje prozora za dodavanje radnika
	public void dodavanjeRadnika() {
		setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel("Mjesto");
		lbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(lbl);

		String[] mjesto = getMjesta();
		gradoviCb = new JComboBox<String>(mjesto);
		gradoviCb.setSelectedIndex(0);
		panel.add(gradoviCb);

		JLabel l = new JLabel("Ime");
		l.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(l);

		ime = new JTextField();
		ime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		ime.setPreferredSize(new Dimension(0, 30));
		panel.add(ime);

		JLabel l1 = new JLabel("Prezime");
		l1.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(l1);

		prezime = new JTextField();
		prezime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		prezime.setPreferredSize(new Dimension(0, 30));
		panel.add(prezime);

		JLabel brlbl = new JLabel("Br telefona");
		brlbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(brlbl);

		broj = new JTextField();
		broj.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		broj.setPreferredSize(new Dimension(0, 30));
		panel.add(broj);

		JLabel emailLbl = new JLabel("Email");
		emailLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(emailLbl);

		email = new JTextField();
		email.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		email.setPreferredSize(new Dimension(0, 30));
		panel.add(email);

		JLabel adresalLbl = new JLabel("Adresa");
		adresalLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(adresalLbl);

		adresa = new JTextField();
		adresa.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		adresa.setPreferredSize(new Dimension(0, 30));
		panel.add(adresa);

		JLabel datumLbl = new JLabel("Datum rodjenja");
		datumLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(datumLbl);

		datumRodjenja = new JTextField();
		datumRodjenja.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(datumRodjenja);

		JLabel datumLbl1 = new JLabel("Datum zaposlenja");
		datumLbl1.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(datumLbl1);

		datumZaposlenja = new JTextField();
		datumZaposlenja.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(datumZaposlenja);

		confirmButton = new JButton("POTVRDI");
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertOsoblje();
				dispose();
			}
		});
		panel.add(confirmButton);

		for (Component c : panel.getComponents()) {
			c.setMaximumSize(new Dimension(200, 30));
			((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);

		}

	}

	// ubacivanje korisnika u bazu
	protected void insertOsoblje() {
		long date = new java.util.Date().getTime();
		java.sql.Date sqlDate = new java.sql.Date(date);

		ProcedureClass.procedura2("{ call INSERT_RADNIK(?,?,?,?,?,?,?,?)}",
				gradovi.get(gradoviCb.getSelectedIndex()).id, ime.getText(), prezime.getText(), broj.getText(),
				email.getText(), adresa.getText(), sqlDate, sqlDate);
	}

	// kreiranje prozora za dodavanje kupca
	public void dodavanjeKupca() {
		setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel("Mjesto");
		lbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(lbl);

		String[] mjesto = getMjesta();
		gradoviCb = new JComboBox<String>(mjesto);
		gradoviCb.setSelectedIndex(0);
		panel.add(gradoviCb);

		JLabel l = new JLabel("Ime");
		l.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(l);

		ime = new JTextField();
		ime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		ime.setPreferredSize(new Dimension(0, 30));
		panel.add(ime);

		JLabel l1 = new JLabel("Prezime");
		l1.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(l1);

		prezime = new JTextField();
		prezime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		prezime.setPreferredSize(new Dimension(0, 30));
		panel.add(prezime);

		JLabel brlbl = new JLabel("Br telefona");
		brlbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(brlbl);

		broj = new JTextField();
		broj.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		broj.setPreferredSize(new Dimension(0, 30));
		panel.add(broj);

		JLabel adresalLbl = new JLabel("Adresa");
		adresalLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(adresalLbl);

		adresa = new JTextField();
		adresa.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		adresa.setPreferredSize(new Dimension(0, 30));
		panel.add(adresa);

		JLabel JMBlbl = new JLabel("JMB");
		JMBlbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(JMBlbl);

		maticniBroj = new JTextField();
		maticniBroj.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(maticniBroj);

		confirmButton = new JButton("POTVRDI");
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertKupac();
				dispose();
			}
		});
		panel.add(confirmButton);

		for (Component c : panel.getComponents()) {
			c.setMaximumSize(new Dimension(200, 30));
			((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);

		}

	}

	// ubacivanje korisnika u bazu
	protected void insertKupac() {
		long date = new java.util.Date().getTime();
		java.sql.Date sqlDate = new java.sql.Date(date);

		ProcedureClass.procedura2("{ call INSERT_KUPAC(?,?,?,?,?,?)}", gradovi.get(gradoviCb.getSelectedIndex()).id,
				ime.getText(), prezime.getText(), adresa.getText(), maticniBroj.getText(), broj.getText());
	}

	// kreiranje prozora za dodavanje mjesta
	public void dodavanjeMjesta() {
		setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setSize(new Dimension(300, 250));
		JLabel lbl = new JLabel("Naziv Mjesta");
		lbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(lbl);

		ime = new JTextField();
		ime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(ime);

		confirmButton = new JButton("POTVRDI");
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertMjesto();
				dispose();
			}
		});
		panel.add(confirmButton);

		for (Component c : panel.getComponents()) {
			c.setMaximumSize(new Dimension(200, 30));
			((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);

		}

	}

	// ubacivanje korisnika u bazu
	protected void insertMjesto() {

		ProcedureClass.procedura2("{ call INSERT_MJESTO(?)}", ime.getText());
	}

	// kreiranje prozora za dodavanje prozivoda
	public void dodavanjeProizvoda() {
		setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setSize(new Dimension(300, 300));
		JLabel l = new JLabel("Naziv");
		l.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(l);

		ime = new JTextField();
		ime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		ime.setPreferredSize(new Dimension(0, 30));
		panel.add(ime);

		JLabel l1 = new JLabel("Cijena");
		l1.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(l1);

		cijena = new JTextField();
		cijena.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		cijena.setPreferredSize(new Dimension(0, 30));
		panel.add(cijena);

		JLabel pdvLbl = new JLabel("PDV stopa");
		pdvLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(pdvLbl);

		broj = new JTextField();
		broj.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(broj);

		confirmButton = new JButton("POTVRDI");
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertProizvod();
				dispose();
			}
		});
		panel.add(confirmButton);

		for (Component c : panel.getComponents()) {
			c.setMaximumSize(new Dimension(200, 30));
			((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);

		}

	}

	// ubacivanje korisnika u bazu
	protected void insertProizvod() {

		ProcedureClass.procedura2("{ call INSERT_PROIZVOD(?,?,?)}", ime.getText(), Convert.toDouble(cijena.getText()),
				Convert.toDouble(broj.getText()));
	}

	// kreiranje prozora za dodavanje tipa placanja
	public void dodavanjeTipaPlacanja() {
		setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setSize(new Dimension(300, 250));
		JLabel lbl = new JLabel("Tip placanja");
		lbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(lbl);

		ime = new JTextField();
		ime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(ime);

		confirmButton = new JButton("POTVRDI");
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertTipPlacanja();
				dispose();
			}
		});
		panel.add(confirmButton);

		for (Component c : panel.getComponents()) {
			c.setMaximumSize(new Dimension(200, 30));
			((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);

		}

	}

	// ubacivanje korisnika u bazu
	protected void insertTipPlacanja() {

		ProcedureClass.procedura2("{ call INSERT_TIPPLACANJA(?)}", ime.getText());
	}

	// kreiranje prozora za dodavanje kupca
	public void dodavanjeStavkeNarudzbe() {
		setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lbl = new JLabel("Narudzba ID");
		lbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(lbl);

		String[] narduzbe = getNarudzbe();
		narduzbeCb = new JComboBox<String>(narduzbe);
		narduzbeCb.setSelectedIndex(0);
		panel.add(narduzbeCb);

		JLabel prLbl = new JLabel("Proizvodi ID");
		prLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(prLbl);

		String[] proizvodi = getProizvodi();
		proizvodiCb = new JComboBox<String>(proizvodi);
		proizvodiCb.setSelectedIndex(0);
		panel.add(proizvodiCb);

		JLabel l = new JLabel("Kolicina");
		l.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(l);

		broj = new JTextField();
		broj.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		broj.setPreferredSize(new Dimension(0, 30));
		panel.add(broj);

		JLabel cjLbl = new JLabel("Cijena");
		cjLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(cjLbl);

		cijena = new JTextField();
		cijena.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(cijena);

		confirmButton = new JButton("POTVRDI");
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertNoveStavke();
				dispose();
			}
		});
		panel.add(confirmButton);

		for (Component c : panel.getComponents()) {
			c.setMaximumSize(new Dimension(200, 30));
			((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);

		}

	}

	// popunjavanje modela narudzbi cb
	private String[] getNarudzbe() {
		ResultSet rs = ProcedureClass.procedura2("{ call UCITAJ_NARUDZBE }");
		ArrayList<String> narudzbe = new ArrayList<String>();
		try {
			int i = 0;
			while (rs.next()) {
				this.narudzbe.add(new Element(null, rs.getInt(1)));
				narudzbe.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] tipNiz = new String[narudzbe.size()];
		int i = 0;
		for (String string : narudzbe) {
			tipNiz[i++] = string;
		}
		return tipNiz;
	}

	// popunjavanje modela proizvoda cb
	private String[] getProizvodi() {
		ResultSet rs = ProcedureClass.procedura2("{ call UCITAJ_PROIZVODE }");
		ArrayList<String> pro = new ArrayList<String>();
		try {
			int i = 0;
			while (rs.next()) {
				this.proizvodi.add(new Element(rs.getString(2), rs.getInt(1)));
				pro.add(rs.getString(1) + " " + rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] tipNiz = new String[pro.size()];
		int i = 0;
		for (String string : pro) {
			tipNiz[i++] = string;
		}
		return tipNiz;
	}

	// ubacivanje korisnika u bazu
	protected void insertNoveStavke() {
		ProcedureClass.procedura2("{ call INSERT_STAVKENARUDZBE(?,?,?,?)}",
				narudzbe.get(narduzbeCb.getSelectedIndex()).id, proizvodi.get(proizvodiCb.getSelectedIndex()).id,
				Convert.toInt16(broj.getText()), Convert.toDouble(cijena.getText()));
	}

}
