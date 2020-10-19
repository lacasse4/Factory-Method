package wheather;

import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public abstract class AbstractWheatherDataFactory implements WheatherDataFactory {

	abstract public String getURI();

	abstract String getConditionAttribute();

	public WheatherData getWheatherData() {

		String location = "";
		String dateTime = "";
		String data = "";

		SearchableElement base;
		try {
			base = getBaseElement();
			location = base.getTagContent("title");
			dateTime = base.getTagContent("updated");

			Iterator<SearchableElement> entry = base.getTagElementsUnder("entry");
			while (entry.hasNext()) {
				SearchableElement entryItem = entry.next();
				SearchableElement category = entryItem.getSingleTag("category");
				if (category.hasAttributeValue("term", getConditionAttribute())) {
					SearchableElement summary = entryItem.getSingleTag("summary");
					data = summary.getTextContent();
					break;
				}				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return new WheatherDataProduct(location, dateTime, data);
	}


	private SearchableElement getBaseElement() throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf;
		DocumentBuilder db;
		Document doc;

		// Get a DOM Document of the XML document referenced by uri

		dbf = DocumentBuilderFactory.newInstance();
		db = dbf.newDocumentBuilder();
		doc = db.parse(getURI());
		doc.getDocumentElement().normalize();

		// Return the base of the DOM document

		return new SearchableElement(doc.getDocumentElement());
	}



	private class WheatherDataProduct implements WheatherData {

		private String location, dateTime, data;

		public WheatherDataProduct(String location, String dateTime, String data) {
			this.location = location;
			this.dateTime = dateTime;
			this.data = data;
		}

		public String getLocation() { return "<h2>" + location + "<h2>"; }
		public String getDateTime() { return "<h3>" + dateTime + "<h3>"; }
		public String getData() 	{ return data; }
	}
}
