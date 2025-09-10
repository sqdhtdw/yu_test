package com.Lmall.config.handler;

import com.Lmall.config.annotation.TokenToMallUser;
import com.Lmall.dao.MallUserMapper;
import com.Lmall.dao.MallUserTokenMapper;
import com.Lmall.entity.MallUser;
import com.Lmall.entity.MallUserToken;
import com.Lmall.common.Constants;
import com.Lmall.common.LouMallException;
import com.Lmall.common.ServiceResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenToMallUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MallUserMapper mallUserMapper;
    @Autowired
    private MallUserTokenMapper mallUserTokenMapper;

    public TokenToMallUserMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToMallUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (parameter.getParameterAnnotation(TokenToMallUser.class) instanceof TokenToMallUser) {
            MallUser mallUser = null;
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == Constants.TOKEN_LENGTH) {
                MallUserToken mallUserToken = mallUserTokenMapper.selectByToken(token);
                if (mallUserToken == null || mallUserToken.getExpireTime().getTime() <= System.currentTimeMillis()) {
                    LouMallException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }
                mallUser = mallUserMapper.selectByPrimaryKey(mallUserToken.getUserId());
                if (mallUser == null) {
                    LouMallException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }
                if (mallUser.getLockedFlag().intValue() == 1) {
                    LouMallException.fail(ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
                }
                return mallUser;
            } else {
                LouMallException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

}
