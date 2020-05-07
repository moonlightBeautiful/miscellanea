#Spring Cloudѧϰ
���
	Spring Cloud��һϵ�п�ܵ����򼯺ϡ�������Spring Boot�Ŀ�������������ؼ��˷ֲ�ʽϵͳ������ʩ�Ŀ�����
	�������ע�ᡢ�������ġ���Ϣ���ߡ����ؾ��⡢��·�������ݼ�صȣ���������Spring Boot�Ŀ����������һ�������Ͳ���
	Spring Cloud��û���ظ��������ӣ���ֻ�ǽ�Ŀǰ���ҹ�˾�����ıȽϳ��졢������ʵ�ʿ���ķ��������������
	ͨ��Spring Boot�������ٷ�װ���ε��˸��ӵ����ú�ʵ��ԭ��
	���ո�������������һ�׼��׶����ײ������ά���ķֲ�ʽϵͳ�������߰���
    ��dubboţһЩ
�����վ
	https://projects.spring.io/spring-cloud/    springcloud��Ŀ�ٷ���ҳ
	https://springcloud.cc/  springcloud������ �к���ϸ�ķ����ĵ� 
	http://springcloud.cn/  springcloud������̳ 
�����
	1.����Ŀ�
		��Ҫ��һ��pom������module���Լ������������淶����jar���汾��
	2.����Ŀ������ģ����Ŀ�
	    ��Ҫ�Ƿ�һЩ������Ŀ���õĶ���������ʵ���࣬������ȣ�
	    �������ݿ�db_springcloud
	3.����Ŀ�������ṩ����Ŀ�
	    Controller���ŵĶ˿����ʵ������+@RequestBody������Զ�������ߵ��ã����ݴ��ݲ�������
	4.����Ŀ��������������Ŀ�
	    config���� ������
	    restTemplate���÷����ṩ���ṩ�ķ���
	        ���������ߵ��÷����ṩ�ߵĴ������ݣ������ṩ�߷������ʵ��������Ҫ��@requestBody
        springboot����ʱ���Զ�ע������Դ������jpa����Ҫ�Զ�ע�롣
        
1.�ӿ� extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student>
    1.JPA(java persistence API):�Ǵ������ݳ־û���һ���ӿڣ�JavaAPI��
    2.Spring Data���ṩ��һ�������ݷ��ʲ�(DAO)�Ľ�������������ڼ������ݷ��ʲ�(DAO)�Ŀ�������
            ��ʹ��һ������Repository�Ľӿ���Ϊ��������������Ϊ���ʵײ�����ģ�͵ĳ����ӿڡ�
            ���м̳�����ӿڵ�interface����spring������
    3.Repository�ӽӿ�JpaRepository<ʵ���࣬��������>��ʵ��һ��JPA�淶��صķ�����
    4.����JAP�ӿ�JpaSpecificationExecutor<ʵ����>���ṩ������Hibernated ��Criteria�Ĳ�ѯ��ʽ��
2.@RestController��@Controller
    RestController = @Controller + @ResponseBody
3.@resources @awired
    1.��ͬ��
        1.������bean��ע��ʱʹ�ã�д��setter�����ϡ�
    2.��ͬ��
        1.���Բ�ͬ
            @Autowired��Spring�ṩ��
            @Resource��J2EE�ṩ��
        2.ע��ƥ�䲻ͬ
            Autowiredע���ǰ������ͣ�byType��װ����������Ĭ���������Ҫ���������������ڡ�
            @ResourceĬ�ϰ���ByName�Զ�ע�롣
4.@PostMapping  @GetMapping  
    @GetMapping����@RequestMapping(method = RequestMethod.GET)����д��ֻ����get����������յĲ���post�����405����
    @PostMapping����@RequestMapping(method = RequestMethod.POST)����д��ֻ����post����������յĲ���post�����405����
5.@SpringBootApplication
    @SpringBootApplication = @ComponentScan + @EnableAutoConfiguration + @SpringBootConfiguration��
    1.@ComponentScanɨ�裺@Service,@Repository,@Component,@Controller��
    2.@SpringBootConfiguration������һ�������࣬��@Configuration������ͬ��
        �൱�ڰѸ�����Ϊspring��xml�����ļ��е�<beans>������Ϊ������spring����(Ӧ��������)
    3.@EnableAutoConfiguration����springbootʵ���Զ������õĺ���ע�⣬ͨ�����ע���springӦ�������beanע�������С�
        �򵥽�����ȥ���ң����ˣ����������configuration,@ComponentScanɨ�������Զ����bean 
    @SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
        ��ֹspringboot�Զ�ע������Դ����
6.@RequestBody   
    @RequestBody��Ҫ��������ǰ�˴��ݸ���˵�json�ַ����е����ݵ�(�������е����ݵ�)��get�������塣 
    һ������ֻ��һ��RequestBody��ֻ����һ��������ʹ��һ�Ρ�    
7.@Configuration
    �൱�ڰѸ�����Ϊspring��xml�����ļ��е�<beans>������Ϊ������spring����(Ӧ��������)��
    1.@Bean������@Configuration��ע��ķ�����
        @Bean��ע�ڷ���ĳ��ʵ���ķ����ϣ��ȼ���spring��xml�����ļ��е�<bean>������Ϊ��ע��bean����
    2.@Scope("prototype")
        @Beanע��Ĭ��������Ϊ����singleton�����򣬿�ͨ��@Scope(��prototype��)����Ϊԭ��������
