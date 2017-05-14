package cn.shop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck
{
    /**
     * 是否检查处于登录状态。
     * 
     * @return
     */
    boolean login() default false;

    /**
     * 是否json请求。
     * 
     * @return
     */
    boolean json() default false;

    /**
     * 不是json请求时，未处于登录状态所返回的路径。
     * 
     * @return
     */
    String loginTarge();

    /**
     * json请求失败所返回的编码。
     * 
     * @return
     */
    int code() default 0;
}
