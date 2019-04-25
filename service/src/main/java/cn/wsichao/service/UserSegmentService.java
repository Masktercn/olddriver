package cn.wsichao.service;

import cn.wsichao.pojo.Segment;
import cn.wsichao.pojo.User;
import cn.wsichao.pojo.UserSegment;
import org.springframework.stereotype.Service;

@Service
public class UserSegmentService extends ManyToManyBaseService<UserSegment, User, Segment> {

}