/***********************************************************************
 * Module:  LogInView.java
 * Author:  Petar
 * Purpose: Defines the Class LogInView
 ***********************************************************************/

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Procedure.ProcedureClass;
import model.Korisnik;

public class LogInView extends JFrame {

	private Statement statement;
	public JTextField username;
	public JPasswordField password;
	public JButton confirmButton;
	public JButton registration;

	public LogInView() {
		setLayout(null);
		JPanel panel = new JPanel();

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		panel.setLayout(null);
		panel.setLocation(new Point(0, 0));
		panel.setSize(new Dimension(600, 500));
		panel.setBackground(Color.decode("#98B4D4"));

		JButton closeButton = new JButton("X");
		closeButton.setFont(new Font("Calibri", Font.BOLD, 20));
		closeButton.setSize(new Dimension(50, 50));
		closeButton.setLocation(new Point(330, 0));
		closeButton.setOpaque(false);
		closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel usernameLbl = new JLabel("Korisnicko ime");
		usernameLbl.setFont(new Font("Calibri", Font.BOLD, 20));
		usernameLbl.setLocation(new Point(100, 70));
		usernameLbl.setSize(new Dimension(200, 30));
		usernameLbl.setForeground(Color.white);
		panel.add(usernameLbl);

		username = new JTextField();
		username.setLocation(new Point(100, 100));
		username.setSize(new Dimension(200, 30));
		username.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(username);

		JLabel passwordLbl = new JLabel("Lozinka");
		passwordLbl.setFont(new Font("Calibri", Font.BOLD, 20));
		passwordLbl.setLocation(new Point(100, 140));
		passwordLbl.setSize(new Dimension(200, 30));
		passwordLbl.setForeground(Color.white);
		panel.add(passwordLbl);

		password = new JPasswordField();
		password.setLocation(new Point(100, 170));
		password.setSize(new Dimension(200, 30));
		password.setEchoChar('*');
		password.setFont(new Font("Calibri", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		panel.add(password);

		confirmButton = new JButton("ULOGUJ SE");
		confirmButton.setMnemonic(KeyEvent.VK_ENTER);
		confirmButton.setLocation(new Point(110, 230));
		confirmButton.setSize(new Dimension(180, 40));
		confirmButton.setUI(new StyledButtonUI());
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				provjeriPodatke();
			}
		});
		panel.add(confirmButton);

		registration = new JButton("REGISTRUJ SE");
		registration.setMnemonic(KeyEvent.VK_ENTER);
		registration.setLocation(new Point(110, 280));
		registration.setSize(new Dimension(180, 10));
		registration.setForeground(Color.black);
		registration.setContentAreaFilled(false);
		registration.setBorderPainted(false);
		registration.setOpaque(false);

		panel.add(registration);

		panel.add(closeButton);

	}

	public void provjeriPodatke() {
		ResultSet rs = ProcedureClass.getInstance().procedura2("{call LOGIN(?,?)}", username.getText(),
				String.valueOf(password.getPassword()));

		try {
			while (rs.next()) {
				if (rs.getString(1) != null && (username.getText().equals(rs.getString(1)))
						&& String.valueOf(password.getPassword()).equals(rs.getString(2))) {
					Korisnik korisnik = Korisnik.getInstance();
					korisnik.setUsername(username.getText());
					korisnik.setPassword(String.valueOf(password.getPassword()));

					dispose();
					new ApplicationView().show();
					return;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "Pogresno korisnicko ime ili lozinka!!!");

	}
}