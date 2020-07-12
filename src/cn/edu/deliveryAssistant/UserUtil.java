package cn.edu.deliveryAssistant;

import cn.edu.deliveryAssistant.control.AddressManager;
import cn.edu.deliveryAssistant.control.ClassifyManager;
import cn.edu.deliveryAssistant.control.CouponManager;
import cn.edu.deliveryAssistant.control.MerchandiseManager;
import cn.edu.deliveryAssistant.control.MerchantManager;
import cn.edu.deliveryAssistant.control.OrderManager;
import cn.edu.deliveryAssistant.control.RiderManager;
import cn.edu.deliveryAssistant.control.UserManager;
import cn.edu.deliveryAssistant.itf.IAddressManager;
import cn.edu.deliveryAssistant.itf.IClassifyManager;
import cn.edu.deliveryAssistant.itf.ICouponManager;
import cn.edu.deliveryAssistant.itf.IMerchandiseManager;
import cn.edu.deliveryAssistant.itf.IMerchantManager;
import cn.edu.deliveryAssistant.itf.IOrderManager;
import cn.edu.deliveryAssistant.itf.IRiderManager;
import cn.edu.deliveryAssistant.itf.IUserManager;

public class UserUtil {
	public static IUserManager userManager=new UserManager();
	public static IRiderManager riderManager=new RiderManager();
	public static IMerchantManager merchantManager=new MerchantManager();
	public static IClassifyManager classifyManager=new ClassifyManager();
	public static IMerchandiseManager merchandiseManager=new MerchandiseManager();
	public static IAddressManager addressManager=new AddressManager();
	public static ICouponManager couponManager=new CouponManager();
	public static IOrderManager orderManager=new OrderManager();
}
