
##备份数据
java -cp D:\repository\com\h2database\h2\1.4.196\h2-1.4.196.jar org.h2.tools.Script -url jdbc:h2:tcp://localhost:9092/F:/tjz/tjz;MODE=MYSQL -user test -password test -script d://test/test.sql

##导入数据
java -cp D:\repository\com\h2database\h2\1.4.196\h2-1.4.196.jar org.h2.tools.RunScript -url jdbc:h2:tcp://localhost:9092/F:/tjz/tjz;MODE=MYSQL -user test -password test -script d://test/data.sql 

##将h2导出的sql分离成schema和data
* cd d:
* sed -rn '/^(CREATE CACHED TABLE |\s{4}|\);\s*$)|PRIMARY KEY/p' test.sql > schema.sql
* sed -rn '/^(INSERT |\()/p' test.sql > data.sql

##查看帮助
mvn spring-boot:help -Dgoal=run -Ddetail=true

##运行tjz.8080.sh
```
#!/bin/sh
#project's name
pname=`basename ${0%.sh} | cut -d. -f1`

#project's port
pport=`basename ${0%.sh} | cut -d. -f2`

#change to workspace
cd ${pname}

#run
mvn spring-boot:run \
-Drun.arguments=\
--server.port=${pport}\
,--spring.datasource.driverClassName=com.mysql.jdbc.Driver\
,--spring.datasource.url=jdbc:mysql://127.0.0.1:3306/tjz?zeroDateTimeBehavior=convertToNull\
,--spring.datasource.username=tjz\
,--spring.datasource.password=123
```






