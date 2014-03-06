package com.cti.web.controllers


import play.api.db.DB
import play.api._
import play.api.mvc._
import play.api.Play.current

import scala.slick.driver.MySQLDriver.simple._

import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText, email}
import play.api.i18n.Messages
import com.cti.core.service.UserExtensions._
import com.cti.core.service.EntityExtensions._
import com.cti.core.model.User._

object Users extends Controller {
//id: Long,firstName: String, lastName: String, username: String, email: String, phoneId: String, password: String
    private val userForm: Form[User] = Form(
		  mapping(   
		      //"id" -> longNumber.verifying("validation.ean.duplicate", users.findById(_).list.isEmpty),
		      "id" -> longNumber,
		      "firstName" -> nonEmptyText,
		      "lastName" -> nonEmptyText,
		      "username" -> nonEmptyText,
		      "email" -> email,
		      "phoneId" -> longNumber,
		      "password" -> nonEmptyText
		      )(User.apply)(User.unapply)
		  )
  def save = Action {implicit request =>
    val newProductForm = userForm.bindFromRequest()
    newProductForm.fold(
        hasErrors = { form =>
          Redirect(com.cti.web.controllers.routes.Users.newUser()).
          	flashing(Flash(form.data) + 
          	    ("error" -> Messages("validation.errors")))
        },
        success = { newUser =>
          def db = Database.forDataSource(DB.getDataSource())
          val message = Messages("products.new.sucess", newUser.firstName)
          db withSession {
        	   implicit session => users += newUser
          }
          Redirect(routes.Users.editUser(newUser.id)).
          	flashing("success" -> message)
        })
  }
    
   def newUser = Action { implicit request =>
    	val form: Form[User] = if (flash.get("error").isDefined)
    			userForm.bind(flash.data)
    		else
    		  userForm
    	Ok(views.html.users.addEditUser(form))   
  }		   
    
  def editUser(id: Long) = Action { implicit request =>
	  def db = Database.forDataSource(DB.getDataSource())
			  db withSession {
		  implicit session => {	
			  users.findById(id).list.headOption match { 
			    case Some(user) => Ok(views.html.users.addEditUser(userForm.fill(user))) 
			    case None => Ok(views.html.users.addEditUser(userForm))
			  }
		  }
	  }
  }   
}