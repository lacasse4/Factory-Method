package wheather;

public class WheatherDataFactoryFR extends AbstractWheatherDataFactory {
	
	public String getURI() { return "https://weather.gc.ca/rss/city/qc-147_f.xml"; }
	public String getConditionAttribute() { return "Conditions actuelles"; }
	
}
