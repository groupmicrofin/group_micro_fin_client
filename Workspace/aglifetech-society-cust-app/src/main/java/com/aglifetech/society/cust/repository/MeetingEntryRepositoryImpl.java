package com.aglifetech.society.cust.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aglifetech.society.cust.model.MeetingEntry;

public class MeetingEntryRepositoryImpl implements MeetingEntryRepository {
	
	@Override
	public int addMeeting(MeetingEntry meetingEntry) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;
		try {
			conn = MyConnectionManager.getConnection();
			String sql = "INSERT INTO societyclient.meeting_nobook (  society_master_id, account_master_id, ln_disbursed_amt,"
			        + " total_paid_amt, meeting_dt, user,created_dttm)" + " VALUES ( ?, ?, ?, ?, ?, ?, Now()) ";
			
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setLong(1, meetingEntry.getSocietyMasterId());
			pStatement.setLong(2, meetingEntry.getSocietyAccountMasterId());
			pStatement.setDouble(3, meetingEntry.getLoanDisbursedAmount());
			pStatement.setDouble(4, meetingEntry.getTotalPaidAmount());
			pStatement.setDate(5, java.sql.Date.valueOf(meetingEntry.getMeetingDate()));
			pStatement.setString(6, meetingEntry.getUser());
			
			pStatement.execute();
			
			rowsInserted = pStatement.getUpdateCount();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			if (pStatement != null) {
				try {
					pStatement.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			
		}
		
		return rowsInserted;
		
	}
	
	@Override
	public int deleteMeeting(MeetingEntry meetingEntry) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;
		
		try {
			
			// Step - 2 Get Database Connection
			conn = MyConnectionManager.getConnection();
			
			String sql = "delete from  societyclient.meeting_nobook where account_master_id=? AND meeting_dt = ?";
			
			// Step- 3 create Database Statement
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setLong(1, meetingEntry.getid());
			pStatement.setDate(2, java.sql.Date.valueOf(meetingEntry.getMeetingDate()));
			
			result = pStatement.execute();
			
			rowsInserted = pStatement.getUpdateCount();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pStatement != null) {
				try {
					pStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return rowsInserted;
	}
	
}
