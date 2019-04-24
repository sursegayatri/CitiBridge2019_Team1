package upload;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Transaction;
import bean.TransactionDate;
import feedgeneration.ClearingFeedFile;
import validations.ValidateInputFile;
/*
 * This class is used to read and store the file so that t can be further validated
 *  All the parameters read are segregated and added to the transaction parameter objects
 *  which is then added to arraylist
 *  Here all the validations are called
 *  
 *  Functions :
 *  setTransaction()
 *  processInputFeeds()
 *  validateInput()
 *  parseDate()
 * */
public class ProcessInputFeeds {
	String filename;
	ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	Transaction feed;
	private static final Logger log = LogManager.getLogger(ProcessInputFeeds.class);
	ValidateInputFile validateFile;
	ClearingFeedFile clearingFeedFile = new ClearingFeedFile();
	public ProcessInputFeeds(String filename){
		this.filename = filename;
	}
	public void readFile() {
		log.info("Entering read file ...");
		try {
			FileReader fr = new FileReader(filename);
			//FileReader fr = new FileReader("sample_with_space.txt");
			BufferedReader br = new BufferedReader(fr);
			String transactionFeed = new String();
			log.info("Reading File .....\n-------------------------------------------------------\nFile Data : \n");
			while((transactionFeed = br.readLine())!=null)
				setTransaction(transactionFeed);
			br.close();
			fr.close();
			clearingFeedFile.clearingFeed(transactionList,filename);
			log.info("Exiting read file ...");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("File Not found error : "+e);
		} catch (IOException e) {
			log.error("IOException : "+e);
		}

	}
	
	//For setting transaction parameter
	public void setTransaction(String transactionFeed)
	{
		validateFile = new ValidateInputFile();
		StringTokenizer stringTokenizer = new StringTokenizer(transactionFeed," ");
		feed = new Transaction();
		 feed.setTransactionRef(stringTokenizer.nextToken());
		 feed.setTransactionDate(parseDate(stringTokenizer.nextToken()));
		 feed.setPayerName(stringTokenizer.nextToken());
		 feed.setPayerAccount(stringTokenizer.nextToken());
		 feed.setPayeeName(stringTokenizer.nextToken());
		 feed.setPayeeAccount(stringTokenizer.nextToken());
		 feed.setTransactionAmount(Double.parseDouble(stringTokenizer.nextToken()));//?check Datatype changes 00 truncation
		 validateInput();
		 feed.setFeedStatus(validateFile.getFeedStatus());
		 transactionList.add(feed);
		 //log.info(feed.toString());
	}
	
	//Date is parsed into day,month and year
	public TransactionDate parseDate(String dateString) {
		int day, month, year;
		// Date Format DD/MM/YYYY thus 2,2,4 split of dateString
		day = Integer.parseInt(dateString.substring(0, 2));
		month = Integer.parseInt(dateString.substring(2, 4));
		year = Integer.parseInt(dateString.substring(4, 8));
		return (new TransactionDate(day, month, year));
	}
	
	//All the validations are called
	public void validateInput() {
		log.info("Entering validateInput file ");

		if(validateFile.checkAlphanumericString(feed.getTransactionRef(), 12))
			log.info("\nValid Transaction Ref : "+feed.getTransactionRef());
		else
			log.info("Invalid Transaction Ref : "+feed.getTransactionRef());
		
		if(validateFile.checkAlphanumericString(feed.getPayeeName(), 35))
			log.info("Valid Payee name : "+feed.getPayeeName());
		else
			log.info("Invalid Payee name : "+feed.getPayeeName());

		if(validateFile.checkNumericString(feed.getPayeeAccount()))
			log.info("Valid Payee Account Number : "+feed.getPayerAccount());
		else
			log.info("Invalid Payee Account Number : "+feed.getPayerAccount());
		
		if(validateFile.checkDate(feed.getTransactionDate()))
			log.info("Valid Transaction Date : "+feed.getTransactionDate().toString());
		else
			log.info("Invalid Transaction Date : "+feed.getTransactionDate().toString());	
		
		if(validateFile.checkAlphanumericString(feed.getPayerName(), 35))
			log.info("Valid Payer name : "+feed.getPayerName());
		else
			log.info("Invalid Payer name : "+feed.getPayerName());

		if(validateFile.checkNumericString(feed.getPayerAccount()))
			log.info("Valid Payer Account Number : "+feed.getPayerAccount());
		else
			log.info("Invalid Payer Account Number : "+feed.getPayerAccount());
		
		if(validateFile.checkAmount(feed.getTransactionAmount()))
			log.info("Valid Transaction Amount : "+feed.getTransactionAmount());
		else
			log.info("Invalid Transaction Amount : "+feed.getTransactionAmount());

		log.info(validateFile.toString());
		log.info("Exiting validateInput file ");

	}
}
