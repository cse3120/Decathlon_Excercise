package com.decathlon;

import com.decathlon.model.Athlete;
import com.decathlon.model.OutputXml;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


class ReadInputAndProcessOutputTest {

	ReadInputAndProcessOutput readInputAndProcessOutput;
	DecathlonResult decathlonResult;
	File file=null;
	@BeforeEach
	void init() {
		this.readInputAndProcessOutput = new ReadInputAndProcessOutput();
		this.decathlonResult = new DecathlonResult();
		ClassLoader classLoader = getClass().getClassLoader();
		 file=new File(classLoader.getResource("results.csv").getFile());
	}

	@DisplayName("Verify the calculate of Score to Javelin Throw Event")
	@Test
	void verifyReadFile() {
		List<String> rows;
		try {
			rows = this.readInputAndProcessOutput.readFile(file.getAbsolutePath());
			assertNotNull(rows);
			assumeTrue(rows.size() > 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DisplayName("Fail the calculate of Score to Javelin Throw Event")
	@Test
	void failReadFile() {
		try {
			List<String> rows = this.readInputAndProcessOutput.readFile(file.getAbsolutePath());
			assumeTrue(!(rows.size() > 0));
			assumeTrue(false);
			assertTrue(true);
		} catch (Exception e) {
			assumeTrue(true);
		}
	}

	@DisplayName("Verify if I can create the xml file")
	@Test
	void verifyCreateXmlFile() {
		List<Athlete> athletes = new ArrayList<Athlete>();
		athletes.add(this.decathlonResult
				.registerOneAthlete("John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72"));
		String outFile = file.getAbsolutePath().replaceFirst("results.csv", "");
		try {
			this.readInputAndProcessOutput.createdXmlWithJaxb(athletes, outFile);
			OutputXml xc = this.readXmlWithJaxb(outFile + "DecathlonResult.xml");
			assertNotNull(xc);
			assertNotNull(xc.getAthletes().get(0));
			Athlete athletesXml = xc.getAthletes().get(0);
			assertEquals("John Smith",athletesXml.getName());
			assumeTrue(true);
		} catch (Exception e) {
			assumeTrue(false);
			e.printStackTrace();
		}
	}

	public OutputXml readXmlWithJaxb(String pathFile) throws Exception {
		File xmlFile = new File(pathFile);
		OutputXml xc = new OutputXml();
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(OutputXml.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			xc = (OutputXml) jaxbUnmarshaller.unmarshal(xmlFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xc;
	}
}
