/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.validator.test.internal.engine.customerror;

import static org.hibernate.validator.testutil.ConstraintViolationAssert.assertThat;
import static org.hibernate.validator.testutil.ConstraintViolationAssert.violationOf;
import static org.hibernate.validator.testutils.ValidatorUtil.getValidator;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.hibernate.validator.testutil.TestForIssue;

import org.testng.annotations.Test;

/**
 * @author Hardy Ferentschik
 */
public class CustomErrorMessageTest {
	/**
	 * HV-297
	 *
	 * @throws Exception in case the test fails.
	 */
	@Test
	@TestForIssue(jiraKey = "HV-297")
	public void testReportAsSingleViolationDoesNotInfluenceCustomError() throws Exception {
		Validator validator = getValidator();
		DummyTestClass dummyTestClass = new DummyTestClass();

		Set<ConstraintViolation<DummyTestClass>> constraintViolations = validator.validate( dummyTestClass );
		assertThat( constraintViolations ).containsOnlyViolations(
				violationOf( IsValid.class ).withMessage( IsValidValidator.message )
		);
	}
}
