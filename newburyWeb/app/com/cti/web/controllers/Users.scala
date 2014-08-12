package com.cti.web.controllers

import play.api.i18n.Messages
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import com.cti.core.service.UserService
import com.cti.core.model.User._
import com.cti.core.model.Phone._

object Users extends Controller {
    private val userPhoneForm: Form[UserPhone] = Form(
		  mapping(
		      "user" -> optional(mapping(
		           "id" -> optional(longNumber),
		           "firstName" -> nonEmptyText,
		           "lastName" -> nonEmptyText,
		           "username" -> nonEmptyText,
		           "email" -> email,
		           "phoneId" -> optional(longNumber),
		           "password" -> nonEmptyText
		    		  )(User.apply)(User.unapply)),
		      "phone" -> optional(mapping(
		          "id" -> optional(longNumber),
		          "serialNumber" -> optional(text)
		          )(Phone.apply)(Phone.unapply))
		      )(UserPhone.apply)(UserPhone.unapply)
		  )
		  
  def list = Action {implicit request => 
  	Ok(views.html.users.listUsers(UserService.findAll()))
  }
    
  def save = Action {implicit request =>
    println(" REQUEST " + request)
    val newUserPhoneForm = userPhoneForm.bindFromRequest()
    newUserPhoneForm.fold(
        hasErrors = { form =>
          Redirect(com.cti.web.controllers.routes.Users.newUser()).
          	flashing(Flash(form.data) + 
          	    ("error" -> Messages("validation.errors")))
        },
        success = { newUser =>
          if(newUser.user.isDefined && newUser.user.get.id.isDefined){
            val message = Messages("users.update.sucess", newUser.user.get.firstName)
            UserService.saveOrUpdateUserAndPhone(newUser)
            Redirect(com.cti.web.controllers.routes.Users.list()).
          	flashing("success" -> message)
          } else {
          val message = Messages("users.new.sucess", newUser.user.get.firstName)
          UserService.saveOrUpdateUserAndPhone(newUser)
          Redirect(com.cti.web.controllers.routes.Users.list()).
          	flashing("success" -> message)
          }
        })
  }
    
   def newUser = Action { implicit request =>
    	val form: Form[UserPhone] = if (request.flash.get("error").isDefined)
    			userPhoneForm.bind(request.flash.data)
    		else
    		  userPhoneForm
    	Ok(views.html.users.addEditUser(form, true))   
  }		
   
  def delete(id: Long) = Action { implicit request => 
    val message = Messages("users.delete.sucess")
          UserService.delete(id)
          Redirect(com.cti.web.controllers.routes.Users.list()).
          	flashing("success" -> message)
  }
  
  def edit(id: Long) = Action { implicit rs =>
    val userPhone = UserService.findByIdWithPhone(id)
    userPhone match { 
  		case Some(userPhone) => Ok(views.html.users.addEditUser(userPhoneForm.fill(userPhone), false)) 
  		case None => Ok(views.html.users.addEditUser(userPhoneForm, true))
	}
  }
     
}