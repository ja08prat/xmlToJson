
    Contact ja08prat@alum.siena.edu for questions
    This program will take a list of file paths to XML documents as a command line parameter and perform the
    following actions:
    1. reads the xml file from the given absolute file path
    2. parses xml to prettified JSON
    3. the prettified JSON is then parsed back to prettified XML
    4. The prettified JSON and re-generated prettified XML is then printed to the terminal
    5. Steps 1-4 are repeated for every file path provided as a command line param

 
Program has the following dependencies:

    <dependency>
      <groupId>com.fasterxml.jackson.dataformat</groupId>
      <artifactId>jackson-dataformat-xml</artifactId>
      <version>2.12.3</version>
    </dependency>
    
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.12.3</version>
    </dependency>

#TO RUN: #
configure run configuration to execute main method in XmlToJsonParser.java with absolute file paths to valid XML files delimited by spaces. Example config shown below:

![image](https://user-images.githubusercontent.com/14113300/115154817-c9e23000-a04a-11eb-85ce-dac7fcf210a5.png)
