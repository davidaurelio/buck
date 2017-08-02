/*
 * Copyright 2016-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.cxx.platform;

import com.facebook.buck.rules.Tool;
import com.facebook.buck.rules.ToolProvider;
import java.nio.file.Path;
import java.util.Optional;

public class PreprocessorProvider extends CxxToolProvider<Preprocessor> {

  public PreprocessorProvider(Path path, Optional<Type> type) {
    super(path, type);
  }

  public PreprocessorProvider(ToolProvider toolProvider, Type type) {
    super(toolProvider, type);
  }

  @Override
  protected Preprocessor build(Type type, Tool tool) {
    switch (type) {
      case CLANG:
        return new ClangPreprocessor(tool);
      case GCC:
        return new GccPreprocessor(tool);
      case WINDOWS:
        return new WindowsPreprocessor(tool);
        // $CASES-OMITTED$
      default:
    }
    throw new IllegalStateException("cannot get preprocessor for type " + type);
  }
}