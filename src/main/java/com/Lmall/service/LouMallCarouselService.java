package com.Lmall.service;

import com.Lmall.api.vo.LouMallIndexCarouselVO;

import java.util.List;

public interface LouMallCarouselService {

    /**
     * 返回固定数量的轮播图对象(首页调用)
     *
     * @param number
     * @return
     */
    List<LouMallIndexCarouselVO> getCarouselsForIndex(int number);
}
