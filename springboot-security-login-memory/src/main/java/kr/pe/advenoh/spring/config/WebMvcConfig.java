//package kr.pe.advenoh.spring.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//    }
//
////    @Override
////    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/").setViewName("home");
////    }
//
////    @Bean
////    public InternalResourceViewResolver setupViewResolver() {
////        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
////        resolver.setPrefix("/jsp/");
////        resolver.setSuffix(".jsp");
////        resolver.setViewClass(JstlView.class);
////        return resolver;
////    }
//}
