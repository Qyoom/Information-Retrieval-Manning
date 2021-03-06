package manning_ir.ch2

object BiWord_lab_2 {

	// BiWords: (7,8), (58,59), (59,60)
	// Phrase: (58,59,60)
	val term1 = List(4, 7, 23, 27, 58, 99)    //> term1  : List[Int] = List(4, 7, 23, 27, 58, 99)
	val term2 = List(8, 42, 59, 127)          //> term2  : List[Int] = List(8, 42, 59, 127)
	val term3 = List(2, 8, 23, 47, 60, 84, 101)
                                                  //> term3  : List[Int] = List(2, 8, 23, 47, 60, 84, 101)
  val terms = List(term1, term2, term3)           //> terms  : List[List[Int]] = List(List(4, 7, 23, 27, 58, 99), List(8, 42, 59, 
                                                  //| 127), List(2, 8, 23, 47, 60, 84, 101))
	// --------------------------- //
	
	def biWords(terms: List[List[Int]]): List[List[(Int, Int)]] = {
	
		def inter(terms: List[List[Int]], accum: List[List[(Int, Int)]]): List[List[(Int, Int)]] = {
			terms match {
				case term :: Nil => accum
				case term :: xs => {
					val result = for{
						left <- term
						rightIndex = {
							val hit = xs.head.indexOf(left + 1)
							println("left:" + left + " hit:" + hit)
							hit
						}
						if rightIndex > -1
					} yield (left, xs.head(rightIndex))
					inter(xs, accum ++ List(result))
				} // end case term :: xs
			} // end terms match
		} // end inter
		
		inter(terms, List.empty[List[(Int, Int)]])
		
	} // end biWords                          //> biWords: (terms: List[List[Int]])List[List[(Int, Int)]]
	
	val res1 = biWords(terms)                 //> left:4 hit:-1
                                                  //| left:7 hit:0
                                                  //| left:23 hit:-1
                                                  //| left:27 hit:-1
                                                  //| left:58 hit:2
                                                  //| left:99 hit:-1
                                                  //| left:8 hit:-1
                                                  //| left:42 hit:-1
                                                  //| left:59 hit:4
                                                  //| left:127 hit:-1
                                                  //| res1  : List[List[(Int, Int)]] = List(List((7,8), (58,59)), List((59,60)))
	res1                                      //> res0: List[List[(Int, Int)]] = List(List((7,8), (58,59)), List((59,60)))
	
	val result1 = for{
		left <- term1
		right = {
			val hit = term2.indexOf(left + 1)
			println("left:" + left + " hit:" + hit)
			hit
		}
		if right > -1
	} yield (left, term2(right))              //> left:4 hit:-1
                                                  //| left:7 hit:0
                                                  //| left:23 hit:-1
                                                  //| left:27 hit:-1
                                                  //| left:58 hit:2
                                                  //| left:99 hit:-1
                                                  //| result1  : List[(Int, Int)] = List((7,8), (58,59))
  val result2 = for{
		left <- term2
		right = {
			val hit = term3.indexOf(left + 1)
			println("left:" + left + " hit:" + hit)
			hit
		}
		if right > -1
	} yield (left, term3(right))              //> left:8 hit:-1
                                                  //| left:42 hit:-1
                                                  //| left:59 hit:4
                                                  //| left:127 hit:-1
                                                  //| result2  : List[(Int, Int)] = List((59,60))
}
/*






*/