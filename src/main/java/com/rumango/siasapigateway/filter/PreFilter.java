/*----------------------------------------------------------------------------------------------------
 **
 **  File Name  	: PreFilter.java
 **
 **  Description    : PreFilter executes before routing the URL to appropriate service
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

package com.rumango.siasapigateway.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.rumango.siasapigateway.exception.CustomException;
import com.rumango.siasapigateway.model.DeviceExist;
import com.rumango.siasapigateway.service.GatewayService;

@Component
public class PreFilter extends ZuulFilter {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	DeviceExist deviceIdExists;

	@Autowired
	GatewayService gatewayService;
	
	private static Logger log = LoggerFactory.getLogger(PreFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		/** Logging header properties */
		log.info("Running Pre Zuul Filter");
		log.info("Method: "+request.getMethod());
		log.info("path info: "+request.getPathInfo());
		log.info("Path Translated: "+request.getPathTranslated());
		log.info("Protocol: "+request.getProtocol().toString());
		log.info("Query String: "+request.getQueryString());
		log.info("Remote Adrr: "+request.getRemoteAddr());
		log.info("Remote Host: "+request.getRemoteHost());
		log.info("Request Session ID: "+request.getRequestedSessionId());
		log.info("request URI: "+request.getRequestURI());
		{
			Enumeration<String> headerNames = request.getHeaderNames();
	        while (headerNames.hasMoreElements()) {
	            String key = headerNames.nextElement();
	            String value = request.getHeader(key);
	            log.info("Key: "+key+" value: "+value);
		}
		}
		
		String uri=request.getRequestURI();
		
		/** Checking device id is required for URI or not... */
		if(gatewayService.checkURI(uri)) {

			log.info("URI checked successfully...");

			String deviceId=request.getHeader("deviceId");
			log.info("Device Id Received is: "+deviceId+"  ***deviceId should not be null***");
			

			if(deviceId!=null) {
				log.info("Checking deviceId in our database...");

					deviceIdExists = restTemplate.getForObject(
						"http://172.16.4.26:80/gateway/isDeviceIdExists/" + deviceId, DeviceExist.class);

				log.info("Device ID status : " + deviceIdExists);

				/** Checking device id exits in our database or not... */
				if (deviceIdExists != null) {

					if (!deviceIdExists.isDeviceExists()) {

						log.error("Unauthorized Device : " + deviceId);
						CustomException ce = new CustomException(System.currentTimeMillis(),
								HttpStatus.UNAUTHORIZED.toString(), HttpStatus.UNAUTHORIZED.value(), "Not Authorized");
						ctx.setResponseBody(ce.toString());
						ctx.setSendZuulResponse(false);
						ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
					}
				} else {
					log.error("Unauthorized Device : " + deviceId);
					log.info("Device not found in our database...");
					CustomException ce=new CustomException(System.currentTimeMillis(),HttpStatus.UNAUTHORIZED.toString(),HttpStatus.UNAUTHORIZED.value(),"Not Authorized");
					ctx.setResponseBody(ce.toString());
					ctx.setSendZuulResponse(false);
					ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
				}


			} else {
				
				log.error("Unauthorized Device : " + deviceId);
				log.info("Device not found in our database...");
				CustomException ce=new CustomException(System.currentTimeMillis(),HttpStatus.UNAUTHORIZED.toString(),HttpStatus.UNAUTHORIZED.value(),"Not Authorized");
				ctx.setResponseBody(ce.toString());
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			}
		}
		
		log.info("PreFilter : "
				+ String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		log.info("End of Pre Zuul Filter");

		return null;

	}
	
}
