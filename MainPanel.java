package wheather;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;

public class MainPanel extends JFrame implements PropertyChangeListener {
	
	State state;
	
	public MainPanel(State state) {
		this.state = state;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		state.addPropertyChangeListener(this);
		setMenu(state.getMenuFactory());
		setMainPanel(state.getWheatherDataFactory());
	}	
	
	public void setMenu(MenuFactory mf) {
		JMenuBar menuBar = mf.getMenu();
		setJMenuBar(menuBar);
	}
	
	public void setMainPanel(WheatherDataFactory wdf) {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		JEditorPane textPane = new JEditorPane();
		textPane.setEditable(false);
		textPane.setContentType("text/html");
		JScrollPane scrollPane = new JScrollPane(textPane);
		contentPane.add(scrollPane);
		
		WheatherData wd = wdf.getWheatherData();
		textPane.setText(wd.getLocation() + wd.getDateTime() + wd.getData());	

		setContentPane(contentPane);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("LANGUAGE CHANGE")) {
			setMenu(state.getMenuFactory());
			setMainPanel(state.getWheatherDataFactory());	
			setVisible(true);
		}
		repaint();
	}
}
