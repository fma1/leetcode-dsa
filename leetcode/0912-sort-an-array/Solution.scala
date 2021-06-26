object Solution {
    def countingSort(array: Array[Int], exponent: Int): Unit = {
        val base = 10
        val output = Array.fill(array.length)(0)
        var counts = Array.fill(base)(0)

        val getDigit = (num: Int) => num / exponent % base

        // Loop through items, incrementing counter for each
        array.foreach(num => {
            val digit = getDigit(num)
            counts(digit) = counts(digit) + 1
        })

        // Loop through counts table to get summation of counts
        counts = counts.foldLeft(List[Int]())((acc, curr) => {
            acc match {
                case prevSum :: _ => (prevSum + curr) :: acc
                case Nil => curr :: acc
            }
        }).reverse.toArray

        /*
         * Loop through items in reverse
         * For each item
         *   - Use key/num to index the counts table
         *   - Decrement the count in counts table
         *   - Use decremented count to copy items into new sorted array
         */
        array.reverse.foreach(num => {
            val digit = getDigit(num)
            counts(digit) = counts(digit) - 1
            output(counts(digit)) = num
        })

        Array.copy(output, 0, array, 0, array.length)
    }

    def radixSort(array: Array[Int]): Unit = {
        val max = array.max
        var exponent = 1

        while (max / exponent > 0) {
            countingSort(array, exponent)
            exponent = exponent * 10
        }
    }

    def sortArray(nums: Array[Int]): Array[Int] = {
        if (nums == null || nums.length == 0) nums
        
        // Negative number check
        if (nums.min < 0) {
            val diff = 0 - nums.min
            val newNums = nums.map(num => num + diff)
            radixSort(newNums)
            newNums.map(num => num - diff)
        } else {
            radixSort(nums)
            nums
        }
    }
}
