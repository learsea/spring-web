package com.csy.webmvc.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.csy.webmvc.constant.Code;

public class ErrorResult {
	public static JSONObject getResult(Code code, String errMsg) {
		JSONObject result = new JSONObject();
		result.put("code", code.getCode());
		result.put("msg", errMsg);
		return result;
	}
}
