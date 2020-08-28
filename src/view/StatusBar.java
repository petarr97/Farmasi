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
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Korisnik;

public class StatusBar extends JPanel {
	public JLabel label1;
	public JLabel stanje;
	public JLabel rows;
	public JLabel vrijeme;

	public StatusBar() {
		setLayout(null);
		setPreferredSize(new Dimension(0, 40));

	}

	public void postaviPodatke(String username) {

		String tip_korisnika;
		int tip = Korisnik.getInstance().tipKorisnika;
		if (tip == 1)
			tip_korisnika = "Administrator";
		else if (tip == 2)
			tip_korisnika = "Menadzer";
		else
			tip_korisnika = "Radnik";

		JLabel label = new JLabel("Ulogovani ste kao: " + username);
		label.setLocation(5, 0);
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		label.setSize(new Dimension(300, 20));
		label.setForeground(Color.white);
		add(label);

		label1 = new JLabel("Tip korisnika: " + tip_korisnika);
		label1.setLocation(5, 20);
		label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		label1.setSize(new Dimension(300, 20));
		label1.setForeground(Color.white);
		add(label1);

		stanje = new JLabel();
		stanje.setLocation(300, 20);
		stanje.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		stanje.setSize(new Dimension(300, 20));
		stanje.setForeground(Color.white);
		add(stanje);

		rows = new JLabel();
		rows.setLocation(300, 0);
		rows.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		rows.setSize(new Dimension(250, 20));
		rows.setForeground(Color.white);
		add(rows);

		time();

		vrijeme = new JLabel();
		vrijeme.setLocation(550, 0);
		vrijeme.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		vrijeme.setSize(new Dimension(300, 20));
		vrijeme.setForeground(Color.white);
		add(vrijeme);

		JButton odjava = new JButton("Odjavi se");
		odjava.setSize(new Dimension(130, 30));
		odjava.setLocation(new Point((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 150), 5));
		odjava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationView view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());
				Korisnik.getInstance().username = null;
				Korisnik.getInstance().password = null;
				new LogInView().show();
				view.dispose();
			}
		});
		odjava.setBackground(Color.decode("#ff4d4d"));
		odjava.setUI(new StyledButtonUI());
		add(odjava);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image;
		try {
			image = ImageIO.read(new File("./img/userBar.jpg"));
			g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void time() {
		Thread timer = new Thread() {
			@Override
			public void run() {
				while (true) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					vrijeme.setText(formatter.format(new Date()).toString());
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		};
		timer.start();
	}

}
