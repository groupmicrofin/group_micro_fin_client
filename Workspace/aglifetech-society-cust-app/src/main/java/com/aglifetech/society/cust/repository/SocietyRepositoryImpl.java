package com.aglifetech.society.cust.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.aglifetech.society.cust.model.Society;

public class SocietyRepositoryImpl implements SocietyRepository {

	@Override
	public int addSociety(Society society) {

		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;

		try {

			// Step - 2 Get Database Connection
			conn = MyConnectionManager.getConnection();

			String sql = "INSERT INTO societyclient.society_master ( society_ref_no, society_name, society_start_dt, share_amount,"
					+ " int_rate, schedule_frequency, created_dttm, last_updated_dttm, user)"
					+ " VALUES ( ?, ?, ?, ?, ?, ?, Now(), Now(), ?)";
			// Step- 3 create Database Statement
			pStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			pStatement.setString(1, society.getSocietyRefId());
			pStatement.setString(2, society.getSocietyName());
			pStatement.setDate(3, java.sql.Date.valueOf(society.getSocietyStartDate()));
			pStatement.setDouble(4, society.getShareAmount());
			pStatement.setDouble(5, society.getIntrestRate());
			pStatement.setString(6, society.getScheduleFrequency());

			pStatement.setString(7, society.getUser());

			pStatement.executeUpdate();
			resultSet = pStatement.getGeneratedKeys();
			if (resultSet.next()) {
				society.setId(resultSet.getLong(1));

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
	public int updateSociety(Society society) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;

		try {

			// Step - 2 Get Database Connection
			conn = MyConnectionManager.getConnection();

			String sql = "Update societyclient.society_master set share_amount = ? , int_rate =?,schedule_frequency=?,last_updated_dttm = Now() ,user = ? where ID = ?";
			// Step- 3 create Database Statement
			pStatement = conn.prepareStatement(sql);

			pStatement.setDouble(1, society.getShareAmount());
			pStatement.setDouble(2, society.getIntrestRate());
			pStatement.setString(3, society.getScheduleFrequency());
			pStatement.setString(4, society.getUser());
			pStatement.setLong(5, society.getId());

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
	public int deleteSociety(Society society) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int rowsInserted = 0;

		try {

			// Step - 2 Get Database Connection
			conn = MyConnectionManager.getConnection();

			String sql = "delete from  societyclient.society_master where id = ? ";

			// Step- 3 create Database Statement
			pStatement = conn.prepareStatement(sql);

			pStatement.setLong(1, society.getId());

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
	public List<Society> findAllSocieties() {

		List<Society> socList = new ArrayList<Society>();
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			conn = MyConnectionManager.getConnection();
			String sql = "select ID,society_ref_no, society_name, society_start_dt, share_amount, "
					+ "int_rate, schedule_frequency,  created_dttm,"
					+ " last_updated_dttm, user from societyclient.society_master  ";

			pStatement = conn.prepareStatement(sql);

			Long custId;
			custId = resultSet.getLong(1);
			String refNo = resultSet.getString(2);
			String nameOfSoc = resultSet.getString(3);
			Date strDate = resultSet.getDate(4);
			LocalDate strDateLoc = strDate.toLocalDate();
			double shareAmt = resultSet.getDouble(5);
			double intRate = resultSet.getDouble(6);
			String scheduleFeq = resultSet.getString(7);
			String user = resultSet.getString(8);

			Society soc = new Society();
			soc.setId(custId);
			soc.setSocietyRefId(refNo);
			soc.setSocietyName(nameOfSoc);
			soc.setSocietyStartDate(strDateLoc);
			soc.setShareAmount(shareAmt);
			soc.setIntrestRate(intRate);
			soc.setScheduleFrequency(scheduleFeq);
			soc.setUser(user);
			socList.add(soc);

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

					e.printStackTrace();
				}
			}
		}
		return socList;
	}

	@Override
	public Society findSocietyById(Long societymasterId) {

		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;

		Society soc = new Society();
		try {

			// Step - 2 Get Database Connection
			conn = MyConnectionManager.getConnection();

			String sql = "select ID,society_ref_no, society_name, society_start_dt, share_amount, int_rate, \r\n"
					+ "schedule_frequency,created_dttm, last_updated_dttm, \r\n"
					+ "user from societyclient.society_master where ID = ? ";

			// Step- 3 create Database Statement
			pStatement = conn.prepareStatement(sql);

			pStatement.setLong(1, societymasterId);
			resultSet = pStatement.executeQuery();
			// result = pStatement.execute();

			while (resultSet.next()) {
				Long custId = resultSet.getLong(1);
				String refNo = resultSet.getString(2);
				String nameOfSoc = resultSet.getString(3);
				Date strDate = resultSet.getDate(4);
				LocalDate strDateLoc = strDate.toLocalDate();
				double shareAmt = resultSet.getDouble(5);
				double intRate = resultSet.getDouble(6);
				String scheduleFeq = resultSet.getString(7);
				String user = resultSet.getString(8);

				soc.setId(custId);
				soc.setSocietyRefId(refNo);
				soc.setSocietyName(nameOfSoc);
				soc.setSocietyStartDate(strDateLoc);
				soc.setShareAmount(shareAmt);
				soc.setIntrestRate(intRate);
				soc.setScheduleFrequency(scheduleFeq);
				soc.setUser(user);

			}

			// rowsInserted = pStatement.getUpdateCount();

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

		// return rowsInserted ;
		return soc;
	}

	@Override
	public Society findSocietyByRefId(String userSocietyId) {

		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;

		Society soc = new Society();
		try {

			// Step - 2 Get Database Connection
			conn = MyConnectionManager.getConnection();

			String sql = "select ID,society_ref_no, society_name, society_start_dt, share_amount, int_rate, \r\n"
					+ "schedule_frequency, created_dttm, last_updated_dttm, \r\n"
					+ "user from societyclient.society_master where society_ref_no = ? ";

			// Step- 3 create Database Statement
			pStatement = conn.prepareStatement(sql);

			pStatement.setString(1, userSocietyId);
			resultSet = pStatement.executeQuery();
			// result = pStatement.execute();

			if (resultSet.next()) {
				Long custId = resultSet.getLong(1);
				String refNo = resultSet.getString(2);
				String nameOfSoc = resultSet.getString(3);
				Date strDate = resultSet.getDate(4);
				LocalDate strDateLoc = strDate.toLocalDate();
				double shareAmt = resultSet.getDouble(5);
				double intRate = resultSet.getDouble(6);
				String scheduleFeq = resultSet.getString(7);
				String user = resultSet.getString(8);

				soc.setId(custId);
				soc.setSocietyRefId(refNo);
				soc.setSocietyName(nameOfSoc);
				soc.setSocietyStartDate(strDateLoc);
				soc.setShareAmount(shareAmt);
				soc.setIntrestRate(intRate);
				soc.setScheduleFrequency(scheduleFeq);
				soc.setUser(user);

			}

			// rowsInserted = pStatement.getUpdateCount();

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

		// return rowsInserted ;
		return soc;
	}

}
