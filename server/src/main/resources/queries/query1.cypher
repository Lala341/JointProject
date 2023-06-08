MATCH (h:HealthCondition)
WITH COLLECT(DISTINCT h.condition) AS uniqueValues
MATCH (h:HealthCondition)
SET h.OHECondition = gds.alpha.ml.oneHotEncoding(uniqueValues, [h.condition]);
MATCH (h:Demographics)
WITH COLLECT(DISTINCT h.country) AS uniqueValues
MATCH (h:Demographics)
SET h.OHECountry = gds.alpha.ml.oneHotEncoding(uniqueValues, [h.country]);
MATCH (h:Demographics)
WITH COLLECT(DISTINCT h.gender) AS uniqueValues
MATCH (h:Demographics)
SET h.OHEGender = gds.alpha.ml.oneHotEncoding(uniqueValues, [h.gender]);
MATCH (h:BodyMeasures)
WITH COLLECT(DISTINCT h.date) AS uniqueValues
MATCH (h:BodyMeasures)
SET h.OHEdatebody = gds.alpha.ml.oneHotEncoding(uniqueValues, [h.date]);
MATCH (h:PhysicalActivity)
WITH COLLECT(DISTINCT h.date) AS uniqueValues
MATCH (h:PhysicalActivity)
SET h.OHEdatephy = gds.alpha.ml.oneHotEncoding(uniqueValues, [h.date]);

MATCH (h:DietBehaviorQuestion)
WITH COLLECT(DISTINCT h.id) AS uniqueValues
MATCH (h:DietBehaviorQuestion)
SET h.OHEDietQuestion = gds.alpha.ml.oneHotEncoding(uniqueValues, [h.id]);
MATCH (h:HabitQuestion)
WITH COLLECT(DISTINCT h.id) AS uniqueValues
MATCH (h:HabitQuestion)
SET h.OHEHabitQuestion = gds.alpha.ml.oneHotEncoding(uniqueValues, [h.id]);
MATCH (h:HealthQuestion)
WITH COLLECT(DISTINCT h.id) AS uniqueValues
MATCH (h:HealthQuestion)
SET h.OHEHealthQuestion = gds.alpha.ml.oneHotEncoding(uniqueValues, [h.id]);
MATCH (h:Answer)
WITH COLLECT(DISTINCT h.answer) AS uniqueValues
MATCH (h:Answer)
SET h.OHEAnswer = gds.alpha.ml.oneHotEncoding(uniqueValues, [h.answer]);

CALL gds.graph.project(
  'fullGraph22',
  ['Person', 'HealthCondition','Symptom', 'Demographics','BodyMeasures','PhysicalActivity','DietBehaviorQuestion','HabitQuestion',
  'HealthQuestion','Answer'],
  {
    HAS_CONDITION:{
    orientation: 'UNDIRECTED'
  },
    EXPERIENCES_SYMPTOM:{
      orientation: 'UNDIRECTED'
  },
    RELATED_TO_SYMPTOM:{
      orientation: 'UNDIRECTED'
  },
  RELATED_TO:{
          orientation: 'UNDIRECTED'
      },
  HAS_DEMOGRAPHICS:{
      orientation: 'UNDIRECTED'
  },
  HAS_BODY_MEASURES:{
      orientation: 'UNDIRECTED'
  },
  HAS_PHYSICAL_ACTIVITY:{
      orientation: 'UNDIRECTED'
  },
  HAS_ANSWER:{
        orientation: 'UNDIRECTED'
    },
    ANSWERED_HE:{
          orientation: 'UNDIRECTED'
      },
      ANSWERED_D:{
            orientation: 'UNDIRECTED'
        },
        ANSWERED_HA:{
              orientation: 'UNDIRECTED'
          }
  },
  { nodeProperties: {
      OHECondition: {defaultValue: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},
      age: {defaultValue: 0},
      OHEGender : {defaultValue: [0,0]},
      OHECountry : {defaultValue: [0,0]},
      OHEdatebody : {defaultValue: [0,0]},
      OHEdatephy : {defaultValue: [0]},
      height: {defaultValue: 0.0},
      weight: {defaultValue: 0.0},
      sedentaryMinutes: {defaultValue: 0},
      moderateMinutes: {defaultValue: 0},
      vigorousMinutes: {defaultValue: 0},


      OHEDietQuestion: {defaultValue: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]},
      OHEHabitQuestion: {defaultValue: [0,0]},
      OHEHealthQuestion : {defaultValue: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]},
      OHEAnswer: {defaultValue: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]}


    }
  }
);
CALL gds.beta.pipeline.linkPrediction.create('pipe14');
CALL gds.beta.pipeline.linkPrediction.addNodeProperty(
  'pipe14',
  'gds.degree',
   {
    mutateProperty: 'degree'
  }
);
CALL gds.beta.pipeline.linkPrediction.addNodeProperty(
  'pipe14',
  'gds.betweenness',
   {
    mutateProperty: 'betweenness'
  }
);
CALL gds.beta.pipeline.linkPrediction.addNodeProperty(
  'pipe14',
  'beta.hashgnn',
   {
    mutateProperty:'embedding',
    heterogeneous: true,
    iterations: 2,
    embeddingDensity: 4,
    binarizeFeatures: {dimension: 4, threshold: 32},
    featureProperties: ['age','height', 'weight','sedentaryMinutes','moderateMinutes','vigorousMinutes','OHECondition','OHECountry','OHEGender','OHEdatephy','OHEdatebody','OHEDietQuestion','OHEHabitQuestion','OHEHealthQuestion','OHEAnswer', 'degree','betweenness'],
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
CALL gds.alpha.pipeline.linkPrediction.addRandomForest('pipe14', {numberOfDecisionTrees: {range:[10,50]}})
YIELD  parameterSpace as parameterSpaceRF;
CALL gds.alpha.pipeline.linkPrediction.addMLP('pipe14',
{hiddenLayerSizes: [4, 2], penalty: 0.5, patience: 2, classWeights: [0.55, 0.45], focusWeight: {range: [0.0, 0.1]}})
YIELD parameterSpace as parameterSpaceMLP;
CALL gds.beta.pipeline.linkPrediction.addLogisticRegression('pipe14', {maxEpochs: 500, penalty: {range: [1e-4, 1e2]}})
YIELD parameterSpace
RETURN parameterSpace.RandomForest AS randomForestSpace, parameterSpace.LogisticRegression AS logisticRegressionSpace, parameterSpace.MultilayerPerceptron AS MultilayerPerceptronSpace;
CALL gds.beta.pipeline.linkPrediction.addFeature('pipe14', 'hadamard', {
  nodeProperties: ['embedding','age','height', 'weight','sedentaryMinutes','moderateMinutes','vigorousMinutes','OHECondition','OHECountry','OHEGender','OHEdatephy','OHEdatebody','OHEDietQuestion','OHEHabitQuestion','OHEHealthQuestion','OHEAnswer', 'degree','betweenness']
});


CALL gds.beta.pipeline.linkPrediction.train('fullGraph22', {
  pipeline: 'pipe14',
  modelName: 'lp-pipeline-model-filtered4',
  metrics: ['AUCPR', 'OUT_OF_BAG_ERROR'],
  sourceNodeLabel: 'Person',
  targetRelationshipType: 'HAS_CONDITION',
  randomSeed: 12
}) YIELD modelInfo, modelSelectionStats
RETURN
  modelInfo.bestParameters AS winningModel,
  modelInfo.metrics.AUCPR.train.avg AS avgTrainScore,
  modelInfo.metrics.AUCPR.outerTrain AS outerTrainScore,
  modelInfo.metrics.AUCPR.test AS testScore,
  [cand IN modelSelectionStats.modelCandidates | cand.metrics.AUCPR.validation.avg] AS validationScores;