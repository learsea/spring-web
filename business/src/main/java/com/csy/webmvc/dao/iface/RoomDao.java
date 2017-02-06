package com.csy.webmvc.dao.iface;

import com.csy.webmvc.bean.po.Room;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 为规范化dao层，禁止在service层直接调用该层。应该调用daoImpl
 * 比如：某个查询需要查询多个dao，为了可以复用这个查询，则查询、拼装操作应该在daoImpl中进行
 */
@Repository
public interface RoomDao {

	List<Room> getRooms(@Param("offset") int offset, @Param("size") int size);

	int saveRoom(Room room);

	int delRoom(int userId);

}
