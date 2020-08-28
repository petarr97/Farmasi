package controler;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ReportsControler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("finansije")) {
			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new File("reports/Finansije.pdf");
					Desktop.getDesktop().open(myFile);

				} catch (IOException ex) {
					// no application registered for PDFs
				}
			}
		} else if (e.getActionCommand().equals("rezervacije")) {
			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new File("reports/rezervacija.pdf");
					Desktop.getDesktop().open(myFile);
					// JasperPrintManager.printReport( myFile, true);

				} catch (IOException ex) {
					// no application registered for PDFs
				}
			}
		} else if (e.getActionCommand().equals("poslovni_sistemi")) {
			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new File("reports/Poslovni-sistemi.pdf");
					Desktop.getDesktop().open(myFile);
					// JasperPrintManager.printReport( myFile, true);

				} catch (IOException ex) {
					// no application registered for PDFs
				}
			}
		}
	}

}
