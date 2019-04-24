package upload;
/*
 * This class sends the path of file (selected on browser) for processing
 * 
 */
public class InitiateTransaction {

	public void Initiate(String filename) {
		ProcessInputFeeds inputTransaction = new ProcessInputFeeds(filename);
		inputTransaction.readFile();
	}

}
