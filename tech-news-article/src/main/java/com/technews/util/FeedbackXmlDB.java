package com.technews.util;

import com.technews.model.Feedback;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** Tiny XML database: feedbacks.xml is the data store, feedback.xsd validates it,
 *  XPath is used to run conditional searches (e.g. rating greater than N). */
public class FeedbackXmlDB {

    private static Document load(String xmlPath) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(new File(xmlPath));
    }

    // Validate an in-memory Document against the XSD
    private static void validate(Document doc, String xsdPath) throws Exception {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(xsdPath));
        schema.newValidator().validate(new DOMSource(doc));
    }

    public static void addFeedback(String xmlPath, String xsdPath, String name, String email,
                                    String subject, String message, int rating) throws Exception {
        Document doc = load(xmlPath);
        Element root = doc.getDocumentElement();

        int nextId = root.getElementsByTagName("feedback").getLength() + 1;
        Element fb = doc.createElement("feedback");
        fb.setAttribute("id", String.valueOf(nextId));
        fb.appendChild(textEl(doc, "name", name));
        fb.appendChild(textEl(doc, "email", email));
        fb.appendChild(textEl(doc, "subject", subject));
        fb.appendChild(textEl(doc, "message", message));
        fb.appendChild(textEl(doc, "rating", String.valueOf(rating)));
        root.appendChild(fb);

        validate(doc, xsdPath);   // reject the save if it breaks the schema

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(doc), new StreamResult(new File(xmlPath)));
    }

    private static Element textEl(Document doc, String tag, String value) {
        Element e = doc.createElement(tag);
        e.setTextContent(value);
        return e;
    }

    // XPath conditional search, e.g. /feedbacks/feedback[rating>3]
    public static List<Feedback> search(String xmlPath, Integer minRating) throws Exception {
        Document doc = load(xmlPath);
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expr = (minRating != null)
                ? "/feedbacks/feedback[rating>" + minRating + "]"
                : "/feedbacks/feedback";
        NodeList nodes = (NodeList) xpath.evaluate(expr, doc, XPathConstants.NODESET);

        List<Feedback> list = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element fb = (Element) nodes.item(i);
            list.add(new Feedback(
                    Integer.parseInt(fb.getAttribute("id")),
                    text(fb, "name"), text(fb, "email"), text(fb, "subject"),
                    text(fb, "message"), Integer.parseInt(text(fb, "rating"))));
        }
        return list;
    }

    private static String text(Element parent, String tag) {
        return parent.getElementsByTagName(tag).item(0).getTextContent();
    }
}
