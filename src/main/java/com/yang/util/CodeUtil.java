package com.yang.util;

import javax.servlet.http.HttpServletRequest;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExpected = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        return verifyCodeActual != null && verifyCodeActual.equals(verifyCodeExpected);
    }
}
