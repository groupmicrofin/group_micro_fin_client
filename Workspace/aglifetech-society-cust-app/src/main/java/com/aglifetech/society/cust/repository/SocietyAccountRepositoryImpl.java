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

import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;

public class SocietyAccountRepositoryImpl implements SocietyAccountRepository {
	
	@Override
	public int addSociety(SocietyAccount societyAc) {
		
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;
		
		try {
			
			// Step - 2 Get Database Connection
			conn = MyConnectionManager.getConnection();
			
			String sql = "INSERT INTO societyclient.account_master ( society_master_id	, society_account_refNo, member_name,"
			        + " email, phone_no, photo_id,user)" + " VALUES ( ?, ?, ?, ?, ?,?,?) ";
			// Step- 3 create Database Statement
			pStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStatement.setLong(1, societyAc.getSocietyMasterID());
			pStatement.setString(2, societyAc.getSocietyAccountId());
			pStatement.setString(3, societyAc.getMemberName());
			// pStatement.setDouble(4, societyAc.getPendingPrincipalLoanAmount());
			pStatement.setString(4, societyAc.getEmailId());
			pStatement.setString(5, societyAc.getPhoneNum());
			pStatement.setString(6, societyAc.getPhotoId());
			// pStatement.setDate(7, java.sql.Date.valueOf(societyAc.getLastMeetingDate()));
			/*
			 * LocalDateTime locTm = societyAc.getAlertDatetime(); Timestamp timestamp =
			 * Timestamp.valueOf(locTm); pStatement.setTimestamp(8, timestamp);
			 */
			pStatement.setString(7, societyAc.getUser());
			// pStatement.setLong(9, societyAc.getiD());
			
			pStatement.executeUpdate();
			resultSet = pStatement.getGeneratedKeys();
			if (resultSet.next()) {
				societyAc.setid(resultSet.getLong(1));
			}
			
			rowsInserted = pStatement.getUpdateCount();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			if (pStatement != null) {
				try {
					pStatement.close();
				} catch (SQLException e) {
					
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
		
		return rowsInserted;
		
	}
	
	@Override
	public int updateSociety(SocietyAccount societyAc) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;
		
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "Update societyclient.account_master set email=?, phone_no=?,user=?,last_meeting_dt=?, alert_dttm= ?where ID = ?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, societyAc.getEmailId());
			pStatement.setString(2, societyAc.getPhoneNum());
			pStatement.setString(3, societyAc.getUser());
			pStatement.setDate(4, java.sql.Date.valueOf(societyAc.getLastMeetingDate()));
			
			LocalDateTime locTm = societyAc.getAlertDatetime();
			Timestamp timestamp = Timestamp.valueOf(locTm);
			pStatement.setTimestamp(5, timestamp);
			pStatement.setLong(6, societyAc.getid());
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
	public int deleteSociety(SocietyAccount societyAc) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;
		
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "delete from  societyclient.account_master where ID = ? ";
			
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setLong(1, societyAc.getid());
			
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
	
	/**
	 * This method will take society id as an input and returns all society accounts
	 * 
	 * @param societyId
	 * @return
	 */
	public List<SocietyAccount> findSocietyAccountsBySocietyId(Long societyId) {
		List<SocietyAccount> socAccounts = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			conn = MyConnectionManager.getConnection();
			String sql = " select  ID,society_master_id,society_account_refNo,member_name,email,\r\n"
			        + " phone_no,photo_id,last_meeting_dt,alert_dttm,user\r\n"
			        + "          from societyclient.account_master where society_master_id = ? ";
			statement = conn.prepareStatement(sql);
			statement.setLong(1, societyId);
			resultset = statement.executeQuery();
			while (resultset.next()) {
				Long id = resultset.getLong(1);
				Long masterId = resultset.getLong(2);
				String acRefno = resultset.getString(3);
				String memName = resultset.getString(4);
				String email = resultset.getString(5);
				String phoneNum = resultset.getString(6);
				String photoId = resultset.getString(7);
				Date lastMeetDt = resultset.getDate(8);
				LocalDate lastMeetingDate = lastMeetDt.toLocalDate();
				Timestamp tp = resultset.getTimestamp(9);
				LocalDateTime alertDttm = tp.toLocalDateTime();
				String user = resultset.getString(10);
				
				SocietyAccount socAc = new SocietyAccount();
				socAc.setid(id);
				socAc.setSocietyMasterID(masterId);
				socAc.setSocietyAccountId(acRefno);
				socAc.setMemberName(memName);
				
				socAc.setEmailId(email);
				socAc.setPhoneNum(phoneNum);
				socAc.setPhotoId(photoId);
				socAc.setLastMeetingDate(lastMeetingDate);
				socAc.setAlertDatetime(alertDttm);
				socAc.setUser(user);
				
				socAccounts.add(socAc);
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
		
		return socAccounts;
	}
	
	@Override
	public SocietyAccount findSocietyAccountById(Long id) {
		// boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		// int rowsInserted = 0;
		SocietyAccount socAc = new SocietyAccount();
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "select ID, society_master_id, society_account_refNo, member_name,"
			        + "  email, phone_no, photo_id,last_meeting_dt,alert_dttm,user"
			        + "  from societyclient.account_master where ID = ? ";
			
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, id);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Long custId = resultSet.getLong(1);
				Long socrefNo = resultSet.getLong(2);
				String socAcId = resultSet.getString(3);
				String memberName = resultSet.getString(4);
				String email = resultSet.getString(5);
				String phoneNum = resultSet.getString(6);
				String photoId = resultSet.getString(7);
				Date lastMeet = resultSet.getDate(8);
				LocalDate lastMeetingDate = null;
				if (lastMeet != null) {
					lastMeetingDate = lastMeet.toLocalDate();
				}
				Timestamp tp = resultSet.getTimestamp(9);
				LocalDateTime alertDttm = null;
				if (tp != null) {
					alertDttm = tp.toLocalDateTime();
				}
				String userI = resultSet.getString(10);
				
				socAc.setid(custId);
				socAc.setSocietyMasterID(socrefNo);
				socAc.setSocietyAccountId(socAcId);
				socAc.setMemberName(memberName);
				socAc.setEmailId(email);
				socAc.setPhoneNum(phoneNum);
				socAc.setPhotoId(photoId);
				socAc.setLastMeetingDate(lastMeetingDate);
				socAc.setAlertDatetime(alertDttm);
				socAc.setUser(userI);
				
			}
			
		} catch (
		
		SQLException e) {
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
		
		return socAc;
	}
	
	@Override
	public Society findSocietyByAccountId(Long accountmasterId) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		Society society = new Society();
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "SELECT master.ID,society_ref_no, society_name, society_start_dt, share_amount, int_rate, schedule_frequency,\r\n"
			        + "					 master.user\r\n"
			        + "FROM societyclient.society_master MASTER INNER JOIN societyclient.account_master account ON master.id = account.society_master_id\r\n"
			        + "WHERE account.id = ?";
			
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setLong(1, accountmasterId);
			
			resultSet = pStatement.executeQuery();
			
			while (resultSet.next()) {
				Long custId = resultSet.getLong(1);
				String socRefno = resultSet.getString(2);
				String socName = resultSet.getString(3);
				Date societyStrdate = resultSet.getDate(4);
				LocalDate socStrDate = societyStrdate.toLocalDate();
				double shareAmt = resultSet.getDouble(5);
				double intRate = resultSet.getDouble(6);
				String scheduleFreq = resultSet.getString(7);
				String user = resultSet.getString(8);
				
				society.setId(custId);
				society.setSocietyRefId(socRefno);
				society.setSocietyStartDate(socStrDate);
				society.setSocietyName(socName);
				society.setShareAmount(shareAmt);
				society.setIntrestRate(intRate);
				society.setScheduleFrequency(scheduleFreq);
				society.setUser(user);
				
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
		
		return society;
		
	}
	
}
