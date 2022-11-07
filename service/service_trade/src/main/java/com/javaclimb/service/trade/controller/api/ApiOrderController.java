package com.javaclimb.service.trade.controller.api;

/**
 * @Author yaCoding
 * @create 2022-11-07 上午 12:04
 */


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.trade.entity.Order;
import com.javaclimb.service.trade.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 订单前端控制器
 */
@Api(description = "订单前端控制器")
@RestController
@RequestMapping("/api/trade/order")
@Slf4j
@CrossOrigin
public class ApiOrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation("新增订单")
    @PostMapping("auth/save/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request){
        String orderId = orderService.saveOrder(courseId,request);
        return R.ok().data("orderId",orderId);
    }

    @ApiOperation("用户是否购买了该课程")
    @GetMapping("isBuyCourse/{memberId}/{courseId}")
    public boolean isBuyCourse(@PathVariable String memberId,@PathVariable String courseId){
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",memberId);
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("status",1);
        int count = orderService.count(queryWrapper);
        return count>0;
    }

    @ApiOperation("根据订单id获取订单详情")
    @GetMapping("auth/get/{orderId}")
    public R get(@PathVariable String orderId){
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",orderId);
        Order order = orderService.getOne(queryWrapper);
        return R.ok().data("item",order);
    }

    @ApiOperation("支付成功")
    @GetMapping("auth/payOk/{orderId}")
    public R payOk(@PathVariable String orderId){
        Order order = orderService.updateOrderStatus(orderId);
        return R.ok().data("item",order);
    }
}
