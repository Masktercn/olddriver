package cn.wsichao.mapper;

import java.util.List;

public interface IManyToManyMapper<T,F,S> extends IMapper<T> {

    /**
     * 根据第一个id删除所有关联
     * 比如根据指定学习卡删除和所有学科的关系
     * @param firstId
     * @return
     */
    int deleteByFirstId(Long firstId);

    /**
     * 删除指定学科和所有学习卡的关系
     * @param secondId
     * @return
     */
    int deleteBySecondId(Long secondId);

    /**
     * 根据第二个Id查询出第一个的所有关联
     * 比如查询出指定学科拥有的所有学习卡
     * @param secondId
     * @return
     */
    List<F> selectFirstListBySecondId(Long secondId);

    /**
     * 查询出指定学习卡可用于的所有学科
     * @param firstId
     * @return
     */
    List<S> selectSecondListByFirstId(Long firstId);

}
