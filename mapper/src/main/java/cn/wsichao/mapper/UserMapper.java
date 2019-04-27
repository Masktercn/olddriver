package cn.wsichao.mapper;


import cn.wsichao.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends IMapper<User> {

    List<User> search(Map<String, Object> params);

}
