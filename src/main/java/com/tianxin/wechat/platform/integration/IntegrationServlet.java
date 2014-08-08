package com.tianxin.wechat.platform.integration;

import com.tianxin.wechat.platform.manager.MessageProcessor;
import com.tianxin.wechat.platform.manager.impl.DefaultMessageProcessor;
import com.tianxin.wechat.platform.utils.SignatureUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 验证微信HTTP签名
 *
 * @author snowway
 * @since 6/13/14 12:09
 */
public class IntegrationServlet extends HttpServlet {

    public static final String TOKEN = "6d0728c0-f2b1-11e3-9fd9-222e80f960f2";//和微信平台一致的token

    private MessageProcessor messageProcessor = new DefaultMessageProcessor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String echostr = StringUtils.trimToEmpty(req.getParameter("echostr"));//随机字符串
        boolean valid = SignatureUtils.verify(req, TOKEN);
        if (valid) {//将echostr原封返回
            PrintWriter writer = resp.getWriter();
            IOUtils.write(echostr, writer);//将echostr原封返回
            IOUtils.closeQuietly(writer);
        }
    }


    /**
     * 当收到消息或事件后回调
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean valid = SignatureUtils.verify(req, TOKEN);
        if (!valid) {
            return;
        }
        String content = IOUtils.toString(req.getInputStream());
        String result = messageProcessor.process(content);
        if (StringUtils.isNotBlank(result)) {
            PrintWriter writer = resp.getWriter();
            IOUtils.write(result, writer);
            IOUtils.closeQuietly(writer);
        }
    }
}
