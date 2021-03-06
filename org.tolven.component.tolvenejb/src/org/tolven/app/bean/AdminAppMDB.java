package org.tolven.app.bean;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.interceptor.Interceptors;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;
import org.tolven.app.ActivateNewTrimHeadersMessage;
import org.tolven.app.MenuLocal;
import org.tolven.app.TrimLocal;
import org.tolven.gatekeeper.client.interceptor.QueueAuthenticationInterceptor;
import org.tolven.gatekeeper.client.interceptor.QueueSessionInterceptor;
import org.tolven.msg.QueueTolvenRequestInterceptor;


@MessageDriven
@Interceptors({
    QueueSessionInterceptor.class,
    QueueAuthenticationInterceptor.class,
    QueueTolvenRequestInterceptor.class })
public class AdminAppMDB implements MessageListener {
	@EJB TrimLocal trimBean;
	
	@EJB MenuLocal menuLocal;

	@Resource MessageDrivenContext ctx;
	private Logger logger = Logger.getLogger(this.getClass());

    public void onMessage(Message msg) {
		String msgId = "<tbd>";
		Date now = new Date();
		try {
			msgId = msg.getJMSMessageID();
			if (((ObjectMessage)msg).getObject() instanceof ActivateNewTrimHeadersMessage) {
				ActivateNewTrimHeadersMessage tmmsg = (ActivateNewTrimHeadersMessage) ((ObjectMessage)msg).getObject();
				// Keep queuing work until there is no more to do
				if (trimBean.activateNewTrimHeaders()) {
					trimBean.queueActivateNewTrimHeaders();
				}
			}
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			logger.error( "Message " + msgId + " failed with error: " + e.getMessage());
			e.printStackTrace();
            throw new RuntimeException(e);
		}
	}
}
