package com.csy.webmvc.service;

import com.alibaba.fastjson.JSONObject;
import com.csy.webmvc.bean.po.Room;
import com.csy.webmvc.bean.vo.SuccessResult;
import com.csy.webmvc.constant.Code;
import com.csy.webmvc.dao.RoomDaoImpl;
import com.csy.webmvc.exception.BusinessException;
import com.csy.webmvc.redis.RRoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

	@Autowired
	private RoomDaoImpl roomDaoImpl;
	@Autowired
	private RRoomDao rRoomDao;

	@Value("{serverUrl}")
	private String serverUrl;

	public String hotRooms(int page, int size) {
		List<Room> roomList = roomDaoImpl.getRooms(page, size);
		JSONObject result = SuccessResult.getResult();
		result.put("rooms", roomList);
		return result.toJSONString();
	}

	public String creRoom(int userId, String address) {
		if (serverUrl == null) {
			throw new BusinessException(Code._400, "没有可选服务器");
		}
		Room room = new Room();
		room.setUserId(userId);
		room.setAddress(address);
		// TODO 优先级规则
		room.setPriority(0l);
		room.setUrl(serverUrl + userId);
		if (roomDaoImpl.saveRoom(room) != 1) {
			throw new BusinessException(Code._300, "创建失败");
		}
		rRoomDao.creRoom(room.getUserId());
		JSONObject result = SuccessResult.getResult();
		result.put("url", serverUrl);
		return result.toJSONString();
	}

	public String delRoom(int userId) {
		if (roomDaoImpl.delRoom(userId) != 1) {
			throw new BusinessException(Code._300, "删除失败");
		}
		rRoomDao.delRoom(userId);
		return SuccessResult.getResult().toJSONString();
	}

	public String joinRoom(int userId) {
		rRoomDao.addAud(userId);
		JSONObject result = SuccessResult.getResult();
		result.put("url", serverUrl + userId);
		return result.toJSONString();
	}

	public String leaveRoom(int userId) {
		rRoomDao.minusAud(userId);
		return SuccessResult.getResult().toJSONString();
	}
}
