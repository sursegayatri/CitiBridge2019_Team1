package validations;

import java.io.*;
import java.util.Date;

import bean.TransactionDate;

/*
 * /*
 * This is the Validation class for validating different 
 * transaction parameters (size,validation):
 * transaction refernce id (12,alphanumeric)
 * payer name and payee name (35,alphanumeric)
 * payer account and payee account(12,alphanumeric)
 * Amount(10 digit and 2 decimal)
 * transaction date : should be current date neither before nor after
 * feed Status (true or false)
 * 
 * Flags is set for each type of error encountered in the code 
 * these flags are logged in the logger
 * Flags(6 date flags,5 number and character checks,2 decimal checks):
 * invalidCharacterFlag,invalidNumberFlag,
 * nameLengthExceeded,numberLengthInvalid,
 * dateBeforeFlag,dateAfterFlag, invalidDateFormat,
 *  invalidMonthFlag,invalidDayFlag,invalidYearFlag,
 *  InvalidAmountFlag, balanceExceededFlag,invalidDateOccurance;
 * Functions : 
 * checkAlphanumericString()|checkSpecialCharacter()|checkNumericString()
 * checkDate()|checkDateFormat()|checkDay()
 * checkTransactionOccuranceDate()
 * getFeedStatus()
 */
public class ValidateInputFile {
	boolean invalidCharacterFlag;
	boolean invalidNumberFlag;
	boolean dateBeforeFlag;
	boolean dateAfterFlag;
	boolean invalidDateFormat;
	boolean nameLengthExceeded;
	boolean numberLengthInvalid;
	boolean invalidMonthFlag;
	boolean invalidDayFlag;
	boolean InvalidAmountFlag;
	boolean balanceExceededFlag;
	boolean invalidYearFlag;
	boolean invalidDateOccurance;

	public ValidateInputFile() {
		invalidCharacterFlag = false;
		invalidNumberFlag = false;
		dateBeforeFlag = false;
		dateAfterFlag = false;
		invalidDateFormat = false;
		nameLengthExceeded = false;
		numberLengthInvalid = false;
		invalidMonthFlag = false;
		invalidDayFlag = false;
		InvalidAmountFlag = false;
		balanceExceededFlag = false;
		invalidYearFlag = false;
		invalidDateOccurance = false;
	}

	/*
	 * checks if the string contains only characters Returns true if completely
	 * alphabet based string else false + takes space and dot as normal character 48
	 * - 57 are 0-9 65 - 90 are A-Z numbers 97 - 122 are a-z 46 is '.' 45 is - 32 is
	 * [space]
	 */
	public boolean checkAlphanumericString(String n, int lengthConstraint) {
		if (n.length() == 0) // Must not be a null
			return false;
		else if (n.length() > lengthConstraint) {// like string length <=35
			nameLengthExceeded = true;
			return false;
		}
		for (int i = 0; i < n.length(); i++) {
			if (n.charAt(i) > 57 || n.charAt(i) < 48) {// not 0-9
				// check further only if characters allowed
				if ((n.charAt(i) > 90 && n.charAt(i) < 97) || (n.charAt(i) > 122) || (n.charAt(i) < 65)) {// not A-Z //
																											// |a-z
					if (specialCharacter(n.charAt(i)) == false) {// Not an allowed special character
						invalidCharacterFlag = true;
						return false;
					}
				}
			}
		}
		return true; // String passes the test cases

	}

	public boolean specialCharacter(char c) {
		if (c == 45 || c == 46)
			return true;
		else
			return false;
	}

	public boolean checkNumericString(String n) {
		if (n.length() == 0) // Must not be a null string & <=12
			return false;
		else if (n.length() > 12) {
			numberLengthInvalid = true;
			return false;
		}
		for (int i = 0; i < n.length(); i++) {
			if (n.charAt(i) > 57 || n.charAt(i) < 48) {// not 0-9
				invalidNumberFlag = true;
				return false;
			}
		}
		return true; // String passes the test cases

	}

	public boolean checkDate(TransactionDate transactionDate) {
		return checkDateFormat(transactionDate.getDay(), transactionDate.getMonth(), transactionDate.getYear())
				&& checkTransactionOccuranceDate(transactionDate.getDay(), transactionDate.getMonth(),
						transactionDate.getYear());

	}

	public boolean checkDateFormat(int day, int month, int year) {

		if (year > 9999 || year < 1000)
			invalidYearFlag = true;
		if (month > 12 || month < 1) {
			invalidMonthFlag = true;
		}
		if (checkDay(day, month, year) == false) {
			invalidDayFlag = true;
		}
		if (invalidDayFlag == false && invalidYearFlag == false && invalidMonthFlag == false)
			checkTransactionOccuranceDate(day, month, year);
		else {
			invalidDateFormat = true;
			return false;
		}
		return true;
	}

	public boolean checkDay(int day, int month, int year) {
		if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
				&& (day > 0 && day <= 31))
			return true;
		else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 0 && day <= 30))
			return true;
		else if ((year % 4 != 0 && day > 0 && day <= 28) || (year % 4 == 0 && day > 0 && day <= 29))
			return true;
		return false;
	}

	public boolean checkTransactionOccuranceDate(int day, int month, int year) {
		Date currentDate = new Date();
		int currentDay = currentDate.getDate();
		int currentMonth = currentDate.getMonth() + 1;
		int currentYear = currentDate.getYear() + 1900;
		// System.out.println("\nCurrent : " + currentDay + currentMonth + currentYear +
		// "\nTransaction Date : " + day
		// + month + year);

		if (day < currentDay || month < currentMonth || year < currentYear) {
			dateBeforeFlag = true;// transactionDate before currentDate
			return false;
		} else if (day > currentDay || month > currentMonth || year > currentYear) {
			dateAfterFlag = true;// transactionDate after currentDate
			return false;
		}
		return true;
	}

	public boolean checkAmount(double amount) {

		if (amount < 0.00) {
			InvalidAmountFlag = true;
			return false;
		} else if (amount > 9999999999.99) {
			balanceExceededFlag = true;
			return false;
		}

		return true;
	}

	//returns true only if all flags are false i.e. no error
	public boolean getFeedStatus() {
		if (invalidCharacterFlag == false && invalidNumberFlag == false && dateBeforeFlag == false
				&& dateAfterFlag == false && invalidDateFormat == false && nameLengthExceeded == false
				&& numberLengthInvalid == false && invalidMonthFlag == false && invalidDayFlag == false
				&& InvalidAmountFlag == false && balanceExceededFlag == false && invalidYearFlag == false
				&& invalidDateOccurance == false) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ValidateInputFile [invalidCharacterFlag=" + invalidCharacterFlag + ", invalidNumberFlag="
				+ invalidNumberFlag + ", dateBeforeFlag=" + dateBeforeFlag + ", dateAfterFlag=" + dateAfterFlag
				+ ", invalidDateFormat=" + invalidDateFormat + ", nameLengthExceeded=" + nameLengthExceeded
				+ ", numberLengthInvalid=" + numberLengthInvalid + ", invalidMonthFlag=" + invalidMonthFlag
				+ ", invalidDayFlag=" + invalidDayFlag + ", InvalidAmountFlag=" + InvalidAmountFlag
				+ ", balanceExceededFlag=" + balanceExceededFlag + ", invalidYearFlag=" + invalidYearFlag
				+ ", invalidDateOccurance=" + invalidDateOccurance + "]";
	}

}
