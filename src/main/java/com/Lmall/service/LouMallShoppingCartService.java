package com.Lmall.service;

import com.Lmall.api.param.SaveCartItemParam;
import com.Lmall.api.param.UpdateCartItemParam;
import com.Lmall.api.vo.LouMallShoppingCartItemVO;
import com.Lmall.entity.MallShoppingCartItem;
import com.Lmall.util.PageQueryUtil;
import com.Lmall.util.PageResult;

import java.util.List;

public interface LouMallShoppingCartService {

    /**
     * 保存商品至购物车中
     *
     * @param saveCartItemParam
     * @param userId
     * @return
     */
    String saveMallCartItem(SaveCartItemParam saveCartItemParam, Long userId);

    /**
     * 修改购物车中的属性
     *
     * @param updateCartItemParam
     * @param userId
     * @return
     */
    String updateMallCartItem(UpdateCartItemParam updateCartItemParam, Long userId);

    /**
     * 获取购物项详情
     *
     * @param shoppingCartItemId
     * @return
     */
    MallShoppingCartItem getCartItemById(Long shoppingCartItemId);

    /**
     * 删除购物车中的商品
     *
     * @param shoppingCartItemId
     * @return
     */
    Boolean deleteById(Long shoppingCartItemId);

    /**
     * 获取我的购物车中的列表数据
     *
     * @param mallUserId
     * @return
     */
    List<LouMallShoppingCartItemVO> getMyShoppingCartItems(Long mallUserId);

    /**
     * 根据userId和cartItemIds获取对应的购物项记录
     *
     * @param cartItemIds
     * @param mallUserId
     * @return
     */
    List<LouMallShoppingCartItemVO> getCartItemsForSettle(List<Long> cartItemIds, Long mallUserId);

    /**
     * 我的购物车(分页数据)
     *
     * @param pageUtil
     * @return
     */
    PageResult getMyShoppingCartItems(PageQueryUtil pageUtil);
}
