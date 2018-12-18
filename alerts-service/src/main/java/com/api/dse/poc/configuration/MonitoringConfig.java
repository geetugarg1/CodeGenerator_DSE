package com.api.dse.poc.configuration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.api.dse.poc.configuration.RequestMetricInterceptor;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.client.spring.boot.SpringBootMetricsCollector;

@Configuration
public class MonitoringConfig extends WebMvcConfigurerAdapter{

    @Bean
    public SpringBootMetricsCollector springBootMetricsCollector(Collection<PublicMetrics> publicMetrics) {

        SpringBootMetricsCollector springBootMetricsCollector = new SpringBootMetricsCollector(publicMetrics);
        springBootMetricsCollector.register();

        return springBootMetricsCollector;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        DefaultExports.initialize();
        return new ServletRegistrationBean(new MetricsServlet(), "/metrics");
    }
    

	@Autowired RequestMetricInterceptor requestCounterInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestCounterInterceptor);
	}
	
}
