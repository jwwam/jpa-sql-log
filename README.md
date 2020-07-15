# jpa-sql-log
基于Jpa控制台日志的sql格式化插件工具


<h2>jpa sql log</h2>
    <p>This a plugin from <a href="http://www.feelcode.cn">www.feelcode.cn</a> which made by Juiler.</p>
    <p>You can use it to restore your jpa sql.</p>
    <hr style="border-bottom:3px double;">
    <h4>eg1:</h4>
    <p> from </p>
    <xmp>    select * from table where _id=?</xmp>
    <p> to </p>
    <xmp>    select * from table where _id=1</xmp>
    <hr>
    <h4>eg2:</h4>
    <p> from </p>
    <xmp>    update table set create_date=?, update_date=?, product_id=?, status=? where id=?</xmp>
    <p> to </p>
    <xmp>    update table set create_date=2020-07-01 11:48:12.0, update_date=2020-07-01 11:48:12.0, product_id=1, status=0 where id=1</xmp>
    <hr>
    <p>First at all,you must create a file named logback.xml or other,remeber that you must make the console log display jpa sql and parameter through configuration,maybe is application.yml or other config file, or you can add this in logback.xml</p>
    <xmp>
    <logger name="org.hibernate.SQL" level="DEBUG"/>
    <logger name="org.hibernate.type.descriptor.sql.BasicBinder" level="TRACE"/>
    </xmp>
    <p>enjoy it!</p>
    <hr style="border-bottom:3px double;">
    <p>Hello，这是基于使用Jpa持久层ORM框架的一个格式化sql语句的插件，你无需做任何操作即可将带 ? 的控制台sql转化为带真实参数的sql语句，当然这是临时的，仅仅为了方便调试。
    插件执行的前提是控制台有原生sql输出，这要求您打开Jpa的sql日志输出，并且打开参数输出，可能因此您的控制台将输出更多内容，但是这一切仅仅发生在本地debug环境。您可以在生产配置中去除此日志。</p>
    <p>关于日志输出的配置可以参考以下：</p>
    <xmp>
    <logger name="org.hibernate.SQL" level="DEBUG"/>
    <logger name="org.hibernate.type.descriptor.sql.BasicBinder" level="TRACE"/>
    </xmp>
    <p>enjoy it!</p>
    <p>call me :</p>
    <p>QQ:824247231</p>
    <p>Email:824247231@qq.com</p>
