package cn.wsichao.service;


import cn.wsichao.mapper.UserMapper;
import cn.wsichao.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper mapper;

    public PageInfo<User> search(int pageNum, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = mapper.search(params);
        return new PageInfo<User>(list);
    }
}
