package wheather;

public class MenuFactoryEN extends AbstractMenuFactory {
	
	public MenuFactoryEN(State state) { super(state); }

	public String getLabel(int labelNo) {
		switch (labelNo) {
		case FILE:     return "File";
		case EXIT:     return "Exit";
		case LANGUAGE: return "Language";
		case ENGLISH:  return "English";
		case FRENCH:   return "French";
		default:       return null;
		}
	}
}
