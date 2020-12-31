package com.redrabbit.cloud.core.filter;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

public class RequestWapper extends HttpServletRequestWrapper {

    private Map<String, String[]> parameterMap;
    private byte[] body;

    public RequestWapper(HttpServletRequest request) {
        super(request);
        this.parameterMap = request.getParameterMap();
        try {
            this.inputHandler(request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inputHandler(ServletInputStream inputStream) throws IOException {
        this.body = IOUtils.toByteArray(inputStream);
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream bais = new ByteArrayInputStream(this.body);
        ServletInputStream sis = new ServletInputStream() {
            @Override
            public int read() {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
        return sis;
    }

    @Override
    public String getParameter(String name) {
        String[] results = parameterMap.get(name);
        return results != null ? results[0] : null;
    }

    /**
     * 获取指定参数名的所有值的数组，如：checkbox的所有数据
     * 接收数组变量 ，如checkobx类型
     */
    @Override
    public String[] getParameterValues(String name) {
        return parameterMap.get(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public byte[] getBody() {
        return body;
    }
}
