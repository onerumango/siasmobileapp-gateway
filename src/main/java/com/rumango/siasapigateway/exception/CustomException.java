/*----------------------------------------------------------------------------------------------------
 **
 **  File Name  	: CustomException.java
 **
 **  Description    : User defined exception
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

package com.rumango.siasapigateway.exception;


public class CustomException {

	private long timestamp;
	private String status;
	private int error;
	private String massage;
	
	public CustomException(long timestamp, String status, int error, String massage) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.massage = massage;
	}

	@Override
	public String toString() {
		return "{ "+"\"timestamp\""+" : " + timestamp + ","+"\"status\""+" : " + "\""+status+"\"" + ","+"\"error\""+" : " +error+","+"\"massage\""+" : "+ "\""+massage+"\""
				+ "} ";
	}
	
}
