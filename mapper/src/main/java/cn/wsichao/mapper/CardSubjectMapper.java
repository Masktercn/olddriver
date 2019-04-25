package cn.wsichao.mapper;

import cn.wsichao.pojo.Card;
import cn.wsichao.pojo.CardSubject;
import cn.wsichao.pojo.Subject;

public interface CardSubjectMapper extends IManyToManyMapper<CardSubject, Card, Subject> {

}
