package com.Lmall.api;

import com.Lmall.api.vo.IndexInfoVO;
import com.Lmall.api.vo.LouMallIndexCarouselVO;
import com.Lmall.api.vo.LouMallIndexConfigGoodsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.Lmall.common.Constants;
import com.Lmall.common.IndexConfigTypeEnum;
import com.Lmall.service.LouMallCarouselService;
import com.Lmall.service.LouMallIndexConfigService;
import com.Lmall.util.Result;
import com.Lmall.util.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "v1", tags = "1.楼楼商城首页接口")
@RequestMapping("/api/v1")
public class LouMallIndexAPI {

    @Resource
    private LouMallCarouselService louMallCarouselService;

    @Resource
    private LouMallIndexConfigService louMallIndexConfigService;

    @GetMapping("/index-infos")
    @ApiOperation(value = "获取首页数据", notes = "轮播图、新品、推荐等")
    public Result<IndexInfoVO> indexInfo() {
        IndexInfoVO indexInfoVO = new IndexInfoVO();
        List<LouMallIndexCarouselVO> carousels = louMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
        List<LouMallIndexConfigGoodsVO> hotGoodses = louMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
        List<LouMallIndexConfigGoodsVO> newGoodses = louMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_GOODS_NEW_NUMBER);
        List<LouMallIndexConfigGoodsVO> recommendGoodses = louMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUMBER);
        indexInfoVO.setCarousels(carousels);
        indexInfoVO.setHotGoodses(hotGoodses);
        indexInfoVO.setNewGoodses(newGoodses);
        indexInfoVO.setRecommendGoodses(recommendGoodses);
        return ResultGenerator.genSuccessResult(indexInfoVO);
    }
}
