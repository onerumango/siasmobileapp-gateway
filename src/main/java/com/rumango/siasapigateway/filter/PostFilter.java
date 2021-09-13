/*----------------------------------------------------------------------------------------------------
 **
 **  File Name  	: PostFilter.java
 **
 **  Description    : Executes after the Pre filter
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


import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


@Component
public class PostFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PostFilter.class);

	@Override
	public String filterType() {
		return "post";
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

		HttpServletResponse response = RequestContext.getCurrentContext().getResponse();

		log.info("Running Post Zuul Filter");
		log.info("PostFilter : " + String.format("response's content type is %s", response.getStatus()));
		log.info("End of Post Zuul Filter");

		return null;

	}
}
