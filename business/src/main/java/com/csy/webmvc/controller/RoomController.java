package com.csy.webmvc.controller;

import com.csy.webmvc.bean.vo.SuccessResult;
import com.csy.webmvc.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/live")
@ResponseBody
@Api(description = "房间类")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "热播房间列表", notes = "响应说明：")
    @RequestMapping(value = "hot-rooms", method = RequestMethod.GET)
    public String hotRooms(@ApiParam(value = "页数，从0开始", required = true) @RequestParam int page, @ApiParam(value = "每页条数", required = true) @RequestParam int size) {
        return SuccessResult.getResult().toJSONString();
//        return roomService.hotRooms(page, size);
    }

}
