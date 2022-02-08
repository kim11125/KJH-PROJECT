package kjh.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import kjh.security.CustomOAuth2UserService;

//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecutityConfig extends WebSecurityConfigurerAdapter{
	
	//@Lazy
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //인증관련 설정
			////////////누구나 접근 가능한 페이지///////////////
			.antMatchers("/", "/qna/**", "/replies/**").permitAll()
			.antMatchers(HttpMethod.GET).permitAll()
			////////아래는 권한이 필요함////////
			//user 권한만 접근가능
			.antMatchers("/member/**", "/user/**").hasRole("USER")
			//admin 권한만 접근가능
			.antMatchers("/admin", "/custom/**", "/faq/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
			//로그인 안한경우에만 접근가능
			.antMatchers("/loginpage", "/join/**", "/register").anonymous()
			//나머지는 인증필수
			.anyRequest().authenticated()
			;
		/*
		////////////누구나 접근 가능한 페이지///////////////
		.antMatchers(HttpMethod.GET, "/","/loginpage", "/info/**", "/visuals/**").permitAll()
		.antMatchers("/custom/aspolicy/**", "/custom/board/**", "/qna/**").permitAll()
		.antMatchers("/visuals/**", "/goods/**", "/goodspage/**", "/goodsContent/**").permitAll()
		.antMatchers("/replies/**", "/faq", "/withdraw/**").permitAll()
		////////아래는 권한이 필요함////////
		//user 권한만 접근가능
		.antMatchers("/member/**", "/user/**").hasRole("USER")
		//admin 권한만 접근가능
		.antMatchers("/admin/**", "/custom/boards/**", "/faq/**").hasRole("ADMIN")
		//로그인 안한경우에만 접근가능
		.antMatchers("/loginpage", "/join/**", "/register").anonymous()
		//나머지는 인증필수
		.anyRequest().authenticated()
		;
		*/
		
		//소셜로그인
		//http.oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
		
		http.formLogin()
			.loginPage("/loginpage") //사용자 정의 로그인 페이지
			.loginProcessingUrl("/login/proc")//로그인페이지의 action값
			.usernameParameter("userId")//로그인 페이지 username -> userId
			//.defaultSuccessUrl("/") //로그인 성공후 이쪽으로 보냄
			.passwordParameter("pw")//패스워드값은 pw
			;
		
		//http.csrf().disable();
		
		http.logout()
			.logoutSuccessUrl("/")
		;
		
		//인증을 위한 Authenticationmanager 인증처리를 함
		//실제 처리시 UsernamePasswordAuthenticationToken 이라는 토큰이름으로 전달
		//UsernamePasswordAuthenticationFilter 내부에서 authentication()의 파라미터로 전달
		//AuthenticationProvider provider; //DaoAuthenticationProvider
		//UserDetailsService : user체크
		//http.csrf().disable(); //csrf
	}
	
	//비밀번호 암호화 하기위한 메서드, 빈으로 등록
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	//권한요청 무시
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/favicon.ico")
			.antMatchers("/css/**")
			.antMatchers("/js/**")
			.antMatchers("/images/**")
			.antMatchers("/summernote/**")
			.antMatchers("/file/**")
			.antMatchers("/error")
		;
	}
	
//	@Bean
//	public PlatformTransactionManager dataPlatformTransactionManager(DataSource dataSource) {
//		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//		dataSourceTransactionManager.setDataSource(dataSource);
//		return dataSourceTransactionManager;
//	}
	

}
