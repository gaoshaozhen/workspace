package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.AddressDao;
import cn.shop.model.Check;
import cn.shop.model.MemberInfo;

/**
 * 收货地址处理类。
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/mall/")
public class AddressController
{
    /**
     * 获得收货地址列表
     * 
     * @return
     */
    @RequestMapping(value = "address.shtm")
    public String getAddress(ModelMap model,
            @RequestParam Map<String, Object> param, HttpSession session)
    {
        AddressDao dao;
        List<Map<String, Object>> list;
        Map<String, Object> dbParam;
        MemberInfo memberInfo;

        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        memberInfo = Check.getMemberInfo(session);
        dbParam = new HashMap<String, Object>();
        dbParam.put("member_id", memberInfo.getMemberId());

        dao = (AddressDao) SpringContextUtil.getBean("addressDao");
        list = dao.getAddressBymemberId(dbParam);

        model.addAttribute("addressList", list);
        return "mall/v2/address.jsp";
    }

    /**
     * 新增收货地址页面
     */
    @RequestMapping(value = "addAddress.shtm", method = RequestMethod.GET)
    public String addAddress(HttpSession session,
            @RequestParam Map<String, String> param)
    {
        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        return "mall/v2/addAddress.jsp";
    }

    /**
     * 新增收货地址页面
     */
    @RequestMapping(value = "addNewAddress.shtm", method = { RequestMethod.POST })
    public String addNewAddress(HttpSession session,
            @RequestParam Map<String, String> param)
    {
        String addr;
        int defAddr;
        String name;
        String mobile;
        String zip;
        int memberId;
        MemberInfo memberInfo;
        Map<String, Object> dbParam;
        AddressDao dao;

        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        name = StringUtils.defaultIfEmpty(param.get("name"), "").trim();
        addr = StringUtils.defaultIfEmpty(param.get("addr"), "").trim();
        defAddr = NumberUtils.toInt(param.get("defAddr")) == 1 ? 1 : 0;
        mobile = StringUtils.defaultIfEmpty(param.get("mobile"), "").trim();
        zip = StringUtils.defaultIfEmpty(param.get("zip"), "").trim();
        if (name.length() < 3 || mobile.length() < 3 || zip.length() < 3) // 检查参数
        {
            return "forward:/mall/addAddress.shtm";
        }
        memberInfo = Check.getMemberInfo(session);
        memberId = memberInfo.getMemberId();
        dbParam = new HashMap<String, Object>();
        dbParam.put("name", name);
        dbParam.put("def_addr", defAddr);
        dbParam.put("addr", addr);
        dbParam.put("mobile", mobile);
        dbParam.put("zip", zip);
        dbParam.put("member_id", memberId);
        dao = (AddressDao) SpringContextUtil.getBean("addressDao");
        dao.addAddress(dbParam);
        return "redirect:/mall/address.shtm";
    }

    /**
     * 删除收货地址
     */
    @RequestMapping(value = "deleteAddress.shtm", method = { RequestMethod.GET })
    public String deleteAddress(@RequestParam Map<String, String> param,
            HttpSession session)
    {
        Integer addressId;
        MemberInfo memberInfo;
        int memberId;
        Map<String, Object> dbParam;
        AddressDao addressDao;

        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        addressId = NumberUtils.toInt(param.get("addressId"), -1);
        if (addressId < 0)
        {
            return "redirect:/mall/address.shtm";
        }
        memberInfo = Check.getMemberInfo(session);
        memberId = memberInfo.getMemberId();
        dbParam = new HashMap<String, Object>();
        dbParam.put("member_id", memberId);
        dbParam.put("addr_id", addressId);
        addressDao = (AddressDao) SpringContextUtil.getBean("addressDao");
        addressDao.deleteAddress(dbParam);
        return "redirect:/mall/address.shtm";
    }
}
