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

//	@Override
//	public BeanRider search(BeanRider rider) throws BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}

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
	public List<String> load(BeanRider rider) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	//用户评价
	public RiderReceive userEvaluate(MerchandiseEvaluate merchandiseEvaluate) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	//骑手接单数
	public int countOrdre(BeanRider rider) throws BaseException {
		// TODO Auto-generated method stub
		int n=0;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT count(*) FROM merchandise_order WHERE rider_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, rider.getRider_id());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			n=rs.getInt(1);
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
		return n;
	}

	//骑手总得
	public double sumMoney(BeanRider rider) throws BaseException {
		// TODO Auto-generated method stub
		double sum=0;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT sum(Single_income) FROM rider_receive WHERE rider_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, rider.getRider_id());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.next();
			sum=rs.getDouble(1);
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
		return sum;
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
