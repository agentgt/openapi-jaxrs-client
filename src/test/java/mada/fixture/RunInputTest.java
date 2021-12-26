package mada.fixture;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.mada.jaxrs.Generator;

/**
 * Runs end-to-end test from specified directory.
 * 
 * The input is the OpenApi file (openapi.yaml) and
 * the test options (test.props).
 * 
 * The expected output is the api and dto folders (both
 * optional depending on the test options).
 * 
 * The test is executed by generating code from the test
 * input and comparing it to the expected output.
 * 
 * If the generated code does not match the expected output,
 * a diff is run on the folders to help diagnostics.
 */
public class RunInputTest {
	private static final Logger logger = LoggerFactory.getLogger(RunInputTest.class);
	
	public void runTest(Path testDir, Path outputDir) throws IOException {
		String testName = testDir.getFileName().toString();
		Path input = testDir.resolve("openapi.yaml");

		Properties testOptions = readTestOptions(testDir);
		
		DirectoryDeleter.delete(outputDir);
		
		new Generator().generate(input, testOptions, outputDir);

		logger.info("Generator returned");
		logger.info("Expected dir {}", input);
		logger.info("Actual dir   {}", outputDir);

		deleteUnwantedOutput(testOptions, outputDir);

		OutputDiff differ = new OutputDiff(testName);
		if (!Boolean.parseBoolean(testOptions.getProperty("testSkipApiComparison"))) {
			logger.info("Comparing APIs");
			differ.compareDirs(outputDir.resolve("api"), testDir.resolve("api"));
		}
		if (!Boolean.parseBoolean(testOptions.getProperty("testSkipDtoComparison"))) {
			logger.info("Comparing DTOs");
			differ.compareDirs(outputDir.resolve("dto"), testDir.resolve("dto"));
		}
	}

	private void deleteUnwantedOutput(Properties testOptions, Path output) throws IOException {
		String deleteFiles = testOptions.getProperty("testDeleteFiles");
		if (deleteFiles != null) {
			for (String p : deleteFiles.split(",")) {
				Path f = output.resolve(p.trim());
				logger.info("DELETE test file {}", f);
				Files.delete(f);
			}
		}
	}
	
	private Properties readTestOptions(Path testDir) throws IOException {
		var props = new Properties();
		Path optionsInput = testDir.resolve("test.properties");
		
		if (Files.exists(optionsInput)) {
			try (Reader r = Files.newBufferedReader(optionsInput)) {
				props.load(r);
			}
		}
		
		return props;
	}
	
}
