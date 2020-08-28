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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Procedure.ProcedureClass;
import dbpackage.DBconnection;
import model.Element;
import model.Korisnik;

public class DodavanjeFrame extends JFrame {

	public JPanel panel = null;

	public static String[] listaPoslovnihSistema = null;
	public static ArrayList<Element> data = new ArrayList<Element>();
	public ArrayList<Element> tip = new ArrayList<Element>();
	public ArrayList<Element> tipKorisnikaData = new ArrayList<Element>();
	public ArrayList<Element> drzave = new ArrayList<Element>();
	public ArrayList<Element> gradovi = new ArrayList<Element>();

	public JComboBox<String> tipKorisnikaCb = null;
	public JComboBox<String> poslovniSistemi = null;
	public JTextField cijena = null;
	public JComboBox<String> tipSmjestajneJediniceCb = null;
	public JTextField brojSJ = null;
	public JTextArea napomena = null;
	public JTextField ime = null;
	public JTextField prezime = null;
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

	public static String[] getListaPoslovnihSistema() {
		return listaPoslovnihSistema;
	}

	public static void setListaPoslovnihSistema(String[] listaPoslovnihSistema) {
		DodavanjeFrame.listaPoslovnihSistema = listaPoslovnihSistema;
	}

	public static ArrayList<Element> getData() {
		return data;
	}

	public static void setData(ArrayList<Element> data) {
		DodavanjeFrame.data = data;
	}

	public JTextField getCijena() {
		return cijena;
	}

	public void setCijena(JTextField cijena) {
		this.cijena = cijena;
	}

	public JComboBox<String> getTipSmjestajneJediniceCb() {
		return tipSmjestajneJediniceCb;
	}

	public void setTipSmjestajneJediniceCb(JComboBox<String> tipSmjestajneJediniceCb) {
		this.tipSmjestajneJediniceCb = tipSmjestajneJediniceCb;
	}

	public JTextField getBrojSJ() {
		return brojSJ;
	}

	public void setBrojSJ(JTextField brojSJ) {
		this.brojSJ = brojSJ;
	}

	public JTextArea getNapomena() {
		return napomena;
	}

	public void setNapomena(JTextArea napomena) {
		this.napomena = napomena;
	}

	public void setPoslovniSistemi(JComboBox<String> poslovniSistemi) {
		this.poslovniSistemi = poslovniSistemi;
	}

	public ArrayList<Element> getTip() {
		return tip;
	}

	public void setTip(ArrayList<Element> tip) {
		this.tip = tip;
	}

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
		setUndecorated(true);
		setLocation(new Point(100, 100));
		setSize(new Dimension(600, 600));
		setBackground(Color.blue);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

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
				data.clear();
				tip.clear();
				dispose();
			}
		});
		panel.add(closeButton);

		// add(panel);

	}

	// kreiranje prozora za dodavanje sj
	public void dodavanjeSmjestajneJedinice() {
		JLabel izborPoslovnogSistema = new JLabel("Poslovni sistem unutar kog se nalazi smjestajna jedinica");
		izborPoslovnogSistema.setFont(new Font("Calibri", Font.PLAIN, 20));
		izborPoslovnogSistema.setLocation(new Point(100, 70));
		izborPoslovnogSistema.setSize(new Dimension(600, 30));
		panel.add(izborPoslovnogSistema);

		int i = 0;

		getPoslovniSistemi();
		String[] listaPoslovnihSistema = new String[data.size()];
		for (Element element : data) {
			listaPoslovnihSistema[i++] = element.naziv;
		}
		poslovniSistemi = new JComboBox<String>(listaPoslovnihSistema);
		poslovniSistemi.setSelectedIndex(1);
		poslovniSistemi.setSize(new Dimension(150, 20));
		poslovniSistemi.setLocation(new Point(100, 100));

		JLabel cijenaLbL = new JLabel("Unesite cijenu jednog nocenja");
		cijenaLbL.setFont(new Font("Calibri", Font.PLAIN, 20));
		cijenaLbL.setLocation(new Point(100, 130));
		cijenaLbL.setSize(new Dimension(700, 30));

		cijena = new JTextField();
		cijena.setSize(new Dimension(200, 30));
		cijena.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		cijena.setLocation(new Point(100, 160));
		cijena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		getTipJedinice();
		String[] tipJedinice = new String[tip.size()];

		JLabel tipLbl = new JLabel("Izaberite tip smjestajne jedinice");
		tipLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		tipLbl.setLocation(new Point(100, 200));
		tipLbl.setSize(new Dimension(700, 30));

		i = 0;
		for (Element el : tip) {
			tipJedinice[i++] = el.naziv;
		}

		tipSmjestajneJediniceCb = new JComboBox<String>(tipJedinice);
		tipSmjestajneJediniceCb.setSelectedIndex(0);
		tipSmjestajneJediniceCb.setSize(new Dimension(150, 20));
		tipSmjestajneJediniceCb.setLocation(new Point(100, 230));

		JLabel brojlbl = new JLabel("Unesite broj smjestajne jedinice");
		brojlbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		brojlbl.setLocation(new Point(100, 260));
		brojlbl.setSize(new Dimension(700, 30));

		brojSJ = new JTextField();
		brojSJ.setSize(new Dimension(200, 30));
		brojSJ.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		brojSJ.setLocation(new Point(100, 290));
		brojSJ.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		JLabel napomenaLbl = new JLabel("Unesite napomenu");
		napomenaLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		napomenaLbl.setLocation(new Point(100, 330));
		napomenaLbl.setSize(new Dimension(700, 30));

		napomena = new JTextArea();
		napomena.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		napomena.setSize(new Dimension(200, 70));
		napomena.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		napomena.setLocation(new Point(100, 360));

		confirmButton = new JButton("POTVRDI");
		confirmButton.setAlignmentX(CENTER_ALIGNMENT);
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setLocation(new Point(210, 500));
		confirmButton.setSize(new Dimension(180, 40));
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!provjeri_Polja()) {
					if (!provjeriBrojSmjestajneJedinice()) {
						if (mode.equals("edit")) {
							updateSmjestajnaJedinica();
						} else
							IzvrsiDodavanjeSmjestajneJedinice();

						dispose();
						data.clear();
						tip.clear();
					} else
						JOptionPane.showMessageDialog(panel, "Broj " + brojSJ.getText() + " u "
								+ poslovniSistemi.getSelectedItem() + " vec postoji!!!");
				} else
					JOptionPane.showMessageDialog(panel, "Morate popuniti sva polja!!!");
			}
		});

		panel.add(confirmButton);
		panel.add(napomenaLbl);
		panel.add(napomena);
		panel.add(brojSJ);
		panel.add(brojlbl);
		panel.add(tipLbl);
		panel.add(tipSmjestajneJediniceCb);
		panel.add(cijenaLbL);
		panel.add(cijena);
		panel.add(poslovniSistemi);
	}

	// kreiranje prozora za dodavanje zaposlenog
	public void dodavanjeZaposlenog() {
		JLabel izborPoslovnogSistema = new JLabel("Izbor poslovnog sistema ");
		izborPoslovnogSistema.setFont(new Font("Calibri", Font.PLAIN, 20));
		izborPoslovnogSistema.setLocation(new Point(100, 70));
		izborPoslovnogSistema.setSize(new Dimension(600, 30));
		panel.add(izborPoslovnogSistema);

		int i = 0;

		getPoslovniSistemi();
		String[] listaPoslovnihSistema = new String[data.size()];
		for (Element element : data) {
			listaPoslovnihSistema[i++] = element.naziv;
		}
		poslovniSistemi = new JComboBox<String>(listaPoslovnihSistema);
		poslovniSistemi.setSelectedIndex(0);
		poslovniSistemi.setSize(new Dimension(150, 20));
		poslovniSistemi.setLocation(new Point(100, 100));
		panel.add(poslovniSistemi);

		JLabel imeLbl = new JLabel("Ime zaposlenog");
		imeLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		imeLbl.setLocation(new Point(100, 130));
		imeLbl.setSize(new Dimension(600, 30));
		panel.add(imeLbl);

		ime = new JTextField();
		ime.setSize(new Dimension(200, 30));
		ime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		ime.setLocation(new Point(100, 160));
		ime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z')) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel.add(ime);

		JLabel prezimeLbl = new JLabel("Prezime zaposlenog");
		prezimeLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		prezimeLbl.setLocation(new Point(100, 200));
		prezimeLbl.setSize(new Dimension(600, 30));
		panel.add(prezimeLbl);

		prezime = new JTextField();
		prezime.setSize(new Dimension(200, 30));
		prezime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		prezime.setLocation(new Point(100, 230));
		prezime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z')) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel.add(prezime);

		JLabel imeOcaLbl = new JLabel("Ime oca");
		imeOcaLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		imeOcaLbl.setLocation(new Point(100, 265));
		imeOcaLbl.setSize(new Dimension(600, 30));
		panel.add(imeOcaLbl);

		imeOca = new JTextField();
		imeOca.setSize(new Dimension(200, 30));
		imeOca.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		imeOca.setLocation(new Point(100, 290));
		imeOca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z')) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		panel.add(imeOca);

		JLabel mbLbl = new JLabel("Maticni broj");
		mbLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		mbLbl.setLocation(new Point(100, 330));
		mbLbl.setSize(new Dimension(600, 30));
		panel.add(mbLbl);

		maticniBroj = new JTextField();
		maticniBroj.setSize(new Dimension(200, 30));
		maticniBroj.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		maticniBroj.setLocation(new Point(100, 360));
		maticniBroj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel.add(maticniBroj);

		JLabel polLbl = new JLabel("Unesite pol korisnika");
		polLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		polLbl.setLocation(new Point(100, 390));
		polLbl.setSize(new Dimension(600, 30));
		panel.add(polLbl);

		String[] polString = { "Musko", "Žensko" };
		pol = new JComboBox<String>(polString);
		pol.setSelectedIndex(0);
		pol.setSize(new Dimension(100, 20));
		pol.setLocation(new Point(100, 420));
		panel.add(pol);

		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		usernameLbl.setLocation(new Point(350, 70));
		usernameLbl.setSize(new Dimension(600, 30));
		panel.add(usernameLbl);

		username = new JTextField();
		username.setSize(new Dimension(200, 30));
		username.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		username.setLocation(new Point(350, 100));
		panel.add(username);

		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordLbl.setLocation(new Point(350, 130));
		passwordLbl.setSize(new Dimension(600, 30));
		panel.add(passwordLbl);

		password = new JTextField();
		password.setSize(new Dimension(200, 30));
		password.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		password.setLocation(new Point(350, 160));
		panel.add(password);

		getTipKorisnika();
		String[] tipKorisnika = new String[tipKorisnikaData.size()];

		JLabel tipLbl = new JLabel("Izaberite tip korisnika");
		tipLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		tipLbl.setLocation(new Point(350, 190));
		tipLbl.setSize(new Dimension(500, 30));
		panel.add(tipLbl);

		i = 0;
		for (Element el : tipKorisnikaData) {
			tipKorisnika[i++] = el.naziv;
		}

		tipKorisnikaCb = new JComboBox<String>(tipKorisnika);
		tipKorisnikaCb.setSelectedIndex(2);
		tipKorisnikaCb.setSize(new Dimension(150, 20));
		tipKorisnikaCb.setLocation(new Point(350, 220));
		panel.add(tipKorisnikaCb);

		confirmButton = new JButton("DODAJ ZAPOSLENOG");
		confirmButton.setAlignmentX(CENTER_ALIGNMENT);
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setLocation(new Point(210, 500));
		confirmButton.setSize(new Dimension(180, 40));
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!provjeri_Polja()) {
					if (!provjeriUsername()) {
						if (mode.equals("edit"))
							updateUser();
						else
							dodajZaposlenog();

						data.clear();
						tip.clear();
						dispose();
					} else
						JOptionPane.showMessageDialog(panel, "Username " + username.getText() + " vec postoji!!!");

				} else
					JOptionPane.showMessageDialog(panel, "Morate popuniti sva polja!!!");

			}
		});
		panel.add(confirmButton);
	}

	// dodavanje zaposlenog u bazu
	protected void dodajZaposlenog() {

		long date = new java.util.Date().getTime();
		java.sql.Date sqlDate = new java.sql.Date(date);

		String insertZaposleni = "{CALL DodajZaposlenog(?,?,?,?,?,?)}";
		String insertKorisnik = "{CALL insertKorisnik(?,?,?,?,?)}";
		ProcedureClass.getInstance().procedura2(insertZaposleni, data.get(poslovniSistemi.getSelectedIndex()).id,
				prezime.getText(), ime.getText(), imeOca.getText(),
				String.valueOf(String.valueOf(pol.getSelectedItem()).charAt(0)), maticniBroj.getText());

		ProcedureClass.getInstance().procedura2(insertKorisnik, data.get(poslovniSistemi.getSelectedIndex()).id,
				tipKorisnikaData.get(tipKorisnikaCb.getSelectedIndex()).id, sqlDate, username.getText(),
				password.getText());
	}

	// citanje svih tipova korisnika
	private void getTipKorisnika() {
		DBconnection conn = DBconnection.getInstance();
		String Query = "SELECT * FROM TIP_KORISNIKA";
		ResultSet rs = conn.getResultSet(Query);

		try {
			while (rs.next()) {
				tipKorisnikaData.add(new Element(rs.getString(2), Integer.valueOf(rs.getString(1))));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// citanje svih poslovnih sistema
	public void getPoslovniSistemi() {

		DBconnection conn = DBconnection.getInstance();
		String Query = "SELECT * FROM POSLOVNI_SISTEM";
		ResultSet rs = conn.getResultSet(Query);

		try {
			while (rs.next()) {
				data.add(new Element(rs.getString(6), Integer.valueOf(rs.getString(1))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return data;
	}

	// citanje svih tipova jedinice
	public void getTipJedinice() {
		DBconnection conn = DBconnection.getInstance();
		String Query = "SELECT * FROM TIP_SMJESTAJNE_JEDINICE";
		ResultSet rs = conn.getResultSet(Query);

		try {
			while (rs.next()) {
				tip.add(new Element(rs.getString(2), Integer.valueOf(rs.getString(1))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// dodavanje SJ u bazu
	public void IzvrsiDodavanjeSmjestajneJedinice() {

		String Query = "{CALL SmjestajnaJedinicaProcedura(?,?,?,?,?,?,?)}";
		String insertCijena = "{CALL DodajCijenu (?,?,?)} ";

		ResultSet rs = ProcedureClass.getInstance().procedura2(insertCijena, Double.valueOf(cijena.getText()),
				Double.valueOf(10.00), Double.valueOf(cijena.getText()) + Double.valueOf(cijena.getText()) * 0.1);

		rs = ProcedureClass.getInstance().procedura2(Query, 0, tip.get(tipSmjestajneJediniceCb.getSelectedIndex()).id,
				1, 1, Integer.valueOf(brojSJ.getText().toString()), napomena.getText(), "Insert");

		rs = ProcedureClass.getInstance().procedura2("{CALL SmjestajneJedinicePS(?)}",
				data.get(poslovniSistemi.getSelectedIndex()).id);

	}

	// kreiranje prozora za dodavanje rez
	public void dodavanjeRezervacije() {

		setSize(new Dimension(600, 750));
		panel.setSize(new Dimension(600, 750));
		this.paintComponents(getGraphics());

		int i = 0;

		getPoslovniSistemi();
		String[] listaPoslovnihSistema = new String[data.size()];
		for (Element element : data) {
			listaPoslovnihSistema[i++] = element.naziv;
		}
		poslovniSistemi = new JComboBox<String>(listaPoslovnihSistema);
		poslovniSistemi.setSelectedIndex(1);
		poslovniSistemi.setSize(new Dimension(150, 20));
		poslovniSistemi.setLocation(new Point(100, 100));
		poslovniSistemi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				podesiModelSoba();
			}
		});

		JLabel Ime = new JLabel("Ime");
		Ime.setFont(new Font("Calibri", Font.PLAIN, 20));
		Ime.setLocation(new Point(100, 130));
		Ime.setSize(new Dimension(700, 30));

		ime = new JTextField();
		ime.setSize(new Dimension(200, 30));
		ime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		ime.setLocation(new Point(100, 160));
		ime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z')) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		JLabel prezimeLbl = new JLabel("Prezime");
		prezimeLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		prezimeLbl.setLocation(new Point(100, 190));
		prezimeLbl.setSize(new Dimension(700, 30));

		prezime = new JTextField();
		prezime.setSize(new Dimension(200, 30));
		prezime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		prezime.setLocation(new Point(100, 220));
		prezime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z')) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		JLabel imeOcaLbl = new JLabel("Ime roditelja");
		imeOcaLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		imeOcaLbl.setLocation(new Point(100, 250));
		imeOcaLbl.setSize(new Dimension(700, 30));

		imeOca = new JTextField();
		imeOca.setSize(new Dimension(200, 30));
		imeOca.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		imeOca.setLocation(new Point(100, 280));
		imeOca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z')) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		JLabel polLbl = new JLabel("Unesite pol fizickog lica");
		polLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		polLbl.setLocation(new Point(100, 310));
		polLbl.setSize(new Dimension(600, 30));
		panel.add(polLbl);

		String[] polString = { "Musko", "Žensko" };
		pol = new JComboBox<String>(polString);
		pol.setSelectedIndex(0);
		pol.setSize(new Dimension(100, 20));
		pol.setLocation(new Point(100, 340));
		panel.add(pol);

		JLabel maticniLbl = new JLabel("Maticni broj");
		maticniLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		maticniLbl.setLocation(new Point(100, 370));
		maticniLbl.setSize(new Dimension(700, 30));

		maticniBroj = new JTextField();
		maticniBroj.setSize(new Dimension(200, 30));
		maticniBroj.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		maticniBroj.setLocation(new Point(100, 400));
		maticniBroj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		JLabel fiksniLbl = new JLabel("Fiksni telefon");
		fiksniLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		fiksniLbl.setLocation(new Point(100, 430));
		fiksniLbl.setSize(new Dimension(700, 30));

		fiksni = new JTextField();
		fiksni.setSize(new Dimension(200, 30));
		fiksni.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		fiksni.setLocation(new Point(100, 460));
		fiksni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		JLabel mobilniLbl = new JLabel("Mobilni telefon");
		mobilniLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		mobilniLbl.setLocation(new Point(100, 490));
		mobilniLbl.setSize(new Dimension(700, 30));

		mobilni = new JTextField();
		mobilni.setSize(new Dimension(200, 30));
		mobilni.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		mobilni.setLocation(new Point(100, 520));
		mobilni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		JLabel emailLbl = new JLabel("Email ");
		emailLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		emailLbl.setLocation(new Point(350, 130));
		emailLbl.setSize(new Dimension(700, 30));

		email = new JTextField();
		email.setSize(new Dimension(200, 30));
		email.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		email.setLocation(new Point(350, 160));

		JLabel wwwLbl = new JLabel("WWW");
		wwwLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		wwwLbl.setLocation(new Point(350, 190));
		wwwLbl.setSize(new Dimension(700, 30));

		www = new JTextField();
		www.setSize(new Dimension(200, 30));
		www.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		www.setLocation(new Point(350, 220));

		JLabel adresaLbl = new JLabel("Adresa ");
		adresaLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		adresaLbl.setLocation(new Point(350, 250));
		adresaLbl.setSize(new Dimension(700, 30));

		adresa = new JTextField();
		adresa.setSize(new Dimension(200, 30));
		adresa.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		adresa.setLocation(new Point(350, 280));

		getTipJedinice();
		String[] tipJedinice = new String[tip.size()];

		JLabel tipLbl = new JLabel("Izaberite tip sobe");
		tipLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		tipLbl.setLocation(new Point(350, 310));
		tipLbl.setSize(new Dimension(700, 30));

		i = 0;
		for (Element el : tip) {
			tipJedinice[i++] = el.naziv;
		}

		tipSmjestajneJediniceCb = new JComboBox<String>(tipJedinice);
		tipSmjestajneJediniceCb.setSelectedIndex(0);
		tipSmjestajneJediniceCb.setSize(new Dimension(150, 20));
		tipSmjestajneJediniceCb.setLocation(new Point(350, 340));
		tipSmjestajneJediniceCb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				podesiModelSoba();
			}
		});

		JLabel sjLbl = new JLabel("Izaberite sobu");
		sjLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		sjLbl.setLocation(new Point(350, 370));
		sjLbl.setSize(new Dimension(700, 30));

		getSmjestajneJedinice();
		ArrayList<String> sjList = new ArrayList<String>();
		i = 0;
		for (Element el : smjestajneJediniceLista) {
			if (tipSmjestajneJediniceCb.getSelectedItem().toString().equals(el.getTipSobe())
					&& poslovniSistemi.getSelectedItem().toString().equals(el.getPs_naziv()))
				sjList.add(el.naziv);
		}

		String[] sj = new String[sjList.size()];
		for (i = 0; i < sjList.size(); i++) {
			sj[i] = sjList.get(i);
		}

		smjestajneJediniceCB = new JComboBox<String>(sj);
		smjestajneJediniceCB.setSelectedIndex(0);
		smjestajneJediniceCB.setSize(new Dimension(150, 20));
		smjestajneJediniceCB.setLocation(new Point(350, 400));

		JLabel napomenaLbl = new JLabel("Unesite napomenu");
		napomenaLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		napomenaLbl.setLocation(new Point(350, 430));
		napomenaLbl.setSize(new Dimension(700, 30));

		napomena = new JTextArea();
		napomena.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		napomena.setSize(new Dimension(200, 60));
		napomena.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		napomena.setLocation(new Point(350, 460));

		confirmButton = new JButton("KREIRAJ REZERVACIJU");
		confirmButton.setAlignmentX(CENTER_ALIGNMENT);
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setLocation(new Point(175, 630));
		confirmButton.setSize(new Dimension(250, 40));
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!provjeri_datum()) {
					if (!provjeri_Polja()) {
						dodajRezervacijuUBazu();
						smjestajneJediniceLista.clear();
						data.clear();
						dispose();
					} else
						JOptionPane.showMessageDialog(panel, "Morate popuniti sva polja!!!");
				} else
					JOptionPane.showMessageDialog(panel, "Smjestaj je zauzet u izabranom periodu!");

			}

		});

		CalendarWindow calendarWindow = new CalendarWindow();

		calendarWindow.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("selectedDate")) {

					java.util.Calendar cal = (java.util.Calendar) evt.getNewValue();
					Date selDate = cal.getTime();
					datumOd.setValue(selDate);
				}
			}
		});

		CalendarWindow calendarWindow1 = new CalendarWindow();

		calendarWindow1.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("selectedDate")) {

					java.util.Calendar cal = (java.util.Calendar) evt.getNewValue();
					Date selDate = cal.getTime();
					datumDo.setValue(selDate);
				}
			}
		});

		datumOd = new JFormattedTextField();
		datumOd.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		datumOd.setSize(new Dimension(150, 40));
		datumOd.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		datumOd.setLocation(new Point(100, 570));
		datumOd.setValue(new Date());

		JButton a = new JButton("D");
		a.setAlignmentX(CENTER_ALIGNMENT);
		a.setMnemonic(KeyEvent.VK_ENTER);
		a.setLocation(new Point(255, 570));
		a.setSize(new Dimension(45, 40));
		a.setUI(new StyledButtonUI());
		a.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calendarWindow.setLocation(datumOd.getLocationOnScreen().x,
						(datumOd.getLocationOnScreen().y - 100 + datumOd.getHeight()));
				Date d = (Date) datumOd.getValue();

				calendarWindow.resetSelection(d);
				calendarWindow.setUndecorated(true);
				calendarWindow.setVisible(true);
			}
		});

		datumDo = new JFormattedTextField();
		datumDo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		datumDo.setSize(new Dimension(150, 40));
		datumDo.setLocation(new Point(350, 570));
		datumDo.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		datumDo.setValue(new Date());

		JButton b = new JButton("D");
		b.setAlignmentX(CENTER_ALIGNMENT);
		b.setLocation(new Point(505, 570));
		b.setMnemonic(KeyEvent.VK_ENTER);
		b.setSize(new Dimension(45, 40));
		b.setUI(new StyledButtonUI());
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calendarWindow1.setLocation(datumDo.getLocationOnScreen().x,
						(datumDo.getLocationOnScreen().y - 100 + datumDo.getHeight()));
				Date d = (Date) datumDo.getValue();

				calendarWindow1.resetSelection(d);
				calendarWindow1.setUndecorated(true);
				calendarWindow1.setVisible(true);
			}
		});

		panel.add(datumDo);
		panel.add(b);
		panel.add(a);
		panel.add(datumOd);
		panel.add(napomenaLbl);
		panel.add(napomena);
		panel.add(confirmButton);
		panel.add(sjLbl);
		panel.add(smjestajneJediniceCB);
		panel.add(tipLbl);
		panel.add(tipSmjestajneJediniceCb);
		panel.add(adresa);
		panel.add(adresaLbl);
		panel.add(www);
		panel.add(wwwLbl);
		panel.add(email);
		panel.add(emailLbl);
		panel.add(maticniLbl);
		panel.add(maticniBroj);
		panel.add(fiksni);
		panel.add(fiksniLbl);
		panel.add(imeOcaLbl);
		panel.add(imeOca);
		panel.add(prezimeLbl);
		panel.add(prezime);
		panel.add(ime);
		panel.add(Ime);
		panel.add(poslovniSistemi);
		panel.add(mobilniLbl);
		panel.add(mobilni);
	}

	// dodavanje rez u bazu
	protected void dodajRezervacijuUBazu() {
		long date = new java.util.Date().getTime();
		java.sql.Date sqlDate = new java.sql.Date(date);

		String procedura = "{call DodajFizickoLice(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		ProcedureClass.getInstance().procedura2(procedura, data.get(poslovniSistemi.getSelectedIndex()).id, 1,
				prezime.getText(), imeOca.getText(), ime.getText(),
				String.valueOf(String.valueOf(pol.getSelectedItem()).charAt(0)), sqlDate, maticniBroj.getText(),
				fiksni.getText(), mobilni.getText(), email.getText(), www.getText(), adresa.getText());

		SimpleDateFormat f = new SimpleDateFormat("MMM ddd, yyyy");
		Date d = null;
		try {
			d = f.parse(datumOd.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate1 = new java.sql.Date(d.getTime());

		try {
			d = f.parse(datumDo.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate2 = new java.sql.Date(d.getTime());

		System.out.println(poslovniSistemi.getSelectedIndex());
		System.out.println(data.get(poslovniSistemi.getSelectedIndex()).id);
		procedura = "{call DodajRezervaciju(?,?,?,?,?,?,?)}";
		ProcedureClass.getInstance().procedura2(procedura, 1, Korisnik.getInstance().getID(),
				data.get(poslovniSistemi.getSelectedIndex()).id, napomena.getText(), sqlDate1, sqlDate2, null);

		int id = 0;
		procedura = "{call DodajRezervacijuSJ(?)}";
		for (Element element : smjestajneJediniceLista) {
			if (element.naziv.equals(smjestajneJediniceCB.getSelectedItem())
					&& element.getPs_naziv().equals(poslovniSistemi.getSelectedItem()))
				id = element.getId();
		}

		ProcedureClass.getInstance().procedura2(procedura, id);

	}

	// sve smjestajne jedinice
	public void getSmjestajneJedinice() {
		ResultSet rs = ProcedureClass.getInstance().procedura2("{call VratiSveSmjestajneJedinice}");

		try {
			while (rs.next()) {
				smjestajneJediniceLista.add(new Element("Broj : " + rs.getString(1), Integer.valueOf(rs.getString(7))));
				smjestajneJediniceLista.get(smjestajneJediniceLista.size() - 1).setTipSobe(rs.getString(4));
				smjestajneJediniceLista.get(smjestajneJediniceLista.size() - 1).setPs_naziv(rs.getString(2));
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void podesiModelSoba() {
		ArrayList<String> sjList = new ArrayList<String>();
		int i = 0;
		for (Element el : smjestajneJediniceLista) {
			if (tipSmjestajneJediniceCb.getSelectedItem().toString().equals(el.getTipSobe())
					&& poslovniSistemi.getSelectedItem().toString().equals(el.getPs_naziv()))
				sjList.add(el.naziv);
		}

		String[] sj = new String[sjList.size()];
		for (i = 0; i < sjList.size(); i++) {
			sj[i] = sjList.get(i);
		}

		DefaultComboBoxModel model = new DefaultComboBoxModel(sj);

		smjestajneJediniceCB.setModel(model);
	}

	// validacija polja
	public Boolean provjeri_Polja() {
		Boolean neispravno = false;
		for (Component component : panel.getComponents()) {
			if (component instanceof JTextField)
				if (((JTextField) component).getText().isEmpty())
					neispravno = true;
		}
		return neispravno;
	}

	// validacija datuma
	public Boolean provjeri_datum() {
		Boolean odgovor = true;
		int id = 0;
		for (Element element : smjestajneJediniceLista) {
			if (element.naziv.equals(smjestajneJediniceCB.getSelectedItem())
					&& element.ps_naziv.equals(poslovniSistemi.getSelectedItem()))
				id = element.getId();
		}

		String procedura = "{call vratiRezervacijePoID(?)}";
		ResultSet rs = ProcedureClass.getInstance().procedura2(procedura, id);

		ArrayList<Integer> rezervacije = new ArrayList<Integer>();
		try {
			while (rs.next()) {
				try {
					rezervacije.add(rs.getInt(1));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (Integer integer : rezervacije) {
			rs = ProcedureClass.procedura2("{call vratiDatumeRezervacija(?)}", integer);
			ArrayList<String> datumod = new ArrayList<String>();
			ArrayList<String> datumdo = new ArrayList<String>();

			try {
				while (rs.next()) {
					try {
						datumod.add(rs.getString(1));
						datumdo.add(rs.getString(2));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("MMM ddd, yyyy");
			Date rezOd = null;
			Date rezDo = null;
			try {
				rezOd = sdf.parse(datumOd.getText());
				rezDo = sdf.parse(datumDo.getText());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < datumod.size(); i++) {

				Date date1 = null;
				Date date2 = null;
				try {
					date1 = sdf.parse(datumod.get(i));
					date2 = sdf.parse(datumdo.get(i));
					if ((rezOd.compareTo(date1) < 0 && rezDo.compareTo(date1) < 0)
							|| (rezOd.compareTo(date2) > 0 && rezDo.compareTo(date2) > 0)) {
						odgovor = false;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (rezervacije.size() == 0)
			return false;
		else
			return odgovor;
	}

	// provjera username
	public Boolean provjeriUsername() {
		String procedura = "{call getAllUsernames}";
		ResultSet rs = ProcedureClass.procedura2(procedura);
		ArrayList<String> usernames = new ArrayList<String>();

		try {
			while (rs.next()) {
				try {
					usernames.add(rs.getString(1));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (this.username.getText().equals(defaultUsername))
			return false;

		for (String string : usernames) {
			if (username.getText().equals(string))
				return true;

		}
		return false;

	}

	// provjera broja SJ
	public Boolean provjeriBrojSmjestajneJedinice() {
		String procedura = "{call VratiSveSmjestajneJedinice}";
		ResultSet rs = ProcedureClass.procedura2(procedura);
		ArrayList<model.Element> smjestajneJedinice = new ArrayList<model.Element>();

		try {
			while (rs.next()) {
				try {
					smjestajneJedinice.add(new Element(rs.getString(2), Integer.valueOf(rs.getString(1))));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (this.mode.equals("edit")) {
			if (this.defautBr == Integer.valueOf(this.brojSJ.getText()))
				return false;
		}

		for (Element element : smjestajneJedinice) {
			if (element.getId() == Integer.valueOf(brojSJ.getText())
					&& element.getNaziv().equals(poslovniSistemi.getSelectedItem()))
				return true;
		}

		return false;
	}

	// kreiranje prozora za dodavanje poslovnog sistema
	public void dodajPoslovniSistem() {
		JLabel drzavaLbl = new JLabel("Država ");
		drzavaLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		drzavaLbl.setLocation(new Point(100, 70));
		drzavaLbl.setSize(new Dimension(600, 30));
		panel.add(drzavaLbl);

		int i = 0;

		getDrzave();
		String[] drzaveLista = new String[drzave.size()];
		for (Element element : drzave) {
			drzaveLista[i++] = element.naziv;
		}

		drzaveComboBox = new JComboBox<String>(drzaveLista);
		drzaveComboBox.setSelectedIndex(0);
		drzaveComboBox.setSize(new Dimension(150, 20));
		drzaveComboBox.setLocation(new Point(100, 100));
		drzaveComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				podesiModelGradova();
			}
		});
		panel.add(drzaveComboBox);

		JLabel imeLbl = new JLabel("Naziv poslovnog sistema");
		imeLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		imeLbl.setLocation(new Point(100, 130));
		imeLbl.setSize(new Dimension(600, 30));
		panel.add(imeLbl);

		ime = new JTextField();
		ime.setSize(new Dimension(200, 30));
		ime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		ime.setLocation(new Point(100, 160));
		panel.add(ime);

		JLabel adresaLbl = new JLabel("Adresa");
		adresaLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		adresaLbl.setLocation(new Point(100, 190));
		adresaLbl.setSize(new Dimension(600, 30));
		panel.add(adresaLbl);

		adresa = new JTextField();
		adresa.setSize(new Dimension(200, 30));
		adresa.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		adresa.setLocation(new Point(100, 220));
		panel.add(adresa);

		JLabel emailLbl = new JLabel("Email");
		emailLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		emailLbl.setLocation(new Point(100, 250));
		emailLbl.setSize(new Dimension(600, 30));
		panel.add(emailLbl);

		email = new JTextField();
		email.setSize(new Dimension(200, 30));
		email.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		email.setLocation(new Point(100, 280));
		panel.add(email);

		JLabel wwwLbl = new JLabel("Web site");
		wwwLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		wwwLbl.setLocation(new Point(100, 310));
		wwwLbl.setSize(new Dimension(600, 30));
		panel.add(wwwLbl);

		www = new JTextField();
		www.setSize(new Dimension(200, 30));
		www.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		www.setLocation(new Point(100, 340));
		panel.add(www);

		JLabel regBrLbl = new JLabel("Registracioni broj");
		regBrLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		regBrLbl.setLocation(new Point(100, 370));
		regBrLbl.setSize(new Dimension(600, 30));
		panel.add(regBrLbl);

		regBr = new JTextField();
		regBr.setSize(new Dimension(200, 30));
		regBr.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		regBr.setLocation(new Point(100, 400));
		regBr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel.add(regBr);

		JLabel pibLbl = new JLabel("PIB");
		pibLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		pibLbl.setLocation(new Point(100, 430));
		pibLbl.setSize(new Dimension(600, 30));
		panel.add(pibLbl);

		pib = new JTextField();
		pib.setSize(new Dimension(200, 30));
		pib.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		pib.setLocation(new Point(100, 470));
		pib.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel.add(pib);

		JLabel gradLbl = new JLabel("Grad ");
		gradLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		gradLbl.setLocation(new Point(250, 70));
		gradLbl.setSize(new Dimension(600, 30));
		panel.add(gradLbl);

		i = 0;
		getGradovi();
		String[] gradoviLista = new String[gradovi.size()];
		for (Element element : gradovi) {
			gradoviLista[i++] = element.naziv;
		}

		gradoviComboBox = new JComboBox<String>(gradoviLista);
		gradoviComboBox.setSelectedIndex(1);
		gradoviComboBox.setSize(new Dimension(150, 20));
		gradoviComboBox.setLocation(new Point(300, 100));
		gradoviComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(gradoviComboBox);

		confirmButton = new JButton("DODAJ");
		confirmButton.setSize(new Dimension(200, 30));
		confirmButton.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		confirmButton.setLocation(new Point(200, 510));
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!provjeri_Polja()) {
					if (mode.contentEquals("edit")) {
						updatePoslovniSistem();
					} else
						dodajPoslovniSistemUBazu();
					dispose();
				} else
					JOptionPane.showMessageDialog(panel, "Morate popuniti sva polja!!!");
			}
		});
		panel.add(confirmButton);

	}

	// sve drzave
	public void getDrzave() {
		ResultSet rs = ProcedureClass.procedura2("{call vratiSveDrzave()}");

		try {
			while (rs.next()) {
				drzave.add(new Element(rs.getString(4), 0));
				drzave.get(drzave.size() - 1).setPs_naziv(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// gradovi drzave
	public void podesiModelGradova() {
		ArrayList<String> grad = new ArrayList<String>();
		int i = 0;
		for (Element el : gradovi) {
			if (drzave.get(drzaveComboBox.getSelectedIndex()).getPs_naziv().equals(el.ps_naziv))
				grad.add(el.naziv);
		}
		String[] gradovi = new String[grad.size()];
		for (String string : grad) {
			gradovi[i++] = string;
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(gradovi);

		gradoviComboBox.setModel(model);
	}

	// svi gradovi
	public void getGradovi() {
		ResultSet rs = ProcedureClass.procedura2("{call vratiSveGradoveDrzave()}");

		try {
			while (rs.next()) {
				try {
					gradovi.add(new Element(rs.getString(3), Integer.valueOf(rs.getInt(1))));
					gradovi.get(gradovi.size() - 1).setPs_naziv(rs.getString(2));
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

	// dodavnje PS u bazu
	public void dodajPoslovniSistemUBazu() {
		ProcedureClass.procedura2("{call KreirajKarticu()}");
		long date = new java.util.Date().getTime();
		java.sql.Date sqlDate = new java.sql.Date(date);

		int grad_id = 0;
		for (Element element : gradovi) {
			if (element.getNaziv().equals(gradoviComboBox.getSelectedItem())) {
				grad_id = element.id;
				break;
			}
		}
		ProcedureClass.procedura2("{call DodajPoslovniSistem(?,?,?,?,?,?,?,?,?)}",
				drzave.get(drzaveComboBox.getSelectedIndex()).getPs_naziv(), grad_id, ime.getText(), adresa.getText(),
				email.getText(), www.getText(), regBr.getText(), pib.getText(), sqlDate);
	}

	// edit PS
	public void podesiVrijednostiPoslovnogSistema(Integer id) {
		this.mode = "edit";
		this.editId = id;
		ResultSet rs = ProcedureClass.getInstance().procedura2("{call VratiPoslovniSistemPoID(?)}", id);

		try {
			while (rs.next()) {
				try {
					this.ime.setText(rs.getString(6));
					this.adresa.setText(rs.getString(7));
					this.email.setText(rs.getString(8));
					this.www.setText(rs.getString(9));
					this.regBr.setText(rs.getString(10));
					this.pib.setText(rs.getString(11));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.confirmButton.setText("POTVRDI IZMJENE");

	}

	// update ps u bazu
	protected void updatePoslovniSistem() {

		int grad_id = 0;
		for (Element element : gradovi) {
			if (element.getNaziv().equals(gradoviComboBox.getSelectedItem())) {
				grad_id = element.id;
				break;
			}
		}
		ProcedureClass.procedura2("{call UpdatePoslovniSistem(?,?,?,?,?,?,?,?,?)}", editId,
				drzave.get(drzaveComboBox.getSelectedIndex()).getPs_naziv(), grad_id, ime.getText(), adresa.getText(),
				email.getText(), www.getText(), regBr.getText(), pib.getText());
	}

	// edit SJ
	public void podesiVrijednostiSmjestajneJedinice(Integer id) {
		this.mode = "edit";
		this.editId = id;
		ResultSet rs = ProcedureClass.getInstance().procedura2("{call VratiSmjestajnuJedinicuPoID(?)}", id);

		try {
			while (rs.next()) {
				try {
					this.defautBr = (Integer.valueOf(rs.getString(1)));
					this.cijena.setText(rs.getString(2));
					this.napomena.setText(rs.getString(3));
					this.brojSJ.setText(rs.getString(1));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.poslovniSistemi.disable();
		this.confirmButton.setText("POTVRDI IZMJENE");

	}

	// update sj u bazu
	protected void updateSmjestajnaJedinica() {

		ProcedureClass.procedura2("{call UpdateSmjestajnaJedinica(?,?,?,?,?) }", editId,
				Integer.valueOf(brojSJ.getText()), napomena.getText(), Integer.valueOf(cijena.getText()),
				tip.get(tipSmjestajneJediniceCb.getSelectedIndex()).id);
	}

	// edit User
	public void podesiVrijednostiKorisnika(Integer id) {
		this.mode = "edit";
		this.editId = id;
		ResultSet rs = ProcedureClass.getInstance().procedura2("{call VratiKorisnikaPoID(?)}", id);

		try {
			while (rs.next()) {
				try {
					this.defaultUsername = (rs.getString(1));
					this.username.setText(rs.getString(1));
					this.ime.setText(rs.getString(2));
					this.prezime.setText(rs.getString(4));
					this.imeOca.setText(rs.getString(3));
					this.maticniBroj.setText(rs.getString(5));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.confirmButton.setText("POTVRDI IZMJENE");
		this.poslovniSistemi.disable();
		this.tipKorisnikaCb.disable();
	}

	// updateUser
	public void updateUser() {

		ProcedureClass.procedura2("{call UpdateZaposleni(?,?,?,?,?,?,?) }", editId, this.username.getText(),
				this.password.getText(), this.ime.getText(), this.prezime.getText(), this.imeOca.getText(),
				this.maticniBroj.getText());
	}

}
