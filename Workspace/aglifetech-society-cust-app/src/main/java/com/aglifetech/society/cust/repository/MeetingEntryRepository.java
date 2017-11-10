package com.aglifetech.society.cust.repository;

import com.aglifetech.society.cust.model.MeetingEntry;

public interface MeetingEntryRepository {
	
	public int addMeeting(MeetingEntry meetingEntry);
	
	public int deleteMeeting(MeetingEntry meetingEntry);
	
}