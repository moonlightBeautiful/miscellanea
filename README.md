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
	2.子项目：公共模块项目搭建
	    主要是放一些其他项目公用的东西，比如实体类，工具类等等；
	    创建数据库db_springcloud
	3.子项目：服务提供者项目搭建
	    Controller开放的端口入参实体类上+@RequestBody，否则被远程消费者调用，数据传递不过来。
	4.子项目：服务消费者项目搭建
	    config包下 放配置
	    restTemplate调用服务提供者提供的服务
        springboot启动时会自动注入数据源及配置jpa，解决：
            1，在pom文件中加h2引用   2.在启动类上加上
	    