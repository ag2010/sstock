package com.xingrongjinfu.system.dictionary.controller;

import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.dictionary.common.DictionaryConstant;
import com.xingrongjinfu.system.dictionary.model.Dictionary;
import com.xingrongjinfu.system.dictionary.service.IDictionaryService;
import com.xingrongjinfu.utils.UuidUtil;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class DictionaryController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private IDictionaryService iDictionaryService;

    /**
     * 跳转字典列表界面
     */
    @RequestMapping(DictionaryConstant.DICTIONARY_URL)
    public ModelAndView loadSystemDictionary() {
        return this.getModelAndView(DictionaryConstant.DICTIONARY_PAGE);
    }


    /**
     * 查询字典列表数据
     */
    @RequestMapping(DictionaryConstant.DICTIONARY_LIST_URL)
    public @ResponseBody
    List<Map<String, Object>> dictionaryList() {
        List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();

        // 取出所有字典
        List<Dictionary> dictionaryList = iDictionaryService.selectAllDictionary();

        for (Dictionary dictionary : dictionaryList) {
            Map<String, Object> dictionaryMap = new HashMap<String, Object>();
            dictionaryMap.put("id", dictionary.getDictionaryId());
            dictionaryMap.put("pId", dictionary.getParentId());
            dictionaryMap.put("isParent", dictionary.getParentId().equals("0") ? true : false);
            dictionaryMap.put("name", dictionary.getName());
            //dictionaryMap.put("open", false);
            resMapTrees.add(dictionaryMap);
        }

        return resMapTrees;
    }
    
    
    /**
     * 查询字典列表某一类型的数据
     */
    @RequestMapping(DictionaryConstant.DICTIONARY_LIST_BY_TYPE_URL)
    public @ResponseBody
    List<Map<String, Object>> getDictionaryListByType(String type) {
        List<Map<String, Object>> resMapTrees = new ArrayList<Map<String, Object>>();

        // 取出所有字典
        List<Dictionary> dictionaryList = iDictionaryService.selectDictionaryByType(type);

        for (Dictionary dictionary : dictionaryList) {
            Map<String, Object> dictionaryMap = new HashMap<String, Object>();
            dictionaryMap.put("id", dictionary.getDictionaryId());
            dictionaryMap.put("pId", dictionary.getParentId());
            dictionaryMap.put("isParent", dictionary.getParentId().equals("0") ? true : false);
            dictionaryMap.put("name", dictionary.getName());
            //dictionaryMap.put("open", false);
            resMapTrees.add(dictionaryMap);
        }

        return resMapTrees;
    }
    

    /**
     * 获取字典详细信息
     *
     * @return 字典对象
     */
    @RequestMapping(DictionaryConstant.VIEW_URL)
    public ModelAndView getDictionary() {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo = iDictionaryService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 跳转字典新增界面
     */
    @RequestMapping(DictionaryConstant.TO_ADD_URL)
    public ModelAndView toDictionaryAdd(@RequestParam("dictionaryId") String dictionaryId) {
        Dictionary dictionary = null;
        if ("0".equals(dictionaryId)) {
            dictionary = new Dictionary();
            dictionary.setDictionaryId("0");
            dictionary.setName("顶级");
        } else {
            dictionary = iDictionaryService.selectByPrimaryKey(dictionaryId);
        }
        ModelAndView modelAndView = this.getModelAndView(DictionaryConstant.ADD_PAGE);
        modelAndView.addObject("dictionary", dictionary);
        return modelAndView;
    }

    /**
     * 保存字典
     */
    @RequestMapping(DictionaryConstant.SAVE_URL)
    public @ResponseBody
    Message saveDictionary(Dictionary dictionary) {
        int result = 0;
        if ("".equals(dictionary.getDictionaryId()) || dictionary.getDictionaryId() == null) {
            String id = UuidUtil.get32UUID();
            dictionary.setDictionaryId(id);
            result = this.iDictionaryService.insert(dictionary);
        } else {
            result = this.iDictionaryService.updateByPrimaryKeySelective(dictionary);
        }
        return new Message(result);
    }

    /**
     * 修改状态
     */
    @RequestMapping(DictionaryConstant.CHANGE_STATUS_URL)
    public @ResponseBody
    Message changeStatus(Dictionary dictionary) {
        int result = iDictionaryService.updateStatus(dictionary);
        return new Message(result);
    }

    /**
     * 跳转修改页面
     */
    @RequestMapping(DictionaryConstant.TO_MODIFY_URL)
    public ModelAndView toDictionaryModify(@RequestParam(required = true) String dictionaryId) {
        ModelAndView modelAndView = this.getModelAndView(DictionaryConstant.MODIFY_PAGE);
        Dictionary dictionary = iDictionaryService.selectByPrimaryKey(dictionaryId);
        modelAndView.addObject("dictionary", dictionary);
        return modelAndView;
    }


}
