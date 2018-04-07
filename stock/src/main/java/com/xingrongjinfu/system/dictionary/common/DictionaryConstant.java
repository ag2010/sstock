package com.xingrongjinfu.system.dictionary.common;

/**
 * 字典管理 常量信息
 * 
 * @author y
 */
public class DictionaryConstant
{



    /**
     * 请求地址：跳转至字典查询列表
     */
    public final static String DICTIONARY_URL = "dictionaryView";

    /**
     * 请求地址：跳转至字典详细信息查询列表
     */
    public final static String VIEW_URL = "dictionaryInfoDetail";

    /**
     * 请求地址：跳转至字典添加界面
     */
    public final static String TO_ADD_URL = "toDictionaryAdd";

    /**
     * 请求地址：跳转至字典修改界面
     */
    public final static String TO_MODIFY_URL = "toDictionaryModify";


    /**
     * 请求地址：字典列表查询
     */
    public final static String DICTIONARY_LIST_URL = "dictionaryList";
    
    
    /**
     * 请求地址：查询特定类型的字典列表
     */
    public final static String DICTIONARY_LIST_BY_TYPE_URL = "getDictionaryListByType";
    

    /**
     * 请求地址：删除字典信息
     */
    public final static String DEL_URL = "deleteDictionaryById";

    /**
     * 请求地址：保存&修改 字典信息
     */
    public final static String SAVE_URL = "saveDictionary";

    /**
     * 请求地址：启动/停用 字典
     */
    public final static String CHANGE_STATUS_URL = "changeDictionaryStatus";


    /**
     * 请求地址：检查字典名唯一
     */
    public final static String CHECK_NAME_UNIQUE_URL = "checkNameUnique";
    

    /**
     * 逻辑视图名：字典列表界面
     */
    public final static String DICTIONARY_PAGE = "system/dictionary-list";

    /**
     * 逻辑视图名：字典详细信息界面
     */
    public final static String VIEW_PAGE = "system/dictionaryInfo-detail";

    /**
     * 逻辑视图名：字典添加界面
     */
    public final static String ADD_PAGE = "system/dictionary-add";

    /**
     * 逻辑视图名：字典修改界面
     */
    public final static String MODIFY_PAGE = "system/dictionary-modify";


}
