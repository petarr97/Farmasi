/***********************************************************************
 * Module:  TableBrowserView.java
 * Author:  Petar
 * Purpose: Defines the Class TableBrowserView
 ***********************************************************************/

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controler.FinansijeControler;
import controler.PoslovniSistemiControler;
import controler.ReportsControler;
import controler.RezervacijeControler;
import controler.SmjestajnaJedinicaControler;
import controler.ZaposleniControler;
import model.TableBrowserModel;

public class TableBrowserView extends JPanel {
	public TableBrowserModel tableBrowserModel;
	public JButton[] buttons;

	public TableBrowserView() {
		setPreferredSize(new Dimension(250, 0));
		setBackground(Color.decode("#303030"));
		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(240, 240, 240));
		setBorder(border);

		JPanel details = new JPanel();
		details.setOpaque(false);
		details.setLocation(new Point(0, 0));
		details.setPreferredSize(new Dimension(0, 0));
		details.setBackground(Color.decode("#303030"));
		Border border1 = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(240, 240, 240));
		details.setBorder(border1);

		buttons = addButtons();
		int brojac = 0;

		for (JButton btn : buttons) {

			btn.setPreferredSize(new Dimension(230, 30));
			btn.addActionListener(dodavanjeActionListenera(details)[brojac++]);
			btn.setBackground(Color.decode("#66b3ff"));
			btn.setUI(new StyledButtonUI());
			add(btn);
		}
		add(details);

	}

	public JButton[] addButtons() {
		JButton[] buttons = new JButton[6];

		buttons[0] = new JButton("Rad sa poslovnim sistemima");
		buttons[1] = new JButton("Rad sa smjestajnim jedinicama");
		buttons[2] = new JButton("Rad sa rezervacijama");
		buttons[3] = new JButton("Rad sa osobljem");
		buttons[4] = new JButton("Rad sa finansijama");
		buttons[5] = new JButton("Izvještaji");

		return buttons;
	}

	public JButton[] otvoriRadSaPoslovnimSistemima() {
		JButton[] buttons = new JButton[2];
		buttons[0] = new JButton("Prikaz poslovnih sistema");
		buttons[0].addActionListener(new PoslovniSistemiControler());
		buttons[0].setActionCommand("prikaz");
		buttons[1] = new JButton("Dodavanje poslovnog sistema");
		buttons[1].addActionListener(new PoslovniSistemiControler());
		buttons[1].setActionCommand("dodavanje");

		return buttons;
	}

	public JButton[] otvoriRadSaSmjestajnimJedinicama() {
		JButton[] buttons = new JButton[3];
		buttons[0] = new JButton("Prikaz smjestajnih jedinica");
		buttons[0].addActionListener(new SmjestajnaJedinicaControler());
		buttons[0].setActionCommand("prikaz");
		buttons[1] = new JButton("Dodavanje smjestajne jedinice");
		buttons[1].addActionListener(new SmjestajnaJedinicaControler());
		buttons[1].setActionCommand("dodavanje");
		buttons[2] = new JButton("Brisanje smjestajne jedinice");
		buttons[2].addActionListener(new SmjestajnaJedinicaControler());
		buttons[2].setActionCommand("brisanje");

		return buttons;
	}

	public JButton[] otvoriRadSaRezervacijama() {
		JButton[] buttons = new JButton[3];

		buttons[0] = new JButton("Prikaz rezervacija");
		buttons[0].setActionCommand("prikaz");
		buttons[0].addActionListener(new RezervacijeControler());

		buttons[1] = new JButton("Dodavanje rezervacije");
		buttons[1].setActionCommand("dodavanje");
		buttons[1].addActionListener(new RezervacijeControler());

		buttons[2] = new JButton("Otkazivanje rezervacije");
		buttons[2].setActionCommand("otkazivanje");
		buttons[2].addActionListener(new RezervacijeControler());

		return buttons;
	}

	public JButton[] otvoriRadSaOsobljem() {
		JButton[] buttons = new JButton[4];

		buttons[0] = new JButton("Prikaz osoblja");
		buttons[0].setActionCommand("prikaz");
		buttons[0].addActionListener(new ZaposleniControler());

		buttons[1] = new JButton("Dodavanje zaposlenog");
		buttons[1].setActionCommand("dodavanje");
		buttons[1].addActionListener(new ZaposleniControler());

		buttons[2] = new JButton("Brisanje zaposlenog");
		buttons[2].setActionCommand("brisanje");
		buttons[2].addActionListener(new ZaposleniControler());

		buttons[3] = new JButton("Promjena privilegija zaposlenog");
		buttons[3].setActionCommand("izmjena");
		buttons[3].addActionListener(new ZaposleniControler());

		return buttons;
	}

	public JButton[] otvoriRadSaIzvjestajima() {
		JButton[] buttons = new JButton[3];
		buttons[0] = new JButton("Finansijski izvjestaj");
		buttons[0].setActionCommand("finansije");
		buttons[0].addActionListener(new ReportsControler());
		buttons[1] = new JButton("Izvjestaj za rezervacije");
		buttons[1].setActionCommand("rezervacije");
		buttons[1].addActionListener(new ReportsControler());
		buttons[2] = new JButton("Brisanje izvjetaja");
		buttons[2].setActionCommand("poslovni_sistemi");
		buttons[2].addActionListener(new ReportsControler());

		return buttons;
	}

	public JButton[] otvoriRadSaFinansijama() {
		JButton[] buttons = new JButton[1];
		buttons[0] = new JButton("Prikaz stanja ");
		buttons[0].addActionListener(new FinansijeControler());
		return buttons;
	}

	public ActionListener[] dodavanjeActionListenera(JPanel details) {
		ActionListener[] al = new ActionListener[6];

		al[0] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaPoslovnimSistemima();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();

			}
		};

		al[1] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaSmjestajnimJedinicama();
				details.removeAll();
				details.revalidate();
				details.repaint();

				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();
			}
		};
		al[2] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaRezervacijama();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);

				}
				details.revalidate();

			}
		};
		al[3] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaOsobljem();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 30));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();

			}
		};
		al[4] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaFinansijama();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();

			}
		};
		al[5] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaIzvjestajima();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();

			}
		};

		return al;
	}

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

}