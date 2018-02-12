package com.woneway.utils;  
  
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/** 
 * 数据库工具类 
 * @author liang 
 * 
 */  
public class JDBCUtils {  
	 //ComboPooledDataSource(String configName)的参数configName指的是配置文件c3p0-config.xml中的 <named-config name="mysql">...</named-config>
    //如果没有输入configName参数，那么就采用默认的<default-config>...</defalut-config>
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");
    private static Connection con = null;
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    /**
     * 获取连接对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        con = tl.get();
        if(con != null){return con;}
        return dataSource.getConnection();
    }
    /**
     * 获取连接池对象
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }
    /**
     * 开启事务
     * @throws SQLException
     */
    public static void beginTransaction() throws SQLException{
        con = tl.get();
        if(con != null){throw new RuntimeException("事务已经开启！不能重复开启！");}
        con = getConnection();
        con.setAutoCommit(false);
        tl.set(con);
    }
    /**
     * 提交事务
     * @throws SQLException
     */
    public static void commitTransaction() throws SQLException{
        con = tl.get();
        if(con == null){throw new RuntimeException("事务还没开启！不能提交！");}
        con.commit();
        con.close();//归还连接对象到连接池
        tl.remove();//移除连接对象con。那么tl.get() == null
    }
    /**
     * 回滚事务
     * @throws SQLException
     */
    public static void rollbackTransaction() throws SQLException{
        con = tl.get();
        if(con == null){throw new RuntimeException("事务还没开启！不能回滚！");}
        con.rollback();
        con.close();
        tl.remove();
    }
    /**
     * 关闭 非事务专用的连接
     * @param connection
     * @throws SQLException
     */
    public static void releaseConnection(Connection connection) throws SQLException{
        con = tl.get();//获取事务专用连接
        if(con == null){connection.close();}
        if(con != connection){connection.close();}
    } 
}  