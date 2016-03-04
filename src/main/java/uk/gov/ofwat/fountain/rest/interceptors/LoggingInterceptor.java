package uk.gov.ofwat.fountain.rest.interceptors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@ServerInterceptor
public class LoggingInterceptor implements PreProcessInterceptor {
	private static Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Context
    HttpServletRequest servletRequest;

    public ServerResponse preProcess(HttpRequest request,
            ResourceMethod resourceMethod) throws Failure,
            WebApplicationException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        logger.info("" + dateFormat.format(new Date()) + " " +
        			servletRequest.getUserPrincipal().getName() + " " +
        			servletRequest.getMethod() + " " +
        			servletRequest.getRequestURI() +
        			((null == servletRequest.getQueryString()) ? "" : "/" + servletRequest.getQueryString()));
        return null;
    }
}
