package com.aglifetech.society.cust.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

			pStatement.setLong(1, meetingEntry.getSocietyAccountMasterId());
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

	@Override
	public LocalDate findLastMeetingDate(Long accountMaterId) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		LocalDate lastMeetDate = null;
		try {

			conn = MyConnectionManager.getConnection();

			String sql = "SELECT MAX(meeting_dt) FROM societyclient.meeting_nobook WHERE account_master_id=?";

			pStatement = conn.prepareStatement(sql);

			pStatement.setLong(1, accountMaterId);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				Date lastMeetingDate = resultSet.getDate(1);
				if (lastMeetingDate != null) {
					lastMeetDate = lastMeetingDate.toLocalDate();
				}
			}

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

					e.printStackTrace();
				}
			}

		}

		return lastMeetDate;
	}

	@Override
	public List<MeetingEntry> findAllMeetingsByAccountMasterId(Long accountMasterId) {

		List<MeetingEntry> meetings = new ArrayList<>();

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			conn = MyConnectionManager.getConnection();
			String sql = " select  ID ,society_master_id ,account_master_id ,ln_disbursed_amt ,total_paid_amt,"
					+ "meeting_dt ,user,created_dttm from societyclient.meeting_nobook"
					+ " where account_master_id = ? ORDER BY meeting_dt DESC";
			statement = conn.prepareStatement(sql);
			statement.setLong(1, accountMasterId);
			resultset = statement.executeQuery();
			while (resultset.next()) {
				Long id = resultset.getLong(1);
				Long socMasterId = resultset.getLong(2);
				Long acMasterId = resultset.getLong(3);
				double loanDis = resultset.getDouble(4);
				double totalPaid = resultset.getDouble(5);
				Date meetDt = resultset.getDate(6);
				LocalDate meetingDate = meetDt.toLocalDate();
				Timestamp tp = resultset.getTimestamp(7);
				LocalDateTime creDttm = tp.toLocalDateTime();
				String user = resultset.getString(8);

				MeetingEntry meetingEntry = new MeetingEntry();
				meetingEntry.setid(id);
				meetingEntry.setSocietyMasterId(socMasterId);
				meetingEntry.setSocietyAccountMasterId(acMasterId);
				meetingEntry.setLoanDisbursedAmount(loanDis);
				meetingEntry.setTotalPaidAmount(totalPaid);
				meetingEntry.setMeetingDate(meetingDate);
				meetingEntry.setCreatedDateTm(creDttm);
				meetingEntry.setUser(user);
				meetings.add(meetingEntry);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (statement != null) {
					try {
						statement.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (resultset != null) {
					try {
						resultset.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}

		return meetings;
	}
}