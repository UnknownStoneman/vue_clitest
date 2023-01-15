package com.example.springbootvue.controller;

import com.example.springbootvue.pojo.Result;
import com.example.springbootvue.pojo.User;
import com.example.springbootvue.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@ResponseBody
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/allUser")
    public Result getAllUser(){
        PageHelper.startPage(1,7);
        List<User> users = userService.list();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
//        System.out.println(userPageInfo);
        return new Result(200,userPageInfo);
    }

    @GetMapping("/getByPage")
    public Result getBypage(@RequestParam int pageSize,@RequestParam int currentPage){
        PageHelper.startPage(currentPage,pageSize);
        List<User> users = userService.list();
        PageInfo<User> userPageInfo = new PageInfo<>(users);

        System.out.println("当前list："+userPageInfo);
        return new Result(200,userPageInfo);
    }

    @GetMapping("/getUserById/{uid}")
    public Result getUserById(@PathVariable String uid){
        User user = userService.getById(uid);
        if (user!=null)
            return new Result(200,user);
        else {
            return new Result(404,"查询失败");
        }
    }

    @DeleteMapping("/deleteUserById/{uid}")
    public Result  deleteUserById(@PathVariable String uid){
        boolean res = userService.removeById(uid);
        if (res)
            return new Result(200,"删除成功");
        else
            return new Result(404,"删除失败");
    }

    @PostMapping("/insertUser")
    public Result insertUser(@RequestBody User user) throws ParseException {
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formatDate = simpleDateFormat.format(date);
        Date dateFormat = simpleDateFormat.parse(formatDate);

        System.out.println("date:"+dateFormat);
        System.out.println(user);
        user.setCreateDate(dateFormat);
        boolean res = userService.save(user);
        if (res)
            return new Result(200,"添加成功");
        else
            return new Result(404,"添加失败");
    }

    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        boolean res = userService.updateById(user);
        if (res)
            return new Result(200,"修改成功");
        else
            return new Result(404,"修改失败");
    }
}
