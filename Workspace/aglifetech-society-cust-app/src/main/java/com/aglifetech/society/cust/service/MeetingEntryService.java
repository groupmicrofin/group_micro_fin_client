package com.aglifetech.society.cust.service;

import com.aglifetech.society.cust.model.MeetingEntry;

public interface MeetingEntryService {

	public void addMeeting(MeetingEntry meetingentry);

	public void deleteLastMeeting(MeetingEntry meetingEntry);

}
