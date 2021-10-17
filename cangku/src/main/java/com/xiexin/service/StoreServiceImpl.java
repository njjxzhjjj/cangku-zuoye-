package com.xiexin.service;

import com.xiexin.bean.Store;
import com.xiexin.bean.StoreExample;
import com.xiexin.dao.StoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("storeService")
public class StoreServiceImpl implements StoreService{
	@Autowired(required = false)
	private StoreDAO storeDAO;
	public long countByExample(StoreExample example){
    	return storeDAO.countByExample(example);
    }

	public int deleteByExample(StoreExample example){
    	return storeDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return storeDAO.deleteByPrimaryKey(id);
    }

	public int insert(Store record){
    	return storeDAO.insert(record);
    }

	public int insertSelective(Store record){
    	return storeDAO.insertSelective(record);
    }

	public List<Store> selectByExample(StoreExample example){
    	return storeDAO.selectByExample(example);
    }

	public Store selectByPrimaryKey(Integer id){
    	return storeDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(Store record, StoreExample example){
    	return storeDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(Store record, StoreExample example){
    	return storeDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(Store record){
    	return storeDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Store record){
    	return storeDAO.updateByPrimaryKey(record);
    }


}
