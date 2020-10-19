package wheather;

public class MenuFactoryFR extends AbstractMenuFactory {

	public MenuFactoryFR(State state) { super(state); }

	public String getLabel(int labelNo) {
		switch (labelNo) {
		case FILE:     return "Fichier";
		case EXIT:     return "Terminer";
		case LANGUAGE: return "Langue";
		case ENGLISH:  return "Anglais";
		case FRENCH:   return "Francais";
		default:       return null;
		}
	}
}
