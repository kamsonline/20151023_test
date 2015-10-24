/**
 * Calculate the number of lines in Java code excluding comments and empty lines.
 * Similar to http://codekata.com/kata/kata13-counting-code-lines/
 **/
(function () {
  'use strict';

  var tokenHasNewLine = function (token) {
    return !token || token.indexOf('\n') > -1;
  };

  var processSource = function (source, hasMoreToProcess) {

    var patternList = [
      {type: 'SPACE_REGEX', value: /^\s+/},
      {type: 'COMMENT_REGEX', value: /^\/\/.*/},
      {type: 'GROUP_COMMENT_REGEX', value: /^\/\*((?!\*\/)[\s\S])*\*\/\s*/},
      {type: 'STRING_REGEX', value: /^"[^"\n\\]*(\\.[^"\n\\]*)*"/},
      {type: 'CODE_REGEX', value: /^((?!\/\*)[^"\n])+\s*/}
    ];

    var count = 0, sourceMatched, patternMatched, hasMoreToProcess;

    // Loop through the list of patterns and identify the first matching pattern from source
    for (var patternIndex in patternList) {
      var pattern = patternList[patternIndex];
      var matched = source.match(pattern.value);
      if (matched) {
        sourceMatched = matched[0];
        source = source.substr(sourceMatched.length);
        patternMatched = pattern;
        break;
      }

    }

    if (patternMatched) {

      // Once a pattern has been found, depending on the type of pattern and the left over source,
      // identify whether there is more pattern to process in the same line.
      if (patternMatched.type === 'STRING_REGEX' ||
        patternMatched.type === 'CODE_REGEX') {

        // For program code or strings, check if it has end of line in the matched source.
        // If there is end of Line, increment the count and proceed for next iteration.
        if (tokenHasNewLine(sourceMatched)) {
          hasMoreToProcess = false;
          count++;
        }
        else {
          // If there is no end of line, then there is likely more patterns to match in the same line.
          hasMoreToProcess = true;
        }
      }
      else if (hasMoreToProcess) {
        // If the matched source had more to process and the next match is one of the comments,
        // then increment the count and continue on the next interation.
        if ((patternMatched.type === 'GROUP_COMMENT_REGEX' ||
          patternMatched.type === 'COMMENT_REGEX' ||
          patternMatched.type === 'SPACE_REGEX') &&
          (tokenHasNewLine(sourceMatched) || !source)) {
          count++;
        }
        hasMoreToProcess = false;
      }
    }
    else {
      throw new Error("Unable to parse the code fragment");
    }

    // If there is no more source to process but marked as more to process,
    // then increment the count. This will be the last piece of code counted.
    if (!source) {
      if (hasMoreToProcess) {
        count++;
      }
    }
    else {
      // If there is more source after the previous pattern match,
      // Then continue processing recursively and add the returned count to the existing.
      count += processSource(source, hasMoreToProcess);
    }

    return count;
  };

  var getLineCount = function (inputSource) {
    return processSource(inputSource, false);
  };

  return {
    getLineCount: getLineCount
  };

})();