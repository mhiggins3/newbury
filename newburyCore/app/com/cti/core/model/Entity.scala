package com.cti.core.model

trait Entity {
	def id: Option[Long]
	def uuid: String; 
}