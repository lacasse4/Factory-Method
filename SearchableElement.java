package wheather;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.SAXException;

public class SearchableElement implements Element {
	
	private Element delegate;
	
	/**
	 * Returns elements list (iterator) with specific tag under this element 
	 * @param tag - tag sought
	 * @return Iterator containing elements found
	 * @throws SAXException - no child found or tag was not found
	 */
	public Iterator<SearchableElement> getTagElementsUnder(String tag) throws SAXException {
		
		List<SearchableElement> result = new ArrayList<SearchableElement>();
		
		// Extract the node list under this element
		
		NodeList nodeList = getChildNodes();
		if (nodeList.getLength() == 0) {
			throw new SAXException("No child element under <" + getNodeName() + ">");
		}
		
		// Produce the element list
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getNodeName().equals(tag)) {
					result.add(new SearchableElement((Element)node));
				}
			}
		}
		
		// throw SAXException it the tag was not found
		
		if (result.isEmpty()) {
			throw new SAXException(tag + " was not found under <" + getNodeName() + ">");
		}
			
		return result.iterator();
	}
	
	/**
	 * Returns the attribute value of this element if it belongs to the validation list 
	 * @param name - attribute name
	 * @param validation - list of valid attribute values
	 * @return - attribute value
	 * @throws SAXException - attribute value does not belong ot validation list 
	 */
	public String getValidatedAttributeValue(String name, List<String> validation) 
			throws SAXException {
		String attributeValue = getAttribute(name);
		if (!validation.contains(attributeValue)) {
			throw new SAXException("Invalid attribute value (" 
		          + name + "=\"" + attributeValue + "\") in <" + getTagName() + ">");	
		}
		return attributeValue;
	}	
		

	/**
	 * Returns the first sub element having the tagName 
	 * @param tagName - name of the element sought
	 * @return first element that has the name tag
	 * @throws SAXException 
	 */
	public SearchableElement getSingleTag(String tagName) throws SAXException {
		
		// Search for element <"tagName"> 

		Iterator<SearchableElement> iterator = getTagElementsUnder(tagName);

		// return the first one.

		return iterator.next();
	}

	/**
	 * Returns the first element with tagName and specified attribute value.
	 * 
	 * @param tagName - tag name sought
	 * @param attribute - attibute sought
	 * @param value - attribute value sought
	 * @return first element found with matching critera
	 * @throws SAXException - the element sought was not found
	 */
	public SearchableElement getElementWithTagAndAttribute(String tagName, String attribute, String value) 
			throws SAXException {
		
		// Search elements with tagName
		
		Iterator<SearchableElement> iter = getTagElementsUnder(tagName);
		
		// Find first element with seached attribute
		
		while (iter.hasNext()) {
			SearchableElement e = new SearchableElement(iter.next());
			if (e.hasAttributeValue(attribute, value)) {
				return e;
			}
		}
				
		// Lever une SAXException si l'attribut n'a pas été retrouvé dans la liste d'usine
		
		throw new SAXException(attribute + "=\"" + value + "\" not found within " + tagName + "list.");
	}
	
	/**
	 * Checks it this element has specified attibute and value 
	 * @param attribute - attribute name to check
	 * @param value - value to check 
	 * @return true if attribute and value were found 
	 */
	public boolean hasAttributeValue(String attribute, String value) {
		return getAttribute(attribute).equals(value);
	}
	
	
	/**
	 * Search tagName under this element and return its text content
	 * @param tagName - tag sought
	 * @return String containing the text content
	 * @throws SAXException 
	 * @throws DOMException 
	 */
	public String getTagContent(String tagName) throws DOMException, SAXException {		
		return getSingleTag(tagName).getTextContent();
	}
	

	
	// ------------------------------------------------
	// Next are delegate methods generated automaticaly
	// ------------------------------------------------
	
	public SearchableElement(Element element) {
		delegate = element;
	}

	public String getTagName() {
		return delegate.getTagName();
	}

	public String getAttribute(String name) {
		return delegate.getAttribute(name);
	}

	public void setAttribute(String name, String value) throws DOMException {
		delegate.setAttribute(name, value);
	}

	public void removeAttribute(String name) throws DOMException {
		delegate.removeAttribute(name);
	}

	public Attr getAttributeNode(String name) {
		return delegate.getAttributeNode(name);
	}

	public Attr setAttributeNode(Attr newAttr) throws DOMException {
		return delegate.setAttributeNode(newAttr);
	}

	public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
		return delegate.removeAttributeNode(oldAttr);
	}

	public String getNodeName() {
		return delegate.getNodeName();
	}

	public String getNodeValue() throws DOMException {
		return delegate.getNodeValue();
	}

	public NodeList getElementsByTagName(String name) {
		return delegate.getElementsByTagName(name);
	}

	public void setNodeValue(String nodeValue) throws DOMException {
		delegate.setNodeValue(nodeValue);
	}

	public String getAttributeNS(String namespaceURI, String localName) throws DOMException {
		return delegate.getAttributeNS(namespaceURI, localName);
	}

	public short getNodeType() {
		return delegate.getNodeType();
	}

	public Node getParentNode() {
		return delegate.getParentNode();
	}

	public NodeList getChildNodes() {
		return delegate.getChildNodes();
	}

	public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException {
		delegate.setAttributeNS(namespaceURI, qualifiedName, value);
	}

	public Node getFirstChild() {
		return delegate.getFirstChild();
	}

	public Node getLastChild() {
		return delegate.getLastChild();
	}

	public Node getPreviousSibling() {
		return delegate.getPreviousSibling();
	}

	public Node getNextSibling() {
		return delegate.getNextSibling();
	}

	public NamedNodeMap getAttributes() {
		return delegate.getAttributes();
	}

	public Document getOwnerDocument() {
		return delegate.getOwnerDocument();
	}

	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
		return delegate.insertBefore(newChild, refChild);
	}

	public void removeAttributeNS(String namespaceURI, String localName) throws DOMException {
		delegate.removeAttributeNS(namespaceURI, localName);
	}

	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		return delegate.replaceChild(newChild, oldChild);
	}

	public Attr getAttributeNodeNS(String namespaceURI, String localName) throws DOMException {
		return delegate.getAttributeNodeNS(namespaceURI, localName);
	}

	public Node removeChild(Node oldChild) throws DOMException {
		return delegate.removeChild(oldChild);
	}

	public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
		return delegate.setAttributeNodeNS(newAttr);
	}

	public Node appendChild(Node newChild) throws DOMException {
		return delegate.appendChild(newChild);
	}

	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) throws DOMException {
		return delegate.getElementsByTagNameNS(namespaceURI, localName);
	}

	public boolean hasChildNodes() {
		return delegate.hasChildNodes();
	}

	public Node cloneNode(boolean deep) {
		return delegate.cloneNode(deep);
	}

	public boolean hasAttribute(String name) {
		return delegate.hasAttribute(name);
	}

	public boolean hasAttributeNS(String namespaceURI, String localName) throws DOMException {
		return delegate.hasAttributeNS(namespaceURI, localName);
	}

	public void normalize() {
		delegate.normalize();
	}

	public TypeInfo getSchemaTypeInfo() {
		return delegate.getSchemaTypeInfo();
	}

	public void setIdAttribute(String name, boolean isId) throws DOMException {
		delegate.setIdAttribute(name, isId);
	}

	public boolean isSupported(String feature, String version) {
		return delegate.isSupported(feature, version);
	}

	public void setIdAttributeNS(String namespaceURI, String localName, boolean isId) throws DOMException {
		delegate.setIdAttributeNS(namespaceURI, localName, isId);
	}

	public String getNamespaceURI() {
		return delegate.getNamespaceURI();
	}

	public String getPrefix() {
		return delegate.getPrefix();
	}

	public void setIdAttributeNode(Attr idAttr, boolean isId) throws DOMException {
		delegate.setIdAttributeNode(idAttr, isId);
	}

	public void setPrefix(String prefix) throws DOMException {
		delegate.setPrefix(prefix);
	}

	public String getLocalName() {
		return delegate.getLocalName();
	}

	public boolean hasAttributes() {
		return delegate.hasAttributes();
	}

	public String getBaseURI() {
		return delegate.getBaseURI();
	}

	public short compareDocumentPosition(Node other) throws DOMException {
		return delegate.compareDocumentPosition(other);
	}

	public String getTextContent() throws DOMException {
		return delegate.getTextContent();
	}

	public void setTextContent(String textContent) throws DOMException {
		delegate.setTextContent(textContent);
	}

	public boolean isSameNode(Node other) {
		return delegate.isSameNode(other);
	}

	public String lookupPrefix(String namespaceURI) {
		return delegate.lookupPrefix(namespaceURI);
	}

	public boolean isDefaultNamespace(String namespaceURI) {
		return delegate.isDefaultNamespace(namespaceURI);
	}

	public String lookupNamespaceURI(String prefix) {
		return delegate.lookupNamespaceURI(prefix);
	}

	public boolean isEqualNode(Node arg) {
		return delegate.isEqualNode(arg);
	}

	public Object getFeature(String feature, String version) {
		return delegate.getFeature(feature, version);
	}

	public Object setUserData(String key, Object data, UserDataHandler handler) {
		return delegate.setUserData(key, data, handler);
	}

	public Object getUserData(String key) {
		return delegate.getUserData(key);
	}

}
