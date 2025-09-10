package com.Lmall.api;

import com.Lmall.api.vo.LouMallGoodsDetailVO;
import com.Lmall.api.vo.LouMallSearchGoodsVO;
import com.Lmall.common.Constants;
import com.Lmall.common.LouMallException;
import com.Lmall.common.ServiceResultEnum;
import com.Lmall.config.annotation.TokenToMallUser;
import com.Lmall.entity.MallUser;
import com.Lmall.entity.MallGoods;
import com.Lmall.service.LouMallGoodsService;
import com.Lmall.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "v1", tags = "4.楼楼商城商品相关接口")
@RequestMapping("/api/v1")
public class LouMallGoodsAPI {

    private static final Logger logger = LoggerFactory.getLogger(LouMallGoodsAPI.class);

    @Resource
    private LouMallGoodsService louMallGoodsService;

    @GetMapping("/search")
    @ApiOperation(value = "商品搜索接口", notes = "根据关键字和分类id进行搜索")
    public Result<PageResult<List<LouMallSearchGoodsVO>>> search(@RequestParam(required = false) @ApiParam(value = "搜索关键字") String keyword,
                                                                 @RequestParam(required = false) @ApiParam(value = "分类id") Long goodsCategoryId,
                                                                 @RequestParam(required = false) @ApiParam(value = "orderBy") String orderBy,
                                                                 @RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber,
                                                                 @TokenToMallUser MallUser loginMallUser) {
        
        logger.info("goods search api,keyword={},goodsCategoryId={},orderBy={},pageNumber={},userId={}", keyword, goodsCategoryId, orderBy, pageNumber, loginMallUser.getUserId());

        Map params = new HashMap(4);
        //两个搜索参数都为空，直接返回异常
        if (goodsCategoryId == null && StringUtils.isEmpty(keyword)) {
            LouMallException.fail("非法的搜索参数");
        }
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("goodsCategoryId", goodsCategoryId);
        params.put("page", pageNumber);
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        //对keyword做过滤 去掉空格
        if (!StringUtils.isEmpty(keyword)) {
            params.put("keyword", keyword);
        }
        if (!StringUtils.isEmpty(orderBy)) {
            params.put("orderBy", orderBy);
        }
        //搜索上架状态下的商品
        params.put("goodsSellStatus", Constants.SELL_STATUS_UP);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(louMallGoodsService.searchMallGoods(pageUtil));
    }

    @GetMapping("/goods/detail/{goodsId}")
    @ApiOperation(value = "商品详情接口", notes = "传参为商品id")
    public Result<LouMallGoodsDetailVO> goodsDetail(@ApiParam(value = "商品id") @PathVariable("goodsId") Long goodsId, @TokenToMallUser MallUser loginMallUser) {
        logger.info("goods detail api,goodsId={},userId={}", goodsId, loginMallUser.getUserId());
        if (goodsId < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        MallGoods goods = louMallGoodsService.getMallGoodsById(goodsId);
        if (goods == null) {
            return ResultGenerator.genFailResult("参数异常");
        }
        if (Constants.SELL_STATUS_UP != goods.getGoodsSellStatus()) {
            LouMallException.fail(ServiceResultEnum.GOODS_PUT_DOWN.getResult());
        }
        LouMallGoodsDetailVO goodsDetailVO = new LouMallGoodsDetailVO();
        BeanUtil.copyProperties(goods, goodsDetailVO);
        goodsDetailVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));
        return ResultGenerator.genSuccessResult(goodsDetailVO);
    }

}
