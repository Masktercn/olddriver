package cn.wsichao.mapper;

import java.util.List;

public interface IMapper<T> {

    int insert(T pojo);

    /**
     * 根据id进行更新
     * @param pojo
     * @return
     */
    int update(T pojo);

    /**
     * 根据id进行删除
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 以非空字段值作为查询条件进行查询
     * @param pojo
     * @return
     */
    List<T> select(T pojo);
}
