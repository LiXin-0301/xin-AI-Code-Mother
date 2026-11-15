package com.lixin.xinaicodemother.service;

import com.lixin.xinaicodemother.exception.ErrorCode;
import com.lixin.xinaicodemother.exception.ThrowUtils;
import com.lixin.xinaicodemother.model.dto.chathistory.ChatHistoryQueryRequest;
import com.lixin.xinaicodemother.model.entity.User;
import com.lixin.xinaicodemother.model.enums.ChatHistoryMessageTypeEnum;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.lixin.xinaicodemother.model.entity.ChatHistory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;

import java.time.LocalDateTime;

/**
 *  服务层。
 *
 * @author <a href="https://github.com/LiXin-0301">LixinCode</a>
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    /**
     * 添加聊天记录
     * @param appId
     * @param message
     * @param messageType
     * @param userId
     * @return
     */
    boolean addChatMessage(Long appId, String message, String messageType, Long userId);

    /**
     * 根据应用ID删除对话记录
     * @param appId
     * @return
     */
    boolean deleteByAppId(Long appId);

    /**
     * 分页查询某应用的对话记录
     * @param appId
     * @param pageSize
     * @param lastCreateTime
     * @param loginUser
     * @return
     */
    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                               User loginUser);

    /**
     * 加载对话历史到内存
     * @param appId
     * @param chatMemory 会话Id
     * @param maxCount 最多加载多少条
     * @return 加载成功条数
     */
    int loadChatHistoryToMemory(Long appId, MessageWindowChatMemory chatMemory, int maxCount);

    /**
     * 构造查询请求
     * @param chatHistoryQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest);




}
