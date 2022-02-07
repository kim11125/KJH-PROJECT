package kjh.config;

import javax.sql.DataSource;

//import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@PropertySource("classpath:/application.properties")
public class MybatisConfiguration {
	
	/* src/main/resources 폴더의 내부에 resource를 읽어들이기 위해서 필요한 객체 */
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	//DataSource dataSource = new HikariDataSource();
	
	/* map-underscore-to-camel-case=true 적용*/
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisconfig(){
		return new org.apache.ibatis.session.Configuration();
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		//datasource 설정
		System.out.println("dataSource = " + dataSource());
		factoryBean.setDataSource(dataSource());
		//매퍼설정
		Resource[] resources = 					//매퍼위치 설정
				applicationContext.getResources("classpath:/mapper/*.xml");
		factoryBean.setMapperLocations(resources);
		
		//설정정보 등록
		factoryBean.setConfiguration(mybatisconfig());
		
		return factoryBean.getObject();
	}
	
	/* SqlSessionTemplate sqlSession 처리를 더 효율적으로 처리하기위한 객체 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory());
		
	}
	
}
