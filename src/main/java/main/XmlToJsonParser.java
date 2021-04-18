package main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 *
 */
public class XmlToJsonParser {
    private static FileWriter fileWriter;

    public static void main( String[] args ) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please specify an absolute path to a directory for output files: ");
        String outputDirectory = scan.nextLine();

        // don't allow a blank sting for file output directory
        while (outputDirectory.trim().isEmpty()) {
            System.out.println("Please specify an absolute path to a directory for output files: ");
            outputDirectory = scan.nextLine();
        }

        for (String filePath : args) {

            System.out.println("Processing XML file found at: " + filePath);

            if (filePath.isEmpty()) {
                System.out.println("Invalid file name please try again");
                System.exit(1);
            }

            String jsonString = xmlToJson(filePath);
            writeJsonStringToFile(jsonString, outputDirectory);

        }

    }

    private static String xmlToJson(String filePath) {
        File file = new File(filePath);
        XmlMapper mapper = new XmlMapper();
        String json = "";

        try {
            JsonNode node = mapper.readTree(file);
            ObjectMapper jsonMapper = new ObjectMapper();
            json = jsonMapper.writeValueAsString(node);
        } catch (IOException e) {
            System.out.println("File read failed.");
            System.out.println(e.getMessage());
        }

        System.out.println(json);
        return json;
    }

    private static void writeJsonStringToFile(String jsonString, String outputDirectory) {
        try {
            fileWriter = new FileWriter(outputDirectory);
            fileWriter.write(jsonString);
        } catch (IOException e) {
            System.out.println("File failed to write");
            System.out.println(e.getMessage());
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("File failed to flush/close");
                System.out.println(e.getMessage());
            }
        }
    }
}
