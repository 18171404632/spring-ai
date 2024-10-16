package com.newkey.ai.controller;

import com.newkey.ai.service.OpenAiChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;


/**
 * Desc: TODO ADD DESC
 * <p>
 * Function:
 * <dl>
 * <dt>核心功能点1</dt>
 * <dd>核心功能点1说明</dd>
 * <dt>核心功能点2</dt>
 * <dd>核心功能点2说明</dd>
 * </dl>
 *
 * @app <服务名称英文缩写>
 * @layer <代码所在分层>
 * @refApp <依赖服务的英文缩写>
 * @author <a href="mailto:qiliu3@chinaums.com">qiliu3</a>
 * @since 2024/10/12
 * @version 2024/10/12
 */
@RestController
@RequestMapping("/ai/")
@Slf4j
public class OpenAiChatController {

    @Autowired
    private OpenAiChatService openAiChatService;

    @PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getChatMessage3(@RequestBody Map<String, Object> params){
        String message = (String)params.get("message");

        log.info("请求参数为:{}", message);

        return openAiChatService.getChatMessage3(message);

    }
}
