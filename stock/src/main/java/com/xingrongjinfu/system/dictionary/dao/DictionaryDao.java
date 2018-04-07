package com.xingrongjinfu.system.dictionary.dao;

import com.xingrongjinfu.system.dictionary.model.Dictionary;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dictionaryDao")
public class DictionaryDao extends DynamicObjectBaseDao implements IDictionaryDao {
    @Override
    public int deleteByPrimaryKey(String dictionaryid) {
        return 0;
    }

    @Override
    public int insert(Dictionary record) {
        return this.save("SystemDictionaryMapper.insert",record);
    }

    @Override
    public int insertSelective(Dictionary record) {
        return 0;
    }

    @Override
    public Dictionary selectByPrimaryKey(String dictionaryid) {
        Dictionary dictionary = null;
        try {
            dictionary = (Dictionary) this.findForObject("SystemDictionaryMapper.selectByPrimaryKey",dictionaryid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    @Override
    public int updateByPrimaryKeySelective(Dictionary record) {
        return this.update("SystemDictionaryMapper.updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(Dictionary record) {
        return 0;
    }

    @Override
    public int updateStatus(Dictionary record) {
        return this.update("SystemDictionaryMapper.changeStatus",record);
    }

    @SuppressWarnings("all")
	@Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity) {
        List<TableDataInfo> dictionaryPageInfo = null;
        try
        {
            dictionaryPageInfo = (List<TableDataInfo>) this.findForList("SystemDictionaryMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return dictionaryPageInfo;
    }

    @SuppressWarnings("all")
	@Override
    public List<Dictionary> selectAllDictionary() {
        List<Dictionary> dictionaryList = null;
        try {
            dictionaryList = (List<Dictionary>) this.findForList("SystemDictionaryMapper.selectAllDictionary");
        }catch (Exception e){
            e.printStackTrace();
        }
        return dictionaryList;
    }

    @Override
    public List<Dictionary> selectDictionaryByType(String type) {
        List<Dictionary> dictionaryList = null;
        try {
            dictionaryList = (List<Dictionary>) this.findForList("SystemDictionaryMapper.selectDictionaryByType",type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dictionaryList;
    }
}
