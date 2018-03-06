package com.minh.nguyen.service;

import com.minh.nguyen.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Minh
 * @since 06/03/2018
 * Purpose:
 */
@Service("MessageService")
public class MessageService extends BaseService {

    @Autowired
    private MessageMapper messageMapper;
}
