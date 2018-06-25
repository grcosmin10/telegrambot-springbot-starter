package ro.vladfernoaga.telegram_chatbot_starter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@PropertySource("classpath:application.properties")
public class Properties {  
  /** The hibernate auto. */
  @Value("${hibernate.hbm2ddl.auto}")
  private String hibernateAuto;

  /** The hibernate show sql. */
  @Value("${hibernate.show_sql}")
  private String hibernateShowSql;

  /** The hibernate dialect. */
  @Value("${hibernate.dialect}")
  private String hibernateDialect;

  /** The jdbc driver class name. */
  @Value("${datasource.driverClassName}")
  private String jdbcDriverClassName;

  /** The jdbc url. */
  @Value("${datasource.url}")
  private String jdbcUrl;

  /** The jdbc username. */
  @Value("${datasource.username}")
  private String jdbcUsername;

  /** The jdbc password. */
  @Value("${datasource.password}")
  private String jdbcPassword;
  
  /** The geocoding API key. */
	@Value("${key.geocoding_api}")
	private String geocodingApiKey;
  
  /**  button 1 */
	@Value("${menu.button1}")
	private String button1;
	
	/** button 2 */
	@Value("${menu.button2}")
	private String button2;
	
	/**  button 3  */
	@Value("${menu.button3}")
	private String button3;
	
	/**  button 4  */
	@Value("${menu.button4}")
	private String button4;

public String getHibernateAuto() {
	return hibernateAuto;
}

public void setHibernateAuto(String hibernateAuto) {
	this.hibernateAuto = hibernateAuto;
}

public String getHibernateShowSql() {
	return hibernateShowSql;
}

public void setHibernateShowSql(String hibernateShowSql) {
	this.hibernateShowSql = hibernateShowSql;
}

public String getHibernateDialect() {
	return hibernateDialect;
}

public void setHibernateDialect(String hibernateDialect) {
	this.hibernateDialect = hibernateDialect;
}

public String getJdbcDriverClassName() {
	return jdbcDriverClassName;
}

public void setJdbcDriverClassName(String jdbcDriverClassName) {
	this.jdbcDriverClassName = jdbcDriverClassName;
}

public String getJdbcUrl() {
	return jdbcUrl;
}

public void setJdbcUrl(String jdbcUrl) {
	this.jdbcUrl = jdbcUrl;
}

public String getJdbcUsername() {
	return jdbcUsername;
}

public void setJdbcUsername(String jdbcUsername) {
	this.jdbcUsername = jdbcUsername;
}

public String getJdbcPassword() {
	return jdbcPassword;
}

public void setJdbcPassword(String jdbcPassword) {
	this.jdbcPassword = jdbcPassword;
}

public String getButton1() {
	return button1;
}

public void setButton1(String button1) {
	this.button1 = button1;
}

public String getButton2() {
	return button2;
}

public void setButton2(String button2) {
	this.button2 = button2;
}

public String getButton3() {
	return button3;
}

public void setButton3(String button3) {
	this.button3 = button3;
}

public String getButton4() {
	return button4;
}

public void setButton4(String button4) {
	this.button4 = button4;
}

public String getGeocodingApiKey() {
	return geocodingApiKey;
}

public void setGeocodingApiKey(String geocodingApiKey) {
	this.geocodingApiKey = geocodingApiKey;
}


  

}
