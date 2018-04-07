package com.xingrongjinfu.system.dictionary.dao;

import com.xingrongjinfu.system.dictionary.model.Dictionary;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

public interface IDictionaryDao {
    int deleteByPrimaryKey(String dictionaryid);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(String dictionaryid);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);

    int updateStatus(Dictionary record);

    /**
     * 根据条件分页查询字典对象
     *
     * @param page 分页对象
     * @return 字典信息集合信息
     */
    List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    List<Dictionary> selectAllDictionary();
    
    List<Dictionary> selectDictionaryByType(String type);

}