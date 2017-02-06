package com.csy.webmvc.interceptor;

import com.csy.webmvc.redis.RUserDao;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
//    @Autowired
    private RUserDao rUserDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
//		String url = request.getServletPath();
//		if (url.contains(UrlPrefix.NO_NEED_LOGIN_PRE)) {
//			return true;
//		} else {
//			String uid = request.getParameter("uid");
//			String token = request.getParameter("token");
//			if (rUserDao.check(uid, token)) {
//				return true;
//			}
//			// 只有在返回false的时候才设置response，以保证不影响控制层
//			response.setContentType("text/html;charset=UTF-8");
//			ServletOutputStream stream = response.getOutputStream();
//			stream.write(ErrorResult.getResult(Code._200, "请重新登录").toJSONString().getBytes());
//			return false;
//		}
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
