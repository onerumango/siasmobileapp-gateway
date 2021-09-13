/*----------------------------------------------------------------------------------------------------
 **
 **  File Name  	: DeviceExist.java
 **
 **  Description    : Model class for checking device existence
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

package com.rumango.siasapigateway.model;

import org.springframework.stereotype.Component;

@Component
public class DeviceExist {

	private boolean isDeviceExists;

	public boolean isDeviceExists() {
		return isDeviceExists;
	}

	public void setDeviceExists(boolean isDeviceExists) {
		this.isDeviceExists = isDeviceExists;
	}

	@Override
	public String toString() {
		return "DeviceExist [isDeviceExists=" + isDeviceExists + "]";
	}
}
