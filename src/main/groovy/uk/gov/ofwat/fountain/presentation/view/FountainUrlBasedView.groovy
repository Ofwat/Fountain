package uk.gov.ofwat.fountain.presentation.view

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.JstlView;

class FountainUrlBasedView extends InternalResourceView  {
	public boolean checkResource(Locale arg0) throws Exception {
		//check whether the jsp/html file exists, if it doesn't return false. 
		Resource resource = getWebApplicationContext().getResource(url)
		return resource.exists()
	};
}
