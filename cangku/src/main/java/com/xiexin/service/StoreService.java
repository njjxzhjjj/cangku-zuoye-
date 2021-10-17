package com.xiexin.service;

import com.xiexin.bean.Store;
import com.xiexin.bean.StoreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreService {
   
    long countByExample(StoreExample example);

    int deleteByExample(StoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    List<Store> selectByExample(StoreExample example);

    Store selectByPrimaryKey(Integer id);
  
    int updateByExampleSelective(@Param("record") Store record, @Param("example") StoreExample example);

    int updateByExample(@Param("record") Store record, @Param("example") StoreExample example);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

}
