/*
* Copyright (c) 2015-present Facebook.
* All rights reserved.
*/

package endtoend.java.tracing;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.matchers.ResultContainsExactly.containsExactly;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import utils.InferException;
import utils.InferResults;

public class ClassCastExceptionTest {

  public static final String SOURCE_FILE =
      "infer/tests/codetoanalyze/java/tracing/ClassCastExceptionExample.java";

  public static final String CLASS_CAST_EXCEPTION =
      "java.lang.ClassCastException";

  private static InferResults inferResults;

  @BeforeClass
  public static void loadResults() throws InterruptedException, IOException {
    inferResults = InferResults.loadTracingResults(
        ClassCastExceptionTest.class,
        SOURCE_FILE);
  }

  @Test
  public void whenEradicateRunsOnConstructorThenFieldNotInitializedIsFound()
      throws IOException, InterruptedException, InferException {
    String[] methods = {"foo", "bar"};
    assertThat(
        "Results should contain " + CLASS_CAST_EXCEPTION,
        inferResults,
        containsExactly(
            CLASS_CAST_EXCEPTION,
            SOURCE_FILE,
            methods
        )
    );
  }


}