# web08Spring
SpringIOC+SpringMVC+Spring JDBC+Page+Interact Report+Lov
# Web08Spring的项目的样例特点介绍：
## 完全整合SpringIOC和SpringMVC。
包括相关的配置。详细看web.xml的配置以及对应的Spring的配置。
<br>SpringIOC：/web08Spring/src/applicationContext.xml
<br>SpringMVC：/web08Spring/src/springservletConfig.xml
<br>如果要深入了解各个配置的用途什么的，还是要先了解SpringIOC和SpringMVC的逻辑。
## 对Spring JDBC的处理进行二次封装。
在Spring JDBC Template的基础上，封装了一套DevJdbcTemplate功能。
<br>它的主要作用是，可以非常直观调用SQL和PLSQL，而且可以根据参数名称来绑定参数的输入值或者输出值。对于一些常用的输出值(retcode/errbuf)也进行了简易使用的封装。
另外，对于一些常用的方法也进行了一些封装。基本上，经常用到的Oracle的数据访问功能应该都可以通过调用这个类来实现！如果缺少的还可以添加。
<br>简单来说：
<br>DevJdbcTemplate是对SpringJDBC的代码的二次封装 主要是方便使用。 
<br>就是：一些Oracle数据库的查询以及DML处理和调用Pkg的封装好的公用类。属于数据库访问的专用代码。 
整个处理基于NamedParameterJdbc，可以完美实现输入/输出参数的自动匹配 
统一逻辑：如果查询的没数据，则统一返回为空对象，而不是null！
<br>使用方法介绍：
<br>在对应的dao类，只需要继承extends DevJdbcDaoSupport，即可用方法getDevJdbcTemplate使用DevJdbcTemplate类。
<br>例如：public class EmpVODaoImpl extends DevJdbcDaoSupport implements EmpVODao
当然，该dao类必须要先注册bean，注入数据源。
## 对客户化的分页功能的完整封装。
<br>包括一些常用的动态拼凑语句的功能也进行了封装。
<br>使用办法是：
<br>首先在applicationContext.xml配置bean
<bean id="PagePub" class="xygdev.commons.page.PagePub" parent="abstractDao"/>
<br>然后，在要用到这个分页的封装好的代码的java里面，用自动装配注释来加即可：
@Autowired
PagePub pagePub;
## 对客户化的交互式报表功能的完整封装。
<br>首先在applicationContext.xml配置bean:
<bean id="InteractPub" class="xygdev.commons.interact.InteractPub" parent="abstractDao"/>
<br>然后，在要用到这个分页的封装好的代码的java里面，用自动装配注释来加即可：
@Autowired
InteractPub irrPub;
## 对交互式报表，还有分页，CRUD的js前端封装进行同步修改。也可以正常使用。
<br>对一些重要的功能，例如更新前对比数据是否被更新等等的，也进行了修正。
<br>交互式报表的样例以后统一以这个版本为主！
