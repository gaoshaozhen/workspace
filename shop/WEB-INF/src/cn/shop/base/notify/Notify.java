package cn.shop.base.notify;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Notify
{
    Logger logger = Logger.getLogger(this.getClass());

    public enum NotifyType
    {
        EMAIL(1, "邮件"),

        SYSTEM(2, "系统"),

        UNKNOWN(-1, "未知类型");

        private int code;
        private String desc;

        public int getCode()
        {
            return this.code;
        }

        public String getDesc()
        {
            return desc;
        }

        NotifyType(int code, String desc)
        {
            this.code = code;
            this.desc = desc;
        }

        /**
         * 循环获得类型。
         * 
         * @param code
         * @return TradePeriod
         */
        public static NotifyType get(int code)
        {
            for (NotifyType type : NotifyType.values())
            {
                if (type.getCode() == code)
                {
                    return type;
                }
            }
            return UNKNOWN;
        }

        /**
         * 重载。
         * 
         * @param code
         * @return TradePeriod
         */
        public static NotifyType get(String code)
        {
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
            if (code != null && pattern.matcher(code).matches())
            {
                return get(Integer.parseInt(code));
            }
            else
            {
                return UNKNOWN;
            }
        }
    }

    public boolean sendEmail(String message, String[] toUser)
    {
        return true;
    }

    public boolean send(NotifyType type, String message, String[] toUser)
    {
        switch (type)
        {
            case EMAIL:
                sendEmail(message, toUser);
                break;
            case SYSTEM:
                break;
            default:
                logger.error("发送消息未指定类型");
        }
        return true;
    }
}
