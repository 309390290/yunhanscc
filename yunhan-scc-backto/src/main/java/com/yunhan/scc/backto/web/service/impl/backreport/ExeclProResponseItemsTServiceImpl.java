package com.yunhan.scc.backto.web.service.impl.backreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhan.scc.backto.web.entities.backreport.ProResponseItemsTBacktoDO;
import com.yunhan.scc.backto.web.entities.order.ProPurOrderItemsBacktoDO;
import com.yunhan.scc.backto.web.model.order.ProPurOrderItemsBacktoCondition;
import com.yunhan.scc.backto.web.service.order.ProPurOrderItemsService;
import com.yunhan.scc.tools.execl.ExeclBeanInterface;
import com.yunhan.scc.tools.execl.ExeclHandleData;
import com.yunhan.scc.tools.util.DateUtils;
import com.yunhan.scc.tools.util.StringUtil;

/**
 * 
 * 导入的回告临时信息数据验证
 * @author luohoudong
 * @version created at 2016-7-14 下午5:11:05
 */
@Service
public class ExeclProResponseItemsTServiceImpl implements ExeclHandleData{
	@Autowired
	private ProPurOrderItemsService proPurOrderItemsService ;
	
	/**
	 * 
	 * @author YangTao 2016-3-10 下午2:35:19
	 * @param beanInterface
	 * @param oldbean
	 * @throws Exception
	 */
	@Override
	public ExeclBeanInterface handleData(ExeclBeanInterface beanInterface,ExeclBeanInterface oldbean) throws Exception {
		if(beanInterface instanceof  ProResponseItemsTBacktoDO){
			ProResponseItemsTBacktoDO pritdo = (ProResponseItemsTBacktoDO) beanInterface;
			//订单细目是否为空验证
			String proPurOrderItemId = pritdo.getProPurOrderItemsId();
			if(proPurOrderItemId == null || proPurOrderItemId.equals("")){
				pritdo.setErrorMessage("DISCARD");
				return pritdo;
			}
			//如果导入的数据细目ID有两位小数 去掉
			pritdo.setProPurOrderItemsId(StringUtil.delEndTwoZero(proPurOrderItemId));
			
			if(pritdo.getPurchaseOrderCode() == null || pritdo.getPurchaseOrderCode().equals("")){
				pritdo.setErrorMessage("您删除了订单号，请重新导出导入!");
				return pritdo;
			}
			//通过订单细目ID匹配，匹配待处理的品种
			ProPurOrderItemsBacktoCondition backtoCondition = new ProPurOrderItemsBacktoCondition();
			Long id = 0L;
			try {
				id = Long.valueOf(proPurOrderItemId);
			} catch (Exception e) {
				pritdo.setErrorMessage("转换细目ID出错!");
				return pritdo;
			}
			backtoCondition.setId(id);
			ProPurOrderItemsBacktoDO proBacktoDO =  proPurOrderItemsService.findItemById(backtoCondition);
			//表示并没有匹配上
			if(proBacktoDO == null){
				if(pritdo.getSendoutGoodsCode() == null || pritdo.getSendoutGoodsCode().equals("")){
					pritdo.setErrorMessage("无法匹配");
					pritdo.setErrorNumber(2);
					return pritdo;
				}
				
				pritdo.setProPurOrderItemsId("");
				pritdo.setIsSupplierAddProduct("Y");//供应商添加商品标识:Y-是，N-否
			}else{
				//品种已经处理
				//1、不满足处理条件的品种（未发数大于0 且品种有效性 有效）
				if(!( (proBacktoDO.getOrderQty() - proBacktoDO.getSendoutQty() ) > 0 && proBacktoDO.getIsValid().equals("Y"))){
					pritdo.setErrorMessage("品种已处理");
					pritdo.setErrorNumber(1);
					return pritdo;
				}
				//2品种有效性=采购关闭、供应商关闭的品种
				if( proBacktoDO.getIsValid().equals("N") || proBacktoDO.getIsValid().equals("X")){
					pritdo.setErrorMessage("品种已处理");
					pritdo.setErrorNumber(1);
					return pritdo;
				}
				
				pritdo.setSupplierId(proBacktoDO.getSupplierId());//供应商id
				pritdo.setPurchaserId(proBacktoDO.getPurchaserId());//采购商id
				pritdo.setYunhanOrderCode(proBacktoDO.getYunhanOrderCode());//云汉订单号
				pritdo.setPurchaseOrderCode(proBacktoDO.getPurchaseOrderCode());//采购订单号
				pritdo.setYunhanId(proBacktoDO.getYunhanId());//云汉id
				pritdo.setPurchaserCommoditiesId(proBacktoDO.getPurchaserCommoditiesId());//采购商商品id
				pritdo.setIsbn(proBacktoDO.getIsbn());//isbn
				pritdo.setBookTitle(proBacktoDO.getBookTitle());//书名
				pritdo.setPrice(proBacktoDO.getPrice().toString());//定价
				pritdo.setIsSupplierAddProduct("N");//供应商添加商品标识:Y-是，N-否
			}
			
			//如果代码执行到这里，我们需要将我们解析Excel产生的多余数据去除掉。
			if(pritdo.getPreSendDate()!=null && !"".equals(pritdo.getPreSendDate())){
				pritdo.setPreSendDate(pritdo.getPreSendDate().split("\\.")[0]);
			}
			//如果本次发货数为空或者为0，则进入只回告标签页
			if("".equals(pritdo.getThisSendQty()) || Integer.parseInt(pritdo.getThisSendQty())==0){
				pritdo.setSendoutGoodsCode("");
			}else{
				pritdo.setSendoutGoodsCode(StringUtil.delEndTwoZero( pritdo.getSendoutGoodsCode()));
			}
			
			switch (pritdo.getNotEnoughReason()) {
			case "预计可发":
				pritdo.setNotEnoughReason("0");
				break;
			case "暂时缺货":
				pritdo.setNotEnoughReason("1");
				break;
			case "已停产":
				pritdo.setNotEnoughReason("2");
				break;
			case "改版":
				pritdo.setNotEnoughReason("3");
				break;
			case "版权到期":
				pritdo.setNotEnoughReason("4");
				break;
			case "商品无效":
				pritdo.setNotEnoughReason("5");
				break;
			case "销售受限":
				pritdo.setNotEnoughReason("6");
				break;
			case "新书待入库":
				pritdo.setNotEnoughReason("8");
				break;
			case "无货不发":
				pritdo.setNotEnoughReason("9");
				break;
			default:
				pritdo.setNotEnoughReason("");
				break;
			}
			pritdo.setThisSendQty(StringUtil.delEndTwoZero(pritdo.getThisSendQty()));
			pritdo.setOrderQty(StringUtil.delEndTwoZero(pritdo.getOrderQty()));
			pritdo.setIsbn(StringUtil.delEndTwoZero( pritdo.getIsbn()));
			//已发数，已收数
			
			return pritdo;
		}else{
			throw new ClassCastException();
		}
	}

}
