(function () {
  'use strict';

  // Arbitary number used here as the pool size. Please increase this pool size when there is plenty of words added to the bloom filter.
  var BLOOM_SIZE = 512;
  var bloomArray = new Array(BLOOM_SIZE);

  // NOTE: the below 3 hash functions are referenced from
  // http://erlycoder.com/49/javascript-hash-functions-to-convert-string-into-integer-hash-
  var calculateHash = function (stringInput) {
    var hash = 0, i, chr, len;
    if (stringInput.length == 0) return hash;
    for (i = 0, len = stringInput.length; i < len; i++) {
      chr = stringInput.charCodeAt(i);
      hash = ((hash << 5) - hash) + chr;
      hash |= 0; // Convert to 32bit integer
    }
    return convertHashToWithinPoolSize(Math.abs(hash));
  };

  var djb2Code = function (str) {
    var hash = 5381, i, chr;
    for (i = 0; i < str.length; i++) {
      chr = str.charCodeAt(i);
      hash = ((hash << 5) + hash) + chr;
      /* hash * 33 + c */
    }
    return convertHashToWithinPoolSize(Math.abs(hash));
  };

  var sdbmCode = function (str) {
    var hash = 0, i, chr;
    for (i = 0; i < str.length; i++) {
      chr = str.charCodeAt(i);
      hash = chr + (hash << 6) + (hash << 16) - hash;
    }
    return convertHashToWithinPoolSize(Math.abs(hash));
  };

  // Convenient method to convert the generated hash value to be within the pool size (BLOOM_SIZE)
  var convertHashToWithinPoolSize = function (hash) {
    return hash % BLOOM_SIZE;
  };

  // Add given string to the bloom filter
  var addToBloom = function (stringInput) {
    var hash = calculateHash(stringInput);
    var djb2Hash = djb2Code(stringInput);
    var sdbmHash = sdbmCode(stringInput);

    bloomArray[hash] = true;
    bloomArray[djb2Hash] = true;
    bloomArray[sdbmHash] = true;
  };

  // Check whether the given string may or doesn't exist in the bloom filter.
  var checkInBloom = function (stringInput) {
    var hash = calculateHash(stringInput);
    var djb2Hash = djb2Code(stringInput);
    var sdbmHash = sdbmCode(stringInput);

    return !!bloomArray[hash] && !!bloomArray[djb2Hash] && !!bloomArray[sdbmHash];
  };

  return {
    addToBloom: addToBloom,
    checkInBloom: checkInBloom
  };

})();