package com.Lmall.service.impl;

import com.Lmall.api.vo.LouMallSearchGoodsVO;
import com.Lmall.dao.MallGoodsMapper;
import com.Lmall.service.LouMallGoodsService;
import com.Lmall.entity.MallGoods;
import com.Lmall.util.BeanUtil;
import com.Lmall.util.PageQueryUtil;
import com.Lmall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LouMallGoodsServiceImpl implements LouMallGoodsService {

    @Autowired
    private MallGoodsMapper goodsMapper;

    @Override
    public MallGoods getMallGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult searchMallGoods(PageQueryUtil pageUtil) {
        List<MallGoods> goodsList = goodsMapper.findMallGoodsListBySearch(pageUtil);
        int total = goodsMapper.getTotalMallGoodsBySearch(pageUtil);
        List<LouMallSearchGoodsVO> louMallSearchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsList)) {
            louMallSearchGoodsVOS = BeanUtil.copyList(goodsList, LouMallSearchGoodsVO.class);
            for (LouMallSearchGoodsVO louMallSearchGoodsVO : louMallSearchGoodsVOS) {
                String goodsName = louMallSearchGoodsVO.getGoodsName();
                String goodsIntro = louMallSearchGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    louMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    louMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(louMallSearchGoodsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
