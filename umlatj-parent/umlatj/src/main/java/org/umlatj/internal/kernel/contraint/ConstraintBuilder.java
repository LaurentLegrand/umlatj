package org.umlatj.internal.kernel.contraint;

import org.umlatj.internal.kernel.KConstraint;

public abstract class ConstraintBuilder {

	public KConstraint build(String source) {
		return new TrueConstraint();
//		Token token = Boole.parse(source);
//		return this.fromToken(token);
	}

//	KConstraint fromToken(Token token) {
//		if (token instanceof Literal) {
//			return fromLiteral((Literal) token);
//		}
//		if (token instanceof And) {
//			return fromAnd((And) token);
//		}
//		if (token instanceof Or) {
//			return fromOr((Or) token);
//		}
//		if (token instanceof Not) {
//			return fromNot((Not) token);
//		}
//		throw new IllegalStateException("token not known: " + token);
//	}
//
//	protected abstract KConstraint fromLiteral(Literal literal);
//
//	KConstraint fromNot(Not not) {
//		return new NotConstraint(this.fromToken(not.getToken()));
//	}
//
//	KConstraint fromAnd(And and) {
//		return new AndConstraint(this.fromToken(and.getLeft()), this.fromToken(and.getRight()));
//	}
//
//	KConstraint fromOr(Or or) {
//		return new OrConstraint(this.fromToken(or.getLeft()), this.fromToken(or.getRight()));
//	}

}
