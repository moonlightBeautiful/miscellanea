#Eurekaѧϰ
��飺
    Eureka��Netflix�����ķ���ע���뷢�ֿ�ܣ�
    ������һ������REST�ķ�����Ҫ���ڶ�λ������AWS���е��м������Դﵽ���ؾ�����м��������ת�Ƶ�Ŀ�ġ�
    SpringCloud����������������Ŀspring-cloud-netflix�У���ʵ��SpringCloud�ķ����ֹ��ܡ�
    Eureka�������������Eureka Server��Eureka Client��
        Eureka Server�ṩ����ע����񣬸����ڵ������󣬻���Eureka Server�н���ע��
        Eureka Client��һ��java�ͻ��ˣ����ڼ���Eureka Server�Ľ������ͻ���ͬʱҲ�ͱ�һ�����õġ�ʹ����ѯ(round-robin)�����㷨�ĸ��ؾ�������
    ��Ӧ�������󣬽�����Eureka Server��������,Ĭ������Ϊ30�룬���Eureka Server�ڶ������������û�н��յ�ĳ���ڵ��������Eureka Server����ӷ���ע����а��������ڵ��Ƴ�(Ĭ��90��)��
    Eureka Server֮��ͨ�����Ƶķ�ʽ������ݵ�ͬ����Eureka���ṩ�˿ͻ��˻�����ƣ���ʹ���е�Eureka Server���ҵ����ͻ�����Ȼ�������û����е���Ϣ�������������API��
    ���ϣ�Eurekaͨ��������顢�ͻ��˻���Ȼ��ƣ�ȷ����ϵͳ�ĸ߿����ԡ�����ԺͿ������ԡ�
    Eureka2�汾�Ѿ�ֹͣ�����ˡ�����Eureka1�汾���Ǻ��ȶ��������㹻�á�
�����ע������
    1.����
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
    2.����
        server:
          port: 2001
          context-path: /
        eureka:
          instance:
            hostname: localhost     # ע������ʵ�����ƣ��������ƻ���ip��ַ
          client:
            register-with-eureka: false         # ���ڸ�Ӧ��Ϊע�����ģ�����ע������ע���Լ���
            fetch-registry: false           # ��Ӧ��Ϊ����ע�����ģ�ְ��Ϊע��ͷ��ַ���������Լ����Ĭ��ÿ��30����ע�����ķ�������������Լ����
            service-url:
              defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/     # ����ע������ע������ַ
    3.����
        @SpringBootApplication
        @EnableEurekaServer
    4.�鿴�Ƿ������ɹ�
        http://localhost:2001/    
�����ṩ��ע�ᵽ����ע������
    ��Ϊeureka�Ŀͻ���ע�����eureka����ע������
    1.����
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
    2.����
        eureka:
          instance:
            hostname: localhost   # �ͻ��˵�ַ���������ƻ���ip��ַ
            appname: microservice-student    # �ͻ���ʵ������
            instance-id: microservice-student:1001    # �ͻ���ʵ������
            prefer-ip-address: true   # �Ƿ���ʾip
          client:
            service-url:
              defaultZone: http://localhost:2001/eureka   # ����ע�����ĵĵ�ַ
    3.��������ʼע��
        @SpringBootApplication
        @EnableEurekaClient
    4.�Ż�
        ���������id��ʱ�򣬿��Բ鿴һЩ���õ���Ϣ
        1.pom.xml�����actuator�������
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
            </dependency>
        2.����Ŀpom.xml����Ϲ���������ã���ģ�鲻Ҫ�ٴζ��壬�����ε���pom
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
        3.application.yml����info����
            info: 
            groupId: $project.groupId$
            artifactId: $project.artifactId$
            version: $project.version$
            ������: ����
            ��ϵ�绰: 110
�����ע�����ļ�Ⱥ
    1.�½��������ע������
    2.�޸������ļ�
        eureka:
          instance:
            hostname: localhost     
                # ��������ڲ�ͬ�ķ������ϣ���localhost��������һ��windows�ϣ����޸�hosts�ļ���C:\Windows\System32\drivers\etc��
                # ĩβ���
                  #  127.0.0.1 eureka2001.ims.com
                  #  127.0.0.1 eureka2002.ims.com
                  #  127.0.0.1 eureka2003.ims.com
          client:
            register-with-eureka: false         # ���ڸ�Ӧ��Ϊע�����ģ�����ע������ע���Լ���
            fetch-registry: false           # ��Ӧ��Ϊ����ע�����ģ�ְ��Ϊע��ͷ��ַ���������Լ����Ĭ��ÿ��30����ע�����ķ�������������Լ����
            service-url:
              defaultZone: http://eureka2001.ims.com:2001/eureka/,http://eureka2002.ims.com:2002/eureka/  #��Ⱥ �����ķ���ע�����ĵ�ַ      
              # ���� defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/     
    3.�޸ķ����ṩ�������ļ�
        defaultZone: http://eureka2001.ims.com:2001/eureka/,http://eureka2002.ims.com:2002/eureka/,http://eureka2003.ims.com:2003/eureka/  
        #��Ⱥ ȫ������ע�����ĵ�ַ 
    4.���ʲ���
        http://localhost:2001/    
        http://localhost:2002/    
        http://localhost:2003/    
Eureka���ұ�������
    ����http://localhost:2001/������ִ�ĺ�ɫ���֣��Ǿ��档
    ��ʱ�䣨Ĭ��ò��15���ӣ�δ���ʷ��񡢱������ʵ������ʱ������ֺ�ɫ�ľ��档
    Ĭ������£��������ע������һ��ʱ�䣨Ĭ��90�룩û�н��յ�ĳ������ʵ����������Ĭ��30�뷢��һ������������ע�����÷���
    ����ʽ�����£���������ԭ�򾭳����ӳ٣�����ע�����ĺü����ӽ��ղ������������������������ģ���Ӧ��ע������
    ��ʱ���ղ��������������Զ����������Ϣ���������ұ������ơ����յ����������˳�"���ұ�������"��
    �ܳ��ܳ�ʱ���ղ����������������ע��������