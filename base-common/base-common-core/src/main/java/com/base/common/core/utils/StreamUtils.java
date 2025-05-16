package com.base.common.core.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class StreamUtils {

    /**
     * 将 InputStream 转换为字符串，使用默认 UTF-8 编码
     *
     * @param inputStream 输入流
     * @return 转换后的字符串
     * @throws Exception 如果读取失败
     */
    public static String inputStream2String(InputStream inputStream) throws Exception {
        return inputStream2String(inputStream, "UTF-8");
    }

    /**
     * 将 InputStream 转换为字符串，使用指定的字符编码
     *
     * @param inputStream 输入流
     * @param charsetName 字符编码名称，例如 "UTF-8", "GBK", "ISO-8859-1"
     * @return 转换后的字符串
     * @throws Exception 如果读取失败
     */
    public static String inputStream2String(InputStream inputStream, String charsetName) throws Exception {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream 不能为空");
        }
        if (charsetName == null || charsetName.isEmpty()) {
            charsetName = "UTF-8";
        }

        Charset charset = Charset.forName(charsetName);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
