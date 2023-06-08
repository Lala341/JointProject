import React, { useEffect, useState } from 'react';
import { View, Text, TouchableOpacity, ScrollView, StyleSheet, Dimensions } from 'react-native';
import Constants from 'expo-constants';

const { width, height } = Dimensions.get('window');

const QuestionScreen: React.FC = () => {
  const [questions, setQuestions] = useState([]);
  const [selectedAnswers, setSelectedAnswers] = useState([]);

  useEffect(() => {
    fetchQuestions();
  }, []);

  const fetchQuestions = async () => {
    try {
      const response = await fetch('http://192.168.174.23:8090/question/health');
      const data = await response.json();
      setQuestions(data);
    } catch (error) {
      console.log('Error fetching questions:', error);
    }
  };

  const handleAnswerSelection = (answerId: string, answer: string, text: string) => {
    const updatedAnswers = selectedAnswers.filter((answer) => answer.id !== answerId);
    updatedAnswers.push({ id: answerId, answer: answer, date: new Date().toISOString(), text: text });
    setSelectedAnswers(updatedAnswers);
  };

  const handleSaveAnswers = async () => {
    try {
      const response = await fetch('http://192.168.174.23:8090/question/answers?person=' + Constants.installationId, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(selectedAnswers),
      });

      console.log(Constants.installationId)
      console.log('Request Body:', JSON.stringify(selectedAnswers));

      // Handle response as needed
    } catch (error) {
      console.log('Error saving answers:', error);
    }
  };

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>Health Survey</Text>
      {questions.map((question: any) => (
        <View key={question.healthQuestion.id} style={styles.questionContainer}>
          <Text style={styles.questionText}>{question.healthQuestion.text}</Text>
          {question.answers.map((answer: any) => (
            <TouchableOpacity
              key={answer.id}
              onPress={() => handleAnswerSelection(answer.id, answer.answer, answer.text)}
              style={[
                styles.answerButton,
                selectedAnswers.find((selected) => selected.id === answer.id)
                  ? styles.selectedAnswerButton
                  : null,
              ]}
            >
              <Text style={styles.answerButtonText}>{answer.text}</Text>
            </TouchableOpacity>
          ))}
        </View>
      ))}
      <TouchableOpacity onPress={handleSaveAnswers} style={styles.saveButton}>
        <Text style={styles.saveButtonText}>Save Answers</Text>
      </TouchableOpacity>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    padding: 16,
    minHeight: height,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 16,
  },
  questionContainer: {
    marginBottom: 16,
  },
  questionText: {
    fontSize: 16,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  answerButton: {
    backgroundColor: '#eaeaea',
    padding: 8,
    borderRadius: 8,
    marginBottom: 8,
  },
  selectedAnswerButton: {
    backgroundColor: '#3b82f6',
  },
  answerButtonText: {
    fontSize: 14,
    color: '#333',
  },
  saveButton: {
    backgroundColor: '#3b82f6',
    padding: 12,
    borderRadius: 8,
    alignItems: 'center',
    marginTop: 16,
  },
  saveButtonText: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#fff',
  },
});

export default QuestionScreen;
