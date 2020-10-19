package wheather;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class State {
	
	public enum Language { EN, FR };
	private Language language = Language.EN;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public Language getLanguage() { return language; }
	
	public void setLanguage(Language language) {
		this.language = language;
		pcs.firePropertyChange("LANGUAGE CHANGE", null, null);
	}
	
	public WheatherDataFactory getWheatherDataFactory() {
		switch (language) {
		case EN: return new WheatherDataFactoryEN();
		case FR: return new WheatherDataFactoryFR();
		default: return null;
		}
	}
	
	public MenuFactory getMenuFactory() {
		switch (language) {
		case EN: return new MenuFactoryEN(this);
		case FR: return new MenuFactoryFR(this);
		default: return null;
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}
}
