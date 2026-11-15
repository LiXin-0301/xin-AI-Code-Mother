package com.lixin.xinaicodemother.ai;

import com.lixin.xinaicodemother.ai.guardrail.PromptSafetyInputGuardrail;
import com.lixin.xinaicodemother.ai.guardrail.RetryOutputGuardrail;
import com.lixin.xinaicodemother.ai.model.HtmlCodeResult;
import com.lixin.xinaicodemother.ai.model.MultiFileCodeResult;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;
import dev.langchain4j.service.guardrail.OutputGuardrails;
import reactor.core.publisher.Flux;

public interface AiCodeGeneratorService {

    /**
     * 生成 HTML 代码
     *
     * @param userMessage 用户消息
     * @return 生成的代码结果
     */
    @InputGuardrails({ PromptSafetyInputGuardrail.class})
//    @OutputGuardrails({RetryOutputGuardrail.class})(流式输出不允许)
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    HtmlCodeResult generateHtmlCode(String userMessage);

    /**
     * 生成多文件代码
     *
     * @param userMessage 用户消息
     * @return 生成的代码结果
     */
    @InputGuardrails({ PromptSafetyInputGuardrail.class})
//    @OutputGuardrails({RetryOutputGuardrail.class})
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    MultiFileCodeResult generateMultiFileCode(String userMessage);
    /**
     * 生成 HTML 代码（流式）
     *
     * @param userMessage 用户消息
     * @return 生成的代码结果
     */
    @InputGuardrails({ PromptSafetyInputGuardrail.class})
//    @OutputGuardrails({RetryOutputGuardrail.class})
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    Flux<String> generateHtmlCodeStream(String userMessage);

    /**
     * 生成多文件代码（流式）
     *
     * @param userMessage 用户消息
     * @return 生成的代码结果
     */
    @InputGuardrails({ PromptSafetyInputGuardrail.class})
//    @OutputGuardrails({RetryOutputGuardrail.class})
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    Flux<String> generateMultiFileCodeStream(String userMessage);

}


