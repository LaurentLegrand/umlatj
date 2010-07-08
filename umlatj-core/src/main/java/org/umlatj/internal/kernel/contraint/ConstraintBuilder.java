//
// uml@J, UML annotations for Java
//
// Copyright (C) 2010 Laurent Legrand or third-party contributors as
// indicated by the @author tags or express copyright attribution
// statements applied by the authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
//

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
