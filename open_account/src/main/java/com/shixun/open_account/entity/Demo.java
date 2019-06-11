package com.shixun.open_account.entity;

import lombok.Data;

/****
 *@author:cmh
 *@date: 2019/6/110946
 *@descrption:
 */
@Data
public class Demo {
 private String id;
 private String name;
 private String age;
 public Demo(String id,String name,String age)
 {
     setName(name);
     setAge(age);
     setId(id);
 }
}
