package com.xingrongjinfu.system.dictionary.service;

import com.xingrongjinfu.system.dictionary.dao.IDictionaryDao;
import com.xingrongjinfu.system.dictionary.model.Dictionary;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dictionaryService")
public class DictionaryService implements IDictionaryService {

    @Autowired
    private IDictionaryDao iDictionaryDao;

    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        return this.iDictionaryDao.pageInfoQuery(pageUtilEntity);
    }

    @Override
    public List<Dictionary> selectAllDictionary() {
        return this.iDictionaryDao.selectAllDictionary();
    }
    
    @Override
    public List<Dictionary> selectDictionaryByType(String type) {
        return this.iDictionaryDao.selectDictionaryByType(type);
    }
 
    @Override
    public Dictionary selectByPrimaryKey(String dictionaryId) {
        return this.iDictionaryDao.selectByPrimaryKey(dictionaryId);
    }

    @Override
    public int insert(Dictionary dictionary) {
        return iDictionaryDao.insert(dictionary);
    }

    @Override
    public int updateStatus(Dictionary dictionary) {
        return iDictionaryDao.updateStatus(dictionary);
    }

    @Override
    public int updateByPrimaryKeySelective(Dictionary dictionary) {
        return iDictionaryDao.updateByPrimaryKeySelective(dictionary);
    }

}