package cn.edu.deliveryAssistant.itf;

import java.util.List;

import cn.edu.deliveryAssistant.model.BeanRider;
import cn.edu.deliveryAssistant.util.BaseException;

public interface IRiderManager {
	//显示所有骑手
	public List<BeanRider> loadAll() throws BaseException;
	
	//精准查找某骑手
	public BeanRider search(BeanRider rider) throws BaseException;
	
	//模糊查找某骑手
	public List<BeanRider> search(String rider_name) throws BaseException;
	
	//骑手的评价
	public List<String> load(BeanRider rider) throws BaseException;
	
	//骑手接单数
	public int countOrdre(BeanRider rider) throws BaseException;
	
	//骑手总得
	public double sumMoney(BeanRider rider) throws BaseException;
	
	//添加骑手
	public BeanRider addRider(String rider_name) throws BaseException;
	
	//删除骑手
	public void deleteRider(BeanRider rider) throws BaseException;

}
