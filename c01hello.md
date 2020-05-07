#Spring Cloud学习
简介
	Spring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，
	如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署。
	Spring Cloud并没有重复制造轮子，它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，
	通过Spring Boot风格进行再封装屏蔽掉了复杂的配置和实现原理，
	最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包。
    比dubbo牛一些
相关网站
	https://projects.spring.io/spring-cloud/    springcloud项目官方主页
	https://springcloud.cc/  springcloud中文网 有很详细的翻译文档 
	http://springcloud.cn/  springcloud中文论坛 
环境搭建
	1.父项目搭建
		主要是一个pom，管理module，以及管理依赖，规范所有jar包版本等
	2.子项目：公共模块项目搭建
	    主要是放一些其他项目公用的东西，比如实体类，工具类等；
	    创建数据库db_springcloud
	3.子项目：服务提供者项目搭建
	    Controller开放的端口入参实体类上+@RequestBody，否则被远程消费者调用，数据传递不过来。
	4.子项目：服务消费者项目搭建
	    config包下 放配置
	    restTemplate调用服务提供者提供的服务
	        服务消费者调用服务提供者的传递数据，服务提供者方法入参实体类上需要加@requestBody
        springboot启动时会自动注入数据源及配置jpa，不要自动注入。
        
1.接口 extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student>
    1.JPA(java persistence API):是处理数据持久化的一个接口，JavaAPI。
    2.Spring Data：提供了一整套数据访问层(DAO)的解决方案，致力于减少数据访问层(DAO)的开发量。
            它使用一个叫作Repository的接口类为基础，它被定义为访问底层数据模型的超级接口。
            所有继承这个接口的interface都被spring所管理。
    3.Repository子接口JpaRepository<实体类，主键类型>：实现一组JPA规范相关的方法。
    4.特殊JAP接口JpaSpecificationExecutor<实体类>：提供了类似Hibernated 的Criteria的查询方式。
2.@RestController、@Controller
    RestController = @Controller + @ResponseBody
3.@resources @awired
    1.共同点
        1.都是做bean的注入时使用，写在setter方法上。
    2.不同点
        1.来自不同
            @Autowired由Spring提供。
            @Resource由J2EE提供。
        2.注入匹配不同
            Autowired注解是按照类型（byType）装配依赖对象，默认情况下它要求依赖对象必须存在。
            @Resource默认按照ByName自动注入。
4.@PostMapping  @GetMapping  
    @GetMapping：是@RequestMapping(method = RequestMethod.GET)的缩写。只接受get请求，如果接收的不是post请求会405报错。
    @PostMapping：是@RequestMapping(method = RequestMethod.POST)的缩写。只接受post请求，如果接收的不是post请求会405报错。
5.@SpringBootApplication
    @SpringBootApplication = @ComponentScan + @EnableAutoConfiguration + @SpringBootConfiguration。
    1.@ComponentScan扫描：@Service,@Repository,@Component,@Controller。
    2.@SpringBootConfiguration：声明一个配置类，与@Configuration作用相同。
        相当于把该类作为spring的xml配置文件中的<beans>，作用为：配置spring容器(应用上下文)
    3.@EnableAutoConfiguration：是springboot实现自动化配置的核心注解，通过这个注解把spring应用所需的bean注入容器中。
        简单讲就是去查找，过滤，加载所需的configuration,@ComponentScan扫描我们自定义的bean 
    @SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
        禁止springboot自动注入数据源配置
6.@RequestBody   
    @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)，get无请求体。 
    一个请求，只有一个RequestBody，只能在一个方法中使用一次。    
7.@Configuration
    相当于把该类作为spring的xml配置文件中的<beans>，作用为：配置spring容器(应用上下文)。
    1.@Bean，用在@Configuration标注类的方法上
        @Bean标注在返回某个实例的方法上，等价于spring的xml配置文件中的<bean>，作用为：注册bean对象。
    2.@Scope("prototype")
        @Bean注解默认作用域为单例singleton作用域，可通过@Scope(“prototype”)设置为原型作用域；
