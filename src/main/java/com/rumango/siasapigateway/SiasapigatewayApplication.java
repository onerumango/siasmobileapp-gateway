/*----------------------------------------------------------------------------------------------------
 **
 **  File Name  	: SiasapigatewayApplication.java
 **
 **  Description    : SiasapigatewayApplication main application
 **
 **
 **  All rights reserved.  No part of this work may be reproduced,
 **  stored in a retrieval system, adopted or transmitted in any form
 **  or by any means, electronic, mechanical, photographic, graphic,
 **  optic recording or otherwise, translated in any language or
 **  computer language, without the prior written permission  from  Rumango Ltd
 **
 **  Rumango Ltd ,
 **  119 Wards Road,Ilford,
 **  England, IG2 7DZ
 **
 **  Copyright c 2020 by Rumango Ltd..
 **-----------------------------------------------------------------------------------------------------
 **
 **    Created By : Md Rashid Alam
 **    Created On : 09 SEP 2020
 **
 **    Change History    
 **
 ** ------------------------------------------------------------------------------------------------------
 */

package com.rumango.siasapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.rumango.siasapigateway.filter.ErrorFilter;
import com.rumango.siasapigateway.filter.PostFilter;
import com.rumango.siasapigateway.filter.PreFilter;
import com.rumango.siasapigateway.filter.RouteFilter;
import com.rumango.siasapigateway.service.GatewayService;

@EnableHystrixDashboard
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SiasapigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiasapigatewayApplication.class, args);
	}

	@Bean
	public PreFilter getPreFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter getPostFilter() {
		return new PostFilter();
	}

	@Bean
	public RouteFilter getRouteFilter() {
		return new RouteFilter();
	}

	@Bean
	public ErrorFilter getErrorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public GatewayService getGatewayService() {
		return new GatewayService();
	}

}
