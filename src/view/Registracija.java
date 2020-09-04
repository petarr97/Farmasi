package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Procedure.ProcedureClass;

public class Registracija extends JFrame {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	private Statement statement;
	public JTextField username;
	public JTextField password;
	public JButton confirmButton;
	public JButton login;
	public JTextField ime;
	public JTextField prezime;
	public JTextField email;

	JPanel panel = null;

	public Registracija() {

		setLayout(null);
		panel = new JPanel();

		setUndecorated(true);
		setLocation(new Point((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 225.), 100));
		setSize(new Dimension(400, 350));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image;
				try {
					image = ImageIO.read(new File("./img/login.jpg"));
					g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(Box.createVerticalStrut(15));

		JLabel l = new JLabel("Ime");
		l.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(l);

		ime = new JTextField();
		ime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		ime.setPreferredSize(new Dimension(0, 30));
		ime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'A') && (c <= 'Z') || (c >= 'a') && (c <= 'z') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel.add(ime);

		JLabel l1 = new JLabel("Prezime");
		l1.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(l1);

		prezime = new JTextField();
		prezime.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		prezime.setPreferredSize(new Dimension(0, 30));
		prezime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'A') && (c <= 'Z') || (c >= 'a') && (c <= 'z') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		panel.add(prezime);

		JLabel emailLbl = new JLabel("Email");
		emailLbl.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel.add(emailLbl);

		email = new JTextField();
		email.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		email.setPreferredSize(new Dimension(0, 30));
		panel.add(email);

		JLabel usernameLbl = new JLabel("Korisnicko ime");
		usernameLbl.setFont(new Font("Calibri", Font.BOLD, 20));
		usernameLbl.setForeground(Color.white);
		panel.add(usernameLbl);

		username = new JTextField();
		username.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(username);

		JLabel passwordLbl = new JLabel("Lozinka");
		passwordLbl.setFont(new Font("Calibri", Font.BOLD, 20));
		passwordLbl.setForeground(Color.white);
		panel.add(passwordLbl);

		password = new JTextField();
		password.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(password);

		panel.add(Box.createVerticalStrut(15));

		confirmButton = new JButton("REGISTRUJ SE");
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!provjeriPolja()) {
					if (validate(email.getText())) {
						if (provjeriKorisnike()) {
							dodajKorisnika();
							dispose();
							new LogInView().show();
						} else
							JOptionPane.showMessageDialog(null,
									"Korisnicko ime " + username.getText() + " vec postoji!!!");
					} else
						JOptionPane.showMessageDialog(null, "Unesite odgovarajuci format email-a!!!");

				} else
					JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena!!!");
			}
		});
		panel.add(confirmButton);

		this.getRootPane().setDefaultButton(confirmButton);

		login = new JButton("ULOGUJ SE");
		login.setMaximumSize(new Dimension(180, 10));
		login.setForeground(Color.black);
		login.setContentAreaFilled(false);
		login.setBorderPainted(false);
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LogInView().show();
			}
		});
		login.setOpaque(false);

		panel.add(login);

		int i = 0;
		for (Component c : panel.getComponents()) {
			i++;
			c.setMaximumSize(new Dimension(200, 30));
			((JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);
			if (c instanceof JLabel) {
				c.setForeground(Color.white);
			}
		}

		setSize(new Dimension(400, i * 35));
		panel.setSize(new Dimension(400, i * 35));

	}

	protected Boolean provjeriKorisnike() {

		ResultSet rs = ProcedureClass.getInstance().procedura2("{call PROVJERI_KORISNIKA(?)}", username.getText());

		try {
			while (rs.next()) {
				try {
					if (rs.getString(1) != null)
						return false;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}

	protected void dodajKorisnika() {
		ProcedureClass proc = ProcedureClass.getInstance();
		proc.procedura2("{ call INSERT_KORISNIK(?,?,?,?,?) }", username.getText(), password.getText(), ime.getText(),
				prezime.getText(), email.getText());
	}

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public Boolean provjeriPolja() {
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField)
				if (((JTextField) c).getText().isEmpty())
					return true;
		}
		return false;
	}

}
