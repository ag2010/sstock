package org.framework.core.dao;

import java.util.List;
import javax.annotation.Resource;


import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * 数据DAO层通用数据处理
 * 
 * @author y
 */
public class DynamicObjectBaseDao
{

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 保存对象
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public int save(String str, Object obj)
    {
        return sqlSessionTemplate.insert(str, obj);
    }

    /**
     * 批量更新
     * 
     * @param str
     * obj
     * @return
     * @throws Exception
     */
    public int batchSave(String str, List<?> objs)
    {
        return sqlSessionTemplate.insert(str, objs);
    }

    /**
     * 修改对象
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public int update(String str, Object obj)
    {
        return sqlSessionTemplate.update(str, obj);
    }

    /**
     * 批量更新
     * 
     * @param str
     * obj
     * @return
     * @throws Exception
     */
    public void batchUpdate(String str, List<?> objs) throws Exception
    {
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        // 批量执行器
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try
        {
            if (objs != null)
            {
                for (int i = 0, size = objs.size(); i < size; i++)
                {
                    sqlSession.update(str, objs.get(i));
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
        }
        finally
        {
            sqlSession.close();
        }
    }

    /**
     * 批量删除
     * 
     * @param str
     *  obj
     * @return
     * @throws Exception
     */
    public Object batchDelete(String str, List<?> objs) throws Exception
    {
        return sqlSessionTemplate.delete(str, objs);
    }

    /**
     * 删除对象
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public Object delete(String str, Object obj)
    {
        return sqlSessionTemplate.delete(str, obj);
    }

    /**
     * 删除对象
     *
     * @param str
     * @param id
     * @return
     * @throws Exception
     */
    public Object delete(String str, Integer id)
    {
        return sqlSessionTemplate.delete(str, id);
    }

    /**
     * 查找对象
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public Object findForObject(String str, Object obj)
    {
        return sqlSessionTemplate.selectOne(str, obj);
    }

    /**
     * 查找对象
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public Object findForList(String str, Object obj) throws Exception
    {
        return sqlSessionTemplate.selectList(str, obj);
    }

    public Object findForList(String str) throws Exception
    {
        return sqlSessionTemplate.selectList(str);
    }

    public Object findForMap(String str, Object obj, String key, String value) throws Exception
    {
        return sqlSessionTemplate.selectMap(str, obj, key);
    }

    
    
}
