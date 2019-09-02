package cup.com.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import cup.com.config.FilterProperties;
import cup.com.config.JwtProperties;
import cup.com.pojo.UserInfo;
import cup.com.utils.CookieUtils;
import cup.com.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description：
 * Author:TengLu
 * Date:2019/9/2
 * Time:15:13
 */
@Component
public class LoginFilter extends ZuulFilter {

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        List<String> allowPaths = filterProperties.getAllowPaths();

        // 初始化运行上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取request对象
        HttpServletRequest request = context.getRequest();
        // 获取请求的路径
        String url = request.getRequestURL().toString();

        for (String allowPath : allowPaths) {
            StringUtils.contains(url,allowPath);
            return false;
        }

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 初始化运行上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取request对象
        HttpServletRequest request = context.getRequest();

        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());

        try {
            UserInfo infoFromToken = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }
}
