package com.Lmall.service;

import com.Lmall.api.vo.LouMallOrderDetailVO;
import com.Lmall.api.vo.LouMallShoppingCartItemVO;
import com.Lmall.entity.MallUser;
import com.Lmall.entity.MallUserAddress;
import com.Lmall.util.PageQueryUtil;
import com.Lmall.util.PageResult;

import java.util.List;

public interface LouMallOrderService {
    /**
     * 获取订单详情
     *
     * @param orderNo
     * @param userId
     * @return
     */
    LouMallOrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId);

    /**
     * 我的订单列表
     *
     * @param pageUtil
     * @return
     */
    PageResult getMyOrders(PageQueryUtil pageUtil);

    /**
     * 手动取消订单
     *
     * @param orderNo
     * @param userId
     * @return
     */
    String cancelOrder(String orderNo, Long userId);

    /**
     * 确认收货
     *
     * @param orderNo
     * @param userId
     * @return
     */
    String finishOrder(String orderNo, Long userId);

    String paySuccess(String orderNo, int payType);

    String saveOrder(MallUser loginMallUser, MallUserAddress address, List<LouMallShoppingCartItemVO> itemsForSave);
}
