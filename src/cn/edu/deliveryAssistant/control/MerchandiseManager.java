package cn.edu.deliveryAssistant.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.deliveryAssistant.itf.IMerchandiseManager;
import cn.edu.deliveryAssistant.model.BeanMerchandise;
import cn.edu.deliveryAssistant.model.BeanMerchandiseClassify;
import cn.edu.deliveryAssistant.model.BeanMerchant;
import cn.edu.deliveryAssistant.model.MerchandiseEvaluate;
import cn.edu.deliveryAssistant.model.MerchandiseOrder;
import cn.edu.deliveryAssistant.util.BaseException;
import cn.edu.deliveryAssistant.util.BusinessException;
import cn.edu.deliveryAssistant.util.DBUtil;
import cn.edu.deliveryAssistant.util.DbException;

public class MerchandiseManager implements IMerchandiseManager {

	@Override
	public List<BeanMerchandise> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanMerchandise> result=new ArrayList<BeanMerchandise>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				BeanMerchandise  merchandise=new BeanMerchandise();
				merchandise.setMerchandise_id(rs.getInt(1));
				merchandise.setClassify_id(rs.getInt(2));
				merchandise.setMerchant_id(rs.getInt(3));
				merchandise.setMercandise_name(rs.getString(4));
				merchandise.setUnit_price(rs.getDouble(5));
				merchandise.setDiscount_unit_price(rs.getDouble(6));
				merchandise.setRemain(rs.getInt(7));
				result.add(merchandise);
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
	public List<BeanMerchandise> load(BeanMerchant merchant) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanMerchandise> result=new ArrayList<BeanMerchandise>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise WHERE merchant_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchant.getMerchant_id());
			java.sql.ResultSet rs=pst.executeQuery(sql);
			while(rs.next()) {
				BeanMerchandise  merchandise=new BeanMerchandise();
				merchandise.setMerchandise_id(rs.getInt(1));
				merchandise.setClassify_id(rs.getInt(2));
				merchandise.setMerchant_id(rs.getInt(3));
				merchandise.setMercandise_name(rs.getString(4));
				merchandise.setUnit_price(rs.getDouble(5));
				merchandise.setDiscount_unit_price(rs.getDouble(6));
				merchandise.setRemain(rs.getInt(7));
				result.add(merchandise);
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
	public List<BeanMerchandise> load(BeanMerchandiseClassify classify) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanMerchandise> result=new ArrayList<BeanMerchandise>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise WHERE merchant_id=? AND classify_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, classify.getMerchant_id());
			pst.setInt(2, classify.getClassify_id());
			java.sql.ResultSet rs=pst.executeQuery(sql);
			while(rs.next()) {
				BeanMerchandise  merchandise=new BeanMerchandise();
				merchandise.setMerchandise_id(rs.getInt(1));
				merchandise.setClassify_id(rs.getInt(2));
				merchandise.setMerchant_id(rs.getInt(3));
				merchandise.setMercandise_name(rs.getString(4));
				merchandise.setUnit_price(rs.getDouble(5));
				merchandise.setDiscount_unit_price(rs.getDouble(6));
				merchandise.setRemain(rs.getInt(7));
				result.add(merchandise);
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
	public BeanMerchandise search(String merchandise_name) throws BaseException {
		// TODO Auto-generated method stub
		if(merchandise_name==null||"".equals(merchandise_name)) throw new BusinessException("商品名不能为空");
		BeanMerchandise merchandise=new BeanMerchandise();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise WHERE merchandise_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+merchandise_name+"%");
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				merchandise.setMerchandise_id(rs.getInt(1));
				merchandise.setClassify_id(rs.getInt(2));
				merchandise.setMerchant_id(rs.getInt(3));
				merchandise.setMercandise_name(rs.getString(4));
				merchandise.setUnit_price(rs.getDouble(5));
				merchandise.setDiscount_unit_price(rs.getDouble(6));
				merchandise.setRemain(rs.getInt(7));
			}else {
				throw new BusinessException("该商品名不存在");
			}
			pst.close();
			rs.close();
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
		return merchandise;
	}

	@Override
	public List<BeanMerchandise> searchbyclassify(String classify_name) throws BaseException {
		// TODO Auto-generated method stub
		if(classify_name==null||"".equals(classify_name)) throw new BusinessException("类名不能为空");
		List<BeanMerchandise> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_classify WHERE classify_name LIKE ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+classify_name+"%");
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("该类名不存在");
			rs.close();
			pst.close();
			
			sql="SELECT * FROM merchandise \r\n" + 
					"	WHERE classify_id in(\r\n" + 
					"	SELECT classify_id FROM merchandise_classify \r\n" + 
					"	WHERE classify_name LIKE ?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+classify_name+"%");
			rs=pst.executeQuery();
			while(rs.next()) {
				BeanMerchandise  merchandise=new BeanMerchandise();
				merchandise.setMerchandise_id(rs.getInt(1));
				merchandise.setClassify_id(rs.getInt(2));
				merchandise.setMerchant_id(rs.getInt(3));
				merchandise.setMercandise_name(rs.getString(4));
				merchandise.setUnit_price(rs.getDouble(5));
				merchandise.setDiscount_unit_price(rs.getDouble(6));
				merchandise.setRemain(rs.getInt(7));
				result.add(merchandise);
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
	public List<BeanMerchandise> searchbyclassify(BeanMerchant merchant, String classify_name) throws BaseException {
		// TODO Auto-generated method stub
		if(classify_name==null||"".equals(classify_name)) throw new BusinessException("类名不能为空");
		List<BeanMerchandise> result=new ArrayList<>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM merchandise_classify WHERE classify_name LIKE ? AND merchant_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+classify_name+"%");
			pst.setInt(2, merchant.getMerchant_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("该商家中该类名不存在");
			rs.close();
			pst.close();
			
			sql="SELECT * FROM merchandise \r\n" + 
					"	WHERE classify_id in(\r\n" + 
					"	SELECT classify_id FROM merchandise_classify \r\n" + 
					"	WHERE classify_name LIKE ? AND merchant_id=?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+classify_name+"%");
			pst.setInt(2, merchant.getMerchant_id());
			rs=pst.executeQuery();
			while(rs.next()) {
				BeanMerchandise  merchandise=new BeanMerchandise();
				merchandise.setMerchandise_id(rs.getInt(1));
				merchandise.setClassify_id(rs.getInt(2));
				merchandise.setMerchant_id(rs.getInt(3));
				merchandise.setMercandise_name(rs.getString(4));
				merchandise.setUnit_price(rs.getDouble(5));
				merchandise.setDiscount_unit_price(rs.getDouble(6));
				merchandise.setRemain(rs.getInt(7));
				result.add(merchandise);
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
	public BeanMerchandise addMerchandise(BeanMerchant merchant, BeanMerchandiseClassify classify, String name,
			double unit_price, double discount_unit_price, int remain) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMerchandise(BeanMerchandise merchandise) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MerchandiseEvaluate> loadEvaluation(BeanMerchandise merchandise) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MerchandiseEvaluate userEvaluate(MerchandiseOrder merchandiseOrder) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
