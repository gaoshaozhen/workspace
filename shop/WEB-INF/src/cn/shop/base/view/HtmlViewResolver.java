package cn.shop.base.view;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

public class HtmlViewResolver extends AbstractCachingViewResolver
{
    Logger logger = Logger.getLogger(this.getClass());

    // String
    @Override
    protected View loadView(String viewName, Locale locale) throws Exception
    {
        // if (location == null) {
        // throw new Exception(
        // "No location specified for GenericFileViewResolver.");
        // }
        String requestedFilePath = locale + viewName;
        Resource resource = null;

        try
        {
            logger.debug(requestedFilePath);
            resource = getApplicationContext().getResource(requestedFilePath);

        }
        catch (Exception e)
        {
            // 返回 null, 以便被下一个 resolver 处理
            logger.info("No file found for file: " + requestedFilePath);
            return null;
        }
        logger.info("Requested file found: " + requestedFilePath + ",viewName:"
                + viewName);
        // 根据视图名，获取相应的 view 对象
        GenericFileView view = this.getApplicationContext().getBean(viewName,
                GenericFileView.class);
        view.setUrl(requestedFilePath);
        // 写入 view 内容
        // view.setResponseContent(resource.getInputStream());
        return view;
    }
}
