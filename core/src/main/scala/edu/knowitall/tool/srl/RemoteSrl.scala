package edu.knowitall.tool
package srl

import edu.knowitall.tool.parse.graph._
import edu.knowitall.tool.postag.PostaggedToken

class RemoteSrl(val urlString: String) extends Srl with Remote {
  def apply(dgraph: DependencyGraph) = {
    val response = this.post(DependencyGraph.multilineStringFormat.write(dgraph))
    if (response.isEmpty) Seq.empty
    else {
      response.split("\\n").map(Frame.deserialize(dgraph))(scala.collection.breakOut)
    }
  }
}
