package com.Lmall.api;

import com.Lmall.api.vo.LouMallIndexCategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.Lmall.common.LouMallException;
import com.Lmall.common.ServiceResultEnum;
import com.Lmall.service.LouMallCategoryService;
import com.Lmall.util.Result;
import com.Lmall.util.ResultGenerator;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "v1", tags = "3.楼楼商城分类页面接口")
@RequestMapping("/api/v1")
public class LouMallGoodsCategoryAPI {

    @Resource
    private LouMallCategoryService louMallCategoryService;

    @GetMapping("/categories")
    @ApiOperation(value = "获取分类数据", notes = "分类页面使用")
    public Result<List<LouMallIndexCategoryVO>> getCategories() {
        List<LouMallIndexCategoryVO> categories = louMallCategoryService.getCategoriesForIndex();
        if (CollectionUtils.isEmpty(categories)) {
            LouMallException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(categories);
    }
}
