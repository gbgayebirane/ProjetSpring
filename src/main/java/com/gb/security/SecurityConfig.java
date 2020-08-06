package com.gb.security;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
 
	@Autowired
	private DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//auth.inMemoryAuthentication().withUser("admin@gmail.com").password("passer").roles("ADMIN");
		//auth.inMemoryAuthentication().withUser("user@gmail.com").password("passer").roles("USER"); 
		 
		
		 auth.jdbcAuthentication()
	     .dataSource(datasource)
	     .usersByUsernameQuery("select username as principal,password as credentials,active from users where username=?")
	     .authoritiesByUsernameQuery("select username as principal,role as role from users_role where username=?")
	     .passwordEncoder(new Md5PasswordEncoder())
	     .rolePrefix("ROLE_");
		//.usersByUsernameQuery("SELECT email as principal ,password as credentials, etat, nom, prenom  From user Where email=?")
        //.authoritiesByUsernameQuery("SELECT u.email as principal , r.nom as role from user u ,roles r ,user_roles ur where u.id = ur.users_id and r.id = ur.roles_id and u.email=?")
        //.passwordEncoder(new Md5PasswordEncoder())
        
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		try
		{
			http.formLogin().loginPage("/login");
			
			//http.authorizeRequests().antMatchers("/Lieu/**").hasRole("ADMIN");
			
			http.authorizeRequests().antMatchers("/Lieu/**").permitAll();
			
			http.authorizeRequests().antMatchers("/Formation/**").hasRole("ADMIN");

			//http.authorizeRequests().antMatchers("/User/**","/Roles/**").hasRole("SUPERADMIN");
	
			http.exceptionHandling().accessDeniedPage("/Admin/403");
			
			http.csrf().disable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
