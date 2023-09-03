package rbt.log.agent.process;

import org.junit.jupiter.api.Test;
import rbt.log.agent.dto.LogInfoDTO;

import static org.junit.jupiter.api.Assertions.*;

class LogProcessorTest {
    /**
     * 单行string解析
     */
    @Test
    void analysis_test() {

        String line = "usdp-task.log:2023-07-05 17:51:00,404|ERROR|Thread:pool-4-thread-1|com.huawei.ivas.services.system.task.DisasterRecoveryTask.isNotReadWrite|227|LogId:|TransanctionID:|Exception while checking Database videousdpds1 connection: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.Persistenc*****#*#*****eException: [N]### Error querying database.  Cause: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'configurator' defined in URL [jar:file:/home/pltmuser/vrbt/app/WEB-INF/lib/videousdp-common-service-V100R023C00.jar!/com/huawei/ivas/externals/config/Configurator.class]: Initialization of bean failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'configurationDao' defined in URL [jar:file:/home/pltmuser/vrbt/app/WEB-INF/lib/videousdp-common-service-V100R023C00.jar!/com/huawei/ivas/externals/config/db/ConfigurationDao.class]: Cannot resolve referenc*****#*#*****e to bean 'sqlSessionFactory' while setting bean property 'sqlSessionFactory'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'microDataSourceConfig' defined in URL [jar:file:/home/pltmuser/vrbt/app/WEB-INF/lib/rbt-micro-core-1.2.11.13.jar!/com/huawei/rbt/microcore/config/DataSourceConfig.class]: Initialization of bean failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'customRoutingDataSource': Invocation of init method failed; nested exception is java.lang.IllegalStateException: BeanFactory not initialized or already closed - call 'refresh' before accessing beans via the ApplicationContext[N]### The error may exist in file [/home/pltmuser/vrbt/app/WEB-INF/classes/sqlMaps/OamDao.xml][N]### The error may involve com.huawei.ivas.services.system.oam.common.dao.OamDao.checkDatabaseOpenStatus[N]### The error occurred while handling results[N]### SQL: select open_status from dv_database[N]### Cause: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'configurator' defined in URL [jar:file:/home/pltmuser/vrbt/app/WEB-INF/lib/v...";

         /*
         String line = "service-system.log:2023-07-05 17:50:55,067|ERROR|Thread:schedulerFactoryBean_Worker-8|com.huawei.ivas.services.system.datasync.tasks.DataSyncNewTask.initKafka|89|LogId:task_DataSyncNewTask_8|TransanctionID:10402010358202307051750550008|DataSyncNewTask init kafkaProducer failed\n";
         */
        String batchID = null;
        LogInfoDTO logInfoDTO = LogProcessor.analysis(batchID, line);

        System.out.println(logInfoDTO.toString());

    }

    @Test
    void removeTime_test() {

        String line = "usdp-task.log:2023-07-05 17:51:00,404|ERROR|Thread:pool-4-thread-1|com.huawei.ivas.services.system.task.DisasterRecoveryTask.isNotReadWrite|227|LogId:|TransanctionID:|Exception while checking Database videousdpds1 connection: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.Persistenc*****#*#*****eException: [N]### Error querying database.  Cause: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'configurator' defined in URL [jar:file:/home/pltmuser/vrbt/app/WEB-INF/lib/videousdp-common-service-V100R023C00.jar!/com/huawei/ivas/externals/config/Configurator.class]: Initialization of bean failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'configurationDao' defined in URL [jar:file:/home/pltmuser/vrbt/app/WEB-INF/lib/videousdp-common-service-V100R023C00.jar!/com/huawei/ivas/externals/config/db/ConfigurationDao.class]: Cannot resolve referenc*****#*#*****e to bean 'sqlSessionFactory' while setting bean property 'sqlSessionFactory'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'microDataSourceConfig' defined in URL [jar:file:/home/pltmuser/vrbt/app/WEB-INF/lib/rbt-micro-core-1.2.11.13.jar!/com/huawei/rbt/microcore/config/DataSourceConfig.class]: Initialization of bean failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'customRoutingDataSource': Invocation of init method failed; nested exception is java.lang.IllegalStateException: BeanFactory not initialized or already closed - call 'refresh' before accessing beans via the ApplicationContext[N]### The error may exist in file [/home/pltmuser/vrbt/app/WEB-INF/classes/sqlMaps/OamDao.xml][N]### The error may involve com.huawei.ivas.services.system.oam.common.dao.OamDao.checkDatabaseOpenStatus[N]### The error occurred while handling results[N]### SQL: select open_status from dv_database[N]### Cause: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'configurator' defined in URL [jar:file:/home/pltmuser/vrbt/app/WEB-INF/lib/v...";

         /*
         String line = "service-system.log:2023-07-05 17:50:55,067|ERROR|Thread:schedulerFactoryBean_Worker-8|com.huawei.ivas.services.system.datasync.tasks.DataSyncNewTask.initKafka|89|LogId:task_DataSyncNewTask_8|TransanctionID:10402010358202307051750550008|DataSyncNewTask init kafkaProducer failed\n";
         */
        String lineWithoutTime = LogProcessor.removeTime(line);

        System.out.println(lineWithoutTime);

    }
}