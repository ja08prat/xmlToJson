package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.File;
import java.io.IOException;

/**
 * This program will take a list of file paths to XML documents as a command line parameter and perform the
 * following actions:
 *  1. reads the xml file from the given absolute file path
 *  2. parses xml to prettified JSON
 *  3. the prettified JSON is then parsed back to prettified XML
 *  4. The prettified JSON and re-generated XML is then printed to the terminal
 *  5. Steps 1-4 are repeated for every file path provided as a command line param
 *
 *
 */
public class XmlToJsonParser {

    public static void main( String[] args ) {
        int xmlFileCounter = 0;
        for (String filePath : args) {

            if (filePath.isEmpty()) {
                System.out.println("File name cannot be blank");
                System.exit(1);
            }

            System.out.println("Parsing XML file at index " + xmlFileCounter + " to JSON. File found at: " + filePath);

            String json = xmlToJson(filePath);

            System.out.println("Parsing generated JSON back to XML for file at index " + xmlFileCounter);
            System.out.println("---------------------------------------------------------\n");

            String regeneratedXml = jsonToXml(json);

            printResults(json, regeneratedXml, xmlFileCounter);

            xmlFileCounter++;
        }

    }

    private static String xmlToJson(String filePath) {
        File file = new File(filePath);
        XmlMapper mapper = new XmlMapper();
        String json = "";

        try {
            JsonNode node = mapper.readTree(file);
            ObjectMapper jsonMapper = new ObjectMapper();
            json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (IOException e) {
            System.out.println("File read failed.");
            System.out.println(e.getMessage());
        }

        return json;
    }

    private static String jsonToXml(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectMapper xmlMapper = new XmlMapper();
        String xml = "";
        try {
            JsonNode tree = objectMapper.readTree(json);
            xml = xmlMapper.writer().withDefaultPrettyPrinter().writeValueAsString(tree);
        } catch (JsonProcessingException e) {
            System.out.println("Encountered problem regenerating XML from JSON");
            System.out.println(e.getMessage());
        }

        return xml;
    }

    private static void printResults(String json, String regeneratedXml, int xmlFileCounter) {
        System.out.println("Printing results for XML file at index " + xmlFileCounter);
        System.out.println("JSON from index " + xmlFileCounter);
        System.out.println(json);
        System.out.println("---------------------------------------------------------\n");
        System.out.println("XML from generated JSON at index " + xmlFileCounter);
        System.out.println(regeneratedXml);
        System.out.println("---------------------------------------------------------\n");
    }
}
