/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.subtract;

import io.proleap.cobol.Cobol85Parser.SubtractCorrespondingStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractFromGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.SubtractFromStatementContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Subtracts one or the sum of multiple data items from one or more data items.
 */
public interface SubtractStatement extends Statement {

	enum SubtractType {
		CORRESPONDING, FROM, FROM_GIVING
	}

	SubtractCorresponding addSubtractCorresponding(SubtractCorrespondingStatementContext ctx);

	SubtractFrom addSubtractFrom(SubtractFromStatementContext ctx);

	SubtractFromGiving addSubtractFromGiving(SubtractFromGivingStatementContext ctx);

	NotOnSizeErrorPhrase getNotOnSizeErrorPhrase();

	OnSizeErrorPhrase getOnSizeErrorPhrase();

	SubtractCorresponding getSubtractCorresponding();

	SubtractFrom getSubtractFrom();

	SubtractFromGiving getSubtractFromGiving();

	SubtractType getSubtractType();

	void setNotOnSizeErrorPhrase(NotOnSizeErrorPhrase notOnSizeErrorPhrase);

	void setOnSizeErrorPhrase(OnSizeErrorPhrase onSizeErrorPhrase);

	void setSubtractType(SubtractType subtractType);
}
