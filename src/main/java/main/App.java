package main;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        for (String filePath : args) {
            System.out.println(filePath);
            XmlMapper mapper = new XmlMapper();
        }

    }
}
