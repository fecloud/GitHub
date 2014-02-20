/**
 * @(#) XmlUtil.java Created on 2012-7-19
 *
 * Copyright (c) 2012 Aspire. All Rights Reserved
 */
package com.aspire.common.util;

import java.io.FileOutputStream;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * The class <code>XmlUtil</code>
 *
 * @author likai
 * @version 1.0
 */
public class XmlUtil {
    /**
     *  将XML文件流转换成具体的对象
     * @param inputStream 文件流
     * @return  具体的对象
     */
    public static Object deSerialize(Class<?> c, InputStream inputStream){
        String messageAlias;
        XStreamAlias xStreamAlias = c.getAnnotation(XStreamAlias.class);
        if (null == xStreamAlias) {
            // 如果没有注解，则直接取类名
            messageAlias = c.getSimpleName();
        } else {
            // 如果消息别名不等于类名，则取注解中的名称
            messageAlias = xStreamAlias.value();
        }
        XStream xstream = new XStream();
        xstream.alias(messageAlias, c);
        xstream.autodetectAnnotations(true);
        return xstream.fromXML(inputStream);
    }
    
    /**
     * 将对象保存为xml文件
     * @param device 需转换的对象
     * @param out 转换后文件的数据流
     */
    public static void serialize(Object obj, FileOutputStream out){
        String messageAlias;
        XStreamAlias xStreamAlias = obj.getClass().getAnnotation(XStreamAlias.class);
        if (null == xStreamAlias) {
            // 如果没有注解，则直接取类名
            messageAlias = obj.getClass().getSimpleName();
        } else {
            // 如果消息别名不等于类名，则取注解中的名称
            messageAlias = xStreamAlias.value();
        }
        XStream xstream = new XStream();
        xstream.alias(messageAlias, obj.getClass());
        xstream.autodetectAnnotations(true);
        xstream.toXML(obj, out);
    }
    
}
