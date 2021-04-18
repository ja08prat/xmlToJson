package main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.File;
import java.io.IOException;

/**
 *
 *
 */
public class XmlToJsonParser {

    public static void main( String[] args ) {
        for (String filePath : args) {

            System.out.println("Parsing XML file to JSON. File found at: " + filePath);

            if (filePath.isEmpty()) {
                System.out.println("Invalid file name please try again");
                System.exit(1);
            }

            String json = xmlToJson(filePath);
            
            System.out.println(json);


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

}
