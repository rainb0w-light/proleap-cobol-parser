/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.send;

import io.proleap.cobol.Cobol85Parser.SendStatementAsyncContext;
import io.proleap.cobol.Cobol85Parser.SendStatementSyncContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Sends data to another program.
 */
public interface SendStatement extends Statement {

	enum SendType {
		ASYNC, SYNC
	}

	Async addAsync(SendStatementAsyncContext ctx);

	Sync addSync(SendStatementSyncContext ctx);

	Async getAsync();

	NotOnExceptionClause getNotOnExceptionClause();

	OnExceptionClause getOnExceptionClause();

	SendType getSendType();

	Sync getSync();

	void setNotOnExceptionClause(NotOnExceptionClause notOnExceptionClause);

	void setOnExceptionClause(OnExceptionClause onExceptionClause);

	void setSendType(SendType sendType);
}
