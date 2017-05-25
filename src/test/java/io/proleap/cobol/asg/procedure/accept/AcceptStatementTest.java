package io.proleap.cobol.asg.procedure.accept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptFromDateStatement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptFromEscapeKeyStatement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptFromMnemonicStatement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptMessageCountStatement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AcceptStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/accept/AcceptStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("AcceptStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(4, procedureDivision.getStatements().size());

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());
			assertEquals(AcceptStatement.AcceptType.DATE, acceptStatement.getAcceptType());

			assertNotNull(acceptStatement.getAcceptCall());
			assertEquals(Call.CallType.UNDEFINED_CALL, acceptStatement.getAcceptCall().getCallType());

			{
				final AcceptFromDateStatement acceptFromDate = acceptStatement.getAcceptFromDateStatement();
				assertNotNull(acceptFromDate);
				assertEquals(AcceptFromDateStatement.DateType.TODAYS_NAME, acceptFromDate.getDateType());
			}
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());
			assertEquals(AcceptStatement.AcceptType.MNEMONIC, acceptStatement.getAcceptType());

			assertNotNull(acceptStatement.getAcceptCall());
			assertEquals(Call.CallType.UNDEFINED_CALL, acceptStatement.getAcceptCall().getCallType());

			{
				final AcceptFromMnemonicStatement acceptFromMnemonic = acceptStatement.getAcceptFromMnemonicStatement();
				assertNotNull(acceptFromMnemonic);
				assertNotNull(acceptFromMnemonic.getMnemonicCall());
				assertEquals(Call.CallType.MNEMONIC_CALL, acceptFromMnemonic.getMnemonicCall().getCallType());
			}
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(2);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());
			assertEquals(AcceptStatement.AcceptType.MESSAGE_COUNT, acceptStatement.getAcceptType());

			assertNotNull(acceptStatement.getAcceptCall());
			assertEquals(Call.CallType.UNDEFINED_CALL, acceptStatement.getAcceptCall().getCallType());

			{
				final AcceptMessageCountStatement acceptMessageCount = acceptStatement.getAcceptMessageCountStatement();
				assertNotNull(acceptMessageCount);
			}
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(3);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());
			assertEquals(AcceptStatement.AcceptType.FROM_ESCAPE_KEY, acceptStatement.getAcceptType());

			assertNotNull(acceptStatement.getAcceptCall());

			{
				final AcceptFromEscapeKeyStatement acceptFromEscapeKey = acceptStatement.getAcceptFromEscapeKeyStatement();
				assertNotNull(acceptFromEscapeKey);
			}
		}
	}
}
