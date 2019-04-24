package feedgeneration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import bean.Transaction;
import bean.Transaction;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/*
 * This class is used to split the processed feed into
 * 3 different folders : valid,invalid,feed
 * each folder contains filename in the following format:
 * <original_file_name>+<current_date>+ valid|invalid|feed 
 * (keywords according to folder) in tct format. 
 * e.g. sample_20032018_valid.txt
 * 
 * Also the path of where the file is stored is added to the logger
 * Functions: ClearingFeedFile()
 * */
public class ClearingFeedFile {
	// creating a logger object
	private static final Logger log = LogManager.getLogger(ClearingFeedFile.class);
	String outputString = new String();

	public void clearingFeed(ArrayList<Transaction> transactionList, String filename) {
		log.info("Entering clear feed log");
		Transaction tr = new Transaction();
		Date currentDate = new Date();
		String fileDate = "_" + currentDate.getDate() + (currentDate.getMonth() + 1) + (currentDate.getYear() + 1900);
		String archiveFolderPath = new String("C:\\Users\\sneha\\Documents\\archive\\");
		String subFileName = filename.substring(filename.lastIndexOf('\\') + 1, filename.lastIndexOf('.'));
		log.info("date : " + currentDate.getDate() + (currentDate.getMonth() + 1)
				+ (currentDate.getYear() + 1900) + " time : " + currentDate.getTime());
		log.info("Old file path : " + filename + "\nFile name : " + subFileName);
		log.info("New File path : " + archiveFolderPath + subFileName + ".txt");
		Iterator<Transaction> itr = transactionList.listIterator();
		try {
			FileOutputStream fvalid = new FileOutputStream(
					archiveFolderPath + "valid\\" + subFileName + fileDate + "_valid.txt");
			FileOutputStream finvalid = new FileOutputStream(
					new File(archiveFolderPath + "invalid\\" + subFileName + fileDate + "_invalid.txt"));
			FileOutputStream foutput = new FileOutputStream(
					new File(archiveFolderPath + "feed\\" + subFileName + fileDate + "_feed.txt"));
			while (itr.hasNext()) {
				tr = itr.next();
				if (tr.isFeedStatus())
					fvalid.write(tr.toString().getBytes());
				else if (!tr.isFeedStatus())
					finvalid.write(tr.toString().getBytes());
				foutput.write(tr.toString().getBytes());
			}
			fvalid.close();
			finvalid.close();
			foutput.close();
			log.info("Exiting clear feed ...");
		} catch (FileNotFoundException e) {
			log.error("File not found");
		} catch (IOException e) {
			log.error("Error initializing stream");
		} 

	}
}
