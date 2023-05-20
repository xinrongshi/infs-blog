package com.infs.blog.bean;

import com.infs.blog.util.JsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Wrapping JSON objects
 * @Author: Lexi
 * @Date:  2023/04/30
 */
public class Result {

    private int code; //status code
    private String description; //description
    private Object detail; //return object

    /**
     * success
     * return null
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * success
     * return object
     * @return
     */
    public static Result success(Object detail) {
        Result result = new Result();
        result.setCode(200);
        result.setDescription("success");
        result.setDetail(detail);
        return result;
    }

    /**
     * fail
     * @return
     */
    public static Result error() {
        return error(null);
    }

    /**
     * fail
     * @param description
     * @return
     */
    public static Result error(String description) {
        return error(201, description);
    }

    /**
     * fail
     * @param code
     * @param description
     * @return
     */
    public static Result error(int code, String description) {
        Result result = new Result();
        result.setCode(code);
        result.setDescription(description);
        result.setDetail(null);
        return result;
    }

    /**
     * fail
     * If the Token is not legal or expired,
     * the error code and error message will be output in json format.
     * @param response
     * @param code
     * @param description
     * @throws IOException
     */
    public static void error(HttpServletResponse response, int code, String description) throws IOException {
        Result result = new Result();
        result.setCode(code);
        result.setDescription(description);
        result.setDetail(null);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtil.objectToJson(result));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
