package creditcard.model;

/**
 * 
 * This is the class for Credit Card
 * 
 * @author Group S
 *
 */
public class CreditCard {

	private String creditCard;
	private int month;
	private int year;
	private int securityCode;

	/**
	 * @return the creditCard
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the securityCode
	 */
	public int getSecurityCode() {
		return securityCode;
	}

	/**
	 * @param securityCode the securityCode to set
	 */
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

}
