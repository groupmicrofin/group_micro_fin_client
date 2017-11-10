package com.aglifetech.society.cust.model;

import java.time.LocalDate;

/**
 * This is model class for Society.
 * 
 * @author Ankit
 * 
 */
public class Society {

	private Long id;
	private String societyRefId;
	private String societyName;
	private LocalDate societyStartDate;
	private double shareAmount;
	private double intrestRate;
	private String scheduleFrequency;

	private String user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSocietyRefId() {
		return societyRefId;
	}

	public void setSocietyRefId(String societyRefId) {
		this.societyRefId = societyRefId;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public LocalDate getSocietyStartDate() {
		return societyStartDate;
	}

	public void setSocietyStartDate(LocalDate societyStartDate) {
		this.societyStartDate = societyStartDate;
	}

	public double getShareAmount() {
		return shareAmount;
	}

	public void setShareAmount(double shareAmount) {
		this.shareAmount = shareAmount;
	}

	public double getIntrestRate() {
		return intrestRate;
	}

	public void setIntrestRate(double intrestRate) {
		this.intrestRate = intrestRate;
	}

	public String getScheduleFrequency() {
		return scheduleFrequency;
	}

	public void setScheduleFrequency(String scheduleFrequency) {
		this.scheduleFrequency = scheduleFrequency;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * This will be used in case of user opted to have meeting frequency on Date
	 * bases. i.e. any day from month.
	 * 
	 * @input dateBasedScheduleFrequency : below are value mapping for reference 99:
	 *        If user opt for month end date 0: if user opt for Day based logic <=28
	 *        values : Any dates <= 28
	 * @param dateBasedScheduleFrequency
	 */

	/**
	 * This will be used in case of user opted to have meeting frequency on Day
	 * bases. i.e. based on particular week day
	 * 
	 * @input dayBasedWeekDayIndx : Values expectation are as per below 0 : if user
	 *        opted for date based 1 : SUNDAY 2 : MONDAY upto 7: SATURDAY
	 * @return
	 */

	/**
	 * This will be used in case of user opted to have meeting frequency on Day
	 * bases. i.e. based on particular week day
	 * 
	 * @input dayBasedWeekIndx : Values expectation are as per below 0 : if user
	 *        opted for date based 1 : for first week, …. 4 for forth week, 99: for
	 *        last week
	 * @param dayBasedWeekIndx
	 */

	/**
	 * 
	 * @return
	 */

	public boolean equals(Object obj) {
		boolean result = false;
		if (obj == null) {
			return false;
		}
		if (obj instanceof Society) {
			Society other = (Society) obj;
			if (this.getId() == other.getId() && this.getSocietyRefId().equals(other.getSocietyRefId())) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Society [id=" + id + ", societyRefId=" + societyRefId + ", societyName=" + societyName
				+ ", societyStartDate=" + societyStartDate + ", shareAmount=" + shareAmount + ", intrestRate="
				+ intrestRate + ", scheduleFrequency=" + scheduleFrequency + ", user=" + user + "]";
	}

}
