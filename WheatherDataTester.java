package wheather;

public class WheatherDataTester {
	
	public static void main(String[] args) {
		
		WheatherDataFactory wdf = new WheatherDataFactoryFR();
		WheatherData wd = wdf.getWheatherData();
		System.out.print(wd.getLocation());
		System.out.print(wd.getDateTime());
		System.out.print(wd.getData());
	}
}
