package com.Lmall.service;

import com.Lmall.entity.MallGoods;
import com.Lmall.util.PageQueryUtil;
import com.Lmall.util.PageResult;

public interface LouMallGoodsService {

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    MallGoods getMallGoodsById(Long id);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    PageResult searchMallGoods(PageQueryUtil pageUtil);
}
