package com.Lmall.dao;

import com.Lmall.entity.MallOrderAddress;

public interface MallOrderAddressMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(MallOrderAddress record);

    int insertSelective(MallOrderAddress record);

    MallOrderAddress selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(MallOrderAddress record);

    int updateByPrimaryKey(MallOrderAddress record);
}