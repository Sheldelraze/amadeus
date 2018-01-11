package com.minh.nguyen.validator.common;

import com.minh.nguyen.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 12/01/2018
 * Purpose:
 */
@Component
// @Scope("request")
public class BindingResult implements Serializable {

    private static final long serialVersionUID = 5093266952737916926L;

    @Autowired
    protected MessageUtil msgUtil;


    protected List<Throwable> errors = new LinkedList<Throwable>();

    public boolean hasErrors() {
        return this.errors.size() > 0;
    }

    public void addError(Throwable error) {
        this.errors.add(error);
    }

    public void addAllErrors(BindingResult errors) {
        this.errors.addAll(errors.getAllErrors());
    }

    public List<Throwable> getAllErrors() {
        return Collections.unmodifiableList(this.errors);
    }

    public List<ErrorInfoValue> getMessages() {
        return msgUtil.getMessageList(this);
    }

    public void clearErrors() {
        errors = new LinkedList<Throwable>();
    }

}
