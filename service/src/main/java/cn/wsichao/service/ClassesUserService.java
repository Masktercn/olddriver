package cn.wsichao.service;


import cn.wsichao.pojo.Classes;
import cn.wsichao.pojo.ClassesUser;
import cn.wsichao.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class ClassesUserService extends ManyToManyBaseService<ClassesUser, Classes, User> {

}
