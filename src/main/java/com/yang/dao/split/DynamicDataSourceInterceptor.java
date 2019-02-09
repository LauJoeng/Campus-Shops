package com.yang.dao.split;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class,method = "update",args={MappedStatement.class,Object.class}),
        @Signature(type = Executor.class,method = "query",args={MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);

    private static final String REGEX=".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        Object[]objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];
        String lookupKey=DynamicDatasourceHolder.DB_MASTER;
        if (!synchronizationActive){
            //读方法
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)){
                //selectKey是为自增id查询主键(SELECT LAST_INSERT_ID())的方法，使用主库
                if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)){
                    lookupKey=DynamicDatasourceHolder.DB_MASTER;
                }else{
                    BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("\\t\\n\\r"," ");
                    if (sql.matches(REGEX)){
                        lookupKey=DynamicDatasourceHolder.DB_MASTER;
                    }else {
                        lookupKey=DynamicDatasourceHolder.DB_SLAVE;
                    }
                }
            }
        }
        logger.debug("设置方法[{}]use[{}]Strategy,SqlCommondType[{}]..",ms.getId(),lookupKey,ms.getSqlCommandType().name());
        DynamicDatasourceHolder.setDbType(lookupKey);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if(target instanceof Executor){
            return Plugin.wrap(target,this);
        }else{
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
