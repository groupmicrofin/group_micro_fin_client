package com.aglifetech.society.cust.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.aglifetech.society.cust.model.LoanEntryBook;

public class LoanEntryBookRepositoryImpl implements LoanEntryBookRepository {
	
	@Override
	public int addLoanEntryDetail(LoanEntryBook loanDtl) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;
		try {
			conn = MyConnectionManager.getConnection();
			String sql = "INSERT INTO societyclient.loan_nobook (loan_master_id,txn_code,txn_amount,meeting_dt,audit_created_dttm )"
			        + " VALUES( ?, ?, ?, ?,Now())";
			
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setLong(1, loanDtl.getLoanMasterId());
			pStatement.setString(2, loanDtl.getTractionCode());
			pStatement.setDouble(3, loanDtl.getTxnAmt());
			pStatement.setDate(4, java.sql.Date.valueOf(loanDtl.getMeetingDate()));
			
			result = pStatement.execute();
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
	public List<LoanEntryBook> findLoanEntryDetailsByMeetingDate(Long accountMasterId, LocalDate meetDate) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		List<LoanEntryBook> loanEntryDetails = new ArrayList<>();
		
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "select nobook.loan_master_id,nobook.txn_code,nobook.txn_amount,nobook.meeting_dt\r\n"
			        + "from societyclient.loan_nobook nobook\r\n"
			        + "INNER JOIN societyclient.loan_master lnmaster on nobook.loan_master_id = lnmaster.id\r\n"
			        + "where lnmaster.account_master_id=?\r\n"
			        + "AND nobook.meeting_dt = ? ORDER BY audit_created_dttm";
			
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, accountMasterId);
			pStatement.setDate(2, java.sql.Date.valueOf(meetDate));
			resultSet = pStatement.executeQuery();
			
			while (resultSet.next()) {
				Long loanMasterId = resultSet.getLong(1);
				String txnCode = resultSet.getString(2);
				double txnAmt = resultSet.getDouble(3);
				Date meeettingDate = resultSet.getDate(4);
				LocalDate dateOfMeeting = meeettingDate.toLocalDate();
				
				LoanEntryBook loanEntry = new LoanEntryBook();
				
				loanEntry.setLoanMasterId(loanMasterId);
				loanEntry.setTractionCode(txnCode);
				loanEntry.setTxnAmt(txnAmt);
				loanEntry.setMeetingDate(dateOfMeeting);
				loanEntryDetails.add(loanEntry);
				
			}
			
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
		
		return loanEntryDetails;
		
	}
	
	@Override
	public int deleteLoanEntryDetail(LoanEntryBook loanEntryBook) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;
		
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "delete from  societyclient.loan_nobook where loan_master_id = ? AND txn_code=? AND meeting_dt=?";
			
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setLong(1, loanEntryBook.getLoanMasterId());
			pStatement.setString(2, loanEntryBook.getTractionCode());
			pStatement.setDate(3, java.sql.Date.valueOf(loanEntryBook.getMeetingDate()));
			
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
