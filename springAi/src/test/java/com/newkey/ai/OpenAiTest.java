package com.newkey.ai;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newkey.ai.config.datasource.DataSourceBeans;
import com.newkey.ai.dao.OpenAiChatRepository;
import com.newkey.ai.entity.OpenAiChatEntity;
import com.newkey.ai.service.OpenAiChatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OpenAiTest {


    @Autowired(required = false)
    private OpenAiChatRepository openAiChatRepository;

    @Autowired(required = false)
    private DataSourceBeans dataSourceBeans;

    @Autowired
    private OpenAiChatService openAiChatService;

    @Test
    public void testOpenAi(){
//        OpenAiApi openAiApi = new OpenAiApi("https://xiaoai.plus", "sk-y8wsKGMEytYXYV4aC009D5Ca1c2b447a99E07d5951953d09");
//        OpenAiChatModel chatModel = new OpenAiChatModel(openAiApi, OpenAiChatOptions.builder().withModel(OpenAiApi.ChatModel.GPT_3_5_TURBO_1106).build());
//
//        ChatClient chatClient = ChatClient.builder(chatModel).build();

        String user = " vue如何将这段字符串转义输出 Sure!\\u0020Here\\u0020is\\u000Aa\\u0020simple\\u0020Java";

        //Flux<String> chatMessage = openAiChatService.getChatMessage3(user);

        Flux<String> chatMessage = openAiChatService.getChatMessage3(user);

        String assistant = chatMessage.collectList().block().stream().collect(Collectors.joining());
        System.out.println(assistant);

//        Flux<String> flux = Flux.just("Hello World\n", "This is a test\n", "Line 3 with spaces  \n");
//
//        Flux<String> encodedFlux = flux.map(str -> {
//            String escapedStr = str.replaceAll(" ", "\\\\u0020");
//            escapedStr = escapedStr.replaceAll("\n", "\\\\u000A");
//            return escapedStr;
//        });
//
//        encodedFlux.subscribe(System.out::println);

//        String hellou200World = StringEscapeUtils.unescapeJava("Hello\\u0020World");
//        System.out.println(hellou200World);

        //CassandraChatMemory.create(CassandraChatMemoryConfig.builder().withTimeToLive(Duration.ofDays(1)).build());

//        Flux<String> content = null;
//
//            content = chatClient
//                    .prompt()
//                    .advisors(new SimpleLoggerAdvisor(
//                            request -> "Custom request: " + request.userText(),
//                            response -> "Custom response: " + response.getResult()))
//                    .user(user).stream().content();



//        OpenAiChatEntity openAiChatEntity = new OpenAiChatEntity();
//        openAiChatEntity.setUserId("124325342");
//        openAiChatEntity.setSessionId("51221211");
//
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("user", user);
//        jsonObject.put("assistant", assistant);
//        jsonArray.add(jsonObject);
//
//        openAiChatEntity.setMessage(jsonArray.toJSONString());
//
//        log.info("openAiChatEntity={}", openAiChatEntity);
//
//        openAiChatRepository.saveAndFlush(openAiChatEntity);
    }
}
