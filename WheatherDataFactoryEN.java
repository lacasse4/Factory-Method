package wheather;

public class WheatherDataFactoryEN extends AbstractWheatherDataFactory {
	
	public String getURI() { return "https://weather.gc.ca/rss/city/qc-147_e.xml"; }
	public String getConditionAttribute() { return "Current Conditions"; }
	
}
