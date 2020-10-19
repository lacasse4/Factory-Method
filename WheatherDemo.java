package wheather;

import java.awt.EventQueue;

/*
 * Ref: https://meteo.gc.ca/business/index_f.html
 */

public class WheatherDemo implements Runnable {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new WheatherDemo());
	}

	public void run() {
		State state = new State();
		MainPanel frame = new MainPanel(state);
		frame.setVisible(true);
	}
}
