package com.Lmall.service.impl;

import com.Lmall.api.vo.LouMallIndexConfigGoodsVO;
import com.Lmall.dao.IndexConfigMapper;
import com.Lmall.dao.MallGoodsMapper;
import com.Lmall.entity.IndexConfig;
import com.Lmall.entity.MallGoods;
import com.Lmall.service.LouMallIndexConfigService;
import com.Lmall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LouMallIndexConfigServiceImpl implements LouMallIndexConfigService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Autowired
    private MallGoodsMapper goodsMapper;

    @Override
    public List<LouMallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number) {
        List<LouMallIndexConfigGoodsVO> louMallIndexConfigGoodsVOS = new ArrayList<>(number);
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigsByTypeAndNum(configType, number);
        if (!CollectionUtils.isEmpty(indexConfigs)) {
            //取出所有的goodsId
            List<Long> goodsIds = indexConfigs.stream().map(IndexConfig::getGoodsId).collect(Collectors.toList());
            List<MallGoods> mallGoods = goodsMapper.selectByPrimaryKeys(goodsIds);
            louMallIndexConfigGoodsVOS = BeanUtil.copyList(mallGoods, LouMallIndexConfigGoodsVO.class);
            for (LouMallIndexConfigGoodsVO louMallIndexConfigGoodsVO : louMallIndexConfigGoodsVOS) {
                String goodsName = louMallIndexConfigGoodsVO.getGoodsName();
                String goodsIntro = louMallIndexConfigGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 30) {
                    goodsName = goodsName.substring(0, 30) + "...";
                    louMallIndexConfigGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 22) {
                    goodsIntro = goodsIntro.substring(0, 22) + "...";
                    louMallIndexConfigGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        return louMallIndexConfigGoodsVOS;
    }
}
