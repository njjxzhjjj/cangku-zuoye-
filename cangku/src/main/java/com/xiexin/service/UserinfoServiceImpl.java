package com.xiexin.service;

import com.xiexin.bean.Userinfo;
import com.xiexin.bean.UserinfoExample;
import com.xiexin.dao.UserinfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userinfoService")
public class UserinfoServiceImpl implements UserinfoService {
	@Autowired(required = false)
	private UserinfoDAO userinfoDAO;
	public long countByExample(UserinfoExample example){
    	return userinfoDAO.countByExample(example);
    }

	public int deleteByExample(UserinfoExample example){
    	return userinfoDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return userinfoDAO.deleteByPrimaryKey(id);
    }

	public int insert(Userinfo record){
    	return userinfoDAO.insert(record);
    }

	public int insertSelective(Userinfo record){
    	return userinfoDAO.insertSelective(record);
    }

	public List<Userinfo> selectByExample(UserinfoExample example){
    	return userinfoDAO.selectByExample(example);
    }

	public Userinfo selectByPrimaryKey(Integer id){
    	return userinfoDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(Userinfo record, UserinfoExample example){
    	return userinfoDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(Userinfo record, UserinfoExample example){
    	return userinfoDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(Userinfo record){
    	return userinfoDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Userinfo record){
    	return userinfoDAO.updateByPrimaryKey(record);
    }


}
