简介
	Spring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署。Spring Cloud并没有重复制造轮子，它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过Spring Boot风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包。
    比dubbo牛一些
相关网站
	https://projects.spring.io/spring-cloud/    springcloud项目官方主页
	https://springcloud.cc/  springcloud中文网 有很详细的翻译文档 
	http://springcloud.cn/  springcloud中文论坛 
环境搭建
	1.父项目搭建
		主要是一个pom，管理module，以及管理依赖，规范所有jar包版本等
	2.