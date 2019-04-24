package bean;
/*
 * This is the date bean class 
 * For accessing secific day,month and year of transaction
 * Functions : Getters and setters of different date parameters
 * used in transaction class
 * */
public class TransactionDate {
int day;
int month;
int year;

@Override
public String toString() {
	return day + "/" + month + "/" +year ;
}
public TransactionDate(int day, int month, int year) {
	super();
	this.day = day;
	this.month = month;
	this.year = year;
}
public int getDay() {
	return day;
}
public void setDay(int day) {
	this.day = day;
}
public int getMonth() {
	return month;
}
public void setMonth(int month) {
	this.month = month;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
}