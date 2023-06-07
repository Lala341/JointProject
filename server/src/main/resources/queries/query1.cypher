MATCH (h:HealthCondition)
WITH COLLECT(DISTINCT h.condition) AS uniqueValues
MATCH (h:HealthCondition)
SET h.oneHotEncoding = gds.alpha.ml.oneHotEncoding(uniqueValues, [h.condition]);
CALL gds.graph.project(
  'fullGraph22',
  ['Person', 'HealthCondition'],
  {
    HAS_CONDITION:{
    orientation: 'UNDIRECTED'
  }
  },
  { nodeProperties: {
      oneHotEncoding: {defaultValue: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}
    }
  }
);
CALL gds.beta.pipeline.linkPrediction.create('pipe14');
CALL gds.beta.pipeline.linkPrediction.addNodeProperty(
  'pipe14',
  'beta.hashgnn',
   {
     mutateProperty:'embedding',
    heterogeneous: true,
    iterations: 2,
    embeddingDensity: 4,
    binarizeFeatures: {dimension: 6, threshold: 0.2},
    featureProperties: ['oneHotEncoding'],
    randomSeed: 42
  }
);
CALL gds.beta.pipeline.linkPrediction.configureSplit('pipe14', {
   testFraction: 0.25,
  trainFraction: 0.6,
  validationFolds: 3
});
CALL gds.alpha.pipeline.linkPrediction.configureAutoTuning('pipe14', {
  maxTrials: 2
}) YIELD autoTuningConfig;
CALL gds.alpha.pipeline.linkPrediction.addRandomForest('pipe14', {numberOfDecisionTrees: 10})
YIELD parameterSpace;
CALL gds.beta.pipeline.linkPrediction.addFeature('pipe14', 'hadamard', {
  nodeProperties: ['embedding', 'oneHotEncoding']
});
CALL gds.beta.pipeline.linkPrediction.train('fullGraph22', {
  pipeline: 'pipe14',
  modelName: 'lp-pipeline-model-filtered4',
  metrics: ['AUCPR', 'OUT_OF_BAG_ERROR'],
  sourceNodeLabel: 'Person',
  targetNodeLabel: 'HealthCondition',
  targetRelationshipType: 'HAS_CONDITION',
  randomSeed: 12
}) YIELD modelInfo, modelSelectionStats
RETURN
  modelInfo.bestParameters AS winningModel,
  modelInfo.metrics.AUCPR.train.avg AS avgTrainScore,
  modelInfo.metrics.AUCPR.outerTrain AS outerTrainScore,
  modelInfo.metrics.AUCPR.test AS testScore,
  [cand IN modelSelectionStats.modelCandidates | cand.metrics.AUCPR.validation.avg] AS validationScores;

