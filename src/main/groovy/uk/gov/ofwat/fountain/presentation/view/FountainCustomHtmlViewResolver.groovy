package uk.gov.ofwat.fountain.presentation.view

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.Ordered
import org.springframework.web.servlet.View
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver
import java.util.Locale

class FountainCustomHtmlViewResolver extends UrlBasedViewResolver implements Ordered{
	
	Logger logger = LoggerFactory.getLogger(FountainCustomHtmlViewResolver.class)
	
	protected View loadView(String viewName, Locale locale) throws Exception{
		this.setViewClass(FountainUrlBasedView.class)
		AbstractUrlBasedView view = buildView(viewName)
		FountainUrlBasedView viewObj = (FountainUrlBasedView) getApplicationContext().getAutowireCapableBeanFactory().initializeBean(view, viewName)
		logger.debug("checking for existence of view resource: " + view.getUrl())
		if(!view.checkResource(locale)){
			//If the file doesn't exist return null and we will delegate to the 
			//next chained view resolver by returning null or send an error. 
			return null
		}
		return viewObj
	}
}
