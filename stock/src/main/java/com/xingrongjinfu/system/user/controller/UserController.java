package com.xingrongjinfu.system.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xingrongjinfu.utils.IdUtil;
import com.xingrongjinfu.utils.UuidUtil;
import org.apache.shiro.service.MailSenderService;
import org.aspectj.lang.annotation.ActionControllerLog;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.controller.BaseController;
import org.framework.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xingrongjinfu.system.SystemConstant;
import com.xingrongjinfu.system.role.model.Role;
import com.xingrongjinfu.system.role.service.IRoleService;
import com.xingrongjinfu.system.syscode.model.SysCode;
import com.xingrongjinfu.system.user.common.UserConstant;
import com.xingrongjinfu.system.user.model.User;
import com.xingrongjinfu.system.user.model.UserRole;
import com.xingrongjinfu.system.user.service.IUserService;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户 业务处理
 * 
 * @author y
 */
@Controller
@RequestMapping(SystemConstant.SYSTEM_URL)
public class UserController extends BaseController
{

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private MailSenderService mailSenderService;

    /**
     * 跳转用户列表界面
     */
    @RequestMapping(UserConstant.USER_URL)
    public ModelAndView loadSystemUser()

    {
        ModelAndView modelAndView= this.getModelAndView(UserConstant.USER_PAGE);
        modelAndView.addObject("roles",getRoleList());
        return modelAndView;
    }

    /**
     * 用户详细信息
     */
    @RequestMapping(UserConstant.VIEW_URL)
    public ModelAndView loadDeailView()
    {
        return this.getModelAndView(UserConstant.VIEW_PAGE);
    }

    /**
     * 跳转用户新增界面
     */
    @RequestMapping(UserConstant.TO_ADD_URL)
    public ModelAndView toUserAdd(String userId)
    {
        ModelAndView modelAndView = this.getModelAndView(UserConstant.ADD_PAGE);
        modelAndView.addObject("roles", getRoleList());
        return modelAndView;
    }

    /**
     * 跳转用户修改界面
     */
    @RequestMapping(UserConstant.TO_MODIFY_URL)
    public ModelAndView toUserModify(@RequestParam(required = true) String userId)
    {
        ModelAndView modelAndView = this.getModelAndView(UserConstant.MODIFY_PAGE);
        if (userId != null)
        {
            modelAndView.addObject("user", this.userService.findByUserId(userId));
            modelAndView.addObject("role", this.roleService.findByUserId(userId));
            modelAndView.addObject("roles", getRoleList());
        }
        return modelAndView;
    }

    /**
     * 跳转用户修改密码界面
     */
    @RequestMapping(UserConstant.TO_CHANGEPWD_URL)
    public ModelAndView toChangePwd(@RequestParam(required = true) String userId)
    {
        ModelAndView modelAndView = this.getModelAndView(UserConstant.CHANGE_PWD_PAGE);
        if (userId != null)
        {
            modelAndView.addObject("user", this.userService.findByUserId(userId));
        }
        return modelAndView;
    }

    /**
     * 查询用户列表
     */
    @RequestMapping(UserConstant.USER_LIST_URL)
    public ModelAndView userList()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();

        List<TableDataInfo> tableDataInfo = userService.pageInfoQuery(pageUtilEntity);

        return buildDataTable(pageUtilEntity.getTotalResult(), tableDataInfo);
    }

    /**
     * 发送邮件测试
     */
    @RequestMapping(value = "sendEmail")
    public @ResponseBody Message sendEmail()
    {
        int result = 0;
        HashMap<String, Object> emailMap = new HashMap<String, Object>();
        emailMap.put("subject", "恭喜您注册成为火星系统会员");
        emailMap.put("template", "mail_register.vm");
        emailMap.put("loginName", "admin");
        emailMap.put("email", "346039442@qq.com");
        emailMap.put("mobileNo", "15017911213");
        try
        {
            mailSenderService.sendWithTemplate("346039442@qq.com", emailMap);
            result = 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new Message(result);
    }

    /**
     * 保存用户信息
     */
    @ActionControllerLog(title = "系统管理", action = "系统管理-保存用户", isSaveRequestData = true)
    @RequestMapping(UserConstant.SAVE_URL)
    public @ResponseBody Message saveUser(User user, Integer roleId)
    {
        int result = 0;
        String userId = user.getUserId();

        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);

        if (userId != null)
        {
            result = userService.updateUser(user);
            userRole.setUserId(userId);
            roleService.updateUserRole(userRole);
        }
        else
        {
            user.setUserId(UuidUtil.get32UUID());
            result = userService.addUser(user);
            userRole.setUserId(user.getUserId());
            roleService.addUserRole(userRole);
        }
        return new Message(result);
    }

    /**
     * 启动/停用 操作
     */
    @ActionControllerLog(title = "系统管理", action = "系统管理-启用/停用-用户", isSaveRequestData = true)
    @RequestMapping(UserConstant.CHANGE_STATUS_URL)
    public @ResponseBody Message changeUserStatus(User user)
    {
        int result = 0;
        String id = user.getUserId();
        if (id != null)
        {
            result = userService.changeUserStatus(user);
        }
        return new Message(result);
    }

    /**
     * 根据ID删除用户
     */
    @ActionControllerLog(title = "系统管理", action = "系统管理-删除用户", isSaveRequestData = true)
    @RequestMapping(UserConstant.DEL_URL)
    public @ResponseBody Message deleteUserById(User user)
    {
        int result = 0;
        String userId = user.getUserId();
        if (userId != null)
        {
            result = userService.deleteUserByInfo(user);
        }
        return new Message(result);
    }

    /**
     * 根据ID修改密码
     */
    @ActionControllerLog(title = "系统管理", action = "系统管理-修改密码", isSaveRequestData = true)
    @RequestMapping(UserConstant.CHANGE_PWD_URL)
    public @ResponseBody Message changeUserPwd(User user)
    {
        int result = 0;
        String id = user.getUserId();
        if (id != null)
        {
            result = userService.changePassword(user);
        }
        return new Message(result);
    }

    /**
     * 校验用户名
     */
    @RequestMapping(UserConstant.CHECK_NAME_UNIQUE_URL)
    public @ResponseBody String checkNameUnique(User user)
    {
        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = userService.checkNameUnique(user);
        }
        return uniqueFlag;
    }

    /**
     * 获取角色信息
     */
    public List<SysCode> getRoleList()
    {
        List<Role> roleList = roleService.findAllRole();
        List<SysCode> sysCodeList = new ArrayList<SysCode>();
        for (Role role : roleList)
        {
            SysCode sysCode = new SysCode();
            sysCode.setCodeid(role.getRoleId().toString());
            sysCode.setCodevalue(role.getRoleName());
            sysCodeList.add(sysCode);
        }
        return sysCodeList;
    }


    /**
     * 重置密码
     * @param userName
     * @return
     */
    @RequestMapping(UserConstant.RESET_PASSWORD)
    public @ResponseBody Message resetPwd(String userName)
    {
       int result = 0;
       System.out.println(userName);
       String[] userNames=userName.split(",");
       System.out.println("userNames:"+userNames);
       if (userNames!=null) {
           for (String name : userNames) {
               System.out.println("name:" + name);
               User user= userService.findByUserName(name);
               user.setPassword("123456");
               result = userService.changePassword(user);
           }
       }
        return new Message(result);
    }
}
