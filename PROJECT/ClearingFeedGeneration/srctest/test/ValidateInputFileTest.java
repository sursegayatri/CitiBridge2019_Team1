package test;

import bean.TransactionDate;
import validations.*;

import static org.junit.Assert.*;
import org.junit.*;
//import org.junit.Test;

public class ValidateInputFileTest {
	private ValidateInputFile obj=null;
	
	@Before
	public void initiateTest() {
		obj=new ValidateInputFile();
	}
	
	@Test
	public void checkAlphanumericStringTestCorrect() {
		assertEquals(true,obj.checkAlphanumericString("123456789012", 12));
	}
	
	@Test
	public void checkAlphanumericStringTestIncorrect() {
		assertEquals(true,obj.checkAlphanumericString("123456789012", 15));
	}
	
	@Test
	public void specialCharacterTestCorrect() {
		assertEquals(false,obj.specialCharacter('a'));
	}
	
	@Test
	public void specialCharacterTestIncorrect() {
		assertEquals(true,obj.specialCharacter('-'));
	}
	
	@Test
	public void checkNumericStringTestCorrect() {
		assertEquals(true,obj.checkNumericString("200000102011"));
	}
	
	@Test
	public void checkNumericStringTestIncorrect() {
		assertEquals(false,obj.checkNumericString("200000ABC011"));
	}
	
	@Test
	public void checkDateFormatTestCorrect() {
		assertEquals(true,obj.checkDateFormat(28,12,2018)); //correct format
	}
	
	@Test
	public void checkDateFormatTestIncorrectMonth() {
		assertEquals(false,obj.checkDateFormat(28,15,2018)); //incorrect month
	}
	
	@Test
	public void checkDateFormatTestIncorrectYear() {
		assertEquals(false,obj.checkDateFormat(28,12,1)); //incorrect month
	}
	
	@Test
	public void checkDayTestCorrect() {
		assertEquals(true,obj.checkDay(31,12,2018)); //correct day
	}
	
	@Test
	public void checkDayTestIncorrect() {
		assertEquals(false,obj.checkDay(56,12,2018)); //incorrect day
	}
	
	@Test
	public void checkTransactionDateOccurenceTestCorrect() {
		assertEquals(true,obj.checkTransactionOccuranceDate(19,03,2018)); 
	}
	
	@Test
	public void checkTransactionDateOccurenceTestIncorrect() {
		assertEquals(false,obj.checkTransactionOccuranceDate(17,03,2018)); //incorrect date occurence
	}
	
	@Test
	public void checkAmountTest() {
		assertEquals(true,obj.checkAmount(453648.789));
	}
}
