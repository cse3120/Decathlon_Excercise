package com.decathlon;

import com.decathlon.model.Athlete;

import java.util.List;
import java.util.Scanner;

/**
 * @author gopin
 * @Date 14-09-2022
 */
public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the input file name along with path: ");
        String inputFile=scanner.next();
        System.out.println("Enter the output file path: ");
        String outputFile=scanner.next();
        try {
            ReadInputAndProcessOutput readInputAndProcessOutput = new ReadInputAndProcessOutput();
            List<String> rows = readInputAndProcessOutput.readFile(inputFile);
            List<Athlete> athletes = (new DecathlonResult()).resultEvent(rows);
            readInputAndProcessOutput.createdXmlWithJaxb(athletes, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
