package com.cti.web.controllers

import play.api.mvc._
import models.Product
import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}
import play.api.i18n.Messages
//import com.cti.core.service._

object Products extends Controller {
  
  private val productForm: Form[Product] = Form(
		  mapping(
		      "ean" -> longNumber.verifying("validation.ean.duplicate", Product.findByEan(_).isEmpty),
		      "name" -> nonEmptyText,
		      "description" -> nonEmptyText
		      )(Product.apply)(Product.unapply)
		  )
  

  def save = Action { implicit request =>
    val newProductForm = productForm.bindFromRequest()
    newProductForm.fold(
        hasErrors = { form =>
          Redirect(com.cti.web.controllers.routes.Products.newProduct()).
          	flashing(Flash(form.data) + 
          	    ("error" -> Messages("validation.errors")))
        },
        success = { newProduct =>
          val message = Messages("products.new.sucess", newProduct.name)
          Product.add(newProduct)
          Redirect(routes.Products.show(newProduct.ean)).
          	flashing("success" -> message)
        })
  }
  def newProduct = Action { implicit request =>
    	val form = if (flash.get("error").isDefined)
    			productForm.bind(flash.data)
    		else
    		  productForm
    	Ok(views.html.products.editProduct(form))
    		  
    
  }		  
  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.products.list(products))
  }

  def show(ean: Long) = Action { implicit request => 
  	Product.findByEan(ean).map { product => 
  		Ok(views.html.products.detail(product))
  	}.getOrElse(NotFound)
  }

}