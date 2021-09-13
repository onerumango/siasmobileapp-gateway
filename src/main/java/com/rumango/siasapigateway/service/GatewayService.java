/*----------------------------------------------------------------------------------------------------
 **
 **  File Name  	: GatewayService.java
 **
 **  Description    : Gateway Service to check weather URL needs to be authenticated or not
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

package com.rumango.siasapigateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {

	private static Logger log = LoggerFactory.getLogger(GatewayService.class);
	/** Checking URI.... */
	public boolean checkURI(String uri) {
		
		log.info("Checking URI....  " + uri);

		if(uri.contains("guest"))
			return false;
		else if (uri.contains("ping"))
			return false;
		else if (uri.contains("authenticate"))
			return false;
		else
			return true;
		
	}
}
