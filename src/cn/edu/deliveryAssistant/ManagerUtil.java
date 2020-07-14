package cn.edu.deliveryAssistant;

import cn.edu.deliveryAssistant.control.AddressManager;
import cn.edu.deliveryAssistant.control.ClassifyManager;
import cn.edu.deliveryAssistant.control.CouponManager;
import cn.edu.deliveryAssistant.control.ManagerManager;
import cn.edu.deliveryAssistant.control.MerchandiseManager;
import cn.edu.deliveryAssistant.control.MerchantManager;
import cn.edu.deliveryAssistant.control.OrderManager;
import cn.edu.deliveryAssistant.control.PlanManager;
import cn.edu.deliveryAssistant.control.RiderManager;
import cn.edu.deliveryAssistant.control.UserManager;


public class ManagerUtil {
	public static ManagerManager managerManager=new ManagerManager();
	public static UserManager userManager=new UserManager();
	public static RiderManager riderManager=new RiderManager();
	public static MerchantManager merchantManager=new MerchantManager();
	public static ClassifyManager classifyManager=new ClassifyManager();
	public static MerchandiseManager merchandiseManager=new MerchandiseManager();
	public static AddressManager addressManager=new AddressManager();
	public static CouponManager couponManager=new CouponManager();
	public static OrderManager orderManager=new OrderManager();
	public static PlanManager planManager=new PlanManager();
	

}
