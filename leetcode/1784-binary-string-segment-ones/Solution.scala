object Solution {
  def checkOnesSegment(s: String): Boolean = {
    if (s == null || s.length == 0) {
      false
    } else {
      if (s.count(_ == '1') == 1) {
        true
      } else {
        var i = 0
        var segmentCount = 0
        var startIdx = -1
        while (i < s.length) {
          if (s.charAt(i) == '1') {
            if (startIdx == -1) {
              startIdx = i
            }
          } else {
            if (startIdx != -1) {
              segmentCount = segmentCount + 1
              startIdx = -1
            }
          }
          i = i + 1
        }
        if (startIdx != -1) {
          segmentCount = segmentCount + 1
          startIdx = -1
        }

        segmentCount == 1
      }
    }
  }
}
