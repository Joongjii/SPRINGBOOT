package com.mc.mvc.ai;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@SpringBootTest
public class Tess4jTest {

		@Test
		public void testOCR() {
			 File imageFile = new File("C:\\Users\\guswn\\eclipse-workspace\\tessdata\\poem.jpg");
		        ITesseract instance = new Tesseract();  // JNA Interface Mapping
		        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
		        instance.setDatapath("C:\\Users\\guswn\\eclipse-workspace\\tessdata");
		        instance.setLanguage("kor");
		        
		        try {
		            String result = instance.doOCR(imageFile);
		            System.out.println(result);
		        } catch (TesseractException e) {
		            System.err.println(e.getMessage());
		        }
		}
		
}
