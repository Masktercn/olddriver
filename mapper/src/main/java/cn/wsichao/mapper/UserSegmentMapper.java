package cn.wsichao.mapper;


import cn.wsichao.pojo.Segment;
import cn.wsichao.pojo.User;
import cn.wsichao.pojo.UserSegment;

public interface UserSegmentMapper extends IManyToManyMapper<UserSegment, User, Segment> {

}
