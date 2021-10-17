package com.xiexin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiexin.bean.Store;
import com.xiexin.bean.StoreExample;
import com.xiexin.respcode.Result;
import com.xiexin.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired(required = false)
    private StoreService storeService;

    //全查
    @RequestMapping("/selectAll") // /store/selectAll
    public Result selectAll(){
        List<Store> store = storeService.selectByExample(null);
        Result result = new Result(store);
        return result;
    }

    //带参数查询
    //分页查询(带参数)
    @RequestMapping("/selectPageAll") // /store/selectPageAll
    public Result selectPageAll(@RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                                @RequestParam(value = "limit",defaultValue = "10",required = true)Integer pageSize) {

        StoreExample example = new StoreExample();
        StoreExample.Criteria criteria = example.createCriteria();
        //使用pagehelper分页
        PageHelper.startPage(page, pageSize);
        List<Store> stores = storeService.selectByExample(example);
        PageInfo pageInfo = new PageInfo(stores);
        Result result = new Result(pageInfo);
        return result;
    }

    //修改
    @RequestMapping("/updateOne") // /store/updateOne
    public Result updateOne(@RequestBody Store store){
        System.out.println("store = " + store);
        int i = storeService.updateByPrimaryKeySelective(store);
        if (i == 1) {
            return new Result();
        }else{
            return new Result(40001,"修改失败");
        }
    }

    //增
    // 后端订单增加 -- 针对layui的 针对前端传 json序列化的
    @RequestMapping("/addOne") // /store/insert
    public  Result  addOne(@RequestBody Store store){ // orders 对象传参, 规则: 前端属性要和后台的属性一致!!!
        System.out.println("store = " + store);
        int i = storeService.insertSelective(store);
        if(i==1){
            return new Result();
        }else{
            return new Result(50002,"添加失败");
        }
    }


    //普通添加
    @RequestMapping("/insert")
    public Map insert(@RequestBody Store store) { //  对象传参, 规则: 前端属性要和后台的属性一致!!!
        Map map = new HashMap();
        int i = storeService.insertSelective(store);
        if (i > 0) {
            map.put("code", 200);
            map.put("msg", "添加成功");
            return map;
        } else {
            map.put("code", 400);
            map.put("msg", "添加失败,检查网络再来一次");
            return map;
        }
    }

    //删除
    @RequestMapping("/deleteOne") // /store/deleteOne
    public Result deleteOne(@RequestParam(value = "id",required = false) Integer id){
        int i = storeService.deleteByPrimaryKey(id);
        if(i==1){
            return new Result(0,"删除成功");
        }else{
            return new Result("40001","删除失败");
        }
    }

}
