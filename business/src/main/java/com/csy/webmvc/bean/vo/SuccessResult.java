package com.csy.webmvc.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.csy.webmvc.constant.Code;

public class SuccessResult {
	public static JSONObject getResult() {
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("code", Code._100.getCode());
		return result;
	}
}
