import play.api._
import play.api.mvc._
import play.filters.gzip.GzipFilter

object Global extends GlobalSettings {
	override def onStart(app: Application) {
		Logger.info("Application has started")
		
	}

	override def onStop(app: Application) {
		Logger.info("Application shutdown...")
	}
}