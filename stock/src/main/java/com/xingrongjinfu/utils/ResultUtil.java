package com.xingrongjinfu.utils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ResultUtil {

    public static Result success(Object data) throws Exception {
        Result result = new Result();
        result.setCode("0000");
        result.setMsg("成功");
        JSONObject jsonObject = JSONObject.fromObject(data);
        //data = DesUtils.encrypt(jsonObject.toString());
        result.setData(jsonObject);
        return result;
    }

    public static Result successForList(Object list) throws Exception {
        Result result = new Result();
        result.setCode("0000");
        result.setMsg("成功");
        JSONArray jsonObject = JSONArray.fromObject(list);
        //list = DesUtils.encrypt(jsonObject.toString());
        result.setData(jsonObject);
        return result;
    }

    public static Result success()  throws Exception{
        return success(null);
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(String msg) {
        return error("9999", msg);
    }

    /**
     * Description: 返回参数时不用解密<br/>
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static Result successNoDes(Object data) throws Exception {
        Result result = new Result();
        result.setCode("0000");
        result.setMsg("成功");
        JSONObject jsonObject = JSONObject.fromObject(data);
        result.setData(data);
        return result;
    }
    public static Result successNoDes()  throws Exception{
        return successNoDes(null);
    }
}
