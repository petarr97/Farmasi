package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Procedure.ProcedureClass;
import dbpackage.DBconnection;
import model.Element;
import model.Korisnik;

public class Izmjena extends JFrame {

	JPanel panel = null;
	JButton potvrdiIzmjene = null;
	ArrayList<Element> tipPodataka = new ArrayList<Element>();
	JComboBox<String> comboBox = null;
	ArrayList<String> zap_Uloga = new ArrayList<String>();
	ArrayList<String> tipUloge = new ArrayList<String>();
	JLabel izmjena = null;
	JLabel ulogaLbl = null;
	JComboBox<String> tipKorisnikaCb = null;
	public StatusBar info;

	public Izmjena(StatusBar info) {
		setUndecorated(true);
		setLocation(new Point(100, 100));
		setSize(new Dimension(350, 350));
		setBackground(Color.blue);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.info = info;

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		panel.setLocation(new Point(0, 0));
		panel.setSize(new Dimension(350, 350));
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

		postaviKorisnike();
		String[] korisnici = new String[tipPodataka.size()];
		int i = 0;
		for (Element el : tipPodataka) {
			korisnici[i++] = i + " - " + el.naziv;
		}

		comboBox = new JComboBox<String>(korisnici);
		comboBox.setSelectedIndex(0);
		comboBox.setSize(new Dimension(300, 30));
		comboBox.setLocation(new Point(15, 70));
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ulogaLbl.setText("Trenutna uloga: " + zap_Uloga.get(comboBox.getSelectedIndex()));

				DefaultComboBoxModel model = new DefaultComboBoxModel(postaviUloge());
				tipKorisnikaCb.setModel(model);
			}
		});

		ulogaLbl = new JLabel("Trenutna uloga: " + zap_Uloga.get(comboBox.getSelectedIndex()));
		ulogaLbl.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		ulogaLbl.setLocation(new Point(15, 30));
		ulogaLbl.setSize(new Dimension(300, 50));

		potvrdiIzmjene = new JButton("POTVRDI IZMJENE");
		potvrdiIzmjene.setAlignmentX(CENTER_ALIGNMENT);
		potvrdiIzmjene.setMnemonic(KeyEvent.VK_ENTER);
		potvrdiIzmjene.setLocation(new Point(60, 250));
		potvrdiIzmjene.setSize(new Dimension(180, 40));
		potvrdiIzmjene.setUI(new StyledButtonUI());
		potvrdiIzmjene.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				potvrdiIzmjenu();
				tipPodataka.clear();
				zap_Uloga.clear();
				tipUloge.clear();
				dispose();
			}
		});

		JLabel tip = new JLabel("Izaberite novu ulogu korisnika ");
		tip.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		tip.setLocation(new Point(15, 120));
		tip.setSize(new Dimension(300, 50));

		tipKorisnikaCb = new JComboBox<String>(postaviUloge());
		tipKorisnikaCb.setSelectedIndex(0);
		tipKorisnikaCb.setSize(new Dimension(300, 30));
		tipKorisnikaCb.setLocation(new Point(15, 170));

		panel.add(tip);
		panel.add(tipKorisnikaCb);
		panel.add(potvrdiIzmjene);
		panel.add(closeButton);
		panel.add(ulogaLbl);
		panel.add(comboBox);

		add(panel);
	}

	private String[] postaviUloge() {
		int i = 0;
		String[] uloga = new String[2];
		for (String string : tipUloge) {
			if (!string.equals(zap_Uloga.get(comboBox.getSelectedIndex()).toString())) {
				uloga[i++] = string;
			}
		}
		return uloga;
	}

	private void postaviKorisnike() {
		ResultSet rs = ProcedureClass.getInstance().procedura2("{call VratiKorisnikeSistema}");
		try {
			while (rs.next()) {
				try {
					String naziv = rs.getString(3) + " " + rs.getString(4);
					tipPodataka.add(new Element(naziv, Integer.valueOf(rs.getString(1))));
					zap_Uloga.add(rs.getString(2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query = "SELECT * FROM TIP_KORISNIKA";
		rs = new DBconnection().getResultSet(query);
		try {
			while (rs.next()) {
				try {
					tipUloge.add(rs.getString(2));
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

	private void potvrdiIzmjenu() {
		String procedura = "{call UpdateKorisnik(?,?)}";
		int id = tipPodataka.get(comboBox.getSelectedIndex()).id;
		int tip = 0;
		int i = 0;

		for (String element : tipUloge) {
			if (element == tipKorisnikaCb.getSelectedItem())
				tip = i + 1;
			i++;
		}

		ProcedureClass.getInstance().procedura2(procedura, id, tip);
		if (id == Korisnik.getInstance().getID())
			updateKorisnik();

	}

	public void updateKorisnik() {
		ResultSet rs = ProcedureClass.getInstance().procedura2("{call Login(?,?)}",
				Korisnik.getInstance().getUsername(), String.valueOf(Korisnik.getInstance().getPassword()));

		try {
			while (rs.next()) {
				if (rs.getString(1) != null) {
					Korisnik korisnik = Korisnik.getInstance();
					korisnik.setPrikaz(rs.getInt(2));
					korisnik.setDodavanje(rs.getInt(3));
					korisnik.setBrisanje(rs.getInt(4));
					korisnik.setIzmjena(rs.getInt(5));
					korisnik.setTipKorisnika(rs.getInt(6));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int tip_id = Korisnik.getInstance().getTipKorisnika();
		String tip = null;
		if (tip_id == 1)
			tip = "Administrator";
		else if (tip_id == 2)
			tip = "Menadzer";
		else if (tip_id == 3)
			tip = "Radnik";
		this.info.label1.setText("Tip korisnika: " + tip);

	}
}
