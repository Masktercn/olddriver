package cn.wsichao.service;

import cn.wsichao.mapper.IMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseService<T> {


    @Autowired
    private IMapper<T> mapper;

    public int insert(T pojo){
        return mapper.insert(pojo);
    }

    public int update(T pojo){
        return mapper.update(pojo);
    }

    public int delete(Long id){
        if(id==null){
            throw new RuntimeException("删除时必须指定id的值");
        }
        return mapper.delete(id);
    }

    public List<T> selectList(){
        return mapper.select(null);
    }

    public List<T> selectList(T pojo){
        return mapper.select(pojo);
    }

    //条件排序查询 asc desc
    public List<T> selectList(T pojo, String orderBy){
        PageHelper.orderBy(orderBy);
        return mapper.select(pojo);
    }

    public T selectOne(T pojo){
        List<T> list = mapper.select(pojo);
        if(list!=null && list.size()>1){
            throw new RuntimeException("selectOne方法查询出了多条数据");
        }
        if(list==null){
            return null;
        }
        if(list.size()==0){
            return null;
        }
        return list.get(0);
    }

    private T createInstanceAndSetId(Long id) {
        if(id == null){
            throw new RuntimeException("根据id查询时，id不能为null");
        }
        //拿到运行时T的真实类型
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class tClz = (Class) type.getActualTypeArguments()[0];
        try {
            T t = (T) tClz.newInstance();
            tClz.getDeclaredMethod("setId", Long.class).invoke(t, id);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public T selectOne(Long id){
        //创建T的对象，并把id设置到T里面
        T pojo = createInstanceAndSetId(id);
        return selectOne(pojo);
    }

    //条件分页查询
    public PageInfo<T> page(int pageNum, int pageSize, T pojo){
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = mapper.select(pojo);
        return new PageInfo<T>(list);
    }

    public PageInfo<T> page(int pageNum, int pageSize, T pojo, String orderBy){
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(orderBy);
        List<T> list = mapper.select(pojo);
        return new PageInfo<T>(list);
    }

    public boolean isExisted(T pojo){
        List<T> list = mapper.select(pojo);
        if(list == null || list.size()==0){
            return false;
        }
        return true;
    }

}
