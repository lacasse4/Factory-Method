package wheather;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public abstract class AbstractMenuFactory implements MenuFactory {

	public static final int FILE = 0;
	public static final int EXIT = 1;
	public static final int LANGUAGE = 2;
	public static final int ENGLISH = 3;
	public static final int FRENCH = 4;
	public static final int N_COMPONENTS = 5;

	private State state;

	public abstract String getLabel(int labelNo);
	
	public AbstractMenuFactory(State state) {
		this.state = state;
	}

	@Override
	public JMenuBar getMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu mnFile = new JMenu(getLabel(FILE));
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem(getLabel(EXIT));
		mntmExit.addActionListener(new ExitCommand());
		mnFile.add(mntmExit);

		JMenu mnLanguage = new JMenu(getLabel(LANGUAGE));
		menuBar.add(mnLanguage);

		JMenuItem mntmEnglish = new JMenuItem(getLabel(ENGLISH));
		mntmEnglish.addActionListener(new EnglishCommand());
		mnLanguage.add(mntmEnglish);

		JMenuItem mntmFrench = new JMenuItem(getLabel(FRENCH));
		mntmFrench.addActionListener(new FrenchCommand());
		mnLanguage.add(mntmFrench);	

		return menuBar;
	}

	class ExitCommand implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	class EnglishCommand implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			state.setLanguage(State.Language.EN);
		}
	}

	class FrenchCommand implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			state.setLanguage(State.Language.FR);
		}
	}

}
