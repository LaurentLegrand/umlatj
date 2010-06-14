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

package org.umlatj.kernel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Association {

	End[] value();

	public @interface End {

		// Class<?> type();

		/**
		 * Role of the end in this association.
		 */
		String value();
	}

	/**
	 * Represents an association between two classes.
	 * 
	 * @author Laurent Legrand
	 * 
	 * @param <O>
	 * @param <T>
	 */
	public static interface Binary<O, T> {

		/**
		 * Create a link between two elements
		 * 
		 * @param owner
		 *            the first end of the link
		 * @param target
		 *            the second end of the link
		 * 
		 * @return <code>true</code> if the link has been created,
		 *         <code>false</code> otherwise.
		 */
		public boolean add(O owner, T target);

		/**
		 * Remove a link between two elements
		 * 
		 * @param owner
		 *            the first end of the link
		 * @param target
		 *            the second end of the link
		 * 
		 * @return <code>true</code> if the link has been removed,
		 *         <code>false</code> otherwise.
		 */
		public boolean remove(O owner, T target);

		/**
		 * Verify if a link exists between two elements
		 * 
		 * @param owner
		 *            the first end of the link
		 * @param target
		 *            the second end of the link
		 * 
		 * @return <code>true</code> if the link exist, <code>false</code>
		 *         otherwise.
		 */
		public boolean exists(O owner, T target);

	}

	/**
	 * Represents a one to many association of a class on itself.
	 * 
	 * The methods are borrowed from <a
	 * href="http://www.w3.org/TR/xpath20/#axes">XPath axes</a>
	 * 
	 * @author Laurent Legrand
	 * 
	 * @param <E>
	 */
	public static interface Hierarchy<E> extends Binary<E, E> {

		/**
		 * The parent axis contains the parent of the context node, if there is
		 * one.
		 * 
		 * @param self
		 * @return
		 */
		public E getParent(E self);

		/**
		 * Return the root of the hierarchy.
		 * 
		 * @param self
		 * @return
		 */
		public E getRoot(E self);

		/**
		 * The ancestor axis contains the ancestors of the context node; the
		 * ancestors of the context node consist of the parent of context node
		 * and the parent's parent and so on; thus, the ancestor axis will
		 * always include the root node, unless the context node is the root
		 * node.
		 * 
		 * @param self
		 * @return
		 */
		public List<E> getAncestors(E self);

		/**
		 * The ancestor-or-self axis contains the context node and the ancestors
		 * of the context node; thus, the ancestor axis will always include the
		 * root node.
		 * 
		 * @param self
		 * @return
		 */
		public List<E> getAncestorsOrSelf(E self);

		/**
		 * The child axis contains the children of the context node.
		 * 
		 * @param self
		 * @return
		 */
		public List<E> getChildren(E self);

		/**
		 * The descendant axis contains the descendants of the context node; a
		 * descendant is a child or a child of a child and so on.
		 * 
		 * @param self
		 * @return
		 */
		public List<E> getDescendants(E self);

		/**
		 * The descendant-or-self axis contains the context node and the
		 * descendants of the context node.
		 * 
		 * @param self
		 * @return
		 */
		public List<E> getDescendantsOrSelf(E self);

		/**
		 * The following axis contains all nodes in the same document as the
		 * context node that are after the context node in document order,
		 * excluding any descendants and excluding attribute nodes and namespace
		 * nodes.
		 * 
		 * @param self
		 * @return
		 */
		public List<E> getFollowings(E self);

		/**
		 * The following-sibling axis contains all the following siblings of the
		 * context node.
		 * 
		 * @param self
		 * @return
		 */
		public List<E> getFollowingSiblings(E self);

		/**
		 * the preceding axis contains all nodes in the same document as the
		 * context node that are before the context node in document order,
		 * excluding any ancestors.
		 * 
		 * @param self
		 * @return
		 */
		public List<E> getPrecedings(E self);

		/**
		 * The preceding-sibling axis contains all the preceding siblings of the
		 * context node.
		 * 
		 * @param self
		 * @return
		 */
		public List<E> getPrecedingSiblings(E self);

	}

	public static interface Link<O, T> {

		public O getOwner();

		public T getTarget();

		public Binary<O, T> getInstance();

	}
}
