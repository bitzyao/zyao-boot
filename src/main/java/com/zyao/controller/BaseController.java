package com.zyao.controller;

import cn.hutool.json.JSONObject;
import com.zyao.common.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

// @CrossOrigin 允许跨域请求
@CrossOrigin
public class BaseController implements Annotation {
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
    @Autowired
    private HttpServletRequest request;

    public BaseController() {
    }

    public Long getCurrentUserId() {
        Long currentUserId = Long.valueOf((String)this.request.getAttribute("currentUserId"));
        return currentUserId;
    }

    public Long getTokenId() {
        Long tokenId = Long.valueOf(String.valueOf(this.request.getAttribute("tokenId")));
        return tokenId;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    protected <T> ResponseEntity<?> getResponse(Page<T> data) {
        PageVo pageVo = new PageVo((int) data.getTotalElements(),data.getContent());
        return ResponseEntity.ok(pageVo);
    }

}
