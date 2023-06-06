// Prediction graph
CALL gds.graph.project(
'predictionGraph',
['Person', 'Answer', 'HealthQuestion', 'HealthCondition'],
{
  HAS_ANSWER: {
                orientation: 'UNDIRECTED'
              },
  ANSWERED_HE: {
                orientation: 'UNDIRECTED'
              },
  HAS_CONDITION: {
                orientation: 'UNDIRECTED'
              },
  RELATED_TO: {
                orientation: 'UNDIRECTED'
              }
}
);

// Configure pipeline
CALL gds.beta.pipeline.linkPrediction.create('lp-pipeline');

CALL gds.beta.pipeline.linkPrediction.addNodeProperty('lp-pipeline',
'fastRP', {
  mutateProperty: 'embedding',
  embeddingDimension: 56,
  randomSeed: 42
}) YIELD nodePropertySteps;

CALL gds.beta.pipeline.linkPrediction.addFeature('lp-pipeline',
'HADAMARD', {
  nodeProperties: ['embedding']
}) YIELD featureSteps;

CALL gds.beta.pipeline.linkPrediction.configureSplit('lp-pipeline', {
  testFraction: 0.1,
  trainFraction: 0.1,
  validationFolds: 3
}) YIELD splitConfig;

CALL gds.beta.pipeline.linkPrediction.addLogisticRegression('lp-pipeline')
YIELD parameterSpace;

CALL gds.beta.pipeline.linkPrediction.train.estimate('predictionGraph', {
  pipeline: 'lp-pipeline',
  modelName: 'lp-pipeline-model',
  targetRelationshipType: 'HAS_CONDITION' // Specify the appropriate relationship type
}) YIELD requiredMemory;

CALL gds.beta.pipeline.linkPrediction.train('predictionGraph', {
  pipeline: 'lp-pipeline',
  modelName: 'lp-pipeline-model',
  metrics: ['AUCPR'],
  randomSeed: 42,
  targetRelationshipType: 'HAS_CONDITION' // Specify the appropriate relationship type
}) YIELD modelInfo, modelSelectionStats
RETURN
  modelInfo.bestParameters AS winningModel,
  modelInfo.metrics.AUCPR.train.avg AS avgTrainScore,
  modelInfo.metrics.AUCPR.outerTrain AS outerTrainScore,
  modelInfo.metrics.AUCPR.test AS testScore,
  [candidate IN modelSelectionStats.modelCandidates | candidate.metrics.AUCPR.validation.avg] AS validationScores;

// Predict
CALL gds.beta.pipeline.linkPrediction.predict.mutate('predictionGraph', {
  modelName: 'lp-pipeline-model',
  mutateRelationshipType: 'PREDICTED_CONDITION',
  topN: 10,
  threshold: 0.5
}) YIELD relationshipsWritten, samplingStats;
