
package com.aglifetech.society.cust.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.aglifetech.society.cust.model.LoanMaster;

public class LoanMasterRepositoryImpl implements LoanMasterRepository {
	
	@Override
	public int addLoanDetail(LoanMaster loanDtl) {
		
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;
		try {
			conn = MyConnectionManager.getConnection();
			String sql = "INSERT INTO societyclient.loan_master (  account_master_id, loan_disb_amount, pending_princi_loan,"
			        + " total_int_paid, created_dttm, updated_dttm,account_status,disbursment_dt,close_dt)"
			        + " VALUES ( ?, ?, ?, ?,Now(), Now(),?,?,?) ";
			
			pStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStatement.setLong(1, loanDtl.getAccountMasterId());
			pStatement.setDouble(2, loanDtl.getLoanDisbusmentAmount());
			pStatement.setDouble(3, loanDtl.getPendingPrincipleLoan());
			pStatement.setDouble(4, loanDtl.getTotalIntrestPaid());
			pStatement.setInt(5, loanDtl.getAccountStatus());
			pStatement.setDate(6, java.sql.Date.valueOf(loanDtl.getDisbursementDate()));
			if (loanDtl.getCloseDate() != null) {
				pStatement.setDate(7, java.sql.Date.valueOf(loanDtl.getCloseDate()));
			} else {
				pStatement.setDate(7, null);
			}
			pStatement.executeUpdate();
			resultSet = pStatement.getGeneratedKeys();
			if (resultSet.next()) {
				loanDtl.setId(resultSet.getLong(1));
				
			}
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
	public int updateDetail(LoanMaster loanDtl) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;
		
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "Update societyclient.loan_master set  pending_princi_loan=?, "
			        + "total_int_paid= ?,updated_dttm= Now(),account_status=?,close_dt=?  where Id = ?";
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setDouble(1, loanDtl.getPendingPrincipleLoan());
			pStatement.setDouble(2, loanDtl.getTotalIntrestPaid());
			pStatement.setInt(3, loanDtl.getAccountStatus());
			if (loanDtl.getCloseDate() != null) {
				pStatement.setDate(4, java.sql.Date.valueOf(loanDtl.getCloseDate()));
			} else {
				pStatement.setDate(4, null);
			}
			pStatement.setLong(5, loanDtl.getId());
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
	public int deleteLoanMasterDtl(LoanMaster loanDtl) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;
		
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "delete from  societyclient.loan_master where id = ? ";
			
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setLong(1, loanDtl.getId());
			
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
	public LoanMaster findLoanDetailByAccountId(Long accountMasterId) {
		
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		LoanMaster loanMaster = new LoanMaster();
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "select Id, account_master_id, loan_disb_amount, " + "pending_princi_loan, total_int_paid,"
			        + " account_status,disbursment_dt,close_dt from societyclient.loan_master where account_master_id = ? AND account_status=1 ";
			
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setLong(1, accountMasterId);
			resultSet = pStatement.executeQuery();
			
			while (resultSet.next()) {
				Long custId = resultSet.getLong(1);
				Long accountId = resultSet.getLong(2);
				double loanDisbusAmt = resultSet.getDouble(3);
				double pendingloanAmt = resultSet.getDouble(4);
				double intrestPaid = resultSet.getDouble(5);
				int accountStatus = resultSet.getInt(6);
				Date disburseDate = resultSet.getDate(7);
				LocalDate disDate = disburseDate.toLocalDate();
				
				Date clsDate = resultSet.getDate(8);
				if (clsDate == null) {
					loanMaster.setCloseDate(null);
				} else {
					LocalDate closeDate = clsDate.toLocalDate();
					loanMaster.setCloseDate(closeDate);
				}
				loanMaster.setId(custId);
				loanMaster.setAccountMasterId(accountId);
				loanMaster.setLoanDisbusmentAmount(loanDisbusAmt);
				loanMaster.setPendingPrincipleLoan(pendingloanAmt);
				loanMaster.setTotalIntrestPaid(intrestPaid);
				loanMaster.setAccountStatus(accountStatus);
				loanMaster.setDisbursementDate(disDate);
				
			}
			
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
		
		return loanMaster;
	}
	
	@Override
	public List<LoanMaster> getActiveLoanAccounts(Long acccountmasterId) {
		
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		List<LoanMaster> listOfLoanMaster = new ArrayList<>();
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "select Id, account_master_id, loan_disb_amount, " + "pending_princi_loan, total_int_paid,"
			        + " account_status,disbursment_dt,close_dt from societyclient.loan_master where account_master_id = ? and account_status=1 ";
			
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setLong(1, acccountmasterId);
			resultSet = pStatement.executeQuery();
			
			while (resultSet.next()) {
				Long custId = resultSet.getLong(1);
				Long accountId = resultSet.getLong(2);
				double loanDisbusAmt = resultSet.getDouble(3);
				double pendingloanAmt = resultSet.getDouble(4);
				double intrestPaid = resultSet.getDouble(5);
				int accountStatus = resultSet.getInt(6);
				Date disburseDate = resultSet.getDate(7);
				LocalDate disDate = disburseDate.toLocalDate();
				
				Date clsDate = resultSet.getDate(8);
				LoanMaster loanMaster = new LoanMaster();
				
				if (clsDate == null) {
					loanMaster.setCloseDate(null);
				} else {
					LocalDate closeDate = clsDate.toLocalDate();
					loanMaster.setCloseDate(closeDate);
				}
				loanMaster.setId(custId);
				loanMaster.setAccountMasterId(accountId);
				loanMaster.setLoanDisbusmentAmount(loanDisbusAmt);
				loanMaster.setPendingPrincipleLoan(pendingloanAmt);
				loanMaster.setTotalIntrestPaid(intrestPaid);
				loanMaster.setAccountStatus(accountStatus);
				loanMaster.setDisbursementDate(disDate);
				listOfLoanMaster.add(loanMaster);
				
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return listOfLoanMaster;
		
	}
	
	@Override
	public LoanMaster findLoanDetailByLoanMasterId(Long LoanMasterId) {
		
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		LoanMaster loanMaster = new LoanMaster();
		try {
			
			conn = MyConnectionManager.getConnection();
			
			String sql = "select Id, account_master_id, loan_disb_amount, " + "pending_princi_loan, total_int_paid,"
			        + " account_status,disbursment_dt,close_dt from societyclient.loan_master where Id = ?  ";
			
			pStatement = conn.prepareStatement(sql);
			
			pStatement.setLong(1, LoanMasterId);
			resultSet = pStatement.executeQuery();
			
			while (resultSet.next()) {
				Long custId = resultSet.getLong(1);
				Long accountId = resultSet.getLong(2);
				double loanDisbusAmt = resultSet.getDouble(3);
				double pendingloanAmt = resultSet.getDouble(4);
				double intrestPaid = resultSet.getDouble(5);
				int accountStatus = resultSet.getInt(6);
				Date disburseDate = resultSet.getDate(7);
				LocalDate disDate = disburseDate.toLocalDate();
				
				Date clsDate = resultSet.getDate(8);
				if (clsDate == null) {
					loanMaster.setCloseDate(null);
				} else {
					LocalDate closeDate = clsDate.toLocalDate();
					loanMaster.setCloseDate(closeDate);
				}
				loanMaster.setId(custId);
				loanMaster.setAccountMasterId(accountId);
				loanMaster.setLoanDisbusmentAmount(loanDisbusAmt);
				loanMaster.setPendingPrincipleLoan(pendingloanAmt);
				loanMaster.setTotalIntrestPaid(intrestPaid);
				loanMaster.setAccountStatus(accountStatus);
				loanMaster.setDisbursementDate(disDate);
				
			}
			
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
		
		return loanMaster;
	}
}
