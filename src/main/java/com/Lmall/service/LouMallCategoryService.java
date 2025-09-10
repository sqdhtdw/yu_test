package com.Lmall.service;

import com.Lmall.api.vo.LouMallIndexCategoryVO;
import com.Lmall.entity.GoodsCategory;

import java.util.List;

public interface LouMallCategoryService {

    String saveCategory(GoodsCategory goodsCategory);

    String updateGoodsCategory(GoodsCategory goodsCategory);

    GoodsCategory getGoodsCategoryById(Long id);

    Boolean deleteBatch(Integer[] ids);

    /**
     * 返回分类数据(首页调用)
     *
     * @return
     */
    List<LouMallIndexCategoryVO> getCategoriesForIndex();
}
