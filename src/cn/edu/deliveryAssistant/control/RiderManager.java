package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.model.BeanRider;
import cn.edu.deliveryAssistant.model.MerchandiseEvaluate;
import cn.edu.deliveryAssistant.model.RiderReceive;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class RiderManager{

	//显示所有骑手
	public List<BeanRider> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanRider> result=new ArrayList<BeanRider>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM rider";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanRider rider=new BeanRider();
				rider.setRider_id(rs.getInt(1));
				rider.setRider_name(rs.getString(2));
				rider.setDate_on_board(rs.getDate(3));
				rider.setRider_status(rs.getString(4));
				result.add(rider);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	//抽取一位骑手
	public BeanRider loadRandom() throws BaseException {
		// TODO Auto-generated method stub
		BeanRider rider =new BeanRider();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM rider WHERE rider_id NOT in(\r\n" + 
					"SELECT rider_id FROM merchandise_order WHERE merchandise_order.order_status='配送中')";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rider.setRider_id(rs.getInt(1));
				rider.setRider_name(rs.getString(2));
				rider.setDate_on_board(rs.getDate(3));
				rider.setRider_status(rs.getString(4));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return rider;
	}

	//模糊查找某骑手
	public List<BeanRider> search(String rider_name) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanRider> result=new ArrayList<BeanRider>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM rider where rider_name like ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+rider_name+"%");
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanRider rider=new BeanRider();
				rider.setRider_id(rs.getInt(1));
				rider.setRider_name(rs.getString(2));
				rider.setDate_on_board(rs.getDate(3));
				rider.setRider_status(rs.getString(4));
				result.add(rider);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	//对骑手的评价
	public List<String> loadEva(BeanRider rider) throws BaseException {
		List<String> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT user_evaluate FROM rider_receive WHERE rider_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, rider.getRider_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				result.add(rs.getString(1));
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	//用户评价
	public RiderReceive userEvaluate(MerchandiseEvaluate merchandiseEvaluate) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	//骑手接单数和总得
	public List<BeanRider> countOrdre() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanRider> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT rider.rider_id,rider_name,date_on_board,rider_status,COUNT(*),sum(rider_receive.Single_income)\r\n" + 
					"FROM ((rider LEFT JOIN merchandise_order ON rider.rider_id=merchandise_order.rider_id) LEFT JOIN rider_receive ON rider.rider_id=rider_receive.rider_id)\r\n" + 
					"GROUP BY rider.rider_id";
			java.sql.Statement st=conn.createStatement();
			//pst.setInt(1, rider.getRider_id());
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanRider rider=new BeanRider();
				rider.setRider_id(rs.getInt(1));
				rider.setRider_name(rs.getString(2));
				rider.setDate_on_board(rs.getDate(3));
				rider.setRider_status(rs.getString(4));
				rider.setOrder_num(rs.getInt(5));
				rider.setSum_income(rs.getDouble(6));
				result.add(rider);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	//添加骑手
	public BeanRider addRider(String rider_name,String status) throws BaseException {
		// TODO Auto-generated method stub
		if(rider_name==null||"".equals(rider_name)) throw new BusinessException("骑手名不能为空");
		if(status==null||"".equals(status)) throw new BusinessException("身份不能为空");
		BeanRider rider =new BeanRider();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from rider where rider_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString( 1, rider_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("骑手名已存在");
			rs.close();
			pst.close();
			
			sql="select max(rider_id) from rider";
			java.sql.Statement st=conn.createStatement();
			rs=st.executeQuery(sql);
			int rid=0;
			if(rs.next()) rid=rs.getInt(1)+1;
			else rid=1;
			rs.close();
			st.close();
			
			sql="INSERT into rider VALUES(?,?,?,?)";
			Date date=new Date(System.currentTimeMillis());
			pst=conn.prepareStatement(sql);
			pst.setInt(1, rid);
			pst.setString(2, rider_name);
			pst.setDate(3, date);
			pst.setString(4, status);
			pst.executeUpdate();
			rider.setRider_id(rid);
			rider.setRider_name(rider_name);
			rider.setDate_on_board(date);
			rider.setRider_status(status);
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return rider;
	}

	//删除骑手
	public void deleteRider(BeanRider rider) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from rider where rider_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, rider.getRider_id());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}
