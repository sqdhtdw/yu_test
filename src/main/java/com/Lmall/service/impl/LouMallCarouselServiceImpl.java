package com.Lmall.service.impl;

import com.Lmall.api.vo.LouMallIndexCarouselVO;
import com.Lmall.dao.CarouselMapper;
import com.Lmall.entity.Carousel;
import com.Lmall.service.LouMallCarouselService;
import com.Lmall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LouMallCarouselServiceImpl implements LouMallCarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<LouMallIndexCarouselVO> getCarouselsForIndex(int number) {
        List<LouMallIndexCarouselVO> louMallIndexCarouselVOS = new ArrayList<>(number);
        List<Carousel> carousels = carouselMapper.findCarouselsByNum(number);
        if (!CollectionUtils.isEmpty(carousels)) {
            louMallIndexCarouselVOS = BeanUtil.copyList(carousels, LouMallIndexCarouselVO.class);
        }
        return louMallIndexCarouselVOS;
    }
}
