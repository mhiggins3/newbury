package com.cti.web.controllers

import play.api.i18n.Messages
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import com.cti.core.service.BeaconService
import com.cti.core.model.Beacon._

object Beacons extends Controller {
  
  private val beaconForm: Form[Beacon] = Form(
      mapping("id" -> optional(longNumber), 
          "uuid" -> text,
		  "name" -> nonEmptyText,
		  "serialNumber" -> nonEmptyText
      )(Beacon.apply)(Beacon.unapply))
 
  
  def list = Action { implicit request =>
    Ok(views.html.beacons.listBeacons(BeaconService.findAll()))

  }
  def newBeacon = Action { implicit request =>
    	val form: Form[Beacon] = if (request.flash.get("error").isDefined)
    			beaconForm.bind(request.flash.data)
    		else
    		  beaconForm
    	Ok(views.html.beacons.addEditBeacon(form, true))   
  }		
   
   def edit(id: Long) = Action { implicit rs =>
    val beacon = BeaconService.findById(id)
    beacon match { 
  		case Some(beacon) => Ok(views.html.beacons.addEditBeacon(beaconForm.fill(beacon), false)) 
  		case None => Ok(views.html.beacons.addEditBeacon(beaconForm, true))
	}
  }
   
   def save = Action {implicit request =>
    val currentBeaconForm = beaconForm.bindFromRequest()
    currentBeaconForm.fold(
        hasErrors = { form =>
          Redirect(com.cti.web.controllers.routes.Beacons.newBeacon()).
          	flashing(Flash(form.data) + 
          	    ("error" -> Messages("validation.errors")))
        },
        success = { currentBeacon =>
          if(currentBeacon.id.isDefined){
            val message = Messages("beacons.update.sucess", currentBeacon.name)
            BeaconService.saveOrUpdate(currentBeacon)
            Redirect(com.cti.web.controllers.routes.Beacons.list()).
          	flashing("success" -> message)
          } else {
          val message = Messages("beacons.new.sucess", currentBeacon.name)
          BeaconService.saveOrUpdate(currentBeacon)
          Redirect(com.cti.web.controllers.routes.Beacons.list()).
          	flashing("success" -> message)
          }
        })
  }
  def delete(id: Long) = Action { implicit request => 
    val message = Messages("beacons.delete.sucess")
          BeaconService.delete(id)
          Redirect(com.cti.web.controllers.routes.Beacons.list()).
          	flashing("success" -> message)
  }
}