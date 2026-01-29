/*

   Copyright 2026 Sachin Chaurasiya

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
package com.example.SysDatabase;
import java.io.InputStream;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
public class connectionCaching {
    static final String fileName = "db.properties";
    public HikariDataSource conPooling(){
        HikariDataSource ds = null;
        try {
            Properties props = new Properties();
             InputStream file = getClass()
                .getClassLoader()
                .getResourceAsStream("db.properties");

            if (file == null) {
                throw new RuntimeException("db.properties file not found");
            }
            props.load(file);

            String url = props.getProperty("db.url");
            String name = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            String Driver = props.getProperty("db.DriverName");

            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(url); 
            config.setUsername(name);  
            config.setPassword(password);
            config.setDriverClassName(Driver);
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(5);
            config.setInitializationFailTimeout(-1);
            config.setPoolName("BMSPool");
            config.setMaximumPoolSize(10);


            ds = new HikariDataSource(config);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
