CALL gds.beta.pipeline.linkPrediction.predict.stream('fullGraph22', {
  modelName: 'lp-pipeline-model-filtered4',
  relationshipTypes: ['HAS_CONDITION'],
  topN: 5000,
  threshold: 0.0,
  concurrency: 1,
  randomSeed: 42
})
 YIELD node1, node2, probability
 where  ( gds.util.asNode(node1).condition is not null and gds.util.asNode(node2).id = "109298.0") 
 RETURN gds.util.asNode(node1).condition AS person1, gds.util.asNode(node2).id AS person2, probability
 ORDER BY probability DESC