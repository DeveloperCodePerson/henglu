package com.henglu.haiway.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class Constant {

	public static final String SERVER_PATH ="http://code5.tiantianfuyu.com";
	public static final String APP_NAME = "henglu.apk";
	public static final String USERID = "id";
	

	
	/**
	 * 手机字符替换
	 * @param flag
	 * @return
	 */
	public static String getTelephone(String flag) {
		int length = flag.length();
		String des = flag;
		int n=4;
		des = flag.substring(0,3) + "****" + flag.substring(length-n,length);
		return des;
	}
	/**
	 * 姓名字符替换
	 * @param flag
	 * @return
	 */
	public static String getStringFileName(String flag) {
		int length = flag.length();
		String des = flag;
		if (length >= 2) {
			String d = flag.substring(1);
			int l = d.length();
			StringBuffer b = new StringBuffer();
			for (int i = 0; i < l; i++) {
				b.append("*");
			}
			des = flag.substring(0,1) + b.toString();
		}
		return des;
	}
	//
	public static String getConferenceFlagName(String flag) {
		if (Util.isBlank(flag))
			return "";
		if ("1".equals(flag)) {
			return "一次性还本付息";
		} else if ("2".equals(flag)) {
			return "按年付息，到期还本";
		}
		return "";
	}
public static String getBindState(String flag){
	if (Util.isBlank(flag))
		return "";
	if ("1".equals(flag)) {
		return "已绑定";
	} else if ("0".equals(flag)) {
		return "未绑定";
	}
	return "";
}
public static String getRealNameState(String flag){
	if (Util.isBlank(flag))
		return "";
	if ("1".equals(flag)) {
		return "已认证";
	} else if ("0".equals(flag)) {
		return "未认证";
	}
	return "";
}
	public static String getUnit(String flag) {
		if (Util.isBlank(flag))
			return "";
		if ("1".equals(flag)) {
			return "天";
		} else if ("2".equals(flag)) {
			return "个月";
		}else{
			return "年";
		}
	}
	public static String getConferenceEmpFlagName(String flag) {
		if (Util.isBlank(flag))
			return "";
		if ("1".equals(flag)) {
			return "T(成交日)+1天";
		} else if ("2".equals(flag)) {
			return "T(成交日)+0天";
		}
		return "";
	}

	public static String getState(String flag) {
		if (Util.isBlank(flag))
			return "";
		if ("0".equals(flag)) {
			return "提现中";
		} else if ("1".equals(flag)) {
			return "成功";
		} else if ("2".equals(flag)) {
			return "失败";
		}
		return " ";
	}
	public static String getTypeState(String flag) {
		if (Util.isBlank(flag))
			return "";
		if ("1".equals(flag)) {
			return "当前本金";
		} else if ("2".equals(flag)) {
			return "当前利息";
		}else if("3".equals(flag)){
			return "当前本息";
		}
		return "";
	}
	public static String getRepaymentStatus(String flag) {
		if (Util.isBlank(flag))
			return "";
		if ("0".equals(flag)||"-1".equals(flag)) {
			return "未入账";
		} else if ("1".equals(flag)||"2".equals(flag)) {
			return "即将入账";
		}else if("3".equals(flag)){
			return "已入账";
		}
		return "";
	}
	public static String getCZState(String flag) {
		if (Util.isBlank(flag))
			return "";
		if ("0".equals(flag)) {
			return "充值中";
		} else if ("1".equals(flag)) {
			return "充值成功";
		} else if ("2".equals(flag)) {
			return "失败/中断";
		}
		return " ";
	}

	public static String dateTo(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
	public static double getMatchMoney(double money){
		BigDecimal   b   =   new   BigDecimal(money);
		double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1 ;
	}
}
