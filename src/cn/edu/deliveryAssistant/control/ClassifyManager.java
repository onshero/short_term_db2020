package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.itf.IClassifyManager;
import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class ClassifyManager implements IClassifyManager {

	@Override
	public List<BeanMerchandiseClassify> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanMerchandiseClassify> result=new ArrayList<BeanMerchandiseClassify>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_classify GROUP BY merchant_id";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanMerchandiseClassify classify=new BeanMerchandiseClassify();
				classify.setClassify_id(rs.getInt(1));
				classify.setMerchant_id(rs.getInt(2));
				classify.setClassify_name(rs.getString(3));
				classify.setMerchandise_number(rs.getInt(4));
				result.add(classify);
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

	@Override
	public List<BeanMerchandiseClassify> loadAll(BeanMerchant merchant) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanMerchandiseClassify> result=new ArrayList<BeanMerchandiseClassify>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_classify WHERE merchant_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanMerchandiseClassify classify=new BeanMerchandiseClassify();
				classify.setClassify_id(rs.getInt(1));
				classify.setMerchant_id(rs.getInt(2));
				classify.setClassify_name(rs.getString(3));
				classify.setMerchandise_number(rs.getInt(4));
				result.add(classify);
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

	@Override
	public List<BeanMerchandiseClassify> loadAll(int merchant_id) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanMerchandiseClassify> result=new ArrayList<BeanMerchandiseClassify>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_classify WHERE merchant_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant_id);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanMerchandiseClassify classify=new BeanMerchandiseClassify();
				classify.setClassify_id(rs.getInt(1));
				classify.setMerchant_id(rs.getInt(2));
				classify.setClassify_name(rs.getString(3));
				classify.setMerchandise_number(rs.getInt(4));
				result.add(classify);
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
	
	@Override
	public BeanMerchandiseClassify addClassify(BeanMerchant merchant, String classify_name) throws BaseException {
		// TODO Auto-generated method stub
		if(classify_name==null||"".equals(classify_name)) throw new BusinessException("类名不能为空");
		BeanMerchandiseClassify classify=new BeanMerchandiseClassify();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_classify WHERE classify_name=? and merchant_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, classify_name);
			pst.setInt(2,merchant.getMerchant_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("类名已存在");
			rs.close();
			pst.close();
			
			int classify_id=0;
			sql="SELECT max(classify_id) FROM merchandise_classify where merchant_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			rs=pst.executeQuery();
			if(rs.next()) classify_id=rs.getInt(1)+1;
			else classify_id=1;
			rs.close();
			pst.close();
			
			sql="INSERT INTO merchandise_classify(classify_id,merchant_id,classify_name,merchandise_number) VALUES(?,?,?,0)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, classify_id);
			pst.setInt(2, merchant.getMerchant_id());
			pst.setString(3, classify_name);
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
		return classify;
	}

	@Override
	public void deleteClassify(BeanMerchant merchant, BeanMerchandiseClassify classify) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise WHERE merchant_id=? and classify_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, classify.getMerchant_id());
			pst.setInt(2, classify.getClassify_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("该类别存在相关商品，无法删除");
			rs.close();
			pst.close();
			
			sql="delete from merchandise_classify where merchant_id=? and classify_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, classify.getMerchant_id());
			pst.setInt(2, classify.getClassify_id());
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

	public static void main(String[] args) {
		try {
			BeanMerchant merchant=new BeanMerchant();
			merchant.setMerchant_id(2);
			new ClassifyManager().addClassify(merchant, "主食/小食");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
