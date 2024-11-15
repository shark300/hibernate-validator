/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Red Hat Inc. and Hibernate Authors
 */

package org.hibernate.validator.test.constraints.boolcomposition.localconstrval;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hibernate.validator.constraints.CompositionType.OR;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.ConstraintComposition;

/**
 * Test constraint for HV-390.
 *
 * Uses the same constraintValidator as SmallString, but does not negate the result.
 * Hence it tests that a string is long at least 9 characters (or that it matches the given pattern).
 *
 * @author Federico Mancini
 * @author Dag Hovland
 */

@ConstraintComposition(OR)
@Pattern(regexp = "W{4}")
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { LongStringValidator.class })
public @interface PatternOrLong {
	String message() default "Both Pattern and LongString failed";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
