package com.tianxin.wechat.platform.manager.impl;

import com.tianxin.wechat.platform.manager.MessageFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractMessageFormatter
 *
 * @author snowway
 * @since 6/24/14 13:26
 */
public abstract class AbstractMessageFormatter implements MessageFormatter {

    protected Logger logger = LoggerFactory.getLogger(getClass());
}
