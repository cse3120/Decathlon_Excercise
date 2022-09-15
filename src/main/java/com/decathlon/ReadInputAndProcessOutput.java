package com.decathlon;


import com.decathlon.model.Athlete;
import com.decathlon.model.OutputXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReadInputAndProcessOutput {

    /**
     * Read a file.
     * @param path String.
     * @return List<String>
     */
    public List<String> readFile(String path) {
        List<String> rows = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(rowFile -> {
                if (rowFile != null && rowFile.trim().length() > 1) {
                    rows.add(rowFile);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new DecathlonRunTimeException("We have a problem when we reading the file");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DecathlonRunTimeException("We have a unknown problem");
        }
        return rows;
    }

    /**
     * Create the xml file
     * @param athletes List<Athlete>
     * @param outputFile String
     * */
    public void createdXmlWithJaxb(List<Athlete> athletes, String outputFile)  {
        OutputXml outputXml = new OutputXml();
        outputXml.setAthletes(athletes);
        File file = new File(outputFile + "DecathlonResult.xml");
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(OutputXml.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(outputXml, file);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new DecathlonRunTimeException("We have a problem when we are going to created the xml file.");
        }
    }
}
