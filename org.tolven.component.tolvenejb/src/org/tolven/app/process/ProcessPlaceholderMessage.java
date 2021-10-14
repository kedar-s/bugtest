package org.tolven.app.process;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.drools.StatefulSession;
import org.tolven.app.AppEvalAdaptor;
import org.tolven.app.ProcessPlaceholderMessageLocal;
import org.tolven.app.el.GeneralExpressionEvaluator;
import org.tolven.app.el.TrimExpressionEvaluator;
import org.tolven.app.entity.MenuData;
import org.tolven.app.entity.PlaceholderMessage;
import org.tolven.core.TolvenRequest;
import org.tolven.doc.entity.DocBase;
import org.tolven.doctype.DocumentType;
import org.tolven.el.ExpressionEvaluator;

@Stateless
@Local(ProcessPlaceholderMessageLocal.class)

public class ProcessPlaceholderMessage extends AppEvalAdaptor implements ProcessPlaceholderMessageLocal {

	private Logger logger = Logger.getLogger(this.getClass());
	private MenuData placeholder;
	private TrimExpressionEvaluator trimExpEval;
	@EJB org.tolven.app.MenuLocal menuBean;
	
	@Override
	public void process(Object message, Date now) {
		if (message instanceof PlaceholderMessage) { 
			PlaceholderMessage rpm = (PlaceholderMessage) message;
			logger.info("Processing placeholder message: " + rpm.getPlaceholderId());
			placeholder = menuBean.findMenuDataItem(rpm.getPlaceholderId());
			if (placeholder.getAccount().getId() != rpm.getAccountId()) {
				// TODO finish this error
				throw new RuntimeException("Database placeholder (" + placeholder.getId() + ") account id of " + placeholder.getAccount().getId() + " does not equal message placeholder account id of " + rpm.getAccountId());
			}
			try {
				initializeMessage(rpm, TolvenRequest.getInstance().getNow());
				
				runRules( );
			} catch (Exception e) {
				// TODO remove the stack trace
				e.printStackTrace();
				throw new RuntimeException( "Exception in placeholder processor", e);
			}
		}
	}

	@Override
	protected DocBase scanInboundDocument(DocBase doc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void loadWorkingMemory(StatefulSession workingMemory)
			throws Exception {
		assertPlaceholder(placeholder);
		
	}

	@Override
	protected ExpressionEvaluator getExpressionEvaluator() {
		if (trimExpEval==null) {
			trimExpEval = new TrimExpressionEvaluator();
			trimExpEval.addVariable( "now", getNow());
			trimExpEval.addVariable(TrimExpressionEvaluator.ACCOUNT, getAccount());
		}
		return trimExpEval;
	}
	

}

