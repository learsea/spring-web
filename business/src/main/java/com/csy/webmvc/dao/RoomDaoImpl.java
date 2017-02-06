package com.csy.webmvc.dao;

import com.csy.webmvc.bean.po.Room;
import com.csy.webmvc.dao.iface.RoomDao;
import com.csy.webmvc.redis.RRoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomDaoImpl {
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private RRoomDao rRoomDao;

	public List<Room> getRooms(int page, int size) {
		List<Room> rooms = roomDao.getRooms(page * size, size);
		for (Room room : rooms) {
			room.setAudNum(rRoomDao.getAudNum(room.getUserId()));
		}
		return rooms;
	}

	public int saveRoom(Room room) {
		return roomDao.saveRoom(room);
	}

	public int delRoom(int userId) {
		return roomDao.delRoom(userId);
	}

}
