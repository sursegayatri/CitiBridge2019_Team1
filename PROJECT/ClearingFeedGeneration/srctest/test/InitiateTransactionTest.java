package test;

import upload.InitiateTransaction;
import upload.ProcessInputFeeds;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class InitiateTransactionTest {
	
	@Test
	public void initiateTransTest() {
	String fileName="test_sample.txt";
	ProcessInputFeeds inputTransaction = new ProcessInputFeeds(fileName);
	inputTransaction.readFile();

	}
}


