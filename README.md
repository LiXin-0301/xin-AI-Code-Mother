# AI-Code-Mother (Backend)

基于 Spring Boot 3 + LangChain4j + LangGraph4j 的 AI 零代码应用生成平台后端服务。用户输入自然语言描述，由 AI Agent 自动执行并发素材搜集、代码生成、质量检查、项目构建的完整工作流，最终一键部署为可访问的 Web 应用。

## 🚀 后端核心功能特性

### 1. 基础架构与系统底座
- 🏗️ **项目架构搭建**：基于 Spring Boot 3 + Java 21 虚拟线程搭建，整合 Hutool、Lombok 和 Knife4j 接口文档。
- 🛡️ **全局异常与错误码**：自定义业务异常类和错误码规范，结合 AOP 实现全局异常处理器，提升系统容错能力。
- 🔐 **统一权限校验**：通过自定义权限注解和 Spring AOP 环绕切面，实现非侵入式的用户角色权限校验。
- 💾 **分布式 Session**：整合 `spring-session-data-redis`，将用户登录态从本地 Session 迁移至 Redis 统一管理，为系统水平扩展打下基础。
- 📈 **多维可观测性**：基于 ARMS 实现多维监控告警，Prometheus + Grafana 实现自定义业务指标监控（如大模型 Token 用量）。

### 2. AI 能力与 LangChain4j 深度集成
- 🤖 **多模型集成**：利用 LangChain4j 框架集成 DeepSeek、通义等多种 AI 大模型，通过声明式 Ai Service 编程模型快速实现模型调用。
- 🎯 **Prompt 调优与安全**：为代码生成、智能路由等任务设计系统提示词；基于输入护轨对用户 Prompt 进行安全审查，防范注入攻击。
- 🔀 **AI 智能路由**：利用结构化输出特性，根据用户需求智能选择不同的网站生成策略，平衡成本与性能。
- 💰 **AI 成本优化**：为代码生成等复杂任务选用推理模型，为路由分类等简单任务选用低成本、快响应的 Turbo 模型。
- 🔄 **AI 调用重试**：基于输出护轨对 AI 响应进行质量校验并自动触发重试，增强系统稳定性。
- ⚡ **流式响应 (SSE)**：基于 TokenStream 和反应式编程构建 SSE 接口；并通过自定义 TokenStream 解析并格式化 AI 实时输出的工具调用信息。

### 3. LangGraph4j 核心工作流
- 🔄 **自动化工作流**：实现包含素材搜集、代码生成、质量检查、项目构建的多节点自动化工作流。
- 🌿 **条件边特性**：根据代码生成类型和质量检查结果动态决定执行路径（如跳过构建或重试生成），提升流程自动化程度。
- 🖼️ **并发图片搜集**：AI 规划搜集任务，通过 `CompletableFuture` / `Parallel Branch` 并发执行图片搜集，缩短任务总耗时 300%。
- 🔍 **AI 代码质量检查**：通过访问者设计模式读取生成的项目文件并调用 AI 分析代码，实现自动审查和反馈循环。

### 4. 数据访问与性能优化
- 🗄️ **数据访问层**：自定义 MyBatis Flex 代码生成器快速生成 CRUD 代码，结合 LambdaQueryWrapper 实现复杂动态 SQL 查询。
- 🚀 **深度分页优化**：基于时间游标实现分页对话历史查询，避免深分页性能问题，并建立复合索引进一步优化。
- ⚡ **多级缓存体系**：结合本地 Caffeine 与分布式 Redis，如精选应用列表缓存、AI Service 实例缓存。
- 🚦 **接口限流与稳定性**：基于 Redisson 的 RRateLimiter 和 Spring AOP，实现用户/IP/接口级的令牌桶限流，保障 AI 接口稳定性。
- ⏳ **异步处理优化**：利用 Java 21 虚拟线程和异步机制，将项目构建、封面生成等耗时任务异步化，防止请求线程阻塞。

### 5. 业务功能与工程化
- 🏗️ **项目自动化构建**：通过 Java 的 Process API 执行 Vue 工程项目的自动化打包构建，并设置超时控制机制防止进程阻塞。
- 📦 **项目打包下载**：设计多维文件过滤器，结合 Hutool ZIP 压缩，以文件流形式提供给用户下载。
- 📸 **网页截图与云存储**：基于 Selenium 和 WebDriverManager 实现封面自动截图压缩，并封装腾讯云 COS SDK 实现上传与过期清理。
- 🌐 **静态网站预览**：基于 Spring MVC 的 `FileSystemResource` 提供隔离的预览服务，配置目录重定向提升访问兼容性。
- ☁️ **微服务架构演进**（规划中）：利用 Spring Cloud Alibaba + Dubbo + Nacos + Higress 将单体项目重构为微服务，实现弹性扩展和故障隔离。

---

## 技术栈

- **核心框架**：Spring Boot 3, Java 21
- **AI 框架**：LangChain4j, LangGraph4j
- **数据访问**：MyBatis Flex, MySQL
- **缓存与中间件**：Redis, Caffeine, Redisson
- **工具库**：Hutool, Lombok, Knife4j
- **云服务**：腾讯云 COS (对象存储), 阿里云百炼 (模型对比)
- **部署与监控**：Docker, Nginx, Prometheus, Grafana

---

## 项目结构 (后端)

```text
src/main/java/com/lixin/xinaicodemother/
├── ai/                 # AI 核心业务层 (模型封装、Guardrail 护轨)
├── annotation/         # 自定义注解 (如权限校验 @AuthCheck)
├── aop/                # 切面逻辑 (权限拦截器)
├── common/             # 公共类 (统一响应、分页请求、工具类)
├── config/             # 配置类 (CORS, COS客户端, Redis/Caffeine缓存, Redisson)
├── constant/           # 常量定义 (应用常量、用户常量)
├── controller/         # 接口层 (App, ChatHistory, User, StaticResource)
├── core/               # 核心解析与生成模块
│   ├── parser/         # 代码解析器 (HTML, 多文件解析, 访问者模式)
│   ├── saver/          # 代码保存模板 (文件、HTML、多文件)
│   └── AiCodeGeneratorFacade.java # AI 代码生成门面类
├── exception/          # 全局异常处理 (BusinessException, ErrorCode)
├── generator/          # MyBatis 代码生成器
├── langgraph4j/        # AI 工作流引擎
│   ├── ai/             # 工作流内的 AI 服务 (质量检查、图片规划)
│   ├── model/          # 工作流状态与模型 (ImageResource, QualityResult)
│   ├── node/           # 工作流节点 (代码生成、质量检查、图片搜集、路由等)
│   ├── state/          # 工作流上下文 (WorkflowContext)
│   └── tools/          # AI 工具调用 (图片搜索、Logo生成、Mermaid图)
├── manager/            # 外部服务管理 (如 COS 文件管理)
├── mapper/             # 数据库 Mapper
├── model/              # 数据模型
│   ├── dto/            # 数据传输对象 (Request)
│   ├── entity/         # 数据库实体
│   ├── enums/          # 枚举类 (角色、消息类型、代码生成类型)
│   └── vo/             # 视图对象 (Response)
├── ratelimiter/        # 限流模块 (注解、切面、配置)
├── service/            # 业务逻辑层 (Interface & Impl)
└── utils/              # 全局工具类 (Web截图、Spring上下文)
```

---

## 核心业务流程

### 1. AI 网站生成工作流 (LangGraph4j)
1. **输入接收**：用户输入提示词，触发生成工作流。
2. **意图识别/路由**：RouterNode 根据 Prompt 决定生成策略。
3. **并发素材搜集**：ImageCollectionNode 规划并并发搜集图片，调用外部 API 获取资源。
4. **代码生成**：CodeGeneratorNode 调用大模型生成代码。
5. **代码质量检查**：CodeQualityCheckNode 审查代码，若不合格则触发条件边重试。
6. **项目构建**：ProjectBuilderNode 执行编译打包。
7. **部署与预览**：最终输出静态文件，提供给前端展示。

### 2. AI 对话交互
1. 用户发送对话消息。
2. 后端通过分布式 Session 鉴权，从 Redis 中加载对话记忆 (MemoryId)。
3. 调用声明式 AI Service，结合工具 (Tools) 与流式响应 (SSE) 返回给前端。

---

## 开发环境设置

### 1. 环境依赖
请确保您的服务器或本地已安装：
- JDK 21
- MySQL 8.0+
- Redis 6.0+
- Node.js (用于执行前端工程的自动化构建)

### 2. 配置文件
复制 `application-local.yaml.example` 为 `application-local.yaml`，并填写相关配置：

```yaml
# 数据库配置
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ai_code_mother
    username: root
    password: your_password

# AI 模型密钥配置 (如通义千问、DeepSeek)
langchain4j:
  open-ai:
    api-key: your_ai_api_key

# 腾讯云 COS 配置
cos:
  secret-id: your_secret_id
  secret-key: your_secret_key
```

### 3. 启动项目

```sh
# 编译打包
./mvnw clean package -DskipTests

# 运行
java -jar target/xin-ai-code-mother-0.0.1-SNAPSHOT.jar
```

---

## 注意事项

- **安全提醒**：请务必妥善保管您的 `application-prod.yaml` 中的各类密钥（如 AI API Key, 腾讯云 COS 密钥），切勿直接提交到 Git 仓库。
- **AI 幻觉防范**：代码生成部分依赖模型能力，已引入自动重试和护轨机制，但仍需在生成后进行人工质检。
- **成本控制**：大模型 Token 消耗较大，已配置令牌桶限流与模型智能路由，生产环境请密切监控 Token 用量。
