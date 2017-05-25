/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.multiply.impl;

import io.proleap.cobol.Cobol85Parser.MultiplyGivingContext;
import io.proleap.cobol.Cobol85Parser.MultiplyGivingResultContext;
import io.proleap.cobol.Cobol85Parser.MultiplyRegularContext;
import io.proleap.cobol.Cobol85Parser.MultiplyRegularOperandContext;
import io.proleap.cobol.Cobol85Parser.MultiplyStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.Giving;
import io.proleap.cobol.asg.metamodel.procedure.multiply.MultiplyStatement;
import io.proleap.cobol.asg.metamodel.procedure.multiply.Regular;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class MultiplyStatementImpl extends StatementImpl implements MultiplyStatement {

	protected final MultiplyStatementContext ctx;

	protected Giving giving;

	protected MultiplyType multiplyType;

	protected NotOnSizeErrorPhrase notOnSizeErrorPhrase;

	protected OnSizeErrorPhrase onSizeErrorPhrase;

	protected ValueStmt operandValueStmt;

	protected Regular regular;

	protected final StatementType statementType = StatementTypeEnum.MULTIPLY;

	public MultiplyStatementImpl(final ProgramUnit programUnit, final Scope scope, final MultiplyStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public Giving addGiving(final MultiplyGivingContext ctx) {
		Giving result = (Giving) getASGElement(ctx);

		if (result == null) {
			result = new GivingImpl(programUnit, ctx);

			// giving operand
			result.addOperand(ctx.multiplyGivingOperand());

			// giving results
			for (final MultiplyGivingResultContext multiplyGivingResultContext : ctx.multiplyGivingResult()) {
				result.addResult(multiplyGivingResultContext);
			}

			giving = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Regular addRegular(final MultiplyRegularContext ctx) {
		Regular result = (Regular) getASGElement(ctx);

		if (result == null) {
			result = new RegularImpl(programUnit, ctx);

			for (final MultiplyRegularOperandContext multiplyRegularOperandContext : ctx.multiplyRegularOperand()) {
				result.addOperand(multiplyRegularOperandContext);
			}

			regular = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Giving getGiving() {
		return giving;
	}

	@Override
	public MultiplyType getMultiplyType() {
		return multiplyType;
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
	public ValueStmt getOperandValueStmt() {
		return operandValueStmt;
	}

	@Override
	public Regular getRegular() {
		return regular;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

	@Override
	public void setMultiplyType(final MultiplyType multiplyType) {
		this.multiplyType = multiplyType;
	}

	@Override
	public void setNotOnSizeErrorPhrase(final NotOnSizeErrorPhrase notOnSizeErrorPhrase) {
		this.notOnSizeErrorPhrase = notOnSizeErrorPhrase;
	}

	@Override
	public void setOnSizeErrorPhrase(final OnSizeErrorPhrase onSizeErrorPhrase) {
		this.onSizeErrorPhrase = onSizeErrorPhrase;
	}

	@Override
	public void setOperandValueStmt(final ValueStmt operandValueStmt) {
		this.operandValueStmt = operandValueStmt;
	}

}
