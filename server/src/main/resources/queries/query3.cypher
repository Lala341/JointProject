CALL gds.beta.pipeline.linkPrediction.predict.stream('fullGraph22', {
  modelName: 'lp-pipeline-model-filtered4',
  relationshipTypes: ['HAS_CONDITION'],
  sampleRate:0.9,
  concurrency: 1,
  randomSeed : 33
})
 YIELD node1, node2, probability
 where  ( gds.util.asNode(node2).condition is not null and gds.util.asNode(node1).id = ID)
 RETURN gds.util.asNode(node2).condition AS condition, gds.util.asNode(node1).id AS person, probability
 ORDER BY probability DESC ;