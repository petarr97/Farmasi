/***********************************************************************
 * Module:  ToolbarView.java
 * Author:  Petar
 * Purpose: Defines the Class ToolbarView
 ***********************************************************************/

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controler.ToolbarControler;

public class ToolbarView extends JPanel {

	JButton add = null;
	JButton edit = null;
	JButton delete = null;
	JButton prev = null;
	JButton next = null;
	public JTextField filter = null;
	JButton confirm = null;
	public String filterText = "";
	public JTextField filter1 = null;
	JButton confirm1 = null;

	public ToolbarView() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLocation(new Point(40, 0));
		setPreferredSize(new Dimension(0, 40));
		setBackground(Color.decode("#d9dedd"));
		setForeground(Color.black);

	}

	public void dodajListenere() {
		for (Component component : getComponents()) {
			if (component instanceof JButton)
				((JButton) component).addActionListener(new ToolbarControler(this));
		}
	}

	// postavljanje filtera za narudzbe
	public void postaviFilterZaNarudzbe() {

		add(Box.createHorizontalStrut(60));
		JLabel lbl = new JLabel("Pretraga po statusu:");
		lbl.setMaximumSize(new Dimension(150, 30));
		add(lbl);

		filter = new JTextField();
		filter.setBorder(BorderFactory.createEmptyBorder());
		filter.setLocation(450, 0);
		filter.setMaximumSize(new Dimension(100, 30));
		add(filter);

		add(Box.createHorizontalStrut(5));

		confirm = new JButton();

		confirm.setOpaque(false);
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(false);
		confirm.setIcon(new ImageIcon("./icons/search.png"));
		confirm.setBorder(BorderFactory.createEmptyBorder());
		confirm.addActionListener(new ToolbarControler(this));
		confirm.setActionCommand("filterNarudzbeIme");
		confirm.setMaximumSize(new Dimension(35, 35));
		add(confirm);

		JLabel lbl1 = new JLabel("Pretraga po cijeni:");
		lbl1.setMaximumSize(new Dimension(150, 30));
		add(lbl1);

		filter1 = new JTextField();
		filter1.setBorder(BorderFactory.createEmptyBorder());
		filter1.setLocation(450, 0);
		filter1.setMaximumSize(new Dimension(100, 30));
		add(filter1);

		add(Box.createHorizontalStrut(5));

		confirm1 = new JButton();

		confirm1.setOpaque(false);
		confirm1.setContentAreaFilled(false);
		confirm1.setBorderPainted(false);
		confirm1.setIcon(new ImageIcon("./icons/search.png"));
		confirm1.setBorder(BorderFactory.createEmptyBorder());
		confirm1.addActionListener(new ToolbarControler(this));
		confirm1.setActionCommand("filterCijenaNarudzbe");
		confirm1.setMaximumSize(new Dimension(35, 35));
		add(confirm1);
	}

	// podesavanje default opcija toolbara
	public void podesiToolbar() {

		add(Box.createHorizontalStrut(250));

		add = new JButton();
		add.setOpaque(false);
		add.setContentAreaFilled(false);
		add.setBorderPainted(false);
		add.setIcon(new ImageIcon("./icons/add.png"));
		add.setBorder(BorderFactory.createEmptyBorder());
		// add.addActionListener(new ToolbarControler(this));
		add.setActionCommand("addRow");
		add.setLocation(250, 0);
		add.setSize(new Dimension(35, 35));
		add(add);

		edit = new JButton();
		edit.setOpaque(false);
		edit.setContentAreaFilled(false);
		edit.setBorderPainted(false);
		edit.setIcon(new ImageIcon("./icons/edit.png"));
		edit.setBorder(BorderFactory.createEmptyBorder());
		edit.setLocation(290, 0);
		edit.setActionCommand("editRow");
		// edit.addActionListener(new ToolbarControler(this));
		edit.setSize(new Dimension(35, 35));
		add(edit);

		delete = new JButton();
		delete.setOpaque(false);
		delete.setContentAreaFilled(false);
		delete.setBorderPainted(false);
		delete.setIcon(new ImageIcon("./icons/delete.png"));
		delete.setBorder(BorderFactory.createEmptyBorder());
		delete.setLocation(330, 0);
		// delete.addActionListener(new ToolbarControler(this));
		delete.setActionCommand("deleteRow");
		delete.setSize(new Dimension(35, 35));
		add(delete);

		prev = new JButton();
		prev.setOpaque(false);
		prev.setContentAreaFilled(false);
		prev.setBorderPainted(false);
		prev.setIcon(new ImageIcon("./icons/prev.png"));
		prev.setBorder(BorderFactory.createEmptyBorder());
		// prev.addActionListener(new ToolbarControler(this));
		prev.setActionCommand("prev");
		prev.setLocation(370, 0);
		prev.setSize(new Dimension(35, 35));
		add(prev);

		next = new JButton();
		next.setOpaque(false);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		next.setIcon(new ImageIcon("./icons/next.png"));
		next.setBorder(BorderFactory.createEmptyBorder());
		next.setLocation(410, 0);
		// next.addActionListener(new ToolbarControler(this));
		next.setActionCommand("next");
		next.setSize(new Dimension(35, 35));
		add(next);

	}

	// postavljanje filtera za osoblje
	public void postaviFilterZaOsobje() {

		add(Box.createHorizontalStrut(60));
		JLabel lbl = new JLabel("Pretraga po prezimenu:");
		lbl.setMaximumSize(new Dimension(170, 30));
		add(lbl);

		filter = new JTextField();
		filter.setBorder(BorderFactory.createEmptyBorder());
		filter.setMaximumSize(new Dimension(100, 30));
		add(filter);

		add(Box.createHorizontalStrut(5));

		confirm = new JButton();
		confirm.setOpaque(false);
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(false);
		confirm.setIcon(new ImageIcon("./icons/search.png"));
		confirm.setBorder(BorderFactory.createEmptyBorder());
		confirm.addActionListener(new ToolbarControler(this));
		confirm.setActionCommand("filterImeKupca");
		confirm.setMaximumSize(new Dimension(35, 35));
		add(confirm);

		JLabel lbl1 = new JLabel("Pretraga po datumu:");
		lbl1.setMaximumSize(new Dimension(130, 30));
		add(lbl1);

		filter1 = new JTextField();
		filter1.setBorder(BorderFactory.createEmptyBorder());
		filter1.setMaximumSize(new Dimension(100, 30));
		add(filter1);

		add(Box.createHorizontalStrut(5));

		confirm1 = new JButton();
		confirm1.setOpaque(false);
		confirm1.setContentAreaFilled(false);
		confirm1.setBorderPainted(false);
		confirm1.setIcon(new ImageIcon("./icons/search.png"));
		confirm1.setBorder(BorderFactory.createEmptyBorder());
		confirm1.addActionListener(new ToolbarControler(this));
		confirm1.setActionCommand("filterDatum");
		confirm1.setMaximumSize(new Dimension(35, 35));
		add(confirm1);
	}

	// postavljanje Filtera za proizvode
	public void postaviFilterZaProizvode() {

		add(Box.createHorizontalStrut(60));
		JLabel lbl = new JLabel("Pretraga po nazivu:");
		lbl.setMaximumSize(new Dimension(120, 30));
		add(lbl);

		filter = new JTextField();
		filter.setBorder(BorderFactory.createEmptyBorder());
		filter.setMaximumSize(new Dimension(100, 30));
		add(filter);

		add(Box.createHorizontalStrut(5));

		confirm = new JButton();
		confirm.setOpaque(false);
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(false);
		confirm.setIcon(new ImageIcon("./icons/search.png"));
		confirm.setBorder(BorderFactory.createEmptyBorder());
		confirm.addActionListener(new ToolbarControler(this));
		confirm.setActionCommand("filterNazivProizvoda");
		confirm.setMaximumSize(new Dimension(35, 35));
		add(confirm);

		JLabel lbl1 = new JLabel("Pretraga po cijeni:");
		lbl1.setMaximumSize(new Dimension(120, 30));
		add(lbl1);

		filter1 = new JTextField();
		filter1.setBorder(BorderFactory.createEmptyBorder());
		filter1.setMaximumSize(new Dimension(100, 30));
		add(filter1);

		add(Box.createHorizontalStrut(5));

		confirm1 = new JButton();
		confirm1.setOpaque(false);
		confirm1.setContentAreaFilled(false);
		confirm1.setBorderPainted(false);
		confirm1.setIcon(new ImageIcon("./icons/search.png"));
		confirm1.setBorder(BorderFactory.createEmptyBorder());
		confirm1.addActionListener(new ToolbarControler(this));
		confirm1.setActionCommand("filterCijenaProizvoda");
		confirm1.setMaximumSize(new Dimension(35, 35));
		add(confirm1);

	}

	// postabljanje filtera za mjesto
	public void postaviFilterZaMjesto() {

		add(Box.createHorizontalStrut(60));
		JLabel lbl = new JLabel("Pretraga po nazivu:");
		lbl.setMaximumSize(new Dimension(120, 30));
		add(lbl);

		filter = new JTextField();
		filter.setBorder(BorderFactory.createEmptyBorder());
		filter.setLocation(450, 0);
		filter.setMaximumSize(new Dimension(100, 30));
		add(filter);

		add(Box.createHorizontalStrut(5));

		confirm = new JButton();

		confirm.setOpaque(false);
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(false);
		confirm.setIcon(new ImageIcon("./icons/search.png"));
		confirm.setBorder(BorderFactory.createEmptyBorder());
		confirm.addActionListener(new ToolbarControler(this));
		confirm.setActionCommand("filterMjesto");
		confirm.setMaximumSize(new Dimension(35, 35));
		add(confirm);
	}

	// postavi filter za tip placanja
	public void postaviFilterTip() {

		add(Box.createHorizontalStrut(60));
		JLabel lbl = new JLabel("Pretraga po nazivu tipa:");
		lbl.setMaximumSize(new Dimension(150, 30));
		add(lbl);

		filter = new JTextField();
		filter.setBorder(BorderFactory.createEmptyBorder());
		filter.setLocation(450, 0);
		filter.setMaximumSize(new Dimension(130, 30));
		add(filter);

		add(Box.createHorizontalStrut(5));

		confirm = new JButton();

		confirm.setOpaque(false);
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(false);
		confirm.setIcon(new ImageIcon("./icons/search.png"));
		confirm.setBorder(BorderFactory.createEmptyBorder());
		confirm.addActionListener(new ToolbarControler(this));
		confirm.setActionCommand("filterTip");
		confirm.setMaximumSize(new Dimension(35, 35));
		add(confirm);
	}

	// postavi filter za stavke narudzbe
	public void psotaviFilterStavke() {

		add(Box.createHorizontalStrut(60));
		JLabel lbl = new JLabel("Pretraga po kolicini:");
		lbl.setMaximumSize(new Dimension(120, 30));
		add(lbl);

		filter = new JTextField();
		filter.setBorder(BorderFactory.createEmptyBorder());
		filter.setLocation(450, 0);
		filter.setMaximumSize(new Dimension(130, 30));
		add(filter);

		add(Box.createHorizontalStrut(5));

		confirm = new JButton();

		confirm.setOpaque(false);
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(false);
		confirm.setIcon(new ImageIcon("./icons/search.png"));
		confirm.setBorder(BorderFactory.createEmptyBorder());
		confirm.addActionListener(new ToolbarControler(this));
		confirm.setActionCommand("kolicinaFilter");
		confirm.setMaximumSize(new Dimension(35, 35));
		add(confirm);

		JLabel lbl1 = new JLabel("Pretraga po cijeni:");
		lbl1.setMaximumSize(new Dimension(120, 30));
		add(lbl1);

		filter1 = new JTextField();
		filter1.setBorder(BorderFactory.createEmptyBorder());
		filter1.setLocation(450, 0);
		filter1.setMaximumSize(new Dimension(130, 30));
		add(filter1);

		add(Box.createHorizontalStrut(5));

		confirm1 = new JButton();

		confirm1.setOpaque(false);
		confirm1.setContentAreaFilled(false);
		confirm1.setBorderPainted(false);
		confirm1.setIcon(new ImageIcon("./icons/search.png"));
		confirm1.setBorder(BorderFactory.createEmptyBorder());
		confirm1.addActionListener(new ToolbarControler(this));
		confirm1.setActionCommand("cijenaFilter");
		confirm1.setMaximumSize(new Dimension(35, 35));
		add(confirm1);
	}

	// postavljanje filtera za kupce
	public void postaviFilterKupci() {

		add(Box.createHorizontalStrut(60));
		JLabel lbl = new JLabel("Pretraga po prezimenu:");
		lbl.setMaximumSize(new Dimension(130, 30));
		add(lbl);

		filter = new JTextField();
		filter.setBorder(BorderFactory.createEmptyBorder());
		filter.setMaximumSize(new Dimension(100, 30));
		add(filter);

		add(Box.createHorizontalStrut(5));

		confirm = new JButton();
		confirm.setOpaque(false);
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(false);
		confirm.setIcon(new ImageIcon("./icons/search.png"));
		confirm.setBorder(BorderFactory.createEmptyBorder());
		confirm.addActionListener(new ToolbarControler(this));
		confirm.setActionCommand("filterImeKupca");
		confirm.setMaximumSize(new Dimension(35, 35));
		add(confirm);

		JLabel lbl1 = new JLabel("Pretraga po broju telefona:");
		lbl1.setMaximumSize(new Dimension(160, 30));
		add(lbl1);

		filter1 = new JTextField();
		filter1.setBorder(BorderFactory.createEmptyBorder());
		filter1.setMaximumSize(new Dimension(100, 30));
		add(filter1);

		add(Box.createHorizontalStrut(5));

		confirm1 = new JButton();
		confirm1.setOpaque(false);
		confirm1.setContentAreaFilled(false);
		confirm1.setBorderPainted(false);
		confirm1.setIcon(new ImageIcon("./icons/search.png"));
		confirm1.setBorder(BorderFactory.createEmptyBorder());
		confirm1.addActionListener(new ToolbarControler(this));
		confirm1.setActionCommand("filterBrojTelefona");
		confirm1.setMaximumSize(new Dimension(35, 35));
		add(confirm1);
	}

}