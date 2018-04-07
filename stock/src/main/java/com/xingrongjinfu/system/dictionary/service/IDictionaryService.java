package com.xingrongjinfu.system.dictionary.service;

import com.xingrongjinfu.system.dictionary.model.Dictionary;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;

import java.util.List;

public interface IDictionaryService {

    List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity);

    List<Dictionary> selectAllDictionary();
    
    List<Dictionary> selectDictionaryByType(String type);

    Dictionary selectByPrimaryKey(String dictionaryId);

    int insert(Dictionary dictionary);
    int updateStatus(Dictionary dictionary);
    int updateByPrimaryKeySelective(Dictionary dictionary);
}
