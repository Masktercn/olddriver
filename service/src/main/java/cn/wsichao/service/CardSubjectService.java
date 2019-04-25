package cn.wsichao.service;

import cn.wsichao.pojo.Card;
import cn.wsichao.pojo.CardSubject;
import cn.wsichao.pojo.Subject;
import org.springframework.stereotype.Service;

@Service
public class CardSubjectService extends ManyToManyBaseService<CardSubject, Card, Subject> {


}
