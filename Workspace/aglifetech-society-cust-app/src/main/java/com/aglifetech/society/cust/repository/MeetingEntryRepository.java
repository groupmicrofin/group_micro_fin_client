package com.aglifetech.society.cust.repository;

import java.time.LocalDate;
import java.util.List;

import com.aglifetech.society.cust.model.MeetingEntry;

public interface MeetingEntryRepository {

	public int addMeeting(MeetingEntry meetingEntry);

	public int deleteMeeting(MeetingEntry meetingEntry);

	public LocalDate findLastMeetingDate(Long accountMaterId);

	public List<MeetingEntry> findAllMeetingsByAccountMasterId(Long accountMasterId);
}
