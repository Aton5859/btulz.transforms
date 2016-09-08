package org.colorcoding.tools.btulz.test.transformers;

import java.io.File;
import java.util.UUID;

import org.colorcoding.tools.btulz.Environment;
import org.colorcoding.tools.btulz.templates.Parameter;
import org.colorcoding.tools.btulz.transformers.CodeTransformer;

import junit.framework.TestCase;

public class testCodeTransformer extends TestCase {

	private static String domain_file = testXmlTransformer.old_xml_path + File.separator + "domain_models_old.xml";

	public void testEclipseCode() throws Exception {
		CodeTransformer codeTransformer = new CodeTransformer();
		// String tpltFolder =
		// Environment.getResource("code/eclipse/ibas_classic").getPath();
		codeTransformer.setTemplateFolder("eclipse/ibas_classic");
		codeTransformer.setOutputFolder(System.getenv("TEMP"));
		codeTransformer.setGroupId("org.colorcoding");
		codeTransformer.setArtifactId("ibas");
		codeTransformer.setProjectId(UUID.randomUUID().toString());
		codeTransformer.setProjectVersion("0.0.1");
		codeTransformer.setProjectUrl("http://colorcoding.org");
		codeTransformer.addParameters(new Parameter("ibasVersion", "0.1.1"));
		codeTransformer.addParameters(new Parameter("jerseyVersion", "2.22.1"));
		codeTransformer.addDomains(Environment.getWorkingFolder() + domain_file);
		// codeTransformer.addDomain((new testModels()).createDomain());

		codeTransformer.transform();
	}
}