package com.cz.controller;

import com.cz.service.EventService;
import com.cz.vo.EventVo;
import com.cz.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/event")
@CrossOrigin
@Tag(name = "事件管理", description = "提供事件操作的接口")
public class EventController {


    @Resource
    private EventService eventService;

    @Operation(summary = "获取所有事件")
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "获取成功"),
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultVo getEvents() {
        return eventService.getEvents();
    }

    @Operation(summary = "根据ID获取事件")
    @Parameters({
            @Parameter(name = "id",description = "事件ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "获取成功"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultVo getEventById(@PathVariable Integer id) {
        return eventService.getEventById(id);
    }

    @Operation(summary = "创建新事件")
    @Parameters({
            @Parameter(name = "eventTypeId",description = "事件类型", required = true),
            @Parameter(name = "eventTypeContent",description = "事件类型内容", required = true),
            @Parameter(name = "eventTypeIcon",description = "事件类型图标", required = true),
            @Parameter(name = "title",description = "标题", required = true),
            @Parameter(name = "description",description = "详细信息", required = true),
            @Parameter(name = "startTime",description = "开始时间", required = true),
            @Parameter(name = "endTime",description = "结束时间", required = true),

    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "创建成功"),
    })
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultVo createEvent(@RequestBody EventVo event, HttpServletRequest request) {
        return eventService.createEvent(event, request);
    }

    @Operation(summary = "更新事件")
    @Parameters({
            @Parameter(name = "id",description = "事件ID", required = true),
            @Parameter(name = "eventTypeId",description = "事件类型", required = true),
            @Parameter(name = "eventTypeContent",description = "事件类型内容", required = true),
            @Parameter(name = "eventTypeIcon",description = "事件类型图标", required = true),
            @Parameter(name = "title",description = "标题", required = true),
            @Parameter(name = "description",description = "详细信息", required = true),
            @Parameter(name = "startTime",description = "开始时间", required = true),
            @Parameter(name = "endTime",description = "结束时间", required = true)
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "更新成功"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultVo updateEvent(@PathVariable Integer id, @RequestBody EventVo event) {
        return eventService.updateEvent(id, event);
    }

    @Operation(summary = "删除事件")
    @Parameters({
            @Parameter(name = "id",description = "事件ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "删除成功"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultVo deleteEvent(@PathVariable Integer id) {
        return eventService.deleteEvent(id);
    }


}