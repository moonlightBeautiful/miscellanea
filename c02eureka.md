#Eureka学习
简介：
    Eureka是Netflix开发的服务注册与发现框架，
    本身是一个基于REST的服务，主要用于定位运行在AWS域中的中间层服务，以达到负载均衡和中间层服务故障转移的目的。
    SpringCloud将它集成在其子项目spring-cloud-netflix中，以实现SpringCloud的服务发现功能。
    Eureka包含两个组件：Eureka Server和Eureka Client。
        Eureka Server提供服务注册服务，各个节点启动后，会在Eureka Server中进行注册
        Eureka Client是一个java客户端，用于简化与Eureka Server的交互，客户端同时也就别一个内置的、使用轮询(round-robin)负载算法的负载均衡器。
    在应用启动后，将会向Eureka Server发送心跳,默认周期为30秒，如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除(默认90秒)。
    Eureka Server之间通过复制的方式完成数据的同步，Eureka还提供了客户端缓存机制，即使所有的Eureka Server都挂掉，客户端依然可以利用缓存中的信息消费其他服务的API。
    综上，Eureka通过心跳检查、客户端缓存等机制，确保了系统的高可用性、灵活性和可伸缩性。
    Eureka2版本已经停止更新了。但是Eureka1版本还是很稳定，功能足够用。
搭建服务注册中心
    1.依赖
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
    2.配置
        server:
          port: 2001
          context-path: /
        eureka:
          instance:
            hostname: localhost     # 注册中心实例名称：主机名称或者ip地址
          client:
            register-with-eureka: false         # 由于该应用为注册中心，不向注册中心注册自己。
            fetch-registry: false           # 该应用为服务注册中心，职责为注册和发现服务，无需续约服务，默认每隔30秒向注册中心发送心跳包，续约服务
            service-url:
              defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/     # 服务注册中心注册服务地址
    3.启动
        @SpringBootApplication
        @EnableEurekaServer
    4.查看是否启动成功
        http://localhost:2001/    
服务提供者注册到服务注册中心
    作为eureka的客户端注册服务到eureka服务注册中心
    1.依赖
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
    2.配置
        eureka:
          instance:
            hostname: localhost   # 客户端地址：主机名称或者ip地址
            appname: microservice-student    # 客户端实服务名
            instance-id: microservice-student:1001    # 客户端实例名称
            prefer-ip-address: true   # 是否显示ip
          client:
            service-url:
              defaultZone: http://localhost:2001/eureka   # 服务注册中心的地址
    3.启动，开始注册
        @SpringBootApplication
        @EnableEurekaClient
    4.优化
        当点击服务id的时候，可以查看一些配置的信息
        1.pom.xml里加入actuator监控依赖
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
            </dependency>
        2.父项目pom.xml里加上构建插件配置：子模块不要再次定义，会屏蔽掉父pom
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-resources-plugin</artifactId>
                        <configuration>
                            <resources>
                                <resource>
                                    <directory>src/main/resources</directory>
                                    <filtering>true</filtering>
                                    <delimiters>
                                        <delimit>$</delimit>
                                    </delimiters>
                                </resource>
                            </resources>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        3.application.yml加上info配置
            info: 
            groupId: $project.groupId$
            artifactId: $project.artifactId$
            version: $project.version$
            负责人: 张三
            联系电话: 110
搭建服务注册中心集群
    1.新建多个服务注册中心
    2.修改配置文件
        eureka:
          instance:
            hostname: localhost     
                # 如果部署在不同的服务器上，则localhost，部署在一个windows上，则修改hosts文件（C:\Windows\System32\drivers\etc）
                # 末尾添加
                  #  127.0.0.1 eureka2001.ims.com
                  #  127.0.0.1 eureka2002.ims.com
                  #  127.0.0.1 eureka2003.ims.com
          client:
            register-with-eureka: false         # 由于该应用为注册中心，不向注册中心注册自己。
            fetch-registry: false           # 该应用为服务注册中心，职责为注册和发现服务，无需续约服务，默认每隔30秒向注册中心发送心跳包，续约服务
            service-url:
              defaultZone: http://eureka2001.ims.com:2001/eureka/,http://eureka2002.ims.com:2002/eureka/  #集群 其他的服务注册中心地址      
              # 单击 defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/     
    3.修改服务提供者配置文件
        defaultZone: http://eureka2001.ims.com:2001/eureka/,http://eureka2002.ims.com:2002/eureka/,http://eureka2003.ims.com:2003/eureka/  
        #集群 全部服务注册中心地址 
    4.访问测试
        http://localhost:2001/    
        http://localhost:2002/    
        http://localhost:2003/    
Eureka自我保护机制
    访问http://localhost:2001/，会出现大的红色的字，是警告。
    长时间（默认貌似15分钟）未访问服务、变更服务实例名称时，会出现红色的警告。
    默认情况下，如果服务注册中心一段时间（默认90秒）没有接收到某个服务实例的心跳（默认30秒发送一次心跳），则注销掉该服务。
    在正式环境下，由于网络原因经常会延迟，服务注册中心好几分钟接收不到心跳包，但服务是正常的，不应该注销服务。
    短时间收不到心跳包，会自动保存服务信息，进入自我保护机制。当收到心跳包后，退出"自我保护机制"。
    很长很长时间收不到心跳包，会真的注销掉服务。