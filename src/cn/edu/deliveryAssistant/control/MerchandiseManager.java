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
			String sql="SELECT merchandise_id,classify_id,merchant_id,merchandise_name,unit_price,discount_unit_price,remain\r\n" + 
					"	FROM merchandise WHERE merchant_id=? AND classify_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, classify.getMerchant_id());
			pst.setInt(2, classify.getClassify_id());
			java.sql.ResultSet rs=pst.executeQuery();
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
	public BeanMerchandise addMerchandise( BeanMerchandiseClassify classify, String name,
			double unit_price, double discount_unit_price, int remain) throws BaseException {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name)) throw new BusinessException("商品名不能为空");
		if(unit_price<0) throw new BusinessException("单价不能少于0");
		if(discount_unit_price<0) throw new BusinessException("优惠价格不能少于0");
		if(remain<0) throw new BusinessException("余量不能少于0");
		BeanMerchandise merchandise=new BeanMerchandise();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT merchandise_id,remain FROM merchandise\r\n" + 
					"	WHERE merchant_id=? AND classify_id=? AND merchandise_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, classify.getMerchant_id());
			pst.setInt(2, classify.getClassify_id());
			pst.setString(3, name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				int dise_id=rs.getInt(1);
				int r=rs.getInt(2);
				rs.close();
				pst.close();
				sql="UPDATE merchandise SET unit_price=?,discount_unit_price=?,remain=remain+? \r\n" + 
						"	WHERE merchandise_id=? AND merchant_id=? AND classify_id=?";
				pst=conn.prepareStatement(sql);
				pst.setDouble(1, unit_price);
				pst.setDouble(2, discount_unit_price);
				pst.setInt(3, remain);
				pst.setInt(4, dise_id);
				pst.setInt(5, classify.getMerchant_id());
				pst.setInt(6, classify.getClassify_id());
				pst.executeUpdate();
				merchandise.setMerchandise_id(dise_id);
				merchandise.setClassify_id(classify.getClassify_id());
				merchandise.setMerchant_id(classify.getMerchant_id());
				merchandise.setMercandise_name(name);
				merchandise.setUnit_price(unit_price);
				merchandise.setDiscount_unit_price(discount_unit_price);
				merchandise.setRemain(r+remain);
			}else {
				rs.close();
				pst.close();
				sql="SELECT max(merchandise_id) FROM merchandise\r\n" + 
						"	WHERE merchant_id=? AND classify_id=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, classify.getMerchant_id());
				pst.setInt(2, classify.getClassify_id());
				rs=pst.executeQuery();
				int dise_id=0;
				if(rs.next()) dise_id=rs.getInt(1)+1;
				else dise_id=1;
				rs.close();
				pst.close();
				sql="INSERT INTO merchandise VALUES(?,?,?,?,?,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, dise_id);
				pst.setInt(2, classify.getClassify_id());
				pst.setInt(3, classify.getMerchant_id());
				pst.setString(4, name);
				pst.setDouble(5, unit_price);
				pst.setDouble(6, discount_unit_price);
				pst.setInt(7, remain);
				pst.executeUpdate();
				merchandise.setMerchandise_id(dise_id);
				merchandise.setClassify_id(classify.getClassify_id());
				merchandise.setMerchant_id(classify.getMerchant_id());
				merchandise.setMercandise_name(name);
				merchandise.setUnit_price(unit_price);
				merchandise.setDiscount_unit_price(discount_unit_price);
				merchandise.setRemain(remain);
			}
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
		return merchandise;
	}

	@Override
	public void deleteMerchandise(BeanMerchandise merchandise) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from merchandise where merchandise_id=? AND merchant_id=? AND classify_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, merchandise.getMerchandise_id());
			pst.setInt(2, merchandise.getMerchant_id());
			pst.setInt(3, merchandise.getClassify_id());
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

	public static void main(String[] args) {
		try {
			BeanMerchandiseClassify classify=new BeanMerchandiseClassify();
			classify.setClassify_id(1);
			classify.setMerchant_id(1);
			new MerchandiseManager().addMerchandise(classify, "蒜香酱油炸鸡套餐", 51.99, 2, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
