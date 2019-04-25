package cn.wsichao.mapper;


import cn.wsichao.pojo.Card;
import cn.wsichao.pojo.User;
import cn.wsichao.pojo.UserCard;

public interface UserCardMapper extends IManyToManyMapper<UserCard, User, Card> {

}
