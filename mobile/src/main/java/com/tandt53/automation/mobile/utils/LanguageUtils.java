//package com.tandt53.automation.mobile.utils;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//
//public class LanguageUtils {
//
//    public static HashMap<String, String> loadXmlData(String filePath) throws ParserConfigurationException, IOException, SAXException {
//        File xmlFile = new File(filePath);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document doc = builder.parse(xmlFile);
//        return parseWholeXML(doc);
//    }
//
//    // Read nodes having specific attribute
//    private static HashMap<String, String> parseWholeXML(Document doc) {
//        HashMap<String, String> languageData = new HashMap<>();
//        NodeList studentNodes = doc.getElementsByTagName("string");
//        for (int i = 0; i < studentNodes.getLength(); i++) {
//            Node studentNode = studentNodes.item(i);
//            if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element studentElement = (Element) studentNode;
//                languageData.put(studentElement.getAttributeNode("name").getValue(),  studentNode.getTextContent());
//            }
//        }
//        return languageData;
//    }
//}
