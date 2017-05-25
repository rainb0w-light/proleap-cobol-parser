/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide.impl;

import io.proleap.cobol.Cobol85Parser.DivideByGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideGivingContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideRemainderContext;
import io.proleap.cobol.Cobol85Parser.DivideStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.divide.ByGiving;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.Into;
import io.proleap.cobol.asg.metamodel.procedure.divide.IntoGiving;
import io.proleap.cobol.asg.metamodel.procedure.divide.Remainder;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class DivideStatementImpl extends StatementImpl implements DivideStatement {

	protected ByGiving byGiving;

	protected final DivideStatementContext ctx;

	protected DivideType divideType;

	protected ValueStmt divisorValueStmt;

	protected Into into;

	protected IntoGiving intoGiving;

	protected NotOnSizeErrorPhrase notOnSizeErrorPhrase;

	protected OnSizeErrorPhrase onSizeErrorPhrase;

	protected Remainder remainder;

	protected final StatementType statementType = StatementTypeEnum.DIVIDE;

	public DivideStatementImpl(final ProgramUnit programUnit, final Scope scope, final DivideStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public ByGiving addByGiving(final DivideByGivingStatementContext ctx) {
		ByGiving result = (ByGiving) getASGElement(ctx);

		if (result == null) {
			result = new ByGivingImpl(programUnit, ctx);

			// by
			final ValueStmt byValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setByValueStmt(byValueStmt);

			// giving
			if (ctx.divideGivingPhrase() != null) {
				result.addGivings(ctx.divideGivingPhrase());
			}

			byGiving = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Into addInto(final DivideIntoStatementContext ctx) {
		Into result = (Into) getASGElement(ctx);

		if (result == null) {
			result = new IntoImpl(programUnit, ctx);

			// into
			final ValueStmt intoValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setIntoValueStmt(intoValueStmt);

			// givings
			if (ctx.divideGivingPhrase() != null) {
				result.addGivings(ctx.divideGivingPhrase());
			}

			into = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public IntoGiving addIntoGiving(final DivideIntoGivingStatementContext ctx) {
		IntoGiving result = (IntoGiving) getASGElement(ctx);

		if (result == null) {
			result = new IntoGivingImpl(programUnit, ctx);

			for (final DivideGivingContext divideGivingContext : ctx.divideGiving()) {
				result.addGiving(divideGivingContext);
			}

			intoGiving = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Remainder addRemainder(final DivideRemainderContext ctx) {
		Remainder result = (Remainder) getASGElement(ctx);

		if (result == null) {
			result = new RemainderImpl(programUnit, ctx);

			// call
			final Call remainderCall = createCall(ctx.identifier());
			result.setRemainderCall(remainderCall);

			remainder = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ByGiving getByGiving() {
		return byGiving;
	}

	@Override
	public DivideType getDivideType() {
		return divideType;
	}

	@Override
	public ValueStmt getDivisorValueStmt() {
		return divisorValueStmt;
	}

	@Override
	public Into getInto() {
		return into;
	}

	@Override
	public IntoGiving getIntoGiving() {
		return intoGiving;
	}

	@Override
	public NotOnSizeErrorPhrase getNotOnSizeErrorPhrase() {
		return notOnSizeErrorPhrase;
	}

	@Override
	public OnSizeErrorPhrase getOnSizeErrorPhrase() {
		return onSizeErrorPhrase;
	}

	@Override
	public Remainder getRemainder() {
		return remainder;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setDivideType(final DivideType divideType) {
		this.divideType = divideType;
	}

	@Override
	public void setDivisorValueStmt(final ValueStmt divisorValueStmt) {
		this.divisorValueStmt = divisorValueStmt;
	}

	@Override
	public void setNotOnSizeErrorPhrase(final NotOnSizeErrorPhrase notOnSizeErrorPhrase) {
		this.notOnSizeErrorPhrase = notOnSizeErrorPhrase;
	}

	@Override
	public void setOnSizeErrorPhrase(final OnSizeErrorPhrase onSizeErrorPhrase) {
		this.onSizeErrorPhrase = onSizeErrorPhrase;
	}

}
