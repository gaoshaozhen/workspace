package cn.shop.model;

import java.util.HashMap;
import java.util.Map;

import cn.shop.base.Sex;
import cn.shop.base.UserClassification;
import cn.shop.base.util.Default;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.MemberLvDao;

/**
 * 登录用户信息类。
 * 
 * @author shaozhen
 */
public class MemberInfo
{
    Integer memberId;
    String username;
    String password;

    Map<String, Object> memberLv;

    /*
     * 积分。
     */
    Long point;

    /**
     * 用户分类
     */
    UserClassification userClassificationification;

    String mobile;
    String email;
    Sex sex;
    Long birthDay;

    /**
     * 备注。
     */
    String remark;

    /**
     * 消费积分
     */
    Long mp;

    public Long getMp()
    {
        return mp;
    }

    public void setMp(Long long1)
    {
        this.mp = long1;
    }

    public int getMemberId()
    {
        return memberId;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public Map<String, Object> getMemberLv()
    {
        return memberLv;
    }

    public Long getPoint()
    {
        return point;
    }

    public UserClassification getUserClassification()
    {
        return userClassificationification;
    }

    public UserClassification getUserClassificationification()
    {
        return userClassificationification;
    }

    public String getMobile()
    {
        return mobile;
    }

    public String getEmail()
    {
        return email;
    }

    public Sex getSex()
    {
        return sex;
    }

    public Long getBirthDay()
    {
        return birthDay;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setMemberId(Integer memberId)
    {
        this.memberId = memberId;
    }

    public void setUserClassificationification(
            UserClassification userClassificationification)
    {
        this.userClassificationification = userClassificationification;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setSex(Sex sex)
    {
        this.sex = sex;
    }

    public void setBirthDay(Long birthDay)
    {
        this.birthDay = birthDay;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public void setMemberId(int memberId)
    {
        this.memberId = memberId;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setMemberLv(Map<String, Object> memberLv)
    {
        this.memberLv = memberLv;
    }

    public void setPoint(Long point)
    {
        this.point = point;
    }

    public void setUserClassification(
            UserClassification userClassificationification)
    {
        this.userClassificationification = userClassificationification;
    }

    /**
     * 初始化参数。
     * 
     * @param map
     */
    public void setParam(Map<String, Object> map)
    {
        MemberLvDao memBerLvDao = (MemberLvDao) SpringContextUtil
                .getBean("memberLvDao");
        Map<String, Object> gradeMap = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        Integer memberLvid = (Integer) map.get("lv_id");
        dbParam.put("lv_id", memberLvid);
        Map<String, Object> result = (Map<String, Object>) memBerLvDao
                .getMemberLvById(dbParam);
        if (result != null)
        {
            gradeMap.put("name", result.get("name"));
        }
        gradeMap.put("lv_id", map.get("lv_id"));
        setMemberLv(gradeMap);
        setMemberId((Integer) map.get("member_id"));
        setUsername((String) map.get("uname"));
        setPassword((String) map.get("password"));
        setPoint((Long) map.get("point"));
        setMobile((String) map.get("mobile"));
        setEmail((String) map.get("email"));
        setBirthDay((Long) map.get("birthDay"));
        setRemark((String) map.get("remark"));
        setMp((Long) Default.get(map.get("mp"), 0));
    }
}
