package com.example.springbootvue.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableId(type=IdType.AUTO)
    private String uid;

    private String username;

    private String address;
    @TableField("phoneNum")
    private String phoneNum;
    @TableField("createDate")
    private Date createDate;

}
