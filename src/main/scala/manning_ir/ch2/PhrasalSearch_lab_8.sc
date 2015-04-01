package manning_ir.ch2
 
import manning_ir.ch2.PhrasalSearchLib_8._
import util.math.Rounding_lab_1._

import scala.io.Source
import scala.collection.mutable.Map
import scala.collection.mutable.SortedSet
import scala.math.log10
 
/**
 * Exercise in phrasal search. Not using POS or stop words.
 * Doing lowercasing of index and query.
 * Using Term Frequency for ranking. Not using IDF because collection is too small.
 * Some open questions:
 * * Whether to use a sliding window strategy (on the phrase query)
 * * Basis for phrase retreival ranking
 * * Whether to use a proximity parameter on biword pairs (affects ranking)
 * * How to account for repeat terms in query
 */
object PhrasalSearch_lab_8 {
  	// Document path
	val root_path = "/Users/hieronymus/Development/Workspace_BAK/Intro IR Manning/"
                                                  //> root_path  : String = /Users/hieronymus/Development/Workspace_BAK/Intro IR M
                                                  //| anning/
  val doc_path = "src/main/resources/shakespeare/"//> doc_path  : String = src/main/resources/shakespeare/
	
	val docList = List[Document](
	  Document(IDGenerator.next, "Antony_and_Cleopatra.txt", "William Shakespear"),
	 	Document(IDGenerator.next, "Hamlet.txt", "William Shakespear"),
	 	Document(IDGenerator.next, "Julius_Caesar.txt", "William Shakespear"),
	 	Document(IDGenerator.next, "Macbeth.txt", "William Shakespear"),
	 	Document(IDGenerator.next, "Othello.txt", "William Shakespear"),
	 	Document(IDGenerator.next, "The_Tempest.txt", "William Shakespear")
  )                                               //> docList  : List[manning_ir.ch2.PhrasalSearchLib_8.Document] = List(Document
                                                  //| (100,Antony_and_Cleopatra.txt,William Shakespear,0,TreeSet()), Document(101
                                                  //| ,Hamlet.txt,William Shakespear,0,TreeSet()), Document(102,Julius_Caesar.txt
                                                  //| ,William Shakespear,0,TreeSet()), Document(103,Macbeth.txt,William Shakespe
                                                  //| ar,0,TreeSet()), Document(104,Othello.txt,William Shakespear,0,TreeSet()), 
                                                  //| Document(105,The_Tempest.txt,William Shakespear,0,TreeSet()))
  
  // Put docs in Catalog (i.e. Map)
  docList.foreach(doc => docCatalog += (doc.id -> doc))
  
  // ------ Indexing ------------------- //
  
  // partial setup with doc paths
  val index = indexDocument(root_path + doc_path)_//> index  : manning_ir.ch2.PhrasalSearchLib_8.Document => Unit = <function1>
  
  // Initiate indexing
  docList foreach index
 
  	// ------ Logging indexing results ------ //
	
	dictionary.keys.size                      //> res0: Int = 12113
	
	docList foreach(doc => {
		println("------------------")
		println(doc.name)
		println("Unique terms: " + doc.tokensUnique.size)
		println("Word count: " + doc.tokensTotal)
	})                                        //> ------------------
                                                  //| Antony_and_Cleopatra.txt
                                                  //| Unique terms: 4218
                                                  //| Word count: 27137
                                                  //| ------------------
                                                  //| Hamlet.txt
                                                  //| Unique terms: 5142
                                                  //| Word count: 32320
                                                  //| ------------------
                                                  //| Julius_Caesar.txt
                                                  //| Unique terms: 3123
                                                  //| Word count: 20928
                                                  //| ------------------
                                                  //| Macbeth.txt
                                                  //| Unique terms: 3596
                                                  //| Word count: 18314
                                                  //| ------------------
                                                  //| Othello.txt
                                                  //| Unique terms: 4164
                                                  //| Word count: 28026
                                                  //| ------------------
                                                  //| The_Tempest.txt
                                                  //| Unique terms: 3423
                                                  //| Word count: 17468
  
	// ----- Phrasal Querying ------------//

	val query = phrasalQuery(5)_ // set rank rounding
                                                  //> query  : String => List[List[manning_ir.ch2.PhrasalSearchLib_8.QueryResult]
                                                  //| ] = <function1>
	val queryResults = query("And, in conclusion, Elvis")
                                                  //> and
                                                  //| in
                                                  //| conclusion
                                                  //| elvis
                                                  //| ---------------------
                                                  //| queryResults  : List[List[manning_ir.ch2.PhrasalSearchLib_8.QueryResult]] =
                                                  //|  List(List(QueryResult(100,and,0.02469), QueryResult(104,and,0.02822), Quer
                                                  //| yResult(105,and,0.02943), QueryResult(101,and,0.0298), QueryResult(102,and,
                                                  //| 0.03039), QueryResult(103,and,0.03085)), List(QueryResult(105,in,0.00922), 
                                                  //| QueryResult(100,in,0.00973), QueryResult(102,in,0.0108), QueryResult(103,in
                                                  //| ,0.01119), QueryResult(104,in,0.01188), QueryResult(101,in,0.01346)), List(
                                                  //| QueryResult(100,conclusion,4.0E-5), QueryResult(103,conclusion,5.0E-5), Que
                                                  //| ryResult(104,conclusion,1.4E-4)))
	
	queryResults foreach(
		queryResults => {
			println("----------------------------------")
			queryResults foreach println
		}
	)                                         //> ----------------------------------
                                                  //| QueryResult(100,and,0.02469)
                                                  //| QueryResult(104,and,0.02822)
                                                  //| QueryResult(105,and,0.02943)
                                                  //| QueryResult(101,and,0.0298)
                                                  //| QueryResult(102,and,0.03039)
                                                  //| QueryResult(103,and,0.03085)
                                                  //| ----------------------------------
                                                  //| QueryResult(105,in,0.00922)
                                                  //| QueryResult(100,in,0.00973)
                                                  //| QueryResult(102,in,0.0108)
                                                  //| QueryResult(103,in,0.01119)
                                                  //| QueryResult(104,in,0.01188)
                                                  //| QueryResult(101,in,0.01346)
                                                  //| ----------------------------------
                                                  //| QueryResult(100,conclusion,4.0E-5)
                                                  //| QueryResult(103,conclusion,5.0E-5)
                                                  //| QueryResult(104,conclusion,1.4E-4)
}
/*

 

*/