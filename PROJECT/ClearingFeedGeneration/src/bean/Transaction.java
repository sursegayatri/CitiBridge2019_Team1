package bean;

import java.util.Date;
/*
 * This is the bean class for accessing different
 * transaction parameters:
 * transaction refernce id 
 * payer name and payee name
 * payer account and payee account
 * Amount
 * transaction date 
 * feed Status
 * 
 * Functions : Getters and Setters of each parameter
 */
public class Transaction {
	private String transactionRef;
	private String payerName;
	private String payerAccount;
	private String payeeName;
	private String payeeAccount;
	private double transactionAmount; // ?find the datatype signed|unsigned
	private boolean feedStatus;
	private TransactionDate transactionDate;

	public boolean isFeedStatus() {
		return feedStatus;
	}

	public void setFeedStatus(boolean feedStatus) {
		this.feedStatus = feedStatus;
	}

	public String getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}

	public TransactionDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(TransactionDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return  transactionRef + " " + transactionDate.toString()
				+ " " + payerName + payerAccount + " " + payeeName + payeeAccount
				+ " " + transactionAmount + " " + feedStatus +"\n";
	}

	public String inputToString() {
		return "TransactionParameters [transactionRef=" + transactionRef + ", transactionDate=" + transactionDate.toString()
				+ ", payerName=" + payerName + ", payerAccount=" + payerAccount + ", payeeName=" + payeeName
				+ ", payeeAccount=" + payeeAccount + ", transactionAmount=" + transactionAmount + "]";
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerAccount() {
		return payerAccount;
	}

	public void setPayerAccount(String payerAccount) {
		this.payerAccount = payerAccount;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

}
/*
 * private int day; private int month; private int year;
 */