package com.Lmall.service;

import com.Lmall.api.vo.LouMallIndexConfigGoodsVO;

import java.util.List;

public interface LouMallIndexConfigService {

    /**
     * 返回固定数量的首页配置商品对象(首页调用)
     *
     * @param number
     * @return
     */
    List<LouMallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number);
}
