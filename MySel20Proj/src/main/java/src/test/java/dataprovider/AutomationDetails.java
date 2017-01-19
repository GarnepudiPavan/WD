package src.test.java.dataprovider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 * http://www.java2novice.com/java-annotations/custom-metadata/
 */
/**
 * Annotations are created using @ sign, followed by keyword "interface", and annotation name as shown below.
Attributes needed are declared as methods. Below two attributes are defined called Owner and Manager. There's no need to provide implementation for these members.
All annotations extends java.lang.annotation.Annotation interface. Annotations cannot include any extends clause.

- See more at: http://www.java2novice.com/java-annotations/custom-metadata/#sthash.5Vz50aqa.dpuf
 * @author Pavan
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutomationDetails {

	public enum TestGroups {
		Regression, BVT, SmokeTest
	}

	TestGroups TestGroups() default TestGroups.Regression;

	public enum Contacts {
		PavanG
	}

	// public Contacts Contacts();// default Contacts.PavanG

	public Contacts Owner(); // Attribute in Annotation that take value declared
								// in return type

	public String Manager();

	// public String Roles();
}