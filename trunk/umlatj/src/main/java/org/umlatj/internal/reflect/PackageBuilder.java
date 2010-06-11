package org.umlatj.internal.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import org.umlatj.internal.kernel.KAssociation;
import org.umlatj.internal.kernel.KClassifier;
import org.umlatj.internal.kernel.KPackage;
import org.umlatj.internal.kernel.KProperty;
import org.umlatj.internal.kernel.association.HierarchyImpl;
import org.umlatj.internal.util.Classes;
import org.umlatj.internal.util.proxy.TypeProxyImpl;
import org.umlatj.kernel.Association;
import org.umlatj.kernel.Package;
import org.umlatj.kernel.Association.Binary;
import org.umlatj.kernel.Association.Hierarchy;

public class PackageBuilder extends Builder<KPackage> {

	public PackageBuilder() {
	}

	@Override
	public boolean accept(Class<?> type) {
		return type.getAnnotation(Package.class) != null;
	}

	@Override
	public KPackage newInstance(Class<?> type) {
		return new KPackage(new TypeProxyImpl(type));
	}

	@Override
	public void build(Class<?> type, KPackage instance) {

		for (Field field : Classes.findFields(type, Association.class)) {
			Association association = field.getAnnotation(Association.class);
			Class<?> fieldType = field.getType();
			System.out.println(field.getType());
			if (fieldType.equals(Binary.class)) {

				Class<?> ownerType = (Class<?>) ((ParameterizedType) field
						.getGenericType()).getActualTypeArguments()[0];

				Class<?> targetType = (Class<?>) ((ParameterizedType) field
						.getGenericType()).getActualTypeArguments()[1];

				KClassifier ownerClassifier = Types.asClassifier(ownerType);
				KClassifier targetClassifier = Types.asClassifier(targetType);

				KProperty end1 = ownerClassifier.getAttribute(association
						.value()[1].value());
				KProperty end2 = targetClassifier.getAttribute(association
						.value()[0].value());

				KAssociation kAssociation = new KAssociation(field
						.getName());
				kAssociation.setOwner(end1);
				kAssociation.setTarget(end2);
				instance.addAssociation(kAssociation);
			} else if (fieldType.equals(Hierarchy.class)) {
				Class<?> ownerType = (Class<?>) ((ParameterizedType) field
						.getGenericType()).getActualTypeArguments()[0];
				KClassifier ownerClassifier = Types.asClassifier(ownerType);
				KClassifier targetClassifier = Types.asClassifier(ownerType);

				KProperty end1 = ownerClassifier.getAttribute(association
						.value()[1].value());
				KProperty end2 = targetClassifier.getAttribute(association
						.value()[0].value());

				HierarchyImpl hierarchyImpl = new HierarchyImpl(field.getName());
				hierarchyImpl.setOwner(end1);
				hierarchyImpl.setTarget(end2);
				instance.addAssociation(hierarchyImpl);
			}
		}
	}

}
