package cn.wsichao.service;

import cn.wsichao.pojo.Card;
import cn.wsichao.pojo.User;
import cn.wsichao.pojo.UserCard;
import org.springframework.stereotype.Service;

@Service
public class UserCardService extends ManyToManyBaseService<UserCard, User, Card> {

}